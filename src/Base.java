import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Base extends JFrame {
    private DemoPlane demoPlane;
    public Base(DemoPlane demoPlane, KeyBoardMonitor keyBoardMonitor){
        this.demoPlane = demoPlane;
        this.add(demoPlane);
        this.setTitle("demo");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200,700);
        this.addKeyListener(keyBoardMonitor);
        this.setVisible(true);
    }


}
