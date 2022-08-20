package com.vdum.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.vdum.game.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "VDUM";
		config.height = 540;
		config.width = 1080;
		int FPS_I_WISH = 75;
		config.foregroundFPS = FPS_I_WISH ;
		config.backgroundFPS = FPS_I_WISH ;
		config.vSyncEnabled = false;
		new LwjglApplication(new Main(), config);
	}
}
