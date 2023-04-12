package com.example.CS205.gameobject;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v4.content.ContextCompat;

import com.example.CS205.GameDisplay;
import com.example.CS205.GameLoop;
import com.example.CS205.gamepanel.HealthBar;
import com.example.CS205.gamepanel.Joystick;
import com.example.CS205.R;
import com.example.CS205.Utils;
import com.example.CS205.gamepanel.graphics.Animator;

/**
 * Player is the main character of the game, which the user can control with a touch joystick.
 * The player class is an extension of a Circle, which is an extension of a GameObject
 */
public class Player extends Circle {
    public static final double SPEED_PIXELS_PER_SECOND = 400.0;
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;
    public static final int MAX_HEALTH_POINTS = 20;
    private Joystick joystick;
    private HealthBar healthBar;
    private int healthPoints = MAX_HEALTH_POINTS;
    private Animator animator;
    private PlayerState playerState;

    public Player(Context context, Joystick joystick, double positionX, double positionY, double radius, Animator animator) {
        super(context, ContextCompat.getColor(context, R.color.player), positionX, positionY, radius);
        this.joystick = joystick;
        this.healthBar = new HealthBar(context, this);
        this.animator = animator;
        this.playerState = new PlayerState(this);
    }

    public void update() {

        // Update velocity based on actuator of joystick
        velocityX = joystick.getActuatorX()*MAX_SPEED;
        velocityY = joystick.getActuatorY()*MAX_SPEED;

        // Update position
        positionX += velocityX;
        positionY += velocityY;

        // Update direction
        if (velocityX != 0 || velocityY != 0) {
            // Normalize velocity to get direction (unit vector of velocity)
            double distance = Utils.getDistanceBetweenPoints(0, 0, velocityX, velocityY);
            directionX = velocityX/distance;
            directionY = velocityY/distance;
        }

        playerState.update();
    }

    public void draw(Canvas canvas, GameDisplay gameDisplay) {
        animator.draw(canvas, gameDisplay, this);

        healthBar.draw(canvas, gameDisplay);
    }

    public int getHealthPoint() {
        return healthPoints;
    }

    public void setHealthPoint(int healthPoints) {
        // Only allow positive values
        if (healthPoints > MAX_HEALTH_POINTS)
            this.healthPoints = MAX_HEALTH_POINTS;
        else if (healthPoints >= 0)
            this.healthPoints = healthPoints;
        else {
            this.healthPoints = 0;
        }
    }

    public PlayerState getPlayerState() {
        return playerState;
    }

    public int getMaxHealthPoints() {
        return MAX_HEALTH_POINTS;
    }
}
