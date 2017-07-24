package com.trairas.nig.impossible;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class main extends Activity implements View.OnTouchListener {

    Impossible view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);

        //Lógica do jogo
        view = new Impossible(this);

        //habilita o touch
        view.setOnTouchListener(this);

        //configura view
        setContentView(view);

    }


    @Override
    protected void onResume(){
        super.onResume();
        view.resume();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        if(event.getX() < 100 && event.getY() > 290 && event.getY() < 310) {
            view.init();
        }
        // Exit
        if(event.getX() < 100 && event.getY() > 490 && event.getY() < 510) {
            System.exit(0);
        }

        // Incrementa em 10 pixels a posição
        view.moveDown(10);
        // vertical do player e o placar
        view.addScore(100);

        return true;
    }
}
