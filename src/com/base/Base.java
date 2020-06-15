package com.base;

import javax.swing.*;

//基本画布窗口属性设定
public class Base extends JFrame {
    private DemoPlane demoPlane;
    public Base(DemoPlane demoPlane, KeyBoardMonitor keyBoardMonitor){
        this.demoPlane = demoPlane;
        this.add(demoPlane);
        this.setTitle("三国无双");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(660,600);
        this.addKeyListener(keyBoardMonitor);
        this.setVisible(true);
    }


}
