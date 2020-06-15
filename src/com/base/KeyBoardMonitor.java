package com.base;

import com.Test;
import com.base.Command;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoardMonitor implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {
        keyPressed(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Command.add(e.getKeyCode());
    }

    //松开按钮时应该切换回静止状态
    @Override
    public void keyReleased(KeyEvent e) {
        Command.remove(e.getKeyCode());
        //攻击未完成时不作反应
        if(Test.player1.attackpoint!=0)
            return;
        Test.player1.SwitchImage(Test.player1.Dir*Test.player1.divide+1);
    }
}
