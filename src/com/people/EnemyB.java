package com.people;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class EnemyB extends Enemy{
    public static CopyOnWriteArrayList<Image> picListEnemy = new CopyOnWriteArrayList<>();
    static {
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E3_attack/E3_attack_0.png"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E3_attack/E3_attack_0.png"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E3_attack/E3_attack_1.png"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E3_attack/E3_attack_2.png"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E3_attack/E3_attack_3.png"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E3_attack/E3_attack_4.png"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E3_move/E3_move_0.png"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E3_move/E3_move_1.png"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E3_move/E3_move_2.png"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E3_move/E3_move_3.png"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E3_move/E3_move_4.png"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E3_move/E3_move_5.png"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E3_move/E3_move_6.png"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E3_move_L/E3_move_6.png"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E3_attack_L/E3_attack_0.png"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E3_attack_L/E3_attack_1.png"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E3_attack_L/E3_attack_2.png"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E3_attack_L/E3_attack_3.png"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E3_attack_L/E3_attack_4.png"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E3_move_L/E3_move_0.png"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E3_move_L/E3_move_1.png"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E3_move_L/E3_move_2.png"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E3_move_L/E3_move_3.png"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E3_move_L/E3_move_4.png"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E3_move_L/E3_move_5.png"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E3_move_L/E3_move_6.png"));
    }

    public EnemyB(){
        super(150,picListEnemy,((int)(Math.random() * 500)%2)*1000,(int)(Math.random() * 300)+200,250,150,4,1);
    }

    public EnemyB(int diff){
        super(100*diff,picListEnemy,((int)(Math.random() * 500)%2)*1000,(int)(Math.random() * 300)+200,250,150,4,(int)(diff*1.2+1));
    }

    public EnemyB(int HP, CopyOnWriteArrayList<Image> picList, int x, int y, int height, int width, int speed, int attack) {
        super(HP, picList, x, y, height, width,speed,attack);
    }


    @Override
    public void Move(Player a, int LImage, int RImage) {
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
        if(dis<22){
            this.setCurNum(Dir*divide+1);
            movx=0;
            movy=0;
        }
        this.x+=(int)movx;
        this.y+=(int)movy;
    }

    @Override
    public void Attack(Player a, int LImage, int RImage) {
        attackTimeStamp =  System.currentTimeMillis();
        double dis=Math.sqrt(Math.pow(a.x-this.x,2)+Math.pow(a.y-this.y,2));
        if(dis>22)
            return;
    }

    @Override
    public void AttackPostivie(Player a) {
        double dis=Math.sqrt(Math.pow(a.x-this.x,2)+Math.pow(a.y-this.y,2));
        if(dis<22)
            a.DieOrAlive(false,Attack);
    }
}

