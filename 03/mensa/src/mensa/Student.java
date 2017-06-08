package mensa;

import java.util.Random;

/**
 * Created by jakob on 16.05.17.
 */
public class Student extends Thread {
    private final String name;
    private final Mensa mensa;

    public Student(String name, Mensa mensa) {
        this.name = name;
        this.mensa = mensa;
    }

    private CashRegister chooseRegister(Mensa mensa) {
        CashRegister min = mensa.getCashRegisters().get(0);
        for(CashRegister cr : mensa.getCashRegisters())
        {
            if (cr.getQueueLength() < min.getQueueLength())
            {
                min = cr;
            }
        }
        min.incrementQueueLength();
        return min;
    }

    public void run()
    {
        while(!isInterrupted())
        {
            chooseFood();
            CashRegister currentCR = chooseRegister(mensa);
            currentCR.pay(this);
            currentCR.decrementQueueLength();
            System.out.println(name + " hat an " + currentCR.getName() + " bezahlt.");
            eat();
        }
    }

    void eat() {
        try {
            int maxEatTimeMilis = 500;
            Random ran = new Random();
            Thread.sleep(ran.nextInt(maxEatTimeMilis));
            System.out.println(name + " hat gegessen.");
        }
        catch(InterruptedException e)
        {
            this.interrupt();
        }
    }

    private void chooseFood()
    {
        Random ran = new Random();

        try {
            Thread.sleep(ran.nextInt(500));
        } catch (InterruptedException e) {
            this.interrupt();
        }
    }

    public String getStudentName()
    {
        return name;
    }
}
