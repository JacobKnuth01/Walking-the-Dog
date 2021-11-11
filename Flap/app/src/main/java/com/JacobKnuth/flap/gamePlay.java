package com.JacobKnuth.flap;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class gamePlay extends SurfaceView implements Runnable, View.OnTouchListener {
    boolean isPlay = false;
    Thread t;
    SurfaceHolder holder;
    Canvas canvas;
    player dog;

    int h;
    int w;

    pipeGenerator gen;
    long DeltaTime;

    MainActivity contex;
    public gamePlay(MainActivity context) {
        super(context.getBaseContext());

        this.setOnTouchListener(this);
        holder = getHolder();

        this.contex = context;



        //start();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (dog.fall && dog.alive) {

            dog.fall = false;
            dog.t = System.currentTimeMillis();
            contex.jump.start();
        }
        return false;
    }

    @Override
    public void run() {

        while (this.isPlay){
            long mesure = System.currentTimeMillis();


            if (holder.getSurface().isValid()){
                this.canvas = getHolder().lockCanvas();
                if (dog==null){
                    System.out.println("Run");
                    this.dog = new player(this);
                    this.gen = new pipeGenerator(this);

                }


                canvas.drawColor(Integer.parseInt(contex.backround));


                gen.update(canvas, this);
                w = canvas.getWidth();
                h = canvas.getHeight();

                // MOVMENT---------------------------------------
                if (dog.fall){
                    if (dog.y < h) {

                        dog.y = dog.y + (int)(.7*DeltaTime);
                    }

                }
                if (!dog.fall){

                    if (dog.y > 0) {
                        dog.y = dog.y - (int)(2*DeltaTime);
                    }

                    if (System.currentTimeMillis() - dog.t > 80 ){
                        dog.fall = true;
                    }
                }
                //END MOVMENT_--------------------------------------------------------
                //Rect r = new Rect(dog.x, dog.y, dog.x + dog.sprite.getWidth(), dog.y + dog.sprite.getHeight());
                //canvas.drawRect(r, new Paint());
                Paint aplpha = new Paint();
                if (gen.pause) {
                    aplpha.setAlpha(150);
                }
                canvas.drawBitmap(dog.sprite, dog.x, dog.y, aplpha);
                boolean h = isCollison();
                if (h && !gen.pause && dog.alive){
                    dog.alive = false;
                    contex.die.start();
                    this.isPlay = false;
                    //Paint p = new Paint();
                    //p.setTextSize(200);
                    //p.setColor(Color.RED);
                    //canvas.drawText("FAIL", canvas.getWidth(), canvas.getHeight(), p);
                    holder.unlockCanvasAndPost(canvas);

                    contex.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            contex.d = String.valueOf(dog.passed);
                            contex.back();

                        }
                    });
                        break;



                }

                Paint text = new Paint();
                text.setColor(Color.BLUE);
                text.setTextSize(100);
                canvas.drawText(String.valueOf(dog.passed), canvas.getWidth()/2, canvas.getHeight()/16,text);

                holder.unlockCanvasAndPost(canvas);
                DeltaTime = System.currentTimeMillis() - mesure;




            }

        }
        System.out.println("Thread has stopped");

    }
    public void start(){
        this.isPlay = true;
        t = new Thread(this);
        t.start();


    }

    public void end()  {
        this.isPlay = false;
        //contex.setContentView(contex.binder.getRoot());
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("done with that");
    }
    public boolean isCollison(){

        for (pipe p: gen.pipes){

            Rect r = new Rect(dog.x, dog.y, dog.x + dog.sprite.getWidth(), dog.y + dog.sprite.getHeight());



            if (!((r.right < p.rectangel.left || r.left > p.rectangel.right) || (p.rectangel.bottom< r.top || r.bottom < p.rectangel.top))){
                return  true;
            }




        }
        return false;

    }
}
