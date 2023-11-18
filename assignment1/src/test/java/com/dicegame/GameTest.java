package com.dicegame;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

public class GameTest {

    @Mock
    private Dice mockDice;

    private Game game;
    private Player player1;
    private Player player2;

    @Before
    public void setUp() {
        mockDice = Mockito.mock(Dice.class);
        when(mockDice.roll()).thenReturn(7);

        player1 = new Player("Jon");
        player2 = new Player("Sara");

        game = new Game(Arrays.asList(player1, player2), Arrays.asList(mockDice));
    }

    @Test
    public void gameShouldInitializeCorrectly() {
        assertNotNull("Game should not be null", game);
        assertNotNull("Player1 should not be null", player1);
        assertNotNull("Player2 should not be null", player2);
        assertNotNull("Dices should not be null", mockDice);
    }

    @Test
    public void gameShouldHaveCorrectPlayers() {
        List<Player> expectedPlayers = Arrays.asList(player1, player2);
        assertEquals("Game should have the correct players", expectedPlayers, game.getPlayers());
    }

    @Test
    public void gameShouldHaveCorrectDice() {
        List<Dice> expectedDices = Arrays.asList(mockDice);
        assertEquals("Game should have the correct dices",expectedDices, game.getDices());
    }

    @Test
    public void gameShouldStartCorrectly() {
        assertFalse("Game should be inactive before start", game.isActive());
        game.start();
        assertTrue("Game should be active after start", game.isActive());
    }

    @Test
    public void playersShouldGainPointsAfterTurn() {
        assertEquals("Player 1 should have 0 score before first round", 0, player1.getScore());
        assertEquals("Player 2 should have 0 score before first round", 0, player2.getScore());
        game.start();
        assertEquals("Player 1 should have 7 score after first round", 7, player1.getScore());
        assertEquals("Player 2 should have 7 score after first round", 7, player2.getScore());
    }

    @Test
    public void gameShouldEndWhenWinnerIsDetermined() {
        game.start();
        assertTrue("Game ends when there is a winner", game.isOver());
    }
}
