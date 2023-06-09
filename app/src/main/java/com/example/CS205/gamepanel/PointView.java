package com.example.CS205.gamepanel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;

import com.example.CS205.R;

/**
 * Draws current points earned to the screen.
 * */
public class PointView {
    public int points = 0;
    private Context context;

    public PointView(Context context) {
        this.context = context;
    }


    public void draw(Canvas canvas) {
        String text = "Points: " + points;

        float x = 1500;
        float y = 200;

        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.gameOver);
        paint.setColor(color);
        float textSize = 100;
        paint.setTextSize(textSize);

        canvas.drawText(text, x, y, paint);
    }
}
