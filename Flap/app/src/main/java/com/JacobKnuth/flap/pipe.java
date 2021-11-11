package com.JacobKnuth.flap;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class pipe {
    int x;
    int y;
    int w;
    int h;
    Rect rectangel;
    Boolean passed = false;
    public pipe(int x, int y, int w, int h){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        rectangel = new Rect(x, y, w, h);





    }
    public void draw(Canvas c, gamePlay context){

        Paint p = new Paint();
        p.setColor(Integer.parseInt(context.contex.boneColor));
        c.drawRect(rectangel, p );
    }
    public void move(int a){
        this.x = this.x - a;
        this.w = this.w - a;
        this.rectangel = new Rect(this.x, this.y, this.w, this.h);
    }

}
