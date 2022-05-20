package com.vdum.game.Characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.vdum.game.Map.Maps;

public class Student {

    private Texture texture;
    protected Rectangle rectangle;
    private float velocity = 0;
    private float gravity = 0;
    private int dx,dy;

    public Student(String texture, int x, int y, int width, int height){
        this.texture = new Texture(texture);
        this.texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        rectangle = new Rectangle(x, y, width, height);
    }

    public void update(float dt){
        gravity -= 20;
        if (velocity > 0) {
            velocity -= 10;
        }
        if (velocity < 0) {
            velocity += 10;
        }
        dx = (int)(velocity * dt);
        dy = (int)(gravity * dt);
        rectangle.y += dy;
        rectangle.x += dx;
        if (rectangle.y <= 0) {
            rectangle.y = 0;
            gravity = 0;
        }
        if (rectangle.x < 0) {
            rectangle.x = 0;
            velocity = 0;
        }
        if (rectangle.x + rectangle.width > Maps.map.backgroundSize[0]) {
            rectangle.x = Maps.map.backgroundSize[0] - rectangle.width;
            velocity = 0;
        }
    }

    public void jump(){
        if (rectangle.y == 0) {
            gravity += 600;
        }
    }

    public void moveToRight(){
        velocity += 50;
        if (velocity > 300){
            velocity = 300;
        }
    }

    public void moveToLeft(){
        velocity -= 50;
        if (velocity < -300){
            velocity = -300;
        }
    }

    public float getWidth(){
        return rectangle.width;
    }

    public float getHeight(){
        return rectangle.height;
    }

    public float getPositionX(){
        return rectangle.x;
    }

    public float getPositionY(){
        return rectangle.y;
    }

    public Texture getTexture(){
        return texture;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public void dispose(){
        texture.dispose();
    }
}
