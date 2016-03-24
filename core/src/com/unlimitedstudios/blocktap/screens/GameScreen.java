package com.unlimitedstudios.blocktap.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.unlimitedstudios.blocktap.BlockTap;
import com.unlimitedstudios.blocktap.actors.Block;

public class GameScreen implements Screen {
    final BlockTap game;

    Stage stage, tableStage;

    Texture redBlock, greenBlock, yellowBlock, gameOver;
    Sprite red, green, yellow;

    OrthographicCamera camera;

    Image currentColor;
    Color textureColor;

    long lastBlockDrop;
    int score = 0, threshold = 5, colorThreshold = 10, position, preCase, color = 0, random;
    double timer;
    boolean death = false, restartTouched, touchYaw = false, gracePeriod;
    float initVelocity = 0.336f, increaseFactor = 0.005f, velocity;

    Skin skin;
    Table table = new Table();
    Viewport viewport;
    final static float GAME_WORLD_WIDTH = 12;
    final static float GAME_WORLD_HEIGHT = 20;


    public GameScreen(final BlockTap game, boolean restartTouched) {
        this.game = game;
        this.restartTouched = restartTouched;

        redBlock = new Texture(Gdx.files.internal("redBlock.png"));
        greenBlock = new Texture(Gdx.files.internal("greenBlock.png"));
        yellowBlock = new Texture(Gdx.files.internal("yellowBlock.png"));
        gameOver = new Texture(Gdx.files.internal("Game Over.png"));

        red = new Sprite(redBlock);
        green = new Sprite(greenBlock);
        yellow = new Sprite(yellowBlock);

        red.setSize(4 ,4);
        green.setSize(4, 4);
        yellow.setSize(4, 4);

        float aspectRatio = (float)Gdx.graphics.getHeight() / (float)Gdx.graphics.getWidth();

        camera = new OrthographicCamera(GAME_WORLD_WIDTH * aspectRatio, GAME_WORLD_HEIGHT);
        //camera.setToOrtho(false, 480, 800);


        viewport = new ExtendViewport(GAME_WORLD_WIDTH * aspectRatio, GAME_WORLD_HEIGHT, camera);

        stage = new Stage(viewport);
        stage.getViewport().apply();
        camera.position.set(GAME_WORLD_WIDTH / 2 ,GAME_WORLD_HEIGHT/2, 0 );
        tableStage = new Stage();
        timer = 0;





        game.font.getData().setScale(3, 3);

        Gdx.input.setInputProcessor(stage);

        skin = new Skin();

        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("white", new Texture(pixmap));


        Pixmap back = new Pixmap(1,1, Pixmap.Format.RGBA8888);
        back.setColor(Color.BLACK);
        back.fill();
        skin.add("back", new Texture(back));

        //Store the default font
        skin.add("default", new BitmapFont());

        //table.setFillParent(true);
        //table.top();
        float kwab = ((float) Gdx.graphics.getHeight() - 100);
        //kwab = 800f;
        table.setY(kwab);

        table.setWidth(Gdx.graphics.getWidth());
        table.setHeight(140);

        table.background(skin.getDrawable("back"));


        tableStage.addActor(table);
       // Image image = new Image();
        //image.setDrawable(skin.getDrawable("white"));



        // Add an image actor. Have to set the size, else it would be the size of the drawable (which is the 1x1 texture).
        Color color = defaultColor();
        currentColor = new Image(skin.newDrawable("white", color));
        currentColor.setColor(color);
        textureColor = color;
        random = 1;

        //Image secondColor = new Image(skin.newDrawable("white", Color.WHITE));
        //secondColor.setColor(Color.WHITE);



        table.add(currentColor).size(64);
        //table.add(secondColor).size(64).padLeft(20);
        spawnBlock();
    }

    private void spawnBlock() {
        Block block = new Block(game);
        //block.x = MathUtils.random(0, );
        position = MathUtils.random(0, 2);
        block.setBlockX(xPosition());
        block.setBlockY(24);
        //block.width = 128;
        //block.height = 128;
        block.setTexture(randomTexture());
        block.setTextureColor(textureColor);
        //block.setColor(color);
        //blocks.add(block);
        block.initialize();
        stage.addActor(block);
        lastBlockDrop = TimeUtils.nanoTime();
    }

