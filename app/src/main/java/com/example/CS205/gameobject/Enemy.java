package com.example.CS205.gameobject;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v4.content.ContextCompat;

import com.example.CS205.GameDisplay;
import com.example.CS205.GameLoop;
import com.example.CS205.R;
import com.example.CS205.gamepanel.graphics.SpriteSheet;

/**
 * Enemy is a character which always moves in the direction of the player.
 * The Enemy class is an extension of a Circle, which is an extension of a GameObject
 */
public class Enemy extends Circle {

    private static final double SPEED_PIXELS_PER_SECOND = Player.SPEED_PIXELS_PER_SECOND*0.6;
    private double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;
    private static final double SPAWNS_PER_MINUTE = 60;
    private static final double SPAWNS_PER_SECOND = SPAWNS_PER_MINUTE/60.0;
    private static final double UPDATES_PER_SPAWN = GameLoop.MAX_UPS/SPAWNS_PER_SECOND;
    private static double updatesUntilNextSpawn = UPDATES_PER_SPAWN;
    private Player player;
    private boolean enemyType;
    private SpriteSheet spriteSheet;

    public Enemy(Context context, Player player, double positionX, double positionY, double radius) {
        super(context, ContextCompat.getColor(context, R.color.enemy), positionX, positionY, radius);
        if (enemyType) super.setColour(R.color.enemy2);
        this.player = player;
    }

    /**
     * Enemy is an overload constructor used for spawning enemies in random locations
     * @param context
     * @param player
     */
    public Enemy(Context context, Player player, boolean enemyType, SpriteSheet spriteSheet) {
        super(
            context,
            ContextCompat.getColor(context, R.color.enemy),
   Math.random()*1000,
   Math.random()*1000,
     30
        );
        this.player = player;
        this.enemyType = enemyType;
        this.spriteSheet = spriteSheet;
        if (enemyType) {
            super.setColour(R.color.enemy2);
            updateSpeed();
        }
    }

    /**
     * readyToSpawn checks if a new enemy should spawn, according to the decided number of spawns
     * per minute (see SPAWNS_PER_MINUTE at top)
     * @return
     */
    public static boolean readyToSpawn() {
        if (updatesUntilNextSpawn <= 0) {
            updatesUntilNextSpawn += UPDATES_PER_SPAWN;
            return true;
        } else {
            updatesUntilNextSpawn --;
            return false;
        }
    }

    public void update() {
        // =========================================================================================
        //   Update velocity of the enemy so that the velocity is in the direction of the player
        // =========================================================================================
        // Calculate vector from enemy to player (in x and y)
        double distanceToPlayerX = player.getPositionX() - positionX;
        double distanceToPlayerY = player.getPositionY() - positionY;

        // Calculate (absolute) distance between enemy (this) and player
        double distanceToPlayer = GameObject.getDistanceBetweenObjects(this, player);

        // Calculate direction from enemy to player
        double directionX = distanceToPlayerX/distanceToPlayer;
        double directionY = distanceToPlayerY/distanceToPlayer;

        // Set velocity in the direction to the player
        if(distanceToPlayer > 0) { // Avoid division by zero
            velocityX = directionX*MAX_SPEED;
            velocityY = directionY*MAX_SPEED;
        } else {
            velocityX = 0;
            velocityY = 0;
        }

        // =========================================================================================
        //   Update position of the enemy
        // =========================================================================================
        positionX += velocityX;
        positionY += velocityY;
    }

    public boolean getEnemyType(){
        return enemyType;
    }

    public void updateSpeed() {
        MAX_SPEED = (Player.SPEED_PIXELS_PER_SECOND * 0.9) / GameLoop.MAX_UPS;
    }

    public void draw(Canvas canvas, GameDisplay gameDisplay){
        if(enemyType){
            spriteSheet.getStrongEnemySprite().draw4(canvas,(int) gameDisplay.gameToDisplayCoordinatesX(getPositionX()),
                    (int) gameDisplay.gameToDisplayCoordinatesY(getPositionY()));
        } else{
            spriteSheet.getNormalEnemySprite().draw3(canvas,(int) gameDisplay.gameToDisplayCoordinatesX(getPositionX()),
                    (int) gameDisplay.gameToDisplayCoordinatesY(getPositionY()));
        }
    }
}

