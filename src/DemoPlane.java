import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DemoPlane extends JPanel {
    private List<photo> list;
    public DemoPlane(List <photo> list){
        this.list = list;
    }
    @Override
    public void paint(Graphics graphics){
        super.paint(graphics);
        for(photo p : list){
            graphics.drawImage(p.getImage(), p.getX(), p.getY(), p.getWidth() ,p.getHeight(), null);
        }
    }

    public void addThing(photo thing){
        list.add(thing);
    }

    public void removeThing(photo thing){
        //list.removeIf(photo -> photo == thing);
        for(int i = 0; i < list.size(); ++i){
            if(list.get(i)  == thing)
                list.remove(i);
        }
    }
}
