package com.JacobKnuth.flap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


public class player {
    Bitmap sprite;
    int x;
    int y;
    boolean fall = true;
    long t;
    int passed;
    boolean alive = true;

    public player(gamePlay current){
        sprite = BitmapFactory.decodeResource(current.getResources(),R.drawable.dog3 );
        sprite = Bitmap.createScaledBitmap(sprite, (int)(sprite.getWidth()*.25), (int)(sprite.getHeight()*.25), true);

        x=0;
        y=0;
        t = System.currentTimeMillis();
        x = current.canvas.getWidth() / 4;
        y = current.canvas.getHeight() / 2;




        this.passed = 0;

    }
}
