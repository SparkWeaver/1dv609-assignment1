package com.dicegame;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class DiceTest {
    
    @Test
    public void returnNumbersBetweenOneAndDefaultSizeOnDiceRoll() {
        int defaultSize = 6;
        Dice defaultDice = new Dice();
        String message = String.format("Dice roll should be between 1 and %d, but was ", defaultSize);

        int result;
        for(int i = 0; i < 100; i++) {
            result = defaultDice.roll();
            assertTrue(message + result, result >= 1 && result <=defaultSize);
        }
    }

    @Test
    public void returnNumbersBetweenOneAndSpecifiedSizeOnDiceRoll() {
        int specifiedSize = 21;
        Dice dice = new Dice(specifiedSize);
        String message = String.format("Dice roll should be between 1 and %d, but was ", specifiedSize);

        int result;
        for(int i = 0; i < 100; i++) {
            result = dice.roll();
            assertTrue(message + result, result >= 1 && result <= specifiedSize);
        }
    }

    @Test
    public void theGetMethodReturnsTheSameValueAsRoll() {
        int size = 21;
        Dice dice = new Dice(size);
        String message = "Get value should be the same as the roll value";

        int result;
        for(int i = 0; i < 100; i++) {
            result = dice.roll();
            assertTrue(message, result == dice.getValue());
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorThrowExceptionForDiceSizeSmallerThanFour() {
        new Dice(2);
    }
}
