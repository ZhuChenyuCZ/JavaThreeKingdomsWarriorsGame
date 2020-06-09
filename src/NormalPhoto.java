import java.awt.*;
import java.util.List;

public class NormalPhoto implements photo
{
    protected List<Image> PicList;
    protected int x,y,height,width;
    protected int curNum=1;//从1开始

    @Override
    public Image getImage() {
        return PicList.get(curNum-1);
    }

    public boolean SwitchImage(int cur){
        if(cur>PicList.size()+1)
            return false;
        else if(cur<1)
            return false;
        curNum=cur;
        return true;
    }


    public NormalPhoto(){

    }

    public NormalPhoto(List<Image> picList,int x,int y,int height,int width)
    {
        PicList = picList;
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        curNum=1;
    }

    public void AddImage(Image a){
        PicList.add(a);
    }

    public void RemoveImage(Image a){
        PicList.remove(a);
    }

    public List<Image> getPicList() {
        return PicList;
    }

    public void setPicList(List<Image> picList) {
        PicList = picList;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getCurNum() {
        return curNum;
    }

    public void setCurNum(int curNum) {
        this.curNum = curNum;
    }
}