    private float xPosition() {

        int pavly;
        switch (position){
            case 0:
                if (position != preCase ) {
                    preCase = 0;
                    //pavly =  10;
                    pavly = -2;
                }
                else {
                    //pavly = 342;
                    pavly = 10;
                }
                break;
            case 1:
                if (position != preCase ) {
                    preCase = 1;
                    //pavly = 166;
                    pavly = 5 ;
                }
                else {
                   // pavly = 10;
                    pavly = 0;
                }
                break;
            case 2:
                if (position != preCase ) {
                    preCase = 2;
                    //pavly = 342;
                    pavly = 10;
                }
                else{
                    //pavly = 166;
                    pavly = 5;
                }
                break;
            default:
                //pavly = 10;
                pavly = -2;

        }
        return pavly;
    }

    private Sprite randomTexture() {
        int random = MathUtils.random(0, 12);
        // Add more conditions for more types of blocks
        if (random >= 0 && random <= 4) {
            color = 0;
            textureColor = Color.RED;

            return red;
        }
        if (random >= 5 && random <= 8) {
            color = 1;
            textureColor = Color.GREEN;
            return green;
        }
        else {
            color = 2;
            textureColor = Color.YELLOW;
            return yellow;
        }
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

        //Grace peroid timer
        if(gracePeriod) {
            timer += delta;
            if(timer > 2)
                gracePeriod = false;
            else
                gracePeriod = true;
        }

        // update camera
        camera.update();

        game.batch.setProjectionMatrix(camera.combined);


        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

        tableStage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        tableStage.draw();

        // begin new batch
        game.batch.begin();
        game.batch.draw(gameOver, 5, 10);
        // TODO: Add the display for score
        game.batch.end();


        if (TimeUtils.nanoTime() - lastBlockDrop > 1000000000 * 0.4455687968) { // if one second* has passed
            spawnBlock();
            touchYaw = false;
        }


        for (Actor actor : stage.getActors()) {
            if( ((Block)actor).isTouched()  && ((Block)actor).getTextureColor().equals(currentColor.getColor())) {
                //Gdx.app.log("Current Color: ", "" + currentColor.getColor());
                //Gdx.app.log("Texture Color ", "" + ((Block) actor).getTextureColor());
                score++;
                actor.remove();
            }
            else if((((Block)actor).isTouched()) && !(((Block)actor).getTextureColor().equals(currentColor.getColor()))) {
                actor.remove();
                Gdx.app.log("Current Color: ", "" + currentColor.getColor());
                Gdx.app.log("Texture Color ", "" + ((Block) actor).getTextureColor());
                game.setScreen(new GameOver(game, score));
            }
            if((((Block)actor).areaY() < -5) && (((Block)actor).getTextureColor().equals(currentColor.getColor()))) {
                actor.remove();
                if(!gracePeriod) {

                    game.setScreen(new GameOver(game, score));
                }
            }
        }

        if(score > colorThreshold) {
            getColor();
            colorThreshold = colorThreshold + 10;
        }

        Gdx.app.log("Actors : ", "" + stage.getActors().size);

        if(score > threshold) {
            threshold = threshold + 3;
            initVelocity += increaseFactor;
            for(Actor actor : stage.getActors()) {
                ((Block)actor).setVelocity(initVelocity);
            }
        }

    }

    private void getColor() {
        random = (int)(Math.random() * 3);



        switch(random) {
            case 0:
                //(table.getCell(currentColor)).getActor().setDrawable(skin.getDrawable("red"));
                (table.getCell(currentColor)).getActor().setDrawable(skin.newDrawable("white", Color.RED));
                currentColor.setColor(Color.RED);
                gracePeriod = true;
                timer = 0;
                break;
            case 1:
                //(table.getCell(currentColor)).getActor().setDrawable(skin.getDrawable("green"));
                (table.getCell(currentColor)).getActor().setDrawable(skin.newDrawable("white", Color.GREEN));
                currentColor.setColor(Color.GREEN);
                gracePeriod = true;
                timer = 0;
                break;
            case 2:
                //(table.getCell(currentColor)).getActor().setDrawable(skin.getDrawable("yellow"));
                (table.getCell(currentColor)).getActor().setDrawable(skin.newDrawable("white", Color.YELLOW));
                currentColor.setColor(Color.YELLOW);
                gracePeriod = true;
                timer = 0;
                break;
        }
    }

    private Color defaultColor() {
        int random = (int)(Math.random() * 3);
        Color color;

        switch (random) {
            case 1:
                color = Color.GREEN;
            break;
            case 2:
                color = Color.RED;
            break;
            case 3:
                color = Color.YELLOW;
            break;
            default:
                color = Color.YELLOW;
        }

        return color;

    }

    public static void speed() {

    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
        camera.position.set(GAME_WORLD_WIDTH/2, GAME_WORLD_HEIGHT / 2, 0);
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
        stage.dispose();
        skin.dispose();
    }
}
