package com.example.CS205.gamepanel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;

import com.example.CS205.R;


/**
 * GameOver displays the text Game Over on the screen when the player dies.
 */
public class GameOver {

    private Context context;

    public GameOver(Context context) {
        this.context = context;
    }

    public void draw(Canvas canvas) {
        String text = "Game Over";

        float x = 800;
        float y = 500;

        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.gameOver);
        paint.setColor(color);
        float textSize = 150;
        paint.setTextSize(textSize);

        canvas.drawText(text, x, y, paint);
    }
}
