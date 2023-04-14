package com.example.CS205.map;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.CS205.gamepanel.graphics.Sprite;
import com.example.CS205.gamepanel.graphics.SpriteSheet;

class IceTile extends Tile {
    private final Sprite sprite;

    public IceTile(SpriteSheet spriteSheet, Rect mapLocationRect) {
        super(mapLocationRect);
        sprite = spriteSheet.getIceSprite();
    }

    @Override
    public void draw(Canvas canvas) {
        sprite.drawIce(canvas, mapLocationRect.left, mapLocationRect.top);
    }
}
