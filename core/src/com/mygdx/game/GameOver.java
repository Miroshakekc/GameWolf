package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.TimeUtils;

public class GameOver implements Screen {
    final Wolf3 game;
    OrthographicCamera camera;
    Texture gameOver;
    private Long time;






    public GameOver(final Wolf3 gam) {
        game = gam;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        gameOver = new Texture("gameOver.jpg");
        time = TimeUtils.nanoTime() + 1000000000;


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

        game.batch.draw(gameOver,800/2 - gameOver.getWidth()/2,220);
        game.font.draw(game.batch,"TOUCH TO TRY AGAIN", 320, 220);
        game.font.draw(game.batch,"Gathered: " + GameScreen.countSigi, 320, 180);
        game.font.draw(game.batch, "Record: "+ GameStart.highScore, 320, 200);
        if (TimeUtils.nanoTime()-time > 1000000000) {
            if (Gdx.input.isTouched()) {
                game.setScreen(new GameScreen(game));
                GameScreen.countSigi = 0;
                dispose();
            }
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
        gameOver.dispose();


    }
}
