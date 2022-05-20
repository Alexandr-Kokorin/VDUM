package com.vdum.game.Elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Font {

    private Texture letters[];

    public Font(){
        letters = new Texture[33];
        for (int i = 0; i < letters.length; i++) {
            letters[i] = new Texture("Font/" + (i+1) + ".png");
            letters[i].setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }
    }

    public void render(SpriteBatch sb, float x, float y, int size, String text){
        int height = (int)(size * 2.435f);
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) != ' ' && text.charAt(i) != 'ё') {
                sb.draw(letters[(int) text.charAt(i) - (int) 'а'], x + i * size, y, size, height);
            } else if (text.charAt(i) == 'ё') {
                sb.draw(letters[(int) text.charAt(i) - (int) 'а' - 1], x + i * size, y, size, height);
            }
        }
    }

    public void dispose(){
        for (int i = 0; i < letters.length; i++) {
            letters[i].dispose();
        }
    }
}
