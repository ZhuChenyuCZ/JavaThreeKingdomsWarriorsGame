package com.base;

import javax.swing.*;


public class Base extends JFrame {
    private DemoPlane demoPlane;
    public Base(DemoPlane demoPlane, KeyBoardMonitor keyBoardMonitor){
        this.demoPlane = demoPlane;
        this.add(demoPlane);
        this.setTitle("demo");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(660,600);
        this.addKeyListener(keyBoardMonitor);
        this.setVisible(true);
    }


}
