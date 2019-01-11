package com.stratisgames.www.a2048;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.BoringLayout;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private int[][] Board = new int[4][4];
    private ConstraintLayout page = findViewById(R.id.page);
    private static final int DISTANCE_BETWEEN = 100;
    private ImageView toMerge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        page.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                float startX = 0;
                float startY = 0;
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        startX = motionEvent.getX();
                        startY = motionEvent.getY();
                        break;

                    case MotionEvent.ACTION_UP:
                        float diffX = motionEvent.getX() - startX;
                        float diffY = motionEvent.getY() - startY;
                        if(diffX > 0 && Math.abs(diffX) > Math.abs(diffY)){
                            shiftLeft();
                            Toast.makeText(getApplicationContext(), "Left", Toast.LENGTH_SHORT).show();
                        }
                        else if(diffX < 0 && Math.abs(diffX) > Math.abs(diffY)){
                            shiftRight();
                            Toast.makeText(getApplicationContext(), "right", Toast.LENGTH_SHORT).show();
                        }
                        else if(diffY > 0 && Math.abs(diffY) > Math.abs(diffX)){
                            shiftUp();
                            Toast.makeText(getApplicationContext(), "up", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            shiftDown();
                            Toast.makeText(getApplicationContext(), "down", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
                return true;
            }
        });
        newPos();
    }

    public void newPos(){
        int posX = (int)(Math.random()*4);
        int posY = (int)(Math.random()*4);
        while(Board[posX][posY] != 0){
            posX = (int)(Math.random()*4);
            posY = (int)(Math.random()*4);
        }
        Board[posX][posY] = 2;
    }

    public void shiftLeft(){

    }

    public void shiftRight(){

    }

    public void shiftUp(){

    }

    public void shiftDown(){
        ArrayList<ObjectAnimator> animations = new ArrayList<>();
        for(int i = Board.length - 1; i > 0; i--){
            for(int j = 0; j < Board.length; j++){
                if(Board[j][i] == Board[j-1][i] && Board[j][i] != 0){
                    ObjectAnimator animator = ObjectAnimator.ofFloat(toMerge, "x", DISTANCE_BETWEEN);
                    animations.add(animator);
                    Board[j][i] *= 2;
                    Board[j-1][i] = 0;
                }
            }
        }
        AnimatorSet set = new AnimatorSet();
        set.playTogether(animations.get(0), animations.get(1));
        set.start();
    }

}
