package com.example.CS205.gamepanel.graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.example.CS205.R;

public class SpriteSheet {
    private static final int SPRITE_WIDTH_PIXELS = 64;
    private static final int SPRITE_HEIGHT_PIXELS = 64;
    private Bitmap bitmap;
    private Bitmap bitmap2;

    public SpriteSheet(Context context) {
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled = false;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.image, bitmapOptions);
        bitmap2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.char_purple_1, bitmapOptions);
    }

    public Sprite[] getPlayerSpriteArray() {
        Sprite[] spriteArray = new Sprite[3];
        spriteArray[0] = new Sprite(this, new Rect(18, 24, 40, 56)); //stationary
        spriteArray[1] = new Sprite(this, new Rect(16, 136, 41, 168)); //moving_v1
        spriteArray[2] = new Sprite(this, new Rect(74, 136, 95, 168)); //moving_v2
        return spriteArray;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public Bitmap getBitmap2(){
        return bitmap2;
    }

    public Sprite getWaterSprite() {
        return getSpriteByIndex(1, 0);
    }

    public Sprite getLavaSprite() {
        return getSpriteByIndex(1, 1);
    }

    public Sprite getGroundSprite() {
        return getSpriteByIndex(1, 2);
    }

    public Sprite getGrassSprite() {
        return new Sprite(this, new Rect(67, 67, 131, 131));
    }

    public Sprite getTreeSprite() {
        return new Sprite(this, new Rect(8, 539, 49, 577));
    }


    private Sprite getSpriteByIndex(int idxRow, int idxCol) {
        return new Sprite(this, new Rect(
                idxCol*SPRITE_WIDTH_PIXELS,
                idxRow*SPRITE_HEIGHT_PIXELS,
                (idxCol + 1)*SPRITE_WIDTH_PIXELS,
                (idxRow + 1)*SPRITE_HEIGHT_PIXELS
        ));
    }
}
