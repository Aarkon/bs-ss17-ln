package mensa;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * Created by jakob on 16.05.17.
 */
public class CashRegister {

    private final String name;
    private Semaphore queue;
    private int maxPayTimeMillis = 1000;
    private int queueLength = 0;

    public CashRegister(String name) {
        this.name = name;
        this.queue = new Semaphore(1);
    }

    void pay(Student student) {
        try {
            System.out.println(student.getStudentName() + " hat sich an " + name + " angestellt.");
            queue.acquire(); // Kassenressource wird belegt
            Random ran = new Random();
            Thread.sleep(ran.nextInt(maxPayTimeMillis)); // Bezahlvorgang maximal 2 Sekunden
            queue.release(); // Ressource an Kasse wird freigegeben
        } catch (InterruptedException e) {
            student.interrupt();
            queue.release();
        }




    }

    int getQueueLength2(){
        return queue.getQueueLength();
    }

    public String getName()
    {
        return name;
    }

    public int getQueueLength()
    {
        return queueLength;
    }

    public void incrementQueueLength()
    {
        queueLength += 1;
    }

    public void decrementQueueLength()
    {
        queueLength -= 1;
    }
}
