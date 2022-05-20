package com.vdum.game.Elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Button {

    private Texture texture;
    private Rectangle rectangle;

    public Button(String name, float x, float y, float width, float height) {
        texture = new Texture(name);
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        rectangle = new Rectangle(x,y,width,height);
    }

    public boolean collision(Vector3 touch){
        return rectangle.contains(touch.x,touch.y);
    }

    public void render(SpriteBatch sb){
        sb.draw(texture,rectangle.x,rectangle.y,rectangle.width,rectangle.height);
    }

    public void dispose(){
        texture.dispose();
    }

}
