package com.people;

import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.CopyOnWriteArrayList;

public class EnemyList {
    //单例模式
    public CopyOnWriteArrayList<Enemy> enemyList = new CopyOnWriteArrayList<>();
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
