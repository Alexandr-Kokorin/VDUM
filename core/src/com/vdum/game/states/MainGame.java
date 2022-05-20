package com.vdum.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.vdum.game.Characters.Student;
import com.vdum.game.Elements.Button;
import com.vdum.game.Map.Maps;

public class MainGame extends State {

    Student student;
    static int[] topPosition, topCamera, sideCamera;
    Texture topViewBackground, frame, point, bg;
    Button moveToRight, moveToLeft, moveToUp, moveToDown, jump, answer, hint, settings, pause, menu, hero;
    Vector3 touch;

    public MainGame(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false, 1080, 540);
        student = new Student("student.png",100,50,50,100);
        Maps.index = 1;
        Maps.loadMap();
        topViewBackground = new Texture("tvbg.png");
        point = new Texture("point.png");
        bg = new Texture("MainGameBG.png");
        topViewBackground.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        point.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        bg.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        topPosition = new int[]{0,0};
        topCamera = new int[]{0,0};
        sideCamera = new int[]{0,0};
        moveToRight = new Button("MoveRight.png",1017,100,55,55);
        moveToLeft = new Button("MoveLeft.png",907,100,55,55);
        moveToUp = new Button("MoveUp.png",962,155,55,55);
        moveToDown = new Button("MoveDown.png",962,45,55,55);
        jump = new Button("jump.png",967,105,45,45);
        answer = new Button("answer.png",914,250,151,50);
        hint = new Button("hint.png",1000,313,60,60);
        settings = new Button("settings.png",30,488,50,50);
        pause = new Button("pause.png",110,488,50,50);
        menu = new Button("menu.png",190,488,50,50);
        hero = new Button("hero.png",270,488,50,50);
        touch = new Vector3(0,0,0);
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            student.jump();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            student.moveToRight();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            student.moveToLeft();
        }
        if (Gdx.input.isTouched()) {
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);
            if(moveToRight.collision(touch)) {
                student.moveToRight();
            }
            if(moveToLeft.collision(touch)) {
                student.moveToLeft();
            }
            if(jump.collision(touch)) {
                student.jump();
            }
        }
    }

    @Override
    public void update(float dt) {
        student.update(dt);
        sideCamera[0] = sideCamera[0]+student.getDx();
        sideCamera[1] = sideCamera[1]+student.getDy();
        if (student.getPositionX() <= 432) sideCamera[0] = 0;
        if (student.getPositionX()+432 >= Maps.map.backgroundSize[0]) sideCamera[0] = Maps.map.backgroundSize[0]-864;
        if (student.getPositionY() <= 216) sideCamera[1] = 0;
        if (student.getPositionY()+216 >= Maps.map.backgroundSize[1]) sideCamera[1] = Maps.map.backgroundSize[1]-433;
    }

    @Override
    public void render(SpriteBatch sb) {
        camera.update();
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(Maps.map.background,27,44,sideCamera[0],sideCamera[1],864,433);
        sb.draw(student.getTexture(),27+student.getPositionX()-sideCamera[0],44+student.getPositionY()-sideCamera[1],student.getWidth(),student.getHeight());
        sb.draw(topViewBackground,805,396,0,0,264,133);
        sb.draw(point,805 + student.getPositionX() / 3.5f,396,10,10);
        sb.draw(bg,0,0,1080,540);
        moveToRight.render(sb);
        moveToLeft.render(sb);
        moveToUp.render(sb);
        moveToDown.render(sb);
        jump.render(sb);
        answer.render(sb);
        hint.render(sb);
        settings.render(sb);
        pause.render(sb);
        menu.render(sb);
        hero.render(sb);
        sb.end();
        handleInput();
    }

    @Override
    public void dispose() {
        student.dispose();
        topViewBackground.dispose();
        frame.dispose();
        point.dispose();
        bg.dispose();
        moveToRight.dispose();
        moveToLeft.dispose();
        moveToUp.dispose();
        moveToDown.dispose();
        jump.dispose();
        answer.dispose();
        hint.dispose();
        settings.dispose();
        pause.dispose();
        menu.dispose();
        hero.dispose();
    }
}
