package com.example.CS205.map;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.CS205.gamepanel.graphics.SpriteSheet;

abstract class Tile {

    protected final Rect mapLocationRect;

    public Tile(Rect mapLocationRect) {
        this.mapLocationRect = mapLocationRect;
    }

    public enum TileType {
        WALL_TILE,
        SHARD_TILE,
        ICE_TILE,
        BOULDER_TILE
    }

    public static Tile getTile(int idxTileType, SpriteSheet spriteSheet, Rect mapLocationRect) {

        switch(TileType.values()[idxTileType]) {
            case WALL_TILE:
                return new WallTile(spriteSheet, mapLocationRect);
            case SHARD_TILE:
                return new ShardTile(spriteSheet, mapLocationRect);
            case ICE_TILE:
                return new IceTile(spriteSheet, mapLocationRect);
            case BOULDER_TILE:
                return new BoulderTile(spriteSheet, mapLocationRect);
            default:
                return null;
        }

    }

    public abstract void draw(Canvas canvas);
}
