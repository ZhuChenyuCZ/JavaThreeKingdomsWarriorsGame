package com.part;

import com.base.*;
import com.Test;

import javax.swing.*;
import java.awt.*;

public class Level implements photo{
    private static final Image image = new ImageIcon("src/resources/升级.jpg").getImage();

    private final static int x = 250, y = 300, width = 100, height = 100;
    private final static int[] levelRage = {10, 20, 30, 50, 80, 120};
    private int experience, level, cnt;
    public Level(){
        this.experience = 0;
        this.level = 0;
        this.cnt = 0;
    }

    public void updateCnt(){
        ++ cnt;
        if(cnt == 5){
            cnt = 0;
        }
    }

    public void update(int type){
        switch (type){
            case 0:
                experience += 10; // 小兵
                break;
            case 1:
                experience += 50; // Boss
                break;
        }
        if(experience > levelRage[level]){
            for(int i = 0;i < 5; ++ i){
                if(level >= levelRage[i])
                    level = i;
                else break;
            }
        }
    }

    @Override
    public int getX()
    {
        return x;
    }

    @Override
    public int getY()
    {
        return y;
    }

    @Override
    public int getWidth()
    {
        return width;
    }

    @Override
    public int getHeight()
    {
        return height;
    }

    @Override
    public Image getImage()
    {
        return image;
    }
}