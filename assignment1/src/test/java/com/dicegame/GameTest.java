package com.dicegame;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;

public class GameTest {

    private Game game;
    private Player player1;
    private Player player2;

    @Before
    public void setUp() {
        player1 = new Player("Jon");
        player2 = new Player("Sara");
        game = new Game(Arrays.asList(player1, player2));
    }

    @Test
    public void gameShouldInitializeCorrectly() {
        assertNotNull("Game should not be null", game);
        assertNotNull("Player1 should not be null", player1);
        assertNotNull("Player2 should not be null", player2);
    }

    @Test
    public void gameShouldHaveCorrectPlayers() {
        List<Player> expectedPlayers = Arrays.asList(player1, player2);
        assertEquals("Game should have the correct players", expectedPlayers, game.getPlayers());
    }
}
