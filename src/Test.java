import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Test {

    
    static List<photo> totalList = new ArrayList<>(); // 转型成接口列表，用于每次JPlane重画
    static List<Image> picList1 = new ArrayList<>();
    static List<Image> picListEnemy = new ArrayList<>();
    static Background background = new Background("src/resources/background1.jpg");
    static {
        totalList.add(background);
        picList1.add(Toolkit.getDefaultToolkit().getImage("src/resources/关羽_静止.gif"));
        picList1.add(Toolkit.getDefaultToolkit().getImage("src/resources/关羽_移动.gif"));
        picList1.add(Toolkit.getDefaultToolkit().getImage("src/resources/关羽_攻击.gif"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("src/resources/敌兵_静止.gif"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("src/resources/敌兵_移动.gif"));
        picListEnemy.add(Toolkit.getDefaultToolkit().getImage("src/resources/敌兵_攻击.gif"));
    }
    static Player player1 = new Player(1500, picList1, 500, 250, 170, 85, 10, 20);

    public static void main(String[] args) {
        ShowWelcomePage();
        ShowStageChoose();
        KeyBoardMonitor keyBoardMonitor = new KeyBoardMonitor();
        totalList.add(player1);
        DemoPlane demoPlane = new DemoPlane(totalList);
        Base base = new Base(demoPlane, keyBoardMonitor);
        // 显示背景，人物，血条等等

        // 更换坐标

        // 生成新敌人
        for (int i = 0; i <= 0; i++) {
            Enempy oneEnemy = new Enempy();
            EnemeyList.getInstance().EnemeyList.add(oneEnemy);
            totalList.add(oneEnemy);
        }

        while (true) {
            if (player1.curHP <= 0 || EnemeyList.getInstance().EnemeyList.size() <= 0) {
                demoPlane.removeThing(player1);
                System.out.println("game over");
                // 后续看看怎么让画布自动关掉
                break;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            for (int i=0;i<EnemeyList.getInstance().EnemeyList.size();i++)
            {
                double dist = Math.sqrt(Math.pow(player1.x-EnemeyList.getInstance().EnemeyList.get(i).x,2)+Math.pow(player1.y-EnemeyList.getInstance().EnemeyList.get(i).y,2));
                if(dist < 10){
                    EnemeyList.getInstance().EnemeyList.get(i).Attack(player1,1,1);
                    EnemeyList.getInstance().EnemeyList.get(i).SwitchImage(3);
                    try {
                        Thread.sleep(100);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                //System.out.println(EnemeyList.getInstance().EnemeyList.get(i).curNum);
                else EnemeyList.getInstance().EnemeyList.get(i).Move(player1,1,1);
            }
            demoPlane.repaint();
        }
    }

    public static void modifyPlayer(Command InputKey) {
        if (InputKey == Command.UP) {
            player1.MoveUp();
            player1.SwitchImage(2);
            return;
        }
        if (InputKey == Command.LEFT) {
            player1.MoveLeft();
            player1.SwitchImage(2);
            return;
        }
        if (InputKey == Command.DOWN) {
            player1.MoveDown();
            player1.SwitchImage(2);
            return;
        }
        if (InputKey == Command.RIGHT) {
            player1.MoveRight();
            player1.SwitchImage(2);
            return;
        }
        if (InputKey == Command.ATTACK) {
            player1.Attack(1, 30, 10);
            player1.SwitchImage(3);
        }
    }

    public static void ShowWelcomePage()
    {
        //大概就是一个开始的界面。
    }

    public static int ShowStageChoose()
    {
        //关卡选择界面
        return 1;
    }
}