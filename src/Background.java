import javax.swing.*;
import java.awt.*;

public class Background implements photo {
    private Image image;
    private int x = 10,y = 20 ,width = 1100 ,height = 600;

    public Background(String fileName){
        image = new ImageIcon(fileName).getImage();
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
