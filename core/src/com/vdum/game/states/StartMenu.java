package com.vdum.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.vdum.game.Elements.Font;

public class StartMenu extends State {

    private Font font;
    private Texture bg, v, d, u, m, blot;
    private int stage, count;
    private String text;
    private double time, size;

    public StartMenu(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false, 1080, 540);
        font = new Font();
        bg = new Texture("StartMenuBG.png");
        v = new Texture("v.png");
        d = new Texture("d.png");
        u = new Texture("u.png");
        m = new Texture("m.png");
        blot = new Texture("blot.png");
        bg.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        v.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        d.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        u.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        m.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        blot.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        text = "путешествие по тайным мирам лобачевского";
        stage = 1;
        count = 1;
        time = 0;
        size = 3;
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.isTouched()) {
            gsm.set(new MainGame(gsm));
        }
    }

    @Override
    public void update(float dt) {
        time += dt;
        if ((stage == 1 || stage == 2 || stage == 3 || stage == 4 || stage == 6)) {
            if ((60/(1/dt)) < 1) size -= 0.2 * (60/(1/dt));
            else size -= 0.2;
        }
        if (size <= 1) {
            stage++;
            size = 3;
        }
        if (stage == 5 && count < text.length() && time > 0.05) {
            count++;
            time = 0;
        } else if (count == text.length() && stage == 5) stage++;
        if (time > 0.5 && stage > 6) {
            time = 0.5;
            handleInput();
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        camera.update();
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(bg,0,0,1080,540);
        if (stage == 1) sb.draw(v,100+80,300+50,(int)(197*size),(int)(139*size));
        if (stage > 1)  sb.draw(v,100+80,300+50,197,139);
        if (stage == 2) sb.draw(d,300+80,300+50,(int)(155*size),(int)(139*size));
        if (stage > 2) sb.draw(d,300+80,300+50,155,139);
        if (stage == 3) sb.draw(u,470+80,296+50,(int)(125*size),(int)(142*size));
        if (stage > 3) sb.draw(u,470+80,296+50,125,142);
        if (stage == 4) sb.draw(m,607+80,300+50,(int)(162*size),(int)(139*size));
        if (stage > 4) {
            sb.draw(m,607+80,300+50,162,139);
            font.render(sb,255,300,14,text.substring(0,count));
        }
        if (stage == 6) sb.draw(blot,593 - (int)((383*size-383)/2),150 - (int)((446*size-446)/2),(int)(383*size),(int)(446*size));
        if (stage > 6) sb.draw(blot,593,150,383,446);
        sb.end();
    }

    @Override
    public void dispose() {
        font.dispose();
        bg.dispose();
        v.dispose();
        d.dispose();
        u.dispose();
        m.dispose();
        blot.dispose();
    }
}
