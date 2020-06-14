package com.people;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class EnemyA extends Enemy{
    public static CopyOnWriteArrayList<Image> picListEnemy = new CopyOnWriteArrayList<>();
    static {
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E2_attack/E2_attack_0.png"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E2_attack/E2_attack_0.png"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E2_attack/E2_attack_1.png"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E2_attack/E2_attack_2.png"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E2_attack/E2_attack_3.png"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E2_attack/E2_attack_4.png"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E2_move/E2_move_0.png"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E2_move/E2_move_1.png"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E2_move/E2_move_2.png"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E2_move/E2_move_3.png"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E2_move/E2_move_4.png"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E2_move/E2_move_5.png"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E2_move/E2_move_6.png"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E2_move_L/E2_move_6.png"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E2_attack_L/E2_attack_0.png"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E2_attack_L/E2_attack_1.png"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E2_attack_L/E2_attack_2.png"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E2_attack_L/E2_attack_3.png"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E2_attack_L/E2_attack_4.png"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E2_move_L/E2_move_0.png"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E2_move_L/E2_move_1.png"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E2_move_L/E2_move_2.png"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E2_move_L/E2_move_3.png"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E2_move_L/E2_move_4.png"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E2_move_L/E2_move_5.png"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("./resources/E2_move_L/E2_move_6.png"));
    }

    public EnemyA(){
        super(60,picListEnemy,((int)(Math.random() * 500)%2)*1000,(int)(Math.random() * 300)+200,140,90,6,1);
    }

    public EnemyA(int diff){
        super(60*diff,picListEnemy,((int)(Math.random() * 500)%2)*1000,(int)(Math.random() * 300)+200,140,90,6,(int)(diff*1));
    }

    public EnemyA(int HP, CopyOnWriteArrayList<Image> picList, int x, int y, int height, int width, int speed, int attack) {
        super(HP, picList, x, y, height, width,speed,attack);
    }
}
