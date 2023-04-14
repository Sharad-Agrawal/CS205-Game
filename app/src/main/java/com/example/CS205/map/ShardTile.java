package com.example.CS205.map;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.CS205.gamepanel.graphics.Sprite;
import com.example.CS205.gamepanel.graphics.SpriteSheet;

class ShardTile extends Tile {
    private final Sprite iceSprite;
    private final Sprite shardSprite;

    public ShardTile(SpriteSheet spriteSheet, Rect mapLocationRect) {
        super(mapLocationRect);
        iceSprite = spriteSheet.getIceSprite();
        shardSprite = spriteSheet.getShardSprite();
    }

    @Override
    public void draw(Canvas canvas) {
        iceSprite.drawIce(canvas, mapLocationRect.left, mapLocationRect.top);
        shardSprite.drawIce(canvas, mapLocationRect.left, mapLocationRect.top);
    }
}
