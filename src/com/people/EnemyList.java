package com.people;

import java.util.ArrayList;
import java.util.List;

public class EnemyList {
    public List<Enemy> enemyList = new ArrayList<>();
    private static final EnemyList instance=new EnemyList();
    private EnemyList(){
    }

    public static EnemyList getInstance(){
        return instance;
    }

    public void AddEnemy(Enemy x){
        enemyList.add(x);
    }

    public void Remove(Enemy y){
        enemyList.remove(y);
    }
}
