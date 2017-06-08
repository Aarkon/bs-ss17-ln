package mensa;

import java.util.LinkedList;
import java.util.List;

/**
 * A mensa with a variable amount of cash registers.
 */
public class Mensa {
    private List<CashRegister> cashRegisters = new LinkedList<CashRegister>();

    public Mensa(int number) {

        for (int i = 1; i <= number; i++) {

            cashRegisters.add(new CashRegister("CR " + i));
        }
    }

    /**
     * @return A list of the cash registers inside of the mensa.
     */
    public List<CashRegister> getCashRegisters() {
        return cashRegisters;
    }


}
