package com.dicegame;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class GameTest {

    private Game game;
    private String playerName;
    private List<Dice> mockDices;
    private View mochView;
    private Scanner mockScanner;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUp() {
        playerName = "Ted";

        System.setOut(new PrintStream(outContent));
        mockScanner = Mockito.mock(Scanner.class);
        mochView = new View();
        when(mockScanner.nextInt()).thenReturn(3);
        mochView.setScanner(mockScanner);

        Dice mockDice1 = Mockito.mock(Dice.class);
        Dice mockDice2 = Mockito.mock(Dice.class);
        when(mockDice1.roll()).thenReturn(6);
        when(mockDice2.roll()).thenReturn(6);
        mockDices = Arrays.asList(mockDice1, mockDice2);

        game = new Game(playerName, mochView);
        game.setDice(mockDices);
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void gameShouldInitializeCorrectly() {
        assertNotNull("Game should not be null", game);
        assertNotNull("Dices should not be null", mockDices);
    }

    @Test
    public void gameShouldHaveCorrectPlayers() {
        List<Player> expectedPlayers = game.getPlayers();
        assertEquals("Game should have the correct players", expectedPlayers.get(0).getName(), playerName);
    }

    @Test
    public void gameShouldStartAndEndCorrectly() {
        assertFalse("Game should be inactive before start", game.isActive());
        game.start();
        assertTrue("Game should be active after start", game.isOver());
    }

    @Test
    public void playersShouldGainPointsDuringGame() {
        List<Player> expectedPlayers = game.getPlayers();
        assertEquals("Player 2 should have 0 score before first round", 0, expectedPlayers.get(0).getScore());
        game.start();
        assertNotEquals("Player 2 should have 7 score after first round", 0, expectedPlayers.get(0).getScore());
    }

    @Test
    public void DuplicatePlayerNamesShouldBePrevented() {
        Game game = new Game("Emma", mochView);
        List<Player> players = game.getPlayers();
        Set<Player> set = new HashSet<>(players);

        assertTrue(set.size() == players.size());
    }

    @Test
    public void testPlayerTypes() {
        List<Player> players = game.getPlayers();
        assertTrue(players.get(0) instanceof HumanPlayer);

        for(int i = 1; i < players.size(); i++) {
            assertTrue(players.get(i) instanceof BotPlayer);
        }
    }

    @Test
    public void testPlayerTypesWhenDuplicatName() {
        Game game = new Game("Emma", mochView);
        List<Player> players = game.getPlayers();
        assertTrue(players.get(0) instanceof HumanPlayer);

        for(int i = 1; i < players.size(); i++) {
            assertTrue(players.get(i) instanceof BotPlayer);
        }
    }

    @Test
    public void gameShouldHaveTwoDices() {
        Game game = new Game("Emma", mochView);
        assertEquals(2, game.getDices().size());
    }

    @Test
    public void gameShouldPrintAllBotDecisionsBetweenPlayerHumanTurn() {
        when(mockScanner.nextInt()).thenReturn(3);
        game.start();

        String expectedOutput = createPlayerOutput() +
                            createBotOutput();

        assertEquals(expectedOutput, outContent.toString());
        assertTrue(game.isOver());
    }

    private String createPlayerOutput() {
        return System.lineSeparator() +
                "Score: 0 Dice roll: 12" + System.lineSeparator() +
                System.lineSeparator() + "1. Throw again" +
                System.lineSeparator() + "2. Stay" +
                System.lineSeparator() + "3. Hold" +
                System.lineSeparator() + "0. End game" +
                System.lineSeparator() + System.lineSeparator();
    }

    private String createBotOutput() {
        return System.lineSeparator() + System.lineSeparator() +
                System.lineSeparator() + "Emma: rolled 6 and 6 new score 12 active" +
                System.lineSeparator() + "James: rolled 6 and 6 new score 12 active" +
                System.lineSeparator() + "Sophia: rolled 6 and 6 new score 12 active" +
                System.lineSeparator() + System.lineSeparator() +
                System.lineSeparator() + "Emma: rolled 6 and 6 new score 24 non-active" +
                System.lineSeparator() + "James: rolled 6 and 6 new score 24 non-active" +
                System.lineSeparator() + "Sophia: rolled 6 and 6 new score 24 non-active" +
                System.lineSeparator() + System.lineSeparator();
    }
}
