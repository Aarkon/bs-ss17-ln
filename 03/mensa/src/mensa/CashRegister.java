package mensa;

import java.util.concurrent.Semaphore;

/**
 * Created by jakob on 16.05.17.
 */
public class CashRegister {

    private final String name;
    private Semaphore queue;

    public CashRegister(String name) {
        this.name = name;
        this.queue = new Semaphore(1);
    }

    void pay(Student student) {

    }

    int getQueueLength(){
        return queue.getQueueLength();
    }
}
