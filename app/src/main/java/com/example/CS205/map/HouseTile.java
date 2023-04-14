package com.example.CS205.map;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.CS205.gamepanel.graphics.Sprite;
import com.example.CS205.gamepanel.graphics.SpriteSheet;

class HouseTile extends Tile {
    private final Sprite grassSprite;
    private final Sprite houseSprite;

    public HouseTile(SpriteSheet spriteSheet, Rect mapLocationRect) {
        super(mapLocationRect);
        grassSprite = spriteSheet.getGrassSprite();
        houseSprite = spriteSheet.getHouseSprite();
    }

    @Override
    public void draw(Canvas canvas) {
        grassSprite.draw(canvas, mapLocationRect.left, mapLocationRect.top);
        houseSprite.draw(canvas, mapLocationRect.left, mapLocationRect.top);
    }
}
