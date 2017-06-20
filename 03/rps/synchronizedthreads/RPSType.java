package rps.synchronizedthreads;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * An Enum being either rock, paper or scissor. Enhanced with a method to retrieve a random value of the range.
 */
public enum RPSType {
    ROCK, PAPER, SCISSOR;

    private static final List<RPSType> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final Random RANDOM = new Random();

    public synchronized static RPSType randomRockPaperScissor() {
        return VALUES.get(RANDOM.nextInt(VALUES.size()));
    }
}

