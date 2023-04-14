package com.example.CS205.gameobject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.media.MediaPlayer;

import com.example.CS205.GameDisplay;
import com.example.CS205.GameLoop;
import com.example.CS205.R;

import java.util.Random;

public class Spell extends Circle {

    private static final double SPEED_PIXELS_PER_SECOND = 800.0;
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;

    private MediaPlayer soundEffect;

    private Paint outerCirclePaint;
    private Paint innerCirclePaint;
    private Paint sparklePaint;

    private Random random = new Random();

    private float gradientRadius = 0;
    private boolean increasing = true;

    public Spell(Context context, Player spellcaster) {
        super(context, Color.YELLOW, spellcaster.getPositionX(), spellcaster.getPositionY(), 25);

        // Set paint style
        paint.setStyle(Paint.Style.FILL);

        // Add a blur mask filter to enhance the glowing effect
        paint.setMaskFilter(new android.graphics.BlurMaskFilter(10, android.graphics.BlurMaskFilter.Blur.NORMAL));

        // Set velocity based on the direction the player is facing
        velocityX = spellcaster.getDirectionX() * MAX_SPEED;
        velocityY = spellcaster.getDirectionY() * MAX_SPEED;

        soundEffect = MediaPlayer.create(context, R.raw.energypulse);
        soundEffect.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                soundEffect.release();
            }
        });
        soundEffect.start();

        // Set up paint objects for outer and inner circles and sparkle effects
        outerCirclePaint = new Paint();
        outerCirclePaint.setStyle(Paint.Style.STROKE);
        outerCirclePaint.setStrokeWidth(2);
        outerCirclePaint.setColor(Color.WHITE);
        outerCirclePaint.setAlpha(200);

        innerCirclePaint = new Paint();
        innerCirclePaint.setStyle(Paint.Style.FILL);
        innerCirclePaint.setColor(Color.YELLOW);
        innerCirclePaint.setAlpha(150);

        sparklePaint = new Paint();
        sparklePaint.setStyle(Paint.Style.FILL);
        sparklePaint.setColor(Color.WHITE);
        sparklePaint.setAlpha(200);
    }

    @Override
    public void update() {
        positionX += velocityX;
        positionY += velocityY;

        // Update gradient radius for pulsating animation
        if (increasing) {
            gradientRadius += 2;
        } else {
            gradientRadius -= 2;
        }
        if (gradientRadius >= 75) {
            increasing = false;
        }
        if (gradientRadius <= 50) {
            increasing = true;
        }
    }

    @Override
    public void draw(Canvas canvas, GameDisplay gameDisplay) {
        canvas.save();

        // Add outer circle and inner circle with a gradient effect
        RadialGradient gradient = new RadialGradient(
                (float) gameDisplay.gameToDisplayCoordinatesX(positionX),
                (float) gameDisplay.gameToDisplayCoordinatesY(positionY),
                gradientRadius,
                Color.WHITE,
                Color.TRANSPARENT,
                Shader.TileMode.CLAMP
        );
        innerCirclePaint.setShader(gradient);
        canvas.drawCircle(
                (float) gameDisplay.gameToDisplayCoordinatesX(positionX),
                (float) gameDisplay.gameToDisplayCoordinatesY(positionY),
                (float) radius,
                outerCirclePaint
        );
        canvas.drawCircle(
                (float) gameDisplay.gameToDisplayCoordinatesX(positionX),
                (float) gameDisplay.gameToDisplayCoordinatesY(positionY),
                (float) (radius * 0.7),
                innerCirclePaint
        );

        // Add sparkles around the outer circle
        for (int i = 0; i < 10; i++) {
            double sparkleX = positionX + random.nextDouble() * radius * 0.8 * Math.cos(random.nextDouble() * 2 * Math.PI);
            double sparkleY = positionY + random.nextDouble() * radius * 0.8 * Math.sin(random.nextDouble() * 2 * Math.PI);
            canvas.drawCircle(
                    (float) gameDisplay.gameToDisplayCoordinatesX(sparkleX),
                    (float) gameDisplay.gameToDisplayCoordinatesY(sparkleY),
                    3,
                    sparklePaint
            );
        }

        canvas.restore();
    }
}
