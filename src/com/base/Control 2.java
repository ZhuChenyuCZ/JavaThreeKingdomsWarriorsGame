package com.base;

import com.people.*;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.Test.player1;
import static com.Test.demoPlane;
import static com.Test.points;
import static com.Test.lock;

public class Control extends Thread {

    @Override
    public void run() {
        while (true){
            synchronized (lock){
                CopyOnWriteArrayList<Enemy> enemyList = EnemyList.getInstance().enemyList;
                Iterator<Enemy> iterator = enemyList.iterator();
                while (iterator.hasNext())
                {
                    Enemy enemy = iterator.next();
                    if (enemy.curHP <= 0)
                    {
                        enemyList.remove(enemy);
                        demoPlane.removeThing(enemy);
                        player1.update(points);
                        continue;
                    }
                    double dist = Math.sqrt(Math.pow(player1.x-enemy.x,2)+Math.pow(player1.y - enemy.y,2));
                    if(enemy.attackpoint!=0)
                    {
                        enemy.SwitchImage(enemy.Dir*enemy.divide+2+enemy.attackpoint);
                        enemy.attackpoint++;
                        if(enemy.attackpoint==5)
                        {
                            enemy.attackpoint=0;
                            enemy.AttackPostivie(player1);
                        }
                    }
                    else if(dist < enemy.speed && System.currentTimeMillis() - enemy.attackTimeStamp > 1000)
                    {
                        enemy.Attack(player1,1,1);
                        enemy.SwitchImage(enemy.Dir*enemy.divide+2);
                        enemy.attackpoint++;
                        lock.notifyAll();
                    }
                    else if(System.currentTimeMillis() - enemy.moveTimeStamp > 120)
                    {
                        enemy.Move(player1,1,1);
                    }
                }
                lock.notifyAll();
                try {
                    Thread.sleep(1);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                try {
                    lock.wait();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }
}