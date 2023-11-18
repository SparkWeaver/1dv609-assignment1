package com.dicegame;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

import org.junit.Before;

public class DiceTest {

    private Dice dice;

    @Before
    public void setUp() {
        dice = new Dice();
    }
    
    @Test
    public void returnNumbersBetweenOneAndDefaultSizeOnDiceRoll() {
        final int defaultSize = 6;

        int result;
        for(int i = 0; i < 100; i++) {
            result = dice.roll();
            assertTrue(String.format("Dice roll should be between 1 and %d, but was %d", defaultSize, result),
                result >= 1 && result <=defaultSize);
        }
    }

    @Test
    public void theGetMethodReturnsTheSameValueAsRoll() {
        for(int i = 0; i < 100; i++) {
            assertTrue("Get value should be the same as the roll value", 
                dice.roll() == dice.getValue());
        }
    }

    @Test
    public void rollRandomlyAssignNewValueToDice() {
        int previousRoll = dice.roll();
        Boolean variation = false;

        int currentRoll;
        for(int i = 0; i < 100; i++) {
            currentRoll = dice.roll();
            if(currentRoll != previousRoll) {
               variation = true;
               break; 
            }
            previousRoll = currentRoll;
        }

        assertTrue("Dice roll should vary over multiple rolls", variation);
    }
}
