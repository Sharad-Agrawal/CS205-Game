package com.example.CS205.map;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.CS205.gamepanel.graphics.Sprite;
import com.example.CS205.gamepanel.graphics.SpriteSheet;

class FlowerTile extends Tile {
    private final Sprite grassSprite;
    private final Sprite flowerSprite;

    public FlowerTile(SpriteSheet spriteSheet, Rect mapLocationRect) {
        super(mapLocationRect);
        grassSprite = spriteSheet.getGrassSprite();
        flowerSprite = spriteSheet.getFlowerSprite();
    }

    @Override
    public void draw(Canvas canvas) {
        grassSprite.draw(canvas, mapLocationRect.left, mapLocationRect.top);
        flowerSprite.draw(canvas, mapLocationRect.left, mapLocationRect.top);
    }
}
