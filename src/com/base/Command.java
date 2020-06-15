package com.base;

import java.awt.event.KeyEvent;
import java.util.concurrent.CopyOnWriteArraySet;

public enum Command {
    //按键枚举类
//    UP, DOWN, LEFT, RIGHT, ATTACK, JUMP, MENU, PAUSE, RESTART, QUIT, BACK, START, KEY, DEVELOP, HOME
    UP(KeyEvent.VK_W),
    DOWN(KeyEvent.VK_S),
    LEFT(KeyEvent.VK_A),
    RIGHT(KeyEvent.VK_D),
    ATTACK(KeyEvent.VK_J),
    JUMP(KeyEvent.VK_K),
    MENU(KeyEvent.VK_M),
    PAUSE(KeyEvent.VK_M),
    BACK(KeyEvent.VK_B),
    RESTART(KeyEvent.VK_R),
    QUIT(KeyEvent.VK_Q),
    HOME(KeyEvent.VK_H),
    END(KeyEvent.VK_E);


    public final static CopyOnWriteArraySet<Integer> keySet = new CopyOnWriteArraySet<>();

    public int keyValue;

    Command(int vkW) {
        this.keyValue = vkW;
    }

    public boolean use(){
        return keySet.contains(keyValue);
    }

    public static void add(int keyCode){
        keySet.add(keyCode);
    }

    public static void remove(int keyCode){
        keySet.remove(keyCode);
    }
}
