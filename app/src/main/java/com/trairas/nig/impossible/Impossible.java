package com.trairas.nig.impossible;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by nig on 05/05/17.
 */

public class Impossible extends SurfaceView implements Runnable {

    boolean running = false;
    Thread renderThread = null;

    SurfaceHolder holder;
    Paint paint;

    private int playerY = 300;
    private float enemyRadius;

    public Impossible(Context context) {
        super(context);
        paint = new Paint();
        holder = getHolder();
    }

    @Override
    public void run() {

        while(running){
           print("running! ");

            //verifica se a tela ja esta pronta
            if(!holder.getSurface().isValid())
                continue;

            //bloqueia o canvas: <-> () <-> :
            Canvas canvas = holder.lockCanvas();
            canvas.drawColor(Color.BLACK);


            //desenha o player
            drawPlayer(canvas);
            drawEnemy(canvas);


            //atualiza e libera o canvas
            holder.unlockCanvasAndPost(canvas);

        }
    }


    private void drawEnemy(Canvas canvas){
        paint.setColor(Color.GRAY);
        enemyRadius++;
        canvas.drawCircle(100,100, enemyRadius, paint);
    }

    public void moveDown(int pixels){
        playerY += pixels;
    }


    private void drawPlayer(Canvas canvas){
        paint.setColor(Color.GREEN);
        canvas.drawCircle(100, playerY , 50, paint);

    }

    public void resume(){
        running = true;
        renderThread = new Thread(this);
        renderThread.start();
    }


    private void print(String m){
        Log.v(" Impossible => ", m);
    }

}
