import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoardMonitor implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {
        keyPressed(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e != null){
            if(KeyEvent.VK_W == e.getKeyCode()){
                Test.modifyPlayer(Command.UP);
            }
            else if(KeyEvent.VK_S == e.getKeyCode()){
                Test.modifyPlayer(Command.DOWN);
            }
            else if(KeyEvent.VK_A == e.getKeyCode()){
                Test.modifyPlayer(Command.LEFT);
            }
            else if(KeyEvent.VK_D == e.getKeyCode()){
                Test.modifyPlayer(Command.RIGHT);
            }
            else if(KeyEvent.VK_J == e.getKeyCode()){
                Test.modifyPlayer(Command.ATTACK);
            }
            else if(KeyEvent.VK_K == e.getKeyCode()){
                Test.modifyPlayer(Command.JUMP);
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        Test.player1.SwitchImage(1);
    }
}
