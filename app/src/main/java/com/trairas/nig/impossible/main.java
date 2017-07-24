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

        view.moveDown(10);
        view.addScore(100);

        return true;
    }
}
