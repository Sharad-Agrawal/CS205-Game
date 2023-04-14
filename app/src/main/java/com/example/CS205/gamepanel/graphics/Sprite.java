package com.example.CS205.gamepanel.graphics;

import android.graphics.Canvas;
import android.graphics.Rect;

public class Sprite {

    private final SpriteSheet spriteSheet;
    private final Rect rect;

    public Sprite(SpriteSheet spriteSheet, Rect rect) {
        this.spriteSheet = spriteSheet;
        this.rect = rect;
    }

    public void drawWall(Canvas canvas, int x, int y) {
        canvas.drawBitmap(
            spriteSheet.getWallBitmap(),
                rect,
                new Rect(x, y, x+getWidth(), y+getHeight()),
                null
        );
    }

    public void drawCharacter(Canvas canvas, int x, int y) {
        canvas.drawBitmap(
                spriteSheet.getCharacterBitmap(),
                rect,
                new Rect(x, y, x+(getWidth()*2), y+(getHeight()*2)),
                null
        );
    }

    public void drawNormalEnemy(Canvas canvas, int x, int y) {
        canvas.drawBitmap(
                spriteSheet.getNormalEnemyBitmap(),
                rect,
                new Rect(x, y, x+(getWidth()), y+(getHeight())),
                null
        );
    }

    public void drawStrongEnemy(Canvas canvas, int x, int y) {
        canvas.drawBitmap(
                spriteSheet.getStrongEnemyBitmap(),
                rect,
                new Rect(x, y, x+(getWidth()), y+(getHeight())),
                null
        );
    }

    public void drawIce(Canvas canvas, int x, int y) {
        canvas.drawBitmap(
                spriteSheet.getIceBitmap(),
                rect,
                new Rect(x, y, x+(getWidth()*4), y+(getHeight()*4)),
                null
        );
    }

    public int getWidth() {
        return rect.width();
    }

    public int getHeight() {
        return rect.height();
    }


}
