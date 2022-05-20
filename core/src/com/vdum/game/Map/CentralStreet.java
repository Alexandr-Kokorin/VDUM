package com.vdum.game.Map;

import com.badlogic.gdx.graphics.Texture;

public class CentralStreet extends Map {

    public CentralStreet() {
        background = new Texture("bg.png");
        backgroundSize = new int[] {1500,500};
        orientation = "x";
    }
}
