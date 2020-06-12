package com.part;

import com.base.photo;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Point implements photo {
    private static final List<Image> imageList = new ArrayList<>();
    static {
        imageList.add(new ImageIcon("./resources/number0.jpg").getImage());
        imageList.add(new ImageIcon("./resources/number1.jpg").getImage());
        imageList.add(new ImageIcon("./resources/number2.jpg").getImage());
        imageList.add(new ImageIcon("./resources/number3.jpg").getImage());
        imageList.add(new ImageIcon("./resources/number4.jpg").getImage());
        imageList.add(new ImageIcon("./resources/number5.jpg").getImage());
        imageList.add(new ImageIcon("./resources/number6.jpg").getImage());
        imageList.add(new ImageIcon("./resources/number7.jpg").getImage());
        imageList.add(new ImageIcon("./resources/number8.jpg").getImage());
        imageList.add(new ImageIcon("./resources/number9.jpg").getImage());
    }
    private int y = 10, width = 25, height = 25;
    private int x,curNum;
    public Point(int x){
        this.x = x;
    }

    public void update(int num){
        curNum = num;
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
        return (Image) imageList.get(curNum);
    }
}
