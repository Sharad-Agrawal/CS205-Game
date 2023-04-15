package com.example.CS205.gamepanel.graphics;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.support.v4.content.ContextCompat;
import android.util.Log;


import java.util.*;

import com.example.CS205.R;
import com.example.CS205.gameobject.Spell;



public class SpellPool {

    private Paint outerCirclePaint;
    private Paint innerCirclePaint;
    private Paint sparklePaint;

    private Random random = new Random();

    private volatile ArrayDeque<Spell> buffer;
    private final int capacity;

    public int qtyOfspells = 0;

    private Context context;

    public SpellPool(Context context, int capacity) {
        this.context = context;
        this.capacity = capacity;
        buffer = new ArrayDeque<>();

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

    // Blocking the queue if capacity is full
    public synchronized void enqueue(Spell spell) {
        while (buffer.size() == capacity) {
            try {
                this.wait();
            } catch (InterruptedException e) {
            }
        }
        buffer.addLast(spell);
        this.notifyAll();
    }

    // Notifying that the queue has available space after dequeuing
    public synchronized Spell dequeue() {
        while (buffer.isEmpty()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
            }
        }
        Spell spell = buffer.removeFirst();
        this.notifyAll();
        return spell;
    }

    public boolean isFull() {
        if (buffer.size() == capacity) {
            return true;
        }
        return false;
    }

    public boolean isEmpty() {
        return buffer.isEmpty();
    }

    public int size() {
        return buffer.size();
    }

    public int getCapacity() {
        return capacity;
    }


    public void draw(Canvas canvas) {

        qtyOfspells = size();

        // Draw circle to canvas
        canvas.drawCircle(
                (float) 1550,
                (float) 260,
                (float) 25,
                outerCirclePaint
        );
        canvas.drawCircle(
                (float) 1550,
                (float) 260,
                (float) (25 * 0.7),
                innerCirclePaint
        );
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(60);
        canvas.drawText("x" + qtyOfspells, 1600, 275, paint);

    }



}
