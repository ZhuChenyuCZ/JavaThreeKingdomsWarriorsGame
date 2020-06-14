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
//        if(e != null){
//            if(KeyEvent.VK_W == e.getKeyCode()){
//                Test.modifyPlayer(Command.UP);
//            }
//            else if(KeyEvent.VK_S == e.getKeyCode()){
//                Test.modifyPlayer(Command.DOWN);
//                Test.BeginMenuControl(Command.START);
//            }
//            else if(KeyEvent.VK_A == e.getKeyCode()){
//                Test.DiffChooseControl(Command.LEFT);
//                Test.modifyPlayer(Command.LEFT);
//            }
//            else if(KeyEvent.VK_D == e.getKeyCode()){
//                Test.modifyPlayer(Command.RIGHT);
//                Test.BeginMenuControl(Command.DEVELOP);
//                Test.DiffChooseControl(Command.RIGHT);
//            }
//            else if(KeyEvent.VK_J == e.getKeyCode()){
//                Test.modifyPlayer(Command.ATTACK);
//                Test.BeginMenuControl(Command.ATTACK);
//                Test.DiffChooseControl(Command.ATTACK);
//            }
//            else if(KeyEvent.VK_K == e.getKeyCode()){
//                Test.modifyPlayer(Command.JUMP);
//                Test.BeginMenuControl(Command.KEY);
//            }
//            else if(KeyEvent.VK_M == e.getKeyCode()){
//                Test.MenuControl(Command.MENU);
//            }
//            else if(KeyEvent.VK_B == e.getKeyCode()){
//                Test.MenuControl(Command.BACK);
//            }
//            else if(KeyEvent.VK_P == e.getKeyCode()){
//                Test.MenuControl(Command.PAUSE);
//            }
//            else if(KeyEvent.VK_R == e.getKeyCode()){
//                Test.MenuControl(Command.RESTART);
//            }
//            else if(KeyEvent.VK_Q == e.getKeyCode()){
//                Test.MenuControl(Command.QUIT);
//            }
//            else if(KeyEvent.VK_H == e.getKeyCode()){
//
//            }
//        }
        Command.add(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) { //这个函数需要调整
//        Test.player1.SwitchImage(1);
        Command.remove(e.getKeyCode());

        // wxl的6-13下午版本：
        if(Test.player1.attackpoint!=0)
            return;
        Test.player1.SwitchImage(Test.player1.Dir*Test.player1.divide+1);
    }
}
