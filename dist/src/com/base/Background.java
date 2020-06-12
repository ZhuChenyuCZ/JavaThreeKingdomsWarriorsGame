package com.base;

import javax.swing.*;
import java.awt.*;

public class Background implements photo {
    private Image image;
    private int x = 10,y = 20 ,width = 1100 ,height = 600;

    public Background(String fileName){
        image = new ImageIcon(fileName).getImage();
    }

    public void ChangeFile(String fileName)
    {
        //似乎这个没用
        image = new ImageIcon(fileName).getImage();
    }

    public void moveRight(int speed){
        if(this.x <= 0)
            this.x += speed;
        else this.x = -440;
    }

    public void moveLeft(int speed){
        if(this.x >= -440) // width*0.4*(-1)
            this.x -= speed;
        else this.x = -440;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public Image getImage(){
        return image;
    }

}
