package mensa;

import java.util.List;

/**
 * Created by jakob on 16.05.17.
 */
public class Mensa {
    private List<CashRegister> cashRegisters;

    public Mensa(int number) {
        for (int i = 1; i <= number; i++) {

            cashRegisters.add(new CashRegister("CR " + i));
        }
    }

    public List<CashRegister> getCashRegisters() {
        return cashRegisters;
    }


}
