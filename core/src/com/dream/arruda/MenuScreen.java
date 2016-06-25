package com.dream.arruda;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;


/**
 * Created by ti on 22/06/2016.
 */
public class MenuScreen implements Screen {

    private final Game game;
    private OrthographicCamera camera;
    private GlyphLayout glyphLayout;
    private String str1;
    private String str2;
    private boolean start;

    public MenuScreen(Game g){
        game=g;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, game.width, game.height);
        glyphLayout = new GlyphLayout();
        str1="Welcome to Pong!!!";
        str2="Tap anywhere to begin!";
        start =false;
        Gdx.input.setInputProcessor(new InputAdapter(){
            @Override
            public boolean touchDown (int x, int y, int pointer, int button) {
                if(!start){
                    start=true;
                    return true;
                }
                return false;
            }

            @Override
            public boolean touchUp (int x, int y, int pointer, int button) {
                if(start) {
                    game.gameover = false;
                    game.setScreen(new GameScreen(game));
                    dispose();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        glyphLayout.setText(game.font,str1);
        game.font.draw(game.batch, str1, game.width/2-glyphLayout.width/2, game.height/5*3+glyphLayout.height/2);
        glyphLayout.setText(game.font,str2);
        game.font.draw(game.batch, str2, game.width/2-glyphLayout.width/2, game.height/2-glyphLayout.height/2);
        game.batch.end();
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)){
            if(!game.backpressed)
            {
                game.backpressed=true;
                Gdx.app.exit();
            }
        }
        else if(game.backpressed)
            game.backpressed=false;
        if(game.gameover) {
            glyphLayout.setText(game.font2,"Game Over");
            game.batch.begin();
            game.font2.draw(game.batch, "Game Over", game.width/2-glyphLayout.width/2, game.height);
            game.batch.end();
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
    }
}
