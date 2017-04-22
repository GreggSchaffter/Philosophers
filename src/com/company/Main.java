package com.company;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static Lock[] chopsticks = new Lock[5];
    public static void main(String[] args) {
        Philosopher[] phils = new Philosopher[5];
        for(int i = 0; i < phils.length; i++){
            if(i < 4) {
                chopsticks[i] = new ReentrantLock();
                chopsticks[i + 1] = new ReentrantLock();
                phils[i] = new Philosopher(i);
                phils[i].start();
            }
            else {
                chopsticks[i] = new ReentrantLock();
                phils[i] = new Philosopher(i);
                phils[i].start();
            }
        }
    }
}
