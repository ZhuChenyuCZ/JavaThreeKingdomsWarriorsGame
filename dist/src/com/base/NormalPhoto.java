package com.base;

import javax.swing.*;
import java.awt.*;

public class NormalPhoto implements photo {
    private Image image;
    private int x ,y ,width  ,height;

    public NormalPhoto(String fileName,int x,int y,int width,int height)
    {
        image = new ImageIcon(fileName).getImage();
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
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