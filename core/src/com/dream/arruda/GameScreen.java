package com.dream.arruda;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;


/**
 * Created by ti on 23/06/2016.
 */
public class GameScreen implements Screen {

    private final Game game;
    private Paddle paddle;
    private Brick brick;
    private Ball ball;
    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;
    private Vector3 touchPos;
    private float dt;
    private float cttimer;
    private GlyphLayout glyphLayout;
    private int balllife;
    private int score;

    public GameScreen(Game g){
        game=g;
        paddle=new Paddle(game.width,game.height);
        brick=new Brick(game.width,game.height);
        ball=new Ball(game.width,game.height);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, game.width, game.height);
        shapeRenderer=new ShapeRenderer();
        touchPos=new Vector3();
        cttimer=4.0f;
        glyphLayout = new GlyphLayout();
        balllife=3;
        score=0;
        Gdx.input.setInputProcessor(new InputAdapter());
    }

    @Override
    public void show() {
    }

    private void Update(){
        if (Gdx.input.isTouched()) {
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            paddle.Move(Gdx.input.getX()/(int)game.density);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)){
            game.backpressed=true;
            game.setScreen(new MenuScreen(game));
            dispose();
        }
        if(cttimer<=0) {
            if (!game.gameover){
                ball.Move(dt, game.width, game.height);
                if (new Rectangle(ball.pos.x - ball.radius, ball.pos.y - ball.radius, ball.radius * 2, ball.radius * 2).overlaps(
                        new Rectangle(0, 0, game.width, paddle.pos.getY() + paddle.pos.getHeight()))) {
                    if (new Rectangle(ball.pos.x - ball.radius, ball.pos.y - ball.radius, ball.radius * 2, ball.radius * 2).overlaps(paddle.pos))
                        ball.Changedir();
                    else {
                        cttimer = 4.0f;
                        ball.Setpos(game.width, game.height);
                        balllife -= 1;
                        if (balllife <= 0) {
                            game.gameover = true;
                            game.setScreen(new MenuScreen(game));
                            dispose();
                        }
                    }
                }
                for (int i = 0; i <= brick.pos.length - 1; i++) {
                    for (int j = 0; j <= brick.pos[i].length - 1; j++) {
                        if (!brick.colidiu[i][j]) {
                            if (new Rectangle(ball.pos.x - ball.radius, ball.pos.y - ball.radius, ball.radius * 2, ball.radius * 2).overlaps(brick.pos[i][j])) {
                                brick.colidiu[i][j] = true;
                                ball.Changedir();
                                score += 10;
                            }
                        }
                    }
                }
                if(score==420) {
                    game.gameover = true;
                    game.setScreen(new MenuScreen(game));
                    dispose();
                }
            }
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        shapeRenderer.setProjectionMatrix(camera.combined);
        game.batch.setProjectionMatrix(camera.combined);
        Update();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0f, 1f, 1f, 1f);
        shapeRenderer.rect(paddle.pos.getX(),paddle.pos.getY(),paddle.pos.getWidth(),paddle.pos.getHeight());
        shapeRenderer.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0.498f, 1.000f, 0.831f,1f);
        for (int i=0; i<= brick.pos.length-1;i++)
        {
            for (int j=0; j<=brick.pos[i].length-1;j++)
            {
                if(!brick.colidiu[i][j])
                    shapeRenderer.rect(brick.pos[i][j].getX(),brick.pos[i][j].getY(),
                            brick.pos[i][j].getWidth(),brick.pos[i][j].getHeight());
            }
        }
        shapeRenderer.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0.000f, 1.000f, 0.498f, 1f);
        shapeRenderer.circle(ball.pos.x,ball.pos.y,ball.radius);
        shapeRenderer.end();
        dt= Gdx.graphics.getDeltaTime();
        if (cttimer>=0) {
            cttimer-=dt;
            glyphLayout.setText(game.font2,Integer.toString((int)cttimer));
            game.batch.begin();
            game.font2.setColor(1, 0, 0, 1);
            game.font2.draw(game.batch, Integer.toString((int)cttimer), game.width/2-glyphLayout.width/2, game.height/2+glyphLayout.height/2);
            game.batch.end();
        }
        game.batch.begin();
        game.font.draw(game.batch,"Score: ",0,game.height);
        glyphLayout.setText(game.font,"Score: ");
        game.font.draw(game.batch,Integer.toString(score),glyphLayout.width,game.height);
        glyphLayout.setText(game.font,Integer.toString(balllife));
        game.font.draw(game.batch,Integer.toString(balllife),game.width-glyphLayout.width,game.height);
        float i=glyphLayout.width;
        glyphLayout.setText(game.font,"Life: ");
        game.font.draw(game.batch,"Life: ",game.width-glyphLayout.width-i,game.height);
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
        cttimer=4.0f;
        ball.Setpos(game.width,game.height);
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
}
