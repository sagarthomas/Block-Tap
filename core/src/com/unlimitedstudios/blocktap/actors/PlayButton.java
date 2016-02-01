package com.unlimitedstudios.blocktap.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Charles on 31/01/2016.
 */
public class PlayButton extends Rectangle {

    private Texture texture;
    private boolean touched;

    public PlayButton() {

    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }
    public Texture getTexture() {
        return texture;
    }

    public float areaX() {
        return (this.x + this.width);
    }

    public float areaY() {
        return (this.y + this.height);
    }

    public void setTouched(boolean bool) {
        this.touched = bool;
    }

    public boolean isTouched() {
        return touched;
    }
}
