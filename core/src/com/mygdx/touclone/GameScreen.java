package com.mygdx.touclone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class GameScreen extends ScreenAdapter {

    Touclone game;

    float circleX = 300;
    float circleY = 150;
    float circleRadius = 50;
    float circlecolor = 0;

    int clickcount = 1;

    float xSpeed = 4;
    float ySpeed = 3;

    public GameScreen(Touclone game) {
        this.game = game;
    }

    @Override
    public void show() {
        // Clicking Circle
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int x, int y, int pointer, int button) {
                int renderY = Gdx.graphics.getHeight() - y;
                if (Vector2.dst(circleX, circleY, x, renderY) < circleRadius && clickcount > 5) {
                    game.setScreen(new EndScreen(game));
                } else if(Vector2.dst(circleX, circleY, x, renderY) < circleRadius){
                    circlecolor+=0.15;
                    clickcount++;
                }
                return true;
            }
        });
    }

    @Override
    public void render(float delta) {

        update();

        circleX += xSpeed;
        circleY += ySpeed;

        if (circleX < 0 || circleX > Gdx.graphics.getWidth()) {
            xSpeed *= -1;
        }

        if (circleY < 0 || circleY > Gdx.graphics.getHeight()) {
            ySpeed *= -1;
        }

        Gdx.gl.glClearColor(0, 0, .25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw(game.player, game.player.getX(), game.player.getY());
        game.batch.end();

        game.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        game.shapeRenderer.setColor(0, circlecolor, 0, 1);
        game.shapeRenderer.circle(circleX, circleY, 75);
        game.shapeRenderer.end();

    }

    // Update the game state before rendering
    public void update(){
        // handling the input

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int x, int y, int pointer, int button) {
                int renderY = Gdx.graphics.getHeight() - y;

                // clicking circle
                if (Vector2.dst(circleX, circleY, x, renderY) < circleRadius && clickcount > 5) {
                    game.setScreen(new EndScreen(game));
                } else if(Vector2.dst(circleX, circleY, x, renderY) < circleRadius){
                    circlecolor+=0.15;
                    clickcount++;
                } else{
                    // moving player if circle is not clicked
                    game.player.setX(x);
                    game.player.setY(renderY);
                }
                return true;
            }
            @Override
            public boolean touchDragged(int x, int y, int pointer) {
                int renderY = Gdx.graphics.getHeight() - y;
                game.player.setX(x);
                game.player.setY(renderY);
                return true;
            }
        });
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

}
