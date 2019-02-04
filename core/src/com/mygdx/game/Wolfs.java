package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

 public class Wolfs {
    Texture wolfL;
    Texture wolfR;
    Texture wolfT;
    Rectangle wolfLeft;
    Rectangle wolfRight;
    Rectangle wolfGet;
    private float x;
    private float y;

    public Rectangle getWolfReg() {
        return wolfGet;
    }       // квадрат для обработки столкновения

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Texture getWolf() {
        return wolfT;
    }

    public Wolfs (){
        wolfL = new Texture("leftwolf.jpg");
        wolfR = new Texture("rightwolf - 2.jpg");
        wolfGet = new Rectangle();

    }
    public void wolfRectangle( float tochX, float tochY){
        float tx = tochX;
        float ty = tochY;
        if (tx < 400) {
            wolfLeft = new Rectangle(800 / 2 - wolfL.getWidth() /2 - 50 , 0, wolfL.getWidth(), wolfL.getHeight());
            if (ty < 200 )
                wolfLeft .setPosition(800 / 2 - wolfL.getWidth() /2 -100 , 230);
            x = wolfLeft.x;
            y = wolfLeft.y;
            wolfT = wolfL;
            wolfGet=wolfLeft;
        }
        else {
            wolfRight = new Rectangle(800 / 2 - wolfR.getWidth() /2 + 50, 0, wolfL.getWidth(), wolfL.getHeight());
            if (ty < 200 )
                wolfRight.setPosition(800 / 2 - wolfR.getWidth() /2 +100, 230);
            x = wolfRight.x;
            y = wolfRight.y;
            wolfT = wolfR;
            wolfGet=wolfRight;
        }
    }
    public void dispose() {
        wolfL.dispose();
        wolfR.dispose();
        wolfT.dispose();
    }
}
