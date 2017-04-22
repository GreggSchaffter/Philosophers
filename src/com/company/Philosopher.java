package com.company;

import java.util.concurrent.locks.Lock;

/**
 * Created by awsom on 4/21/2017.
 */
public class Philosopher extends Thread {
    private int id;
    public Philosopher(int id){
        this.id = id;
    }
    @Override
    public void run() {
        int thinktime;
        int eattime;
        int total = 0;
        thinktime = (int) Math.ceil(Math.random()*15);
        eattime = (int) Math.ceil(Math.random()*15);
        int chopstick1 = this.id;
        int chopstick2 = this.id + 1;
        Main app = new Main();
        if(chopstick2 == 5){
            chopstick2 = 0;
        }
        System.out.println("Philosopher " + id + " is thinking for " + thinktime + "s");
        while(total < 100) {
            try {
                sleep(thinktime*1000);
            }
            catch (InterruptedException e){
                System.out.println("Sleep failed");
            }
            System.out.println("-- Philosopher " + id + " getting first chopstick --");
            app.chopsticks[chopstick1].lock();
            System.out.println("-- Philosopher " + id + " getting second chopstick --");
            if(app.chopsticks[chopstick2].tryLock()){
                total += eattime;
                System.out.println("Philosopher " + id + " is eating for " + eattime + "s (total=" + total + ")");
                try {
                    sleep(eattime*1000);
                }
                catch (InterruptedException e){
                    System.out.println("Sleep failed");
                }
                thinktime = (int) Math.ceil(Math.random()*15);
                eattime = (int) Math.ceil(Math.random()*15);
                //System.out.println("-- Philosopher " + id + " done eating --");
                System.out.println("Philosopher " + id + " is thinking for " + thinktime + "s");
                app.chopsticks[chopstick1].unlock();
                app.chopsticks[chopstick2].unlock();
            }
            else{
                System.out.println("-- Philosopher " + id + " can't eat. Chopsticks being used --");
                app.chopsticks[chopstick1].unlock();
                System.out.println("Philosopher " + id + " is thinking for " + thinktime + "s");
                continue;
            }
        }
    }
}
