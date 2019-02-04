package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.ArrayList;
import java.util.Iterator;

public class GameScreen implements Screen{
    final Wolf3 game;
    OrthographicCamera camera;
    private Long lastDropTime;
    private float dropTime;
    static int speedDropTime;
    static int countSigi;
    private float tochX;
    private float tochY;
    private int gameOver;
    Wolfs wolfs;
    private ArrayList<Siga> sigas;
    Music whistle;
    Texture rabbitL;
    Texture rabbitR;
    Texture miniSiga;
    private Sprite minSiga;






    public GameScreen (final Wolf3 gam){
        game = gam;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        sigas = new ArrayList<Siga>();
        wolfs = new Wolfs();

        lastDropTime = TimeUtils.nanoTime();
        dropTime = 1000000000.0f;

        rabbitL = new Texture("rabbitLeft.png");
        rabbitR = new Texture("rabbitRight.png");
        miniSiga = new Texture("minisiga.jpg");
        minSiga = new Sprite(miniSiga);
        whistle = Gdx.audio.newMusic(Gdx.files.internal("whistle.mp3"));
        whistle.setLooping(true);
        whistle.play();
        whistle.setVolume(0.3f);

        gameOver = 4;
        wolfs.wolfRectangle(800, 480);   //начальное положение волка

    }


    @Override
    public void show() {
        whistle.play();
    }

    @Override
    public void render(float delta) {

        update();
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();

        game.font.draw(game.batch,"Gathered: " + countSigi, 0, 480);
        gameOver(gameOver);

        for (Siga sig: sigas ){
            sig.getSigisprate().setPosition(sig.getX(), sig.getY());
            sig.getSigisprate().draw(game.batch, 1);
            rabbit(sig.getX(), sig.getY());
        }

        game.batch.draw(wolfs.getWolf(),wolfs.getX(),wolfs.getY());             // отрисовываем волка
        if (Gdx.input.isTouched()) {
            tochX = Gdx.input.getX();                               //кординаты касания по х
            tochY = Gdx.input.getY();                               //кординаты касания по y
            wolfs.wolfRectangle(tochX, tochY);                            // передаем волку
        }
        game.batch.end();
    }
    public void metodsigidrops(){							//добавляем в массив сигарету и ее время
        sigas.add(new Siga());
        lastDropTime = TimeUtils.nanoTime();

    }
    public void update (){
        if (dropTime > 300000000 )
            speedDropTime += 100;
            dropTime -= speedDropTime;
        if (dropTime <= 300000000)dropTime=300000000;

        if (TimeUtils.nanoTime()-lastDropTime > dropTime) metodsigidrops();

        for (Siga sig: sigas ) {
            sig.update();
        }

        Iterator <Siga> iter = sigas.iterator();
        while (iter.hasNext()){
            Siga sig = iter.next();
            if (sig.collides(wolfs.getWolfReg())) {
                iter.remove();
                countSigi++;
            }
            if (sig.getY() < -50){
                iter.remove();
                gameOver--;
            }
        }

        if(countSigi > GameStart.highScore) {
           GameStart.highScore = countSigi;          // сохраняем результат
        }

    }
    public void rabbit(float x, float y){

        if (x < 150 && y < 200 && y > -50){
        game.batch.draw(rabbitL,0,50);

         }
        if (x < 150 && y > 300 && y > -50){
            game.batch.draw(rabbitL,0,250);
        }

        if (x > 650  && y < 150 && y > -50){
            game.batch.draw(rabbitR,750,50);
        }
        if (x > 650 && y > 300 && y > -50){
            game.batch.draw(rabbitR,750,250);
        }
    }

    public void gameOver(int gameOver){                           // отрисовка мини сиги
        minSiga.setPosition(0, 0);
        minSiga.draw(game.batch, 100);
        minSiga.setPosition(45, 0);
        minSiga.draw(game.batch, 100);
        minSiga.setPosition(90, 0);
        minSiga.draw(game.batch, 100);

        switch (gameOver){
            case 2:
                game.batch.draw(miniSiga,0,0);
                break;
            case 3:
                game.batch.draw(miniSiga,0,0);
                game.batch.draw(miniSiga,45,0);
                break;
            case 4:
                game.batch.draw(miniSiga,0,0);
                game.batch.draw(miniSiga,45,0);
                game.batch.draw(miniSiga,90,0);
                break;
            case 1:
                game.setScreen( new GameOver(game));
                Siga.spx = 0;
                Siga.spy = 0;
                GameStart.prefs.putInteger("highscore", GameStart.highScore);
                GameStart.prefs.flush();                  //кэшируется пока не вызвать
                dispose();
        }
    }
    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
    rabbitL.dispose();
    rabbitR.dispose();
    wolfs.dispose();
    miniSiga.dispose();
    whistle.dispose();
        for (Siga sig: sigas ) {
            sig.dispose();
        }
    }
}
