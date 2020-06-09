import java.awt.*;
import java.util.List;

public class Enempy extends People{
    protected int speed=5;
    public Enempy(){
        super(40,Test.picListEnemy,0,(int)(Math.random() * 50), 170, 85, 40);
    }

    public Enempy(int HP, List<Image> picList, int x, int y, int height, int width,int speed,int attack) {
        super(HP, picList, x, y, height, width,attack);
        this.speed=speed;
    }

    public void Move(Player a,int LImage,int RImage){
        if(a.x<this.x){
            this.Dir=1;//向左
            this.setCurNum(LImage);//左向图
        }
        else{
            this.Dir=0;
            this.setCurNum(RImage);
        }
        double dis=Math.sqrt(Math.pow(a.x-this.x,2)+Math.pow(a.y-this.y,2));
        double movx=speed/dis*(a.x-this.x);
        double movy=speed/dis*(a.y-this.y);
        if(dis<speed){
            movx=0;
            movy=0;
        }
        this.x+=(int)movx;
        this.y+=(int)movy;
    }

    public void Attack(Player a,int LImage,int RImage){
        double dis=Math.sqrt(Math.pow(a.x-this.x,2)+Math.pow(a.y-this.y,2));
        if(dis>speed)
            return;
        if(Dir==1)
            this.setCurNum(LImage);
        else if (Dir==0)
            this.setCurNum(RImage);
        a.DieOrAlive(false,AttackPoint);
        //剩下的要考虑切换图片之类的先不写了
    }


}
