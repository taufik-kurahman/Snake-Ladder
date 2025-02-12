package io.github.taufik.kurahman.snakeladder;

import java.util.Random;

public class DiceImpl implements IDice {
    private int diceCount;
    private int diceMaxValue;
    private static final Random random = new Random();

    public DiceImpl(int diceCount, int diceMaxValue) {
        this.diceCount = diceCount;
        this.diceMaxValue = diceMaxValue;
    }

    @Override
    public int roll() {
        int result = 0;
        for(int i = 0; i < diceCount; i++) {
            result += random.nextInt(diceMaxValue) + 1;
        }
        return result;
    }
}