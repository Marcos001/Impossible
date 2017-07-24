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

    private int enemyX, enemyY, enemyRadius = 50;
    private int playerX = 300, playerY = 300, playerRadius = 50;
    double distance;
    boolean gameover;

    private int score;

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
            //desenha o inimigo :(
            drawEnemy(canvas);

            //detecta a colisao
            checkCollision(canvas);

            if (gameover){
                print("Game Over");
                stopGame(canvas);
                print("END stopGame");
                break;
            }

            //atualiza o placar
            drawScore(canvas);

            drawButton(canvas);

            //atualiza e libera o canvas
            holder.unlockCanvasAndPost(canvas);

        }
    }


    private void drawEnemy(Canvas canvas){
        paint.setColor(Color.GRAY);
        enemyRadius++;
        canvas.drawCircle(enemyX, enemyY, enemyRadius, paint);
    }

    public void moveDown(int pixels){
        playerY += pixels;
    }


    private void drawPlayer(Canvas canvas){
        paint.setColor(Color.GREEN);
        canvas.drawCircle(playerX, playerY , 50, paint);

    }

    private void checkCollision(Canvas canvas){

        //calcula a hipotenusa
        distance = Math.pow(playerY - enemyY, 2)+ Math.pow(playerX - enemyX, 2);
        distance = Math.sqrt(distance);

        //verifica a distancia entre os raios
        if(distance < playerRadius + enemyRadius){
            gameover = true;
        }
    }

    private void stopGame(Canvas canvas){
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        paint.setTextSize(50);
        canvas.drawText(String.valueOf("GAME OVER!"), 100, 300, paint);
    }

    public void addScore(int points){
        score += points;
    }

    private void drawScore(Canvas canvas){
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        paint.setTextSize(50);
        canvas.drawText(String.valueOf(score), 50, 200, paint);
    }

    private void drawButton(Canvas canvas){
        // Restart
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        paint.setTextSize(30);
        canvas.drawText("Restart", 50, 300, paint);
        // Exit
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        paint.setTextSize(30);
        canvas.drawText("Exit", 50, 500, paint);
    }

    /**------------------------------------------------------------*/
    public void resume(){
        running = true;
        renderThread = new Thread(this);
        renderThread.start();
    }


    private void print(String m){
        Log.v(" Impossible => ", m);
    }

}
