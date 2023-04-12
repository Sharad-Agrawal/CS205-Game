package com.example.CS205;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.os.Vibrator;

import com.example.CS205.gameobject.Circle;
import com.example.CS205.gameobject.Enemy;
import com.example.CS205.gameobject.Player;
import com.example.CS205.gameobject.Spell;
import com.example.CS205.gamepanel.GameOver;
import com.example.CS205.gamepanel.Joystick;
import com.example.CS205.gamepanel.Performance;
import com.example.CS205.gamepanel.graphics.Animator;
import com.example.CS205.gamepanel.graphics.SpriteSheet;
import com.example.CS205.map.Tilemap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Game manages all objects in the game and is responsible for updating all states and render all
 * objects to the screen
 */
class Game extends SurfaceView implements SurfaceHolder.Callback {

    private final Tilemap tilemap;
    private int joystickPointerId = 0;
    private final Joystick joystick;
    private final Player player;
    private GameLoop gameLoop;
    private List<Enemy> enemyList = new ArrayList<>();
    private List<Spell> spellList = new ArrayList<>();
    private int numberOfSpellsToCast = 0;
    private GameOver gameOver;
    private RestartButton restartButton;
    private Performance performance;
    private GameDisplay gameDisplay;
    private int enemyDamage = 1;

    public Game(Context context) {
        super(context);


        // Get surface holder and add callback
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        gameLoop = new GameLoop(this, surfaceHolder);

        // Initialize game panels
        performance = new Performance(context, gameLoop);
        gameOver = new GameOver(context);
        restartButton = new RestartButton(context);
        joystick = new Joystick(275, 700, 140, 80);

        // Initialize game objects
        SpriteSheet spriteSheet = new SpriteSheet(context);
        Animator animator = new Animator(spriteSheet.getPlayerSpriteArray());
        player = new Player(context, joystick, 2*500, 500, 32, animator);

        // Initialize display and center it around the player
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        gameDisplay = new GameDisplay(displayMetrics.widthPixels, displayMetrics.heightPixels, player);

        // Initialize Tilemap
        tilemap = new Tilemap(spriteSheet);

        setFocusable(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        // Handle user input touch event actions
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                if (player.getHealthPoint() == 0) {
                    float x_coord = event.getX();
                    float y_coord = event.getY();
                    if (restartButton.checkButtonArea(x_coord, y_coord)) {
                        enemyList.clear();
                        spellList.clear();
                        player.setHealthPoint(player.getMaxHealthPoints());
                    }
                } else
                    if (joystick.getIsPressed()) {
                    // Joystick was pressed before this event -> cast spell
                    numberOfSpellsToCast ++;
                } else if (joystick.isPressed((double) event.getX(), (double) event.getY())) {
                    // Joystick is pressed in this event -> setIsPressed(true) and store pointer id
                    joystickPointerId = event.getPointerId(event.getActionIndex());
                    joystick.setIsPressed(true);
                } else {
                    // Joystick was not previously, and is not pressed in this event -> cast spell
                    numberOfSpellsToCast ++;
                }
                return true;
            case MotionEvent.ACTION_MOVE:
                if (joystick.getIsPressed()) {
                    // Joystick was pressed previously and is now moved
                    joystick.setActuator((double) event.getX(), (double) event.getY());
                }
                return true;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                if (joystickPointerId == event.getPointerId(event.getActionIndex())) {
                    // joystick pointer was let go off -> setIsPressed(false) and resetActuator()
                    joystick.setIsPressed(false);
                    joystick.resetActuator();
                }
                return true;
        }

        return super.onTouchEvent(event);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.d("Game.java", "surfaceCreated()");
        if (gameLoop.getState().equals(Thread.State.TERMINATED)) {
            SurfaceHolder surfaceHolder = getHolder();
            surfaceHolder.addCallback(this);
            gameLoop = new GameLoop(this, surfaceHolder);
        }
        gameLoop.startLoop();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.d("Game.java", "surfaceChanged()");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.d("Game.java", "surfaceDestroyed()");
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        // Draw Tilemap
        tilemap.draw(canvas, gameDisplay);

        // Draw game objects
        player.draw(canvas, gameDisplay);

        for (Enemy enemy : enemyList) {
            enemy.draw(canvas, gameDisplay);
        }

        for (Spell spell : spellList) {
            spell.draw(canvas, gameDisplay);
        }

        // Draw game panels
        joystick.draw(canvas);
        performance.draw(canvas);

        // Draw Game over if the player is dead
        if (player.getHealthPoint() <= 0) {
            gameOver.draw(canvas);
            restartButton.draw(canvas);
//            gameLoop.stopLoop();
        }
    }

    public void update() {
        // Stop updating the game if the player is dead
        if (player.getHealthPoint() <= 0) {
            return;
        }

        // Update game state
        joystick.update();
        player.update();

        // Spawn enemy
        if(Enemy.readyToSpawn()) {
            boolean strongEnemy = Math.random() <= 0.2;
            enemyList.add(new Enemy(getContext(), player, strongEnemy));
        }

        // Update states of all enemies
        for (Enemy enemy : enemyList) {
            enemy.update();
        }

        // Update states of all spells
        while (numberOfSpellsToCast > 0) {
            spellList.add(new Spell(getContext(), player));
            numberOfSpellsToCast --;
        }
        for (Spell spell : spellList) {
            spell.update();
        }

        // Iterate through enemyList and Check for collision between each enemy and the player and
        // spells in spellList.
        Iterator<Enemy> iteratorEnemy = enemyList.iterator();
        while (iteratorEnemy.hasNext()) {
            Enemy enemy = iteratorEnemy.next();
            if (Circle.isColliding(enemy, player)) {
                if (enemy.getEnemyType()) {
                    player.setHealthPoint(0);
                    vibrate();
                    return;
                }
                // Remove enemy if it collides with the player
                iteratorEnemy.remove();
                if (enemyDamage != 0) {
                    player.setHealthPoint(player.getHealthPoint() - enemyDamage);
                    vibrate();
                }
                enemyDamage = Math.max(enemyDamage * 2, 1);
                continue;
            }

            Iterator<Spell> iteratorSpell = spellList.iterator();
            while (iteratorSpell.hasNext()) {
                Circle spell = iteratorSpell.next();
                // Remove enemy if it collides with a spell
                if (Circle.isColliding(spell, enemy)) {
                    if (enemy.getEnemyType()) {
                        player.setHealthPoint(player.getHealthPoint() + 5);
                        enemyDamage = 0;
                    }
                    iteratorSpell.remove();
                    iteratorEnemy.remove();
                    enemyDamage = 1;
                    break;
                }
            }
        }
        
        // Update gameDisplay so that it's center is set to the new center of the player's 
        // game coordinates
        gameDisplay.update();
    }

    public void pause() {
        gameLoop.stopLoop();
    }

    public void vibrate() {
        Context context = this.getContext();
        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(300);
    }
}
