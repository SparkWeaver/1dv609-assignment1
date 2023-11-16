package com.dicegame;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class DiceTest {
    
    @Test
    public void returnNumbersBetweenOneAndDefaultSizeOnDiceRoll() {
        int defaultSize = 6;
        Dice defaultDice = new Dice();

        int result;
        for(int i = 0; i < 100; i++) {
            result = defaultDice.roll();
            assertTrue(String.format("Dice roll should be between 1 and %d, but was %d", defaultSize, result),
                result >= 1 && result <=defaultSize);
        }
    }

    @Test
    public void returnNumbersBetweenOneAndSpecifiedSizeOnDiceRoll() {
        int specifiedSize = 21;
        Dice dice = new Dice(specifiedSize);

        int result;
        for(int i = 0; i < 100; i++) {
            result = dice.roll();
            assertTrue( String.format("Dice roll should be between 1 and %d, but was %d", specifiedSize, result),
                result >= 1 && result <= specifiedSize);
        }
    }

    @Test
    public void theGetMethodReturnsTheSameValueAsRoll() {
        Dice dice = new Dice();

        for(int i = 0; i < 100; i++) {
            assertTrue("Get value should be the same as the roll value", 
                dice.roll() == dice.value());
        }
    }

    @Test
    public void constructorThrowExceptionForDiceSizeSmallerThanFour() {
        try {
            new Dice(2);
            fail("Constructor did not throw an IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            //Passes
        }
    }

    @Test
    public void rollRandomlyAssignNewValueToDice() {
        Dice dice = new Dice();
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
