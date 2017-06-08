package mensa;

import java.util.Random;

import javax.swing.text.StyledEditorKit.ForegroundAction;

/**
 * This class models student who visit a mensa to buy and consume food.
 */
public class Student extends Thread {
    private final String name;
    private final Mensa mensa;
    private final int foodChooseTime = 500;
    private final int maxEatTimeMilis = 500;

    /**
     * Instances a student.
     * @param name The name of the studnet
     * @param mensa The mensa the student is visiting
     */
    public Student(String name, Mensa mensa) {
        this.name = name;
        this.mensa = mensa;
    }

    /**
     * Method to find the cash register with the shortest queue
     * @param mensa mensa where a cash register should be found
     * @return cash register with the shortest queue
     */
    private CashRegister chooseRegister(Mensa mensa) {
        CashRegister min = mensa.getCashRegisters().get(0);
        for(CashRegister cr : mensa.getCashRegisters())
        {
            if (cr.getQueueLength() < min.getQueueLength())
            {
                min = cr;
            }
        }
        //TODO queue schützen
        min.incrementQueueLength(this);
        return min;
    }

    /**
     * Initiates the student Thread
     * Simulates the behaviour of a student in a mensa:
     * 1. choose food
     * 2. choose the cash register with the shortest queue
     * 3. pay for food
     * 4. eat food
     * 5. repeat until mensa is closed :-)
     */
    public void run()
    {
        while(!isInterrupted())
        {
            chooseFood();
            CashRegister currentCR = chooseRegister(mensa);
            currentCR.pay(this);
            //TODO
            currentCR.decrementQueueLength(this);
            System.out.println(name + " hat an " + currentCR.getName() + " bezahlt.");
            eat();
        }
        System.out.println(name + " hat die Mensa verlassen");
    }

    /**
     * This method simulates the eating process of a student and prints a message to the console,
     * whenever the student has finished eating
     */
    private void eat() {
        try {
            Random ran = new Random();
            Thread.sleep(ran.nextInt(maxEatTimeMilis));
            System.out.println(name + " hat gegessen.");
        }
        catch(InterruptedException e)
        {
            this.interrupt();
        }
    }

    /**
     * This method simulates the process of choosing food.
     */
    private void chooseFood()
    {
        Random ran = new Random();

        try {
            Thread.sleep(ran.nextInt(foodChooseTime));
        } catch (InterruptedException e) {
            this.interrupt();
        }
    }

    /**
     * @return the name of the student
     */
    public String getStudentName()
    {
        return name;
    }
}
