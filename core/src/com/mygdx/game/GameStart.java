package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class GameStart implements Screen {
    final Wolf3 game;
    OrthographicCamera camera;
    Texture gameStart;
    Texture touchStart;
    Sprite sprite;
    static int i = 0;
    static int highScore;
    static Preferences prefs;



    public GameStart(final Wolf3 gam) {
        game = gam;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        gameStart = new Texture("Start.png");
        touchStart = new Texture("touch.jpg");
        sprite = new Sprite(touchStart);

        prefs = Gdx.app.getPreferences("MyPreferences"); //получаем файл персональных данных
        highScore = prefs.getInteger("highscore"); //получаем текущий лучший результат


    }

    @Override

    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(gameStart,800/2 - gameStart.getWidth()/2,240);
        game.font.draw(game.batch, "Record: "+ GameStart.highScore, 350, 200);

       i+=1;
       if (i > 120) i = 0;
            sprite.setPosition(800/2 - touchStart.getWidth()/2, 200);
            sprite.draw(game.batch, i);

        if (Gdx.input.isTouched()) {
            game.setScreen( new GameScreen(game));
            dispose();
        }
        game.batch.end();
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
        gameStart.dispose();
        touchStart.dispose();

    }
}
