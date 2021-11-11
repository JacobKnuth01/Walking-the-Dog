package com.JacobKnuth.flap;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;
import java.lang.Math;

public class pipeGenerator {
    ArrayList<pipe> pipes;
    long t;
    long add;
    int playerHight;
    player Player;
    gamePlay contex;
    float move = .5f;
    float gen = 2500;
    long lifeCycle;
    boolean pause = false;
    int df = 0;

    long Pausetimer;

    public pipeGenerator(gamePlay contex){
        pipes = new ArrayList<pipe>();
        t = System.currentTimeMillis();
        add = t;
        //pipes.add(new pipe(500, 500, 1000, 1000));
        this.playerHight = contex.dog.sprite.getHeight();
        this.Player = contex.dog;
        this.contex= contex;
        this.lifeCycle = System.currentTimeMillis();
        this.Pausetimer = System.currentTimeMillis();
    }
    public void drawAll(Canvas c, gamePlay con){
        for (pipe p:pipes){
            p.draw(c, con);
        }
    }
    public int genRand(int min, int max){
        double r = Math.random();
        int range = max - min + 1;
        return (int)(r*range)+min;



    }
    public void update(Canvas c, gamePlay con){
        drawAll(c, con);


            this.df = this.df + 1;
            System.out.println(this.df);
            t = System.currentTimeMillis();
            for(pipe p:pipes){
                p.move((int)(this.move*con.DeltaTime));
                if (!p.passed){
                    if (p.rectangel.right < Player.x ){
                        Player.passed = Player.passed + 1;
                        p.passed = true;
                        this.contex.contex.pass.start();

                    }
                }
                //p.draw(c);


            }


        if (System.currentTimeMillis() - add >this.gen && !pause){

            add = System.currentTimeMillis();
            int hight = genRand(c.getHeight()/2, c.getHeight());
            //System.out.println(hight);

            pipes.add(new pipe(c.getWidth(), hight, c.getWidth() + 100, c.getHeight()+100));
            int bott = genRand(0, hight-(playerHight+((int)(playerHight)/2)));
            pipe botPiep = new pipe(c.getWidth(), 0, c.getWidth() + 100, bott);
            botPiep.passed = true;
            pipes.add(botPiep);

        }
        if (System.currentTimeMillis() - lifeCycle > 13000){
            this.lifeCycle = System.currentTimeMillis();
            this.gen= this.gen*.75f;
            this.move= this.move*1.5f;
            System.out.println(gen);
            System.out.println(move);
            this.Pausetimer = System.currentTimeMillis();
            this.pause = true;
        }

        if (System.currentTimeMillis() - this.Pausetimer > 3000 && this.pause){
            this.pause = false;
        }

        if (this.pause){
            Paint text = new Paint();
            text.setColor(Color.BLUE);
            text.setTextSize(100);
            c.drawText("FASTER!!!!!!!!", c.getWidth()/3, c.getHeight()/8,text);

        }
    }

}
