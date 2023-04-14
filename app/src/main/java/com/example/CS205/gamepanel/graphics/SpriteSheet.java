package com.example.CS205.gamepanel.graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.example.CS205.R;

public class SpriteSheet {
    private Bitmap wallBitmap;
    private Bitmap characterBitmap;
    private Bitmap normalEnemyBitmap;
    private Bitmap strongEnemyBitmap;
    private Bitmap iceBitmap;

    public SpriteSheet(Context context) {
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled = false;
        wallBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.wall, bitmapOptions);
        characterBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.character, bitmapOptions);
        normalEnemyBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.normal_enemy, bitmapOptions);
        strongEnemyBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.strong_enemy, bitmapOptions);
        iceBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ice, bitmapOptions);
    }

    public Sprite[] getPlayerSpriteArray() {
        Sprite[] spriteArray = new Sprite[3];
        spriteArray[0] = new Sprite(this, new Rect(18, 24, 40, 56)); //stationary
        spriteArray[1] = new Sprite(this, new Rect(16, 136, 41, 168)); //moving_v1
        spriteArray[2] = new Sprite(this, new Rect(74, 136, 95, 168)); //moving_v2
        return spriteArray;
    }

    public Bitmap getWallBitmap() {
        return wallBitmap;
    }
    public Bitmap getCharacterBitmap(){
        return characterBitmap;
    }
    public Bitmap getNormalEnemyBitmap(){
        return normalEnemyBitmap;
    }
    public Bitmap getStrongEnemyBitmap(){
        return strongEnemyBitmap;
    }
    public Bitmap getIceBitmap(){
        return iceBitmap;
    }


    public Sprite getShardSprite() {
        return new Sprite(this, new Rect(0, 0, 16, 16));
    }
    public Sprite getIceSprite() {
        return new Sprite(this, new Rect(0, 64, 16, 80));
    }
    public Sprite getGroundSprite() {
        return new Sprite(this, new Rect(0, 64, 64, 128));
    }
    public Sprite getBoulderSprite() {
        return new Sprite(this, new Rect(304, 272, 320, 288));
    }
    public Sprite getWallSprite() {
        return new Sprite(this, new Rect(463, 133, 527, 197));
    }
    public Sprite getNormalEnemySprite(){
        return new Sprite(this, new Rect(0, 0, 64, 64));
    }
    public Sprite getStrongEnemySprite(){
        return new Sprite(this, new Rect(2, 212, 40, 283));
    }


}
