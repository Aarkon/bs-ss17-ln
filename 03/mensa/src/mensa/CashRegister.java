package mensa;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * A cash register, which is dealing with one student at a time
 */
public class CashRegister {

    private final String name;
    private Semaphore queue;
    private int maxPayTimeMillis = 1000;
    private  int queueLength = 0;

    /**
     * Instanciates a new cash register
     * @param name Name to describe the cash register
     */
    public CashRegister(String name) {
        this.name = name;
        this.queue = new Semaphore(1);
    }

    /**
     * Payment process of a student, which takes a random amount of time, defined by the field maxPayTimeMillis.
     * @param student The student who is paying
     */
    public void pay(Student student) {
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

    /**
     * @return The name of the cash register
     */
    public String getName()
    {
        return name;
    }

    /**
     * @return The length of the queue at the cash register including students currently in payment process.
     */
    public int getQueueLength()
    {
        return queueLength;
    }

    /**
     * method to increment the queue length by 1.
     */
    public synchronized void incrementQueueLength(Student student)
    {
			queueLength += 1;       
    }

    /**
     * method to decrement the queue length by 1.
     */
    public synchronized void decrementQueueLength(Student student)
    {
			queueLength -= 1;
    }
}
