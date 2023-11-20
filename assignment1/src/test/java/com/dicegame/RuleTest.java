package com.dicegame;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class RuleTest {

    private Rule rule;
    private int scoreLimit;
    private Player player1;
    private Player player2;
    private Player player3;
    private List<Player> players;

    @Before
    public void setUp() {
        scoreLimit = 21;
        rule = new Rule(scoreLimit);
        player1 = Mockito.mock(Player.class);
        player2 = Mockito.mock(Player.class);
        player3 = Mockito.mock(Player.class);
        players = Arrays.asList(player1, player2, player3);
    }

    @Test
    public void getScoreLimitReturnTheScoreLimit() {
        assertEquals(scoreLimit, rule.getScoreLimit());
    }

    @Test
    public void testRollAgainDecision() {
        assertEquals(Rule.Action.ROLL, rule.decideAction(5, 5));
    }

    @Test
    public void testStayDecision() {
        assertEquals(Rule.Action.STAY, rule.decideAction(10, 5));
    }

    @Test
    public void testHoldDecision() {
        assertEquals(Rule.Action.HOLD, rule.decideAction(10, 9));
    }

    @Test
    public void testBustDecision() {
        assertEquals(Rule.Action.BUST, rule.decideAction(10, 12));
    }

    @Test
    public void isGameOverShouldReturnTrueIfAllPlayersAreNonActive(){

        when(player1.getState()).thenReturn(Player.State.NON_ACTIVE);
        when(player2.getState()).thenReturn(Player.State.NON_ACTIVE);
        when(player3.getState()).thenReturn(Player.State.NON_ACTIVE);

        assertTrue(rule.isGameOver(players));
    }

    @Test
    public void isGameOverShouldReturnFalseIfNotAllPlayersAreNonActive(){

        when(player1.getState()).thenReturn(Player.State.NON_ACTIVE);
        when(player2.getState()).thenReturn(Player.State.ACTIVE);
        when(player3.getState()).thenReturn(Player.State.NON_ACTIVE);

        assertFalse(rule.isGameOver(players));
    }

    @Test
    public void determineWinnerReturnsThePlayerAsTheWinningPlayerIfAllPlayersHaveEqualScore() {
        
        when(player1.getScore()).thenReturn(21);
        when(player2.getScore()).thenReturn(21);
        when(player3.getScore()).thenReturn(21);

        assertEquals(player1, rule.determineWinner(players));
    }

    @Test
    public void determineWinnerReturnsTheCorrectWinningPlayer() {
        when(player1.getScore()).thenReturn(30);
        when(player2.getScore()).thenReturn(21);
        when(player3.getScore()).thenReturn(10);

        assertEquals(player2, rule.determineWinner(players));
    }

    @Test
    public void determineWinnerReturnsNullIfThereIsNoWinningPlayer() {
        when(player1.getScore()).thenReturn(24);
        when(player2.getScore()).thenReturn(24);
        when(player3.getScore()).thenReturn(24);

        assertNull(rule.determineWinner(players));
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
    public void isGameOverShouldThrowExceptionIfPlayerListIsEmpty() {
        rule.isGameOver(Arrays.asList());
    }

    @Test(expected = IllegalArgumentException.class)
    public void isGameOverShouldThrowExceptionIfPlayerListIsNull() {
        rule.isGameOver(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void determineWinnerThrowExceptionIfPlayerListIsEmpty() {
        rule.determineWinner(Arrays.asList());
    }

    @Test(expected = IllegalArgumentException.class)
    public void determineWinnerThrowExceptionIfPlayerListIsNull() {
        rule.determineWinner(null);
    }
}
