package com.people;

import com.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class Enemy extends People {
    public static CopyOnWriteArrayList<Image> picListEnemy = new CopyOnWriteArrayList<>();
    static {
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E1_attack/E1_attack_4.jpg"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E1_attack/E1_attack_0.jpg"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E1_attack/E1_attack_1.jpg"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E1_attack/E1_attack_2.jpg"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E1_attack/E1_attack_3.jpg"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E1_attack/E1_attack_4.jpg"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E1_move/E1_move_0.jpg"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E1_move/E1_move_1.jpg"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E1_move/E1_move_2.jpg"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E1_move/E1_move_3.jpg"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E1_move/E1_move_4.jpg"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E1_move/E1_move_5.jpg"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E1_move/E1_move_6.jpg"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E1_move_L/E1_move_6.png"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E1_attack_L/E1_attack_0.png"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E1_attack_L/E1_attack_1.png"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E1_attack_L/E1_attack_2.png"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E1_attack_L/E1_attack_3.png"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E1_attack_L/E1_attack_4.png"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E1_move_L/E1_move_0.png"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E1_move_L/E1_move_1.png"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E1_move_L/E1_move_2.png"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E1_move_L/E1_move_3.png"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E1_move_L/E1_move_4.png"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E1_move_L/E1_move_5.png"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E1_move_L/E1_move_6.png"));
    }

    public int attackpoint=0;
    public int movpoint=0;
    public int divide=13;
    public int speed=5;
    public long attackTimeStamp, moveTimeStamp;
    public Enemy(){
        super(40, picListEnemy,((int)(Math.random() * 500)%2)*1000,(int)(Math.random() * 300)+200, 130, 70, 1);
    }

    public Enemy(int Diff)
    {
        super(30*Diff, picListEnemy,((int)(Math.random() * 500)%2)*1000,(int)(Math.random() * 300)+200, 130, 70, 1+(int)(Diff*0.4));
    }

    public Enemy(int HP, CopyOnWriteArrayList<Image> picList, int x, int y, int height, int width, int speed, int attack) {
        super(HP, picList, x, y, height, width,attack);
        this.speed=speed;
    }

    public void Move(Player a, int LImage, int RImage){
        moveTimeStamp =  System.currentTimeMillis();
        if(a.x<this.x){
            this.Dir=1;//向左
    //        this.setCurNum(LImage);//左向图
        }
        else{
            this.Dir=0;
    //        this.setCurNum(RImage);
        }
        this.setCurNum(Dir*divide+7+movpoint);
        movpoint++;
        if(movpoint==7)
            movpoint=0;
        double dis=Math.sqrt(Math.pow(a.x-this.x,2)+Math.pow(a.y-this.y,2));
        double movx=speed/dis*(a.x-this.x);
        double movy=speed/dis*(a.y-this.y);
        if(dis<speed*2){
            this.setCurNum(Dir*divide+1);
            movx=0;
            movy=0;
        }
        this.x+=(int)movx;
        this.y+=(int)movy;
    }

    public void Attack(Player a,int LImage,int RImage){
        attackTimeStamp =  System.currentTimeMillis();
        double dis=Math.sqrt(Math.pow(a.x-this.x,2)+Math.pow(a.y-this.y,2));
        if(dis>a.speed*2)
            return;
      //  if(Dir==1)
      //      this.setCurNum(LImage);
      //  else if (Dir==0)
      //      this.setCurNum(RImage);
      //  a.DieOrAlive(false,Attack);
        //剩下的要考虑切换图片之类的先不写了
    }

    public void AttackPostivie(Player a){
        double dis=Math.sqrt(Math.pow(a.x-this.x,2)+Math.pow(a.y-this.y,2));
        if(dis<a.speed*2)
            a.DieOrAlive(false,Attack);
    }


}