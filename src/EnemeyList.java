import java.util.ArrayList;
import java.util.List;

public class EnemeyList {
    public List<Enempy> EnemeyList = new ArrayList<>();
    private static EnemeyList instance=new EnemeyList();
    private EnemeyList(){
    }

    public static EnemeyList getInstance(){
        return instance;
    }

    public void AddEnemey(Enempy x){
        EnemeyList.add(x);
    }

    public void Remove(Enempy y){
        EnemeyList.remove(y);
    }
}
