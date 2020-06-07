import javax.swing.*;
import java.awt.*;

public class HpInfo implements photo{
    private int HP;
    private Image image;
    private int x, y, width = 170, height = 17;
    public HpInfo(String filename, int x, int y, int HP){
        image = new ImageIcon(filename).getImage();
        this.x = x;
        this.y = y;
        this.HP = HP;
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
        return image;
    }

}
