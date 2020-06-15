package com.part;

import com.base.photo;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Point implements photo {
    //分数图片显示
    private static final List<Image> imageList = new ArrayList<>();
    static {
        imageList.add(new ImageIcon("src/resources/number0.jpg").getImage());
        imageList.add(new ImageIcon("src/resources/number1.jpg").getImage());
        imageList.add(new ImageIcon("src/resources/number2.jpg").getImage());
        imageList.add(new ImageIcon("src/resources/number3.jpg").getImage());
        imageList.add(new ImageIcon("src/resources/number4.jpg").getImage());
        imageList.add(new ImageIcon("src/resources/number5.jpg").getImage());
        imageList.add(new ImageIcon("src/resources/number6.jpg").getImage());
        imageList.add(new ImageIcon("src/resources/number7.jpg").getImage());
        imageList.add(new ImageIcon("src/resources/number8.jpg").getImage());
        imageList.add(new ImageIcon("src/resources/number9.jpg").getImage());
    }
    private int y = 18, width = 25, height = 25;
    private int x,curNum;
    public Point(int x){
        this.x = x;
    }

    public Point(int x,int y)
    {
        this.x=x; this.y=y;
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
