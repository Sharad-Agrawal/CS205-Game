package com.example.CS205.gamepanel.graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.example.CS205.R;

public class SpriteSheet {
    private Bitmap bitmap;
    private Bitmap bitmap2;
    private Bitmap bitmap3;
    private Bitmap bitmap4;

    public SpriteSheet(Context context) {
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled = false;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.image, bitmapOptions);
        bitmap2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.char_purple_1, bitmapOptions);
        bitmap3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.normal_enemy, bitmapOptions);
        bitmap4 = BitmapFactory.decodeResource(context.getResources(), R.drawable.strong_enemy, bitmapOptions);
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

    public Bitmap getBitmap3(){
        return bitmap3;
    }

    public Bitmap getBitmap4(){
        return bitmap4;
    }

    public Sprite getHouseSprite() {
        return new Sprite(this, new Rect(464, 1, 527, 65));
    }

    public Sprite getGrassSprite() {
        return new Sprite(this, new Rect(67, 67, 131, 131));
    }

    public Sprite getFlowerSprite() {
        return new Sprite(this, new Rect(8, 539, 49, 577));
    }
    public Sprite getWallSprite() {
        return new Sprite(this, new Rect(331, 67, 395, 131));
    }

    public Sprite getSpellSprite() {
        return new Sprite(this, new Rect(476, 207, 512, 261));
    }
    public Sprite getNormalEnemySprite(){
        return new Sprite(this, new Rect(0, 0, 64, 64));
    }
    public Sprite getStrongEnemySprite(){
        return new Sprite(this, new Rect(2, 212, 40, 283));
    }


}
