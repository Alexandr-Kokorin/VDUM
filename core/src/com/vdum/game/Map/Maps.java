package com.vdum.game.Map;

public class Maps {

    public static Map map;
    public static int index;

    public static void loadMap(){
        switch (index) {
            case 1:
                map = new CentralStreet();
                break;
        }
    }
}
