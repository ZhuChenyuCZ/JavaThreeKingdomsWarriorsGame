package com.part;

import com.base.photo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class HpInfo implements photo {
    //血条调用photo接口进行图片显示
    public static List<Image> PlayerHPpicList = new ArrayList<>();
    static {
        PlayerHPpicList.add(Toolkit.getDefaultToolkit().getImage("src/resources/HP/HP_FULL.jpg"));
        PlayerHPpicList.add(Toolkit.getDefaultToolkit().getImage("src/resources/HP/HP_1.jpg"));
        PlayerHPpicList.add(Toolkit.getDefaultToolkit().getImage("src/resources/HP/HP_2.jpg"));
        PlayerHPpicList.add(Toolkit.getDefaultToolkit().getImage("src/resources/HP/HP_3.jpg"));
        PlayerHPpicList.add(Toolkit.getDefaultToolkit().getImage("src/resources/HP/HP_4.jpg"));
        PlayerHPpicList.add(Toolkit.getDefaultToolkit().getImage("src/resources/HP/HP_5.jpg"));
        PlayerHPpicList.add(Toolkit.getDefaultToolkit().getImage("src/resources/HP/HP_6.jpg"));
        PlayerHPpicList.add(Toolkit.getDefaultToolkit().getImage("src/resources/HP/HP_7.jpg"));
        PlayerHPpicList.add(Toolkit.getDefaultToolkit().getImage("src/resources/HP/HP_8.jpg"));
        PlayerHPpicList.add(Toolkit.getDefaultToolkit().getImage("src/resources/HP/HP_9.jpg"));
        PlayerHPpicList.add(Toolkit.getDefaultToolkit().getImage("src/resources/HP/HP_10.jpg"));
        PlayerHPpicList.add(Toolkit.getDefaultToolkit().getImage("src/resources/HP/HP_11.jpg"));
        PlayerHPpicList.add(Toolkit.getDefaultToolkit().getImage("src/resources/HP/HP_12.jpg"));
        PlayerHPpicList.add(Toolkit.getDefaultToolkit().getImage("src/resources/HP/HP_13.jpg"));
        PlayerHPpicList.add(Toolkit.getDefaultToolkit().getImage("src/resources/HP/HP_ZERO.jpg"));
    }
    //后续增加
    private Image image;
    private int x, y, width = 170, height = 17;
    private int HP;
    private int curNum;
    private int mode;//1是玩家，2是敌人1,后续可在添加
    public HpInfo(int a){
        mode=a;
        if(mode==1){
            HP=14;
            this.x = 20;
            this.y = 20;
        }

    }

    public void UpdateHP(int t){
        HP=t;
        curNum=14-t;
    }

    @Override
    public Image getImage(){
        return PlayerHPpicList.get(curNum);
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



}