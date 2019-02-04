package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.math.Rectangle;
import java.util.Iterator;

import java.util.Random;


public class Siga {
    private float x;
    private float y;
    private int randomNumberx;
    private int randomNumbery;
    Random rndx;
    Random rndy;
    Texture img;
    private Rectangle rectangle;
    private Sprite sigisprate;
    private int speedX;
    private int speedY;
    static int spx;
    static int spy;


    public Sprite getSigisprate() {
        return sigisprate;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Siga() {
        img = new Texture("siga2.jpg");
        speedX = 100 + spx;
        speedY = 50 + spy;
        spx+=2;                     // каждая новая сигарета быстрее
        spy+=1;

        rndx = new Random();
        rndy = new Random();

        rectangle = new Rectangle(x, y, img.getWidth(), img.getHeight());
        create();

    }


    public void create() {
        int[] arrayx = {-50, 850};
        int[] arrayy = {100,300};

        randomNumberx = arrayx[rndx.nextInt(2)];
        randomNumbery = arrayy[rndy.nextInt(2)];
        x = randomNumberx;
        y = randomNumbery;
        sigisprate = new Sprite(img);

    }

    public void update() {
            if (randomNumberx== -50 && randomNumbery == 100) {         // это с лева на право внизу
                if (x <= 300) {
                    x += speedX * Gdx.graphics.getDeltaTime();
                    y += speedY * Gdx.graphics.getDeltaTime();
                    float x2;
                    synchronized (this) {
                        x2 = x;
                    }
                    if (x2 >= 150)
                        y -= speedY * 2 * Gdx.graphics.getDeltaTime();
                } else y -= speedY * 2 * Gdx.graphics.getDeltaTime();
                sigisprate.rotate((float) -10.0);
            }

            if (randomNumberx==850 && randomNumbery == 100) {             // это с право на лево внизу
                if (x >= 500) {
                    x -= speedX * Gdx.graphics.getDeltaTime();
                    y += speedY * Gdx.graphics.getDeltaTime();
                    float x3;
                    synchronized (this) {
                        x3 = x;
                    }
                    if (x3 <= 650)
                        y -= speedY * 2 * Gdx.graphics.getDeltaTime();
                } else y -= speedY * 2 * Gdx.graphics.getDeltaTime();
                sigisprate.rotate((float) 10.0);
            }

        if (randomNumberx== -50 && randomNumbery == 300) {                      // это с лева на право вверху
            if (x <= 200) {
                x += speedX * Gdx.graphics.getDeltaTime();
                y += speedY * Gdx.graphics.getDeltaTime();
                float x2;
                synchronized (this) {
                    x2 = x;
                }
                if (x2 >= 150)
                    y -= speedY * 2 * Gdx.graphics.getDeltaTime();
            } else y -= speedY * 2 * Gdx.graphics.getDeltaTime();
            sigisprate.rotate((float) 10.0);
        }

        if (randomNumberx==850 && randomNumbery == 300) {            // это с право на лево вверху
            if (x >= 550) {
                x -= speedX * Gdx.graphics.getDeltaTime();
                y += speedY * Gdx.graphics.getDeltaTime();
                float x3;
                synchronized (this) {
                    x3 = x;
                }
                if (x3 <= 650)
                    y -= speedY * 2 * Gdx.graphics.getDeltaTime();
            } else y -= speedY * 2 * Gdx.graphics.getDeltaTime();
            sigisprate.rotate((float) -10.0);
        }
        rectangle.setPosition(x,y);
    }
    public boolean collides(Rectangle player){
        return player.overlaps(rectangle);
    }
    public void dispose() {
        img.dispose();

    }

        }


