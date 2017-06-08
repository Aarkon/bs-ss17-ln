package mensa;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by jakob on 16.05.17.
 */
public class Mensa {
    private List<CashRegister> cashRegisters = new LinkedList<CashRegister>();

    public Mensa(int number) {

        for (int i = 1; i <= number; i++) {

            cashRegisters.add(new CashRegister("CR " + i));
        }
    }

    public List<CashRegister> getCashRegisters() {
        return cashRegisters;
    }


}
