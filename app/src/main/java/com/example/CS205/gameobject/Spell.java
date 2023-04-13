package com.example.CS205.gameobject;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v4.content.ContextCompat;

import com.example.CS205.GameDisplay;
import com.example.CS205.GameLoop;
import com.example.CS205.R;
import com.example.CS205.gamepanel.graphics.Sprite;

public class Spell extends Circle {
    public static final double SPEED_PIXELS_PER_SECOND = 800.0;
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;

    public Sprite spellSprite;

    public Spell(Context context, Player spellcaster, Sprite spellSprite) {
        super(
            context,
            ContextCompat.getColor(context, R.color.spell),
            spellcaster.getPositionX(),
            spellcaster.getPositionY(),
      25
        );
        velocityX = spellcaster.getDirectionX()*MAX_SPEED;
        velocityY = spellcaster.getDirectionY()*MAX_SPEED;
        this.spellSprite = spellSprite;
    }

    @Override
    public void update() {
        positionX = positionX + velocityX;
        positionY = positionY + velocityY;
    }

    public void draw(Canvas canvas, GameDisplay gameDisplay){
        spellSprite.draw(canvas,(int) gameDisplay.gameToDisplayCoordinatesX(getPositionX()),
                (int) gameDisplay.gameToDisplayCoordinatesY(getPositionY()));
    }
}
