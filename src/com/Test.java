package com;

import com.base.*;
import com.people.*;
import com.part.Point;
import com.part.Music;
import com.part.HpInfo;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class Test {
    public static CopyOnWriteArrayList<photo> totalList = new CopyOnWriteArrayList<>(); // 转型成接口列表，用于每次JPlane重画
    public final static Object lock = new Object();

    //特殊标注变量区
    static int LevelGlobal;
    static boolean MenuOpen=false;
    static int RestartSignal=0,QuitSignal=0;
    static int BeginChoice=0,DiffChoooseOp1=2,DiffChoooseOp2=0,DiffLevel=2,ImproveWhich;
    static int FanHuiNali=1,GoHome=0;

    //通过数组调节关卡难度
    static int[][] EnemyNumber = new int[4][8];
    static{
        EnemyNumber[1][1]=3;
        EnemyNumber[1][2]=4;
        EnemyNumber[1][3]=5;
        EnemyNumber[1][4]=5;
        EnemyNumber[1][5]=7;
        EnemyNumber[1][6]=8;
        EnemyNumber[1][7]=10;

        EnemyNumber[2][1]=5;
        EnemyNumber[2][2]=7;
        EnemyNumber[2][3]=8;
        EnemyNumber[2][4]=8;
        EnemyNumber[2][5]=9;
        EnemyNumber[2][6]=9;
        EnemyNumber[2][7]=10;

        EnemyNumber[3][1]=7;
        EnemyNumber[3][2]=7;
        EnemyNumber[3][3]=8;
        EnemyNumber[3][4]=8;
        EnemyNumber[3][5]=10;
        EnemyNumber[3][6]=9;
        EnemyNumber[3][7]=11;
    }

    static String[] backgroundList = new String[10];

    static{
        backgroundList[1] = "src/resources/b1.jpg";
        backgroundList[2] = "src/resources/background1.jpg";
        backgroundList[3] = "src/resources/background2.png";
        backgroundList[4] = "src/resources/background (3).png";
        backgroundList[5] = "src/resources/background (4).png";
        backgroundList[6] = "src/resources/background (5).png";
        backgroundList[7] = "src/resources/background (6).png";

        //暂时有七大背景
    }
    static Background background= new Background(backgroundList[1]);
    public static DemoPlane demoPlane = new DemoPlane(totalList);
    public static KeyBoardMonitor keyBoardMonitor = new KeyBoardMonitor();
    public static Base base = new Base(demoPlane, keyBoardMonitor);
    public static ExecutorService executor = Executors.newCachedThreadPool();

    //存储点数的展示
    public static Point[] points = new Point[5];
    public static Point[] points1 = new Point[5];

    //关于音乐
    static Music attackSound = new Music("src/resources/attack_1.wav", false);
    static{
        Music music = new Music("src/resources/background_music.wav", true);
        Thread daemon = new Thread(music);
        daemon.setDaemon(true);
        daemon.start();
    }

    //各类开始界面和小按钮
    public static Player player1 = new Player(14, Player.picList1, 20, 100, 150, 85, 10, 50);
    static NormalPhoto LogoPhoto = new NormalPhoto("src/resources/logo1.jpg",100,100,400,400);
    static NormalPhoto MenuPhoto = new NormalPhoto("src/resources/MenuPhoto.png",0,0,200,20);
    static NormalPhoto MenuOpenPhoto = new NormalPhoto("src/resources/MenuOpenPhoto.png",100,100,400,400);
    static NormalPhoto BeginMenuPhoto = new NormalPhoto("src/resources/BeginMenu.png",100,100,400,400);
    static NormalPhoto ByeWordPhoto = new NormalPhoto("src/resources/ByeWord.png",210,440,200,60);
    static NormalPhoto ReadyPhoto = new NormalPhoto("src/resources/readyPhoto.png",120,240,400,120);
    static NormalPhoto GoPhoto = new NormalPhoto("src/resources/GoPhoto.png",170,240,300,120);
    static NormalPhoto Hint1Photo = new NormalPhoto("src/resources/hint1.png",170,40,300,120);
    static NormalPhoto EasyNoPhoto = new NormalPhoto("src/resources/EasyNoPhoto.png",0,240,180,65);
    static NormalPhoto NormalNoPhoto = new NormalPhoto("src/resources/NormalNoPhoto.png",220,240,180,65);
    static NormalPhoto HardNoPhoto = new NormalPhoto("src/resources/HardNoPhoto.png",440,240,180,65);
    static NormalPhoto EasyYesPhoto = new NormalPhoto("src/resources/EasyYesPhoto.png",0,240,180,65);
    static NormalPhoto NormalYesPhoto = new NormalPhoto("src/resources/NormalYesPhoto.png",220,240,180,65);
    static NormalPhoto HardYesPhoto = new NormalPhoto("src/resources/HardYesPhoto.png",440,240,180,65);
    static NormalPhoto SuccessPhoto = new NormalPhoto("src/resources/SuccessPhoto.png",170,400,300,120);
    static NormalPhoto ISpeedPhoto = new NormalPhoto("src/resources/ISpeedPhoto.png",40,240,280,105);
    static NormalPhoto IAttackPhoto = new NormalPhoto("src/resources/IAttackPhoto.png",340,240,280,105);
    static NormalPhoto NISpeedPhoto = new NormalPhoto("src/resources/NISpeedPhoto.png",40,240,280,105);
    static NormalPhoto NIAttackPhoto = new NormalPhoto("src/resources/NIAttackPhoto.png",340,240,280,105);
    static NormalPhoto YourScorePhoto = new NormalPhoto("src/resources/YourScore.png",170,140,300,120);
    static NormalPhoto StoryPhoto = new NormalPhoto("src/resources/StoryPhoto.png",20,100,600,400);
    static NormalPhoto DeveloperPhoto = new NormalPhoto("src/resources/DeveloperPhoto.png",20,100,600,400);


    public static void main(String[] args) {
        while (FanHuiNali!=0)
        {
            //时刻检测是否会退出
            if (FanHuiNali==0) break;
            //开始界面展示
            ShowWelcomePage();
            if (FanHuiNali==0) break;
            BeginMenuChooseArea();
            if (FanHuiNali==0) break;
            if(player1.KillNumber!=0)
                ShowScore();
            if (FanHuiNali==0) break;
            System.out.println("FanHui:"+FanHuiNali);
        }

        ShowByePage();
        System.out.println("Game End.");
    }

    public static void BeginMenuChooseArea()
    {
        int next=1;
        int lastnext = 1;
        if (BeginChoice == 0) 
            return;
        if (BeginChoice == 1) 
        {
            //关卡选择
            ChooseGameDiff();
            Control control = new Control();
            executor.execute(control);
            while (true) {
                lastnext = next;
                next = StartToPlay(next);
                if (next == 0) {
                    break;
                }
                if (lastnext != next)
                    ImprovePointPage();
            }
            return;
        }
        if (BeginChoice == 2) {
            GoHome=0;
            ShowStory();
            GoHome=0;
            return;
        }
        if (BeginChoice == 3) {
            GoHome=0;
            ShowStory();
            GoHome=0;
            return;
        }
        if (BeginChoice == 4) {
            GoHome=0;
            ShowDeveloper();
            GoHome=0;
            return;
        }
        if (BeginChoice == 5) {
            return;
        }
    }

    public static void ShowWelcomePage() {
        RestartSignal=0;QuitSignal=0;
        BeginChoice=0;DiffChoooseOp1=2;DiffChoooseOp2=0;DiffLevel=2;
        FanHuiNali=1;GoHome=0;
        // 大概就是一个开始的界面。
        // 显示logo
        totalList.clear();
        demoPlane.removeAll();
        demoPlane.addThing(LogoPhoto);
        demoPlane.repaint();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        totalList.remove(LogoPhoto);
        demoPlane.repaint();

        // 选项界面
        totalList.clear();
        totalList.add(BeginMenuPhoto);
        demoPlane.repaint();

        BeginChoice = 0;
        while (BeginChoice == 0) {
            otherKeyBoardThing();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (BeginChoice != 0)
                break;

        }


    }

    public static int StartToPlay(int Level) {
        // 限制条件
        if (Level > 7 || Level < 1)
            return 0;

        // 清除原本内容
        demoPlane.removeAll();


        EnemyList.getInstance().enemyList.clear();
        //玩家初始值设定
        player1.curHP = player1.HP;
        player1.x =300;
        player1.y = 300;
        player1.height = 150;
        player1.width = 85;
        // 20, 100, 150, 85
        MenuOpen = false;
        RestartSignal = 0;
        QuitSignal = 0;// 清除菜单原本设置

        // 设置全局Level变量
        LevelGlobal = Level;
        totalList.add(MenuPhoto);

        demoPlane.addThing(background);
        demoPlane.addThing(player1);
        for(int i = 0; i < 5; ++i){
            points[i] = new Point(200 + i * 30);
            totalList.add(points[i]);
        }

        background.switchImage(backgroundList[LevelGlobal]);
        HpInfo PlayerHP = new HpInfo(1);
        totalList.add(player1);
        totalList.add(PlayerHP);

        // 显示背景，人物，血条等等

        // 更换坐标

        // 生成新敌人
        System.out.println("DiffLevel:" + DiffLevel);
        for (int i = 0; i < EnemyNumber[DiffLevel][Level]; i++) {
            //小兵生成
            Enemy oneEnemy = new Enemy(DiffLevel);
            EnemyList.getInstance().enemyList.add(oneEnemy);
            totalList.add(oneEnemy);
            if(i>4){
                //敌人A
                oneEnemy = new EnemyA(DiffLevel);
                EnemyList.getInstance().enemyList.add(oneEnemy);
                totalList.add(oneEnemy);
            }
            if(i>7){
                //敌人B
                oneEnemy = new EnemyB(DiffLevel);
                EnemyList.getInstance().enemyList.add(oneEnemy);
                totalList.add(oneEnemy);
            }
        }

        player1.update0(points);
        totalList.add(ReadyPhoto);
        demoPlane.repaint();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        totalList.remove(ReadyPhoto);
        demoPlane.repaint();

        totalList.add(GoPhoto);
        demoPlane.repaint();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        totalList.remove(GoPhoto);
        demoPlane.repaint();

        //属性加成
        if (ImproveWhich==1)
        {
            player1.speed=player1.speed+5;
        }
        else
        {
            player1.Attack=player1.Attack+20;
        }

        while (true) {
            otherKeyBoardThing();
            // 侦测有无菜单操作
            if (MenuOpen == true) {
                totalList.remove(MenuOpenPhoto);
                System.out.println("pause");
                totalList.add(MenuOpenPhoto);
                demoPlane.repaint();
                while (MenuOpen == true) {
                    otherKeyBoardThing();
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (MenuOpen == false)
                        break;
                }
                if (RestartSignal == 1) {
                    RestartSignal = 0;
                    return Level;
                }
                if (QuitSignal == 1) {
                    QuitSignal = 0;
                    return 0;
                }
                totalList.remove(MenuOpenPhoto);
                demoPlane.repaint();
            }

            // 侦测有无自动结束游戏的条件
            if (player1.curHP <= 0) {
                demoPlane.removeThing(player1);
                System.out.println("game over");
                return 0;
            }
            if (EnemyList.getInstance().enemyList.size() <= 0) {
                System.out.println("you win");
                return Level + 1;
            }
            modifyPlayer();
            PlayerHP.UpdateHP(player1.curHP);
            //对于攻击的帧数切换
            if (player1.attackpoint != 0) {
                player1.AttackSwitchPic();
            }
            player1.updateCnt();
            demoPlane.repaint();
             try {
                 Thread.sleep(80);
             } catch (InterruptedException e){
                 e.printStackTrace();
             }

            //线程锁的应用
            synchronized (lock) {
                lock.notifyAll();
            }
        }

    }

    //关卡切换
    public static void ChooseGameDiff() {

        DiffChoooseOp1 = 2;

        while (DiffChoooseOp2 == 0) {
            otherKeyBoardThing();
            if (DiffChoooseOp2 != 0)
                break;
            DiffLevel = DiffChoooseOp1;
            if (DiffChoooseOp1 == 1) {
                // System.out.println(DiffChoooseOp1);
                totalList.clear();
                totalList.add(Hint1Photo);
                totalList.add(EasyYesPhoto);
                totalList.add(NormalNoPhoto);
                totalList.add(HardNoPhoto);
                demoPlane.repaint();
            } else if (DiffChoooseOp1 == 2) {
                // System.out.println(DiffChoooseOp1);
                totalList.clear();
                totalList.add(Hint1Photo);
                totalList.add(EasyNoPhoto);
                totalList.add(NormalYesPhoto);
                totalList.add(HardNoPhoto);
                demoPlane.repaint();
            } else if (DiffChoooseOp1 == 3) {
                // System.out.println(DiffChoooseOp1);
                totalList.clear();
                totalList.add(Hint1Photo);
                totalList.add(EasyNoPhoto);
                totalList.add(NormalNoPhoto);
                totalList.add(HardYesPhoto);
                demoPlane.repaint();
            }
            try {
                Thread.sleep(70);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    //属性增加页面
    public static void ImprovePointPage()
    {

        DiffChoooseOp1 = 2;
        DiffChoooseOp2 = 0;

        totalList.add(SuccessPhoto);
        totalList.add(Hint1Photo);
        try {
        Thread.sleep(1000);
    } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }

        while (DiffChoooseOp2 == 0)
        {

            otherKeyBoardThing();
            if (DiffChoooseOp2!=0)
                break;
            ImproveWhich=DiffChoooseOp1;
            if (DiffChoooseOp1 == 1|| DiffChoooseOp1==3)
            {
                //System.out.println(DiffChoooseOp1);
                totalList.add(ISpeedPhoto);
                totalList.add(NIAttackPhoto);
                demoPlane.repaint();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                totalList.remove(ISpeedPhoto);
                totalList.remove(NIAttackPhoto);
            }
            else if (DiffChoooseOp1 == 2)
            {
                //System.out.println(DiffChoooseOp1);
                totalList.add(NISpeedPhoto);
                totalList.add(IAttackPhoto);
                demoPlane.repaint();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                totalList.remove(NISpeedPhoto);
                totalList.remove(IAttackPhoto);
            }
        }
        if (ImproveWhich==3)
        {
            ImproveWhich=1;
        }
        totalList.remove(SuccessPhoto);
        totalList.remove(Hint1Photo);
    }

    //结束页面展示
    public static void ShowByePage()
    {

        //显示logo
        totalList.clear();
        totalList.add(LogoPhoto);
        totalList.add(ByeWordPhoto);
        demoPlane.repaint();

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        totalList.remove(LogoPhoto);
        totalList.remove(ByeWordPhoto);
        demoPlane.repaint();
        System.exit(0);
    }

    //显示分数
    public static void ShowScore()
    {
        totalList.clear();
        for(int i = 0; i < 5; ++i){
            points1[i] = new Point(220 + i * 30,450);
            totalList.add(points1[i]);
        }
        totalList.add(YourScorePhoto);
        player1.update0(points1);
        demoPlane.repaint();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        player1.KillNumber=0;
    }

    //背景故事展示函数
    public static void ShowStory()
    {
        totalList.clear();
        totalList.add(StoryPhoto);
        demoPlane.repaint();
        while (GoHome == 0)
        {
            otherKeyBoardThing();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (GoHome!=0) break; 
        }
    }

    //开发人员展示
    public static void ShowDeveloper()
    {
        totalList.clear();
        totalList.add(DeveloperPhoto);
        demoPlane.repaint();
        while (GoHome == 0)
        {
            otherKeyBoardThing();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (GoHome!=0) break; 
        }
    }

    //开始页面键盘监听
    public static void otherKeyBoardThing(){
        if(Command.DOWN.use()){
            Test.BeginMenuControl(Command.DOWN);
        }
        else if(Command.LEFT.use()){
            Test.DiffChooseControl(Command.LEFT);
        }
        else if(Command.RIGHT.use()){
            Test.BeginMenuControl(Command.RIGHT);
            Test.DiffChooseControl(Command.RIGHT);
        }
        else if(Command.ATTACK.use()){
            Test.BeginMenuControl(Command.ATTACK);
            Test.DiffChooseControl(Command.ATTACK);
        }
        else if(Command.JUMP.use()){
            Test.BeginMenuControl(Command.JUMP);
        }
        else if(Command.MENU.use()){
            Test.MenuControl(Command.MENU);
        }
        else if(Command.BACK.use()){
            Test.MenuControl(Command.BACK);
        }
        else if(Command.PAUSE.use()){
            Test.MenuControl(Command.PAUSE);
        }
        else if(Command.RESTART.use()){
            Test.MenuControl(Command.RESTART);
        }
        else if(Command.QUIT.use()){
            Test.MenuControl(Command.QUIT);
        }
        else if(Command.END.use()){
            Test.BeginMenuControl(Command.END);
            System.out.println("OK to Fanhui");
        }
        else if(Command.HOME.use()){
            //Test.MenuControl(Command.HOME);
            GoHome=1;
            System.out.println("OK to GoHome");
        }
        
    }

    //游戏人物键盘监听
    public static void modifyPlayer() {
        boolean temp = false;
        //攻击未完成时不会进行其他动作
        if(player1.attackpoint != 0)
            return;
        if (Command.ATTACK.use()) {
            player1.Attack(1, 30, 50);
            player1.AttackSwitchPic();
            executor.execute(attackSound);
            return;
        }

        if (Command.UP.use()) {
            player1.MoveUp();
            temp = true;
            player1.MoveSwitchPic();
            //return;
        }
        if (Command.LEFT.use()) {
            temp = true;
            if(background.getX() <= 0)
            {
                player1.Dir=1;
                //通过对敌人和背景的移动实现横板展示
                background.moveRight(player1.speed);
                for(Enemy t:EnemyList.getInstance().enemyList){
                    t.x+=player1.speed;
                }
            }

            else player1.MoveLeft();
            player1.MoveSwitchPic();
        }
        if (Command.DOWN.use()) {
            temp = true;
            player1.MoveDown();
            player1.MoveSwitchPic();
        }
        if (Command.RIGHT.use()) {
            temp = true;
            if(player1.getX() <= 450)
                player1.MoveRight();
            else
            {
                player1.Dir=0;
                background.moveLeft(player1.speed);
                for(Enemy t:EnemyList.getInstance().enemyList){
                    t.x-=player1.speed;
                }
            }
            player1.MoveSwitchPic();
        }
    }


    //游戏菜单展示
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

    //开始界面标识符转换
    public static void BeginMenuControl(Command InputKey)
    {
        if (InputKey == Command.DOWN){
            BeginChoice=1;
            return;
        }
        if (InputKey == Command.JUMP){
            BeginChoice=2;
            return;
        }
        if (InputKey == Command.ATTACK){
            BeginChoice=3;
            return;
        }
        if (InputKey == Command.RIGHT){
            BeginChoice=4;
            return;
        }
        if(InputKey==Command.END){
            BeginChoice=5;
            FanHuiNali=0;
            return;
        }
    }

    //属性加成按键监听
    public static void DiffChooseControl(Command InputKey)
    {
        if (InputKey == Command.LEFT){
            DiffChoooseOp1=DiffChoooseOp1-1;
            if (DiffChoooseOp1 == 0) DiffChoooseOp1=3;
            //System.out.println(DiffChoooseOp1);
            return;
        }
        if (InputKey == Command.RIGHT){
            DiffChoooseOp1++;
            if (DiffChoooseOp1 == 4) DiffChoooseOp1=1;
            return;
        }
        if (InputKey == Command.ATTACK){
            DiffChoooseOp2=1;
            return;
        }
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}