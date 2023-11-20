package com.dicegame;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class RuleTest {

    private Rule rule;
    private int scoreLimit;

    @Before
    public void setUp() {
        scoreLimit = 21;
        rule = new Rule(scoreLimit);
    }

    @Test
    public void getScoreLimitReturnTheScoreLimit() {
        assertEquals(scoreLimit, rule.getScoreLimit());
    }

    @Test
    public void testRollAgainDecision() {
        assertEquals(Rule.Decision.ROLL, rule.decideAction(5, 5));
    }

    @Test
    public void testStayDecision() {
        assertEquals(Rule.Decision.STAY, rule.decideAction(10, 5));
    }

    @Test
    public void testHoldDecision() {
        assertEquals(Rule.Decision.HOLD, rule.decideAction(10, 9));
    }

    @Test
    public void testBustDecision() {
        assertEquals(Rule.Decision.BUST, rule.decideAction(10, 12));
    }

    @Test
    public void isGameOverShouldReturnTrueIfAllPlayersAreNonActive(){
        Player player1 = Mockito.mock(Player.class);
        Player player2 = Mockito.mock(Player.class);

        when(player1.getState()).thenReturn(Player.State.NON_ACTIVE);
        when(player2.getState()).thenReturn(Player.State.NON_ACTIVE);

        assertTrue(rule.isGameOver(Arrays.asList(player1, player2)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void decideActionShouldThrowExceptionPlayerScoreIsNegative() {
        rule.decideAction(-1, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void decideActionShouldThrowExceptionDiceValueIsNegative() {
        rule.decideAction(2, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void decideActionShouldThrowExceptionIfBothInputsAreNegative() {
        rule.decideAction(-1, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorShouldThrowExceptionWhenProvidedScoreLimitLessThanTwo() {
        new Rule(1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void isGameOverShouldThrowExceptionIfPlayerListIsEmpty () {
        rule.isGameOver(Arrays.asList());
    }

    @Test(expected = IllegalArgumentException.class)
    public void isGameOverShouldThrowExceptionIfPlayerListIsNull () {
        rule.isGameOver(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void determineWinnerThrowExceptionIfPlayerListIsEmpty () {
        rule.determineWinner(Arrays.asList());
    }
}
