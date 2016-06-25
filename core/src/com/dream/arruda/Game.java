package com.dream.arruda;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class Game extends com.badlogic.gdx.Game {
	public SpriteBatch batch;
    public ShapeRenderer shapes;
    public BitmapFont font;
	public BitmapFont font2;
    public int width;
    public int height;
    public float density;
	public boolean backpressed;
	public boolean gameover;

    @Override
	public void create () {
		batch = new SpriteBatch();
        shapes=new ShapeRenderer();
        width= Gdx.graphics.getWidth();
        height=Gdx.graphics.getHeight();
        density = Gdx.graphics.getWidth()/width;
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("sans.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = width/13;
        font = generator.generateFont(parameter);
		parameter.size = width/6;
		font2 = generator.generateFont(parameter);
        generator.dispose();
		Gdx.input.setCatchBackKey(true);
		backpressed=false;
		gameover=false;
        this.setScreen(new MenuScreen(this));
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {
		this.getScreen().resume();
	}

	@Override
	public void dispose() {
		batch.dispose();
        shapes.dispose();
        font.dispose();
		font2.dispose();
        this.getScreen().dispose();
	}
}
