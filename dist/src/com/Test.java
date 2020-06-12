package com;

import com.base.*;
import com.people.Enemy;
import com.people.EnemyList;
import com.people.Player;
import com.part.Point;
import com.part.Music;
import com.part.HpInfo;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Test {
    
    public static List<photo> totalList = new ArrayList<>(); // 转型成接口列表，用于每次JPlane重画
    static List<Image> picList1 = new ArrayList<>();
    static Background background1 = new Background("src/resources/b1.jpg");

    //特殊标注变量区
    static int LevelGlobal;
    static boolean MenuOpen=false;
    static int RestartSignal=0,QuitSignal=0;

    static Background background[]= new Background[10];
    static{
        background[1] = new Background("src/resources/b1.jpg");
        background[2] = new Background("src/resources/background1.jpg");
        background[3] = new Background("src/resources/background2.png");
        background[4] = new Background("src/resources/background (3).png");
        background[5] = new Background("src/resources/background (4).png");
        background[6] = new Background("src/resources/background (5).png");
        background[7] = new Background("src/resources/background (6).png");

        //暂时有七大背景
    }

    //关于点数的这一段
    static Point[] points = new Point[5];
    static{
        for(int i = 0; i < 5; ++i){
            points[i] = new Point(200 + i * 30);
            totalList.add(points[i]);
        }
    }

    //关于音乐
    static Music attackSound = new Music("src/resources/attack_1.wav", false);
    static{
        Music music = new Music("src/resources/background_music.wav", true);
        Thread daemon = new Thread(music);
        daemon.setDaemon(true);
        daemon.start();
    }

    static {
        picList1.add(Toolkit.getDefaultToolkit().getImage("src/resources/关羽_静止.gif"));
        picList1.add(Toolkit.getDefaultToolkit().getImage("src/resources/关羽_移动.gif"));
        picList1.add(Toolkit.getDefaultToolkit().getImage("src/resources/关羽_攻击.gif"));

    }
    public static Player player1 = new Player(14, picList1, 20, 100, 150, 85, 10, 50);
    static NormalPhoto LogoPhoto = new NormalPhoto("src/resources/logo1.jpg",100,100,400,400);
    static NormalPhoto MenuPhoto = new NormalPhoto("src/resources/MenuPhoto.png",0,0,200,20);
    static NormalPhoto MenuOpenPhoto = new NormalPhoto("src/resources/MenuOpenPhoto.png",100,100,400,400);
    static NormalPhoto ByeWordPhoto = new NormalPhoto("src/resources/ByeWord.png",210,440,200,60);

    public static DemoPlane demoPlane = new DemoPlane(totalList);

    public static void main(String[] args) {
        int next=1;
        ShowWelcomePage();
        while (true)
        {
            next=StartToPlay(next);
            if (next==0) 
            {
                ShowByePage();
                break;
            }
        }
        System.out.println("Game End.");
    }

    public static void modifyPlayer(Command InputKey) {
        if (InputKey == Command.UP) {
            player1.MoveUp();
            player1.SwitchImage(2);
            return;
        }
        if (InputKey == Command.LEFT) {
            if(background[LevelGlobal].getX() <= 0)
                background[LevelGlobal].moveRight(player1.speed);
            else player1.MoveLeft();
            player1.SwitchImage(2);
            return;
        }
        if (InputKey == Command.DOWN) {
            player1.MoveDown();
            player1.SwitchImage(2);
            return;
        }
        if (InputKey == Command.RIGHT) {
            if(player1.getX() <= 450)
                player1.MoveRight();
            else background[LevelGlobal].moveLeft(player1.speed);
            player1.SwitchImage(2);
            return;
        }
        if (InputKey == Command.ATTACK) {
            player1.Attack(1, 30, 10);
            player1.SwitchImage(3);
            try {
                new Thread(attackSound).start();
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public static void ShowWelcomePage()
    {
        //大概就是一个开始的界面。
        KeyBoardMonitor keyBoardMonitor = new KeyBoardMonitor();
        //DemoPlane demoPlane = new DemoPlane(totalList);
        Base base = new Base(demoPlane, keyBoardMonitor);

        //显示logo
        totalList.clear();
        totalList.add(LogoPhoto);
        demoPlane.repaint();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        totalList.remove(LogoPhoto);
        demoPlane.repaint();
        //demoPlane.removeThing(LogoPhoto);
    }


    public static int StartToPlay(int Level)
    {
        //限制条件
        if (Level>7||Level<1) return 0;

        //清除原本内容
        totalList.clear();
        EnemyList.getInstance().enemyList.clear();
        player1.curHP=player1.HP;
        player1.x=20; player1.y=100; player1.height=150; player1.width=85;
        //20, 100, 150, 85
        MenuOpen=false; RestartSignal=0; QuitSignal=0;//清除菜单原本设置

        //设置全局Level变量
        LevelGlobal=Level;

        totalList.add(MenuPhoto);

        totalList.add(background[Level]);
        KeyBoardMonitor keyBoardMonitor = new KeyBoardMonitor();
        HpInfo PlayerHP=new HpInfo(1);
        totalList.add(player1);
        totalList.add(PlayerHP);
        for(int i = 0; i < 5; ++i){
            points[i] = new Point(200 + i * 30);
            totalList.add(points[i]);
        }
        //DemoPlane demoPlane = new DemoPlane(totalList);
        Base base = new Base(demoPlane, keyBoardMonitor);
        // 显示背景，人物，血条等等

        // 更换坐标

        // 生成新敌人
        for (int i = 0; i <= 4; i++) {
            Enemy oneEnemy = new Enemy();
            EnemyList.getInstance().enemyList.add(oneEnemy);
            totalList.add(oneEnemy);
        }

        player1.update0(points);
        demoPlane.repaint();

        /*try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/

        while (true) {
            //侦测有无菜单操作
            if (MenuOpen == true)
            {
                System.out.println("pause");
                totalList.add(MenuOpenPhoto);
                demoPlane.repaint();
                while (MenuOpen == true)
                {
                    //System.out.println("MenuOpen:"+MenuOpen);
                    try {
                        Thread.sleep(200);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    if (MenuOpen == false) break;
                }
                if (RestartSignal == 1)
                {
                    RestartSignal=0;
                    return Level;
                }
                if (QuitSignal == 1)
                {
                    QuitSignal=0;
                    return 0;
                }
                totalList.remove(MenuOpenPhoto);
                demoPlane.repaint();
            }

            //侦测有无自动结束游戏的条件
            if (player1.curHP <= 0) {
                demoPlane.removeThing(player1);
                System.out.println("game over");
                return 0;
                // 后续看看怎么让画布自动关掉
                //break;
            }
            if(EnemyList.getInstance().enemyList.size() <= 0){
                System.out.println("you win");
                return Level+1;
                //break;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            PlayerHP.UpdateHP(player1.curHP);
            List<Enemy> enemyList = EnemyList.getInstance().enemyList;
            Iterator<Enemy> iterator = enemyList.iterator();
            while (iterator.hasNext()){
                Enemy enemy = iterator.next();
                if (enemy.curHP <= 0) {
                    iterator.remove();
                    totalList.remove(enemy);
                    player1.update(points);
                    continue;
                }
                double dist = Math.sqrt(Math.pow(player1.x-enemy.x,2)+Math.pow(player1.y - enemy.y,2));
                if(enemy.attackpoint!=0){
                    enemy.SwitchImage(4+enemy.attackpoint);
                    enemy.attackpoint++;
                    if(enemy.attackpoint==5)
                    {
                        enemy.attackpoint=0;
                        try {
                            Thread.sleep(1000);
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }


                  /*try {
                       Thread.sleep(150);
                   }catch (InterruptedException e){
                       e.printStackTrace();
                   }*/
                }
                else if(dist<enemy.speed)
                {
                    enemy.Attack(player1,1,1);
                    enemy.SwitchImage(4);
                    enemy.attackpoint++;
                }
                else enemy.Move(player1,1,1);
            }
            player1.updateCnt();
            demoPlane.repaint();
        }

        //return 0;
    }

    public static void ShowByePage()
    {
        //大概就是一个开始的界面。
        KeyBoardMonitor keyBoardMonitor = new KeyBoardMonitor();
        //DemoPlane demoPlane = new DemoPlane(totalList);
        Base base = new Base(demoPlane, keyBoardMonitor);

        //显示logo
        totalList.clear();
        totalList.add(LogoPhoto);
        totalList.add(ByeWordPhoto);
        demoPlane.repaint();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        totalList.remove(LogoPhoto);
        totalList.remove(ByeWordPhoto);
        demoPlane.repaint();
        //demoPlane.removeThing(LogoPhoto);
    }


    public static void MenuControl(Command InputKey)
    {
        if (InputKey == Command.MENU){
            if (MenuOpen == false) MenuOpen = true;
        }
        if (MenuOpen == false) return;
        if (InputKey == Command.BACK){
            MenuOpen = false;
            System.out.println("Out Menu");
            return;
        }
        if (InputKey == Command.RESTART){
            MenuOpen = false;
            RestartSignal = 1;
            return;
        }
        if (InputKey == Command.QUIT){
            MenuOpen = false;
            QuitSignal = 1;
            return;
        }
    }
}