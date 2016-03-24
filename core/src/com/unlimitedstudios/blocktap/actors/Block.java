package com.unlimitedstudios.blocktap.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.unlimitedstudios.blocktap.BlockTap;

public class Block extends Actor  {

    private Sprite texture;
    private boolean touched = false;
    //private int color;
    private Color color;
    final BlockTap game;

    float blockX = 0, blockY = 0, velocity = 0.336f;

    public Block(BlockTap game) {
        this.game = game;

        addListener(new InputListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //((Block)event.getTarget()).setTouched(true);
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                ((Block)event.getTarget()).setTouched(true);
            }
        });
    }

    public void initialize() {
        setBounds(blockX, blockY, texture.getWidth(),texture.getHeight());
    }

    @Override
    public void draw(Batch batch, float alpha) {
        setBounds(blockX, blockY, texture.getWidth() + 10,texture.getHeight() + 10);

        batch.draw(texture, blockX, blockY, 4 ,4 );
    }

    @Override
    public void act(float delta) {
        blockY -= velocity;
    }

    public void setTexture(Sprite texture) {
        this.texture = texture;
    }
    public Sprite getTexture() {
        return texture;
    }
    public Color getTextureColor() {return color;}
    public void setTextureColor(Color color) {
        this.color = color;
    }

    public float areaX() {
        return (this.getX() + this.getWidth());
    }

    public float areaY() {
        return (this.getBlockY() + this.getTexture().getHeight());
    }

    public void setTouched(boolean bool) {
        this.touched = bool;
    }

    public void setVelocity(float velocity) {this.velocity = velocity;}

    public boolean isTouched() {
        return touched;
    }

    //public int getColor() {return color;}
    //public void setColor(int color) {this.color = color;}

    public float getBlockX() {
        return blockX;
    }
    public void setBlockX(float x) {
        blockX = x;
    }

    public float getBlockY() {
        return blockY;
    }
    public void setBlockY(float y) {
        blockY = y;
    }


}
