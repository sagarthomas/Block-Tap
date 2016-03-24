package com.unlimitedstudios.blocktap.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

/**
 * Created by Charles on 31/01/2016.
 */
public class PlayButton extends Actor {

    private Sprite sprite;
    private boolean touched;
    private float x, y;

    public PlayButton() {
        addListener(new InputListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //((Block)event.getTarget()).setTouched(true);
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                ((PlayButton)event.getTarget()).setTouched(true);
            }
        });

    }

    public void initialize() {
        setBounds(x, y, sprite.getWidth(), sprite.getHeight());
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
    public Sprite getSprite() {
        return sprite;
    }

    public float areaX() {
        return (this.getX() + this.getWidth());
    }

    public float areaY() {
        return (this.getY() + this.getHeight());
    }

    public void setX(float x) {this.x = x;}
    public void setY(float y) {this.y = y;}

    public void setTouched(boolean bool) {
        this.touched = bool;
    }

    public boolean isTouched() {
        return touched;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        //super.draw(batch, parentAlpha);
        setBounds(x, y, sprite.getWidth(), sprite.getHeight());

        batch.draw(sprite, x, y, 10, 10);
    }

    @Override
    public void act(float delta) {
        //super.act(delta);
    }
}
