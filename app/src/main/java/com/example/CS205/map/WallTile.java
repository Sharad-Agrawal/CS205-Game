package com.example.CS205.map;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.CS205.gamepanel.graphics.Sprite;
import com.example.CS205.gamepanel.graphics.SpriteSheet;

class WallTile extends Tile {
    private final Sprite iceSprite;
    private final Sprite wallSprite;

    public WallTile(SpriteSheet spriteSheet, Rect mapLocationRect) {
        super(mapLocationRect);
        iceSprite = spriteSheet.getIceSprite();
        wallSprite = spriteSheet.getWallSprite();
    }

    @Override
    public void draw(Canvas canvas) {
        iceSprite.drawIce(canvas, mapLocationRect.left, mapLocationRect.top);
        wallSprite.drawWall(canvas, mapLocationRect.left, mapLocationRect.top);
    }
}
