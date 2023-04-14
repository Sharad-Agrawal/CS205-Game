package com.example.CS205.map;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.CS205.gamepanel.graphics.Sprite;
import com.example.CS205.gamepanel.graphics.SpriteSheet;

class BoulderTile extends Tile {
    private final Sprite iceSprite;
    private final Sprite boulderSprite;

    public BoulderTile(SpriteSheet spriteSheet, Rect mapLocationRect) {
        super(mapLocationRect);
        iceSprite = spriteSheet.getIceSprite();
        boulderSprite = spriteSheet.getBoulderSprite();
    }

    @Override
    public void draw(Canvas canvas) {
        iceSprite.drawIce(canvas, mapLocationRect.left, mapLocationRect.top);
        boulderSprite.drawIce(canvas, mapLocationRect.left, mapLocationRect.top);
    }
}
