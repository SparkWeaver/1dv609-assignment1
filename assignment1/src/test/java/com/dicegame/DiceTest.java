package com.dicegame;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class DiceTest {
    
    @Test
    public void returnNumbersBetweenOneAndSixOnDiceRoll() {
        Dice dice = new Dice();

        int result;
        for(int i = 0; i < 100; i++) {
            result = dice.roll();
            assertTrue("Dice roll should be between 1 and 6", result >= 1 && result <=6);
        }
    }

    @Test
    public void returnNumbersBetweenOneAndSpecifiedValueOnDiceRoll() {
        int specifiedValue = 4;
        Dice dice = new Dice(specifiedValue);

        int result;
        for(int i = 0; i < 100; i++) {
            result = dice.roll();
            assertTrue(String.format("Dice roll should be between 1 and %d, but was %d", specifiedValue, result), 
                       result >= 1 && result <= specifiedValue);
        }
    }
}
