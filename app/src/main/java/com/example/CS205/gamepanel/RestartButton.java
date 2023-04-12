package com.example.CS205.gamepanel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;

import com.example.CS205.GameDisplay;
import com.example.CS205.R;

public class RestartButton {
    private int width, height;
    private float x, y;
    private Context context;
    private Paint buttonPaint, textPaint;


    public RestartButton(Context context) {
        this.context = context;
        this.width = 1000;
        this.height = 200;

        this.buttonPaint = new Paint();
        this.textPaint = new Paint();
        int buttonColor = ContextCompat.getColor(context, R.color.white);
        int textColour = ContextCompat.getColor(context, R.color.enemy2);
        buttonPaint.setColor(buttonColor);
        textPaint.setColor(textColour);
        textPaint.setTextSize(100);


        x = context.getResources().getDisplayMetrics().widthPixels / 2 - width / 2;
        y = context.getResources().getDisplayMetrics().heightPixels / 2 - height / 2 + 150;
    }

    public void draw(Canvas canvas) {
        float borderLeft = x;
        float borderRight = borderLeft + width;
        float borderTop = y;
        float borderBottom = borderTop + height;
        canvas.drawRoundRect(borderLeft, borderTop, borderRight, borderBottom, 20, 20, buttonPaint);
        canvas.drawText("Restart?", borderLeft + (width / 2 - 180), borderBottom - (height / 2 - 30), textPaint);
//        canvas.drawRect(borderLeft, borderTop, borderRight, borderBottom, buttonPaint);
    }

    public boolean checkButtonArea(double x, double y) {
        boolean xIsIn = x >= this.x && x <= (this.x + width);
        boolean yIsIn = y >= this.y && y <= (this.y + height);
        return  xIsIn && yIsIn;
    }
}
