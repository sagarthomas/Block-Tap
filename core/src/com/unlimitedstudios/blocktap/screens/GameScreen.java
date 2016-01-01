package com.unlimitedstudios.blocktap.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.unlimitedstudios.blocktap.BlockTap;
import com.unlimitedstudios.blocktap.actors.Block;

import java.util.Iterator;

public class GameScreen implements Screen {
    final BlockTap game;

    Texture redBlock;
    Texture greenBlock;
    Texture yellowBlock;
    OrthographicCamera camera;
    Array<Block> blocks;
    long lastBlockDrop;
    int score = 0;
    double velocity = 300;
    int threshold = 10;
    int position;
    int preCase;

    public GameScreen(final BlockTap game) {
        this.game = game;

        //Load all images for the block
        redBlock = new Texture(Gdx.files.internal("redBlock.png"));
        greenBlock = new Texture(Gdx.files.internal("greenBlock.png"));
        yellowBlock = new Texture(Gdx.files.internal("yellowBlock.png"));

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 480, 800);


        blocks = new Array<Block>();
        spawnBlock();

        game.font.getData().setScale(3,3);
    }

    private void spawnBlock() {
        Block block = new Block();
        //block.x = MathUtils.random(0, );
        position = MathUtils.random(0, 2);
        block.x = xPosition();
        block.y = 800;
        block.width = 128;
        block.height = 128;
        block.setTexture(randomTexture());
        blocks.add(block);
        lastBlockDrop = TimeUtils.nanoTime();
    }

    private int xPosition() {
        int pavly;
        switch (position){
            case 0:
                if (position != preCase ) {
                    preCase = 0;
                    pavly =  10;
                }
                else {
                    pavly = 342;
                }
                break;
            case 1:
                if (position != preCase ) {
                    preCase = 1;
                    pavly = 166;
                }
                else {
                    pavly = 10;
                }
                break;
            case 2:
                if (position != preCase ) {
                    preCase = 2;
                    pavly = 342;
                }
                else{
                    pavly = 166;
                }
                break;
            default:
                pavly = 10;

        }
        return pavly;
    }

    private Texture randomTexture() {
        int random = MathUtils.random(0, 12);
        // Add more conditions for more types of blocks
        if (random >= 0 && random <= 4)
            return redBlock;
        if (random >= 5 && random <= 8)
            return greenBlock;
        else
            return yellowBlock;
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        // clear screen with color specified.
        // The arguments to glClearColor are the red, green
        // blue and alpha component in the range [0,1]
        // of the color to be used to clear the screen.
        Gdx.gl.glClearColor(255, 255, 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // update camera
        camera.update();

        game.batch.setProjectionMatrix(camera.combined);

        // begin new batch
        game.batch.begin();
        // TODO: Add the display for score
        game.font.draw(game.batch, ""+ score, 240 , 780);
        for (Block block : blocks) {
            game.batch.draw(block.getTexture(), block.x, block.y);
        }
        game.batch.end();

        // user input
        if(Gdx.input.isTouched()){
            Vector3 touchPoint = new Vector3();
            touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPoint); // IMPORTANT
            // loop through array of blocks to see if one is touched
            for(Block block : blocks) {
                if(block.contains(touchPoint.x, touchPoint.y))
                    block.setTouched(true);
            }
        }

        if (TimeUtils.nanoTime() - lastBlockDrop > 1000000000*0.4455687968) // if one second* has passed
            spawnBlock();
        if(score > threshold) {
            threshold = threshold*3;
            velocity = velocity * 1.28;
        }

        Iterator<Block> iter = blocks.iterator();
        while(iter.hasNext()){
            Block block = iter.next();
            block.y -= velocity * Gdx.graphics.getDeltaTime();
            if (block.isTouched()) {
                score++;
                iter.remove();
            }
            if (block.areaY() < 0) {
                iter.remove();
                game.setScreen(new GameOver(game, score));
            }
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
        redBlock.dispose();
        greenBlock.dispose();
        yellowBlock.dispose();
    }
}
