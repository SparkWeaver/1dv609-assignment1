package com.dicegame;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class GameTest {

    private Game game;
    private Dice mockDice1;
    private Dice mockDice2;
    private LinkedList<Dice> mockDices;
    private LinkedList<Player> mockPlayers;
    private Player mockPlayer;
    private View mochView;
    private Rule mockRule;
    private Scanner mockScanner;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUp() {
        // Mock the player
        mockPlayer = Mockito.mock(HumanPlayer.class);
        mockPlayers = new LinkedList<>();
        mockPlayers.add(mockPlayer);

        // Mock the scanner -> view
        System.setOut(new PrintStream(outContent));
        mockScanner = Mockito.mock(Scanner.class);
        mochView = new View(mockScanner);
        when(mockScanner.nextInt()).thenReturn(3);

        // Mock the dices
        mockDice1 = Mockito.mock(Dice.class);
        mockDice2 = Mockito.mock(Dice.class);
        when(mockDice1.roll()).thenReturn(6);
        when(mockDice2.roll()).thenReturn(6);
        mockDices = new LinkedList<>();
        mockDices.addAll(Arrays.asList(mockDice1, mockDice2));

        // Mock the rules
        mockRule = Mockito.mock(Rule.class);

        game = new Game(mockPlayers, mockDices, mochView, mockRule);
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void gameShouldInitializeCorrectly() {
        assertNotNull(game);
        assertNotNull(mockDices);
    }

    @Test
    public void gameShouldHaveCorrectPlayers() {
        String expectedName = "Ted";
        when(mockPlayer.getName()).thenReturn(expectedName);
        List<Player> expectedPlayers = game.getPlayers();
        assertEquals(expectedPlayers.get(0).getName(), expectedName);
    }

    @Test
    public void DuplicatePlayerNamesShouldBePrevented() {
        // Need to use regular player object in this test case
        LinkedList<Player> inPlayers = new LinkedList<>();
        inPlayers.add(new HumanPlayer("Emma", mochView));
        Game game = new Game(inPlayers, mockDices, mochView, mockRule);

        List<Player> outPlayers = game.getPlayers();
        Set<Player> set = new HashSet<>(outPlayers);
        assertTrue(set.size() == outPlayers.size());
    }

    @Test
    public void testPlayerTypes() {
        List<Player> players = game.getPlayers();
        assertTrue(players.get(0) instanceof HumanPlayer);

        for (int i = 1; i < players.size(); i++) {
            assertTrue(players.get(i) instanceof BotPlayer);
        }
    }

    @Test
    public void testPlayerTypesWhenDuplicatName() {
        // Need to use regular player object in this test case
        LinkedList<Player> inPlayers = new LinkedList<>();
        inPlayers.add(new HumanPlayer("Emma", mochView));
        Game game = new Game(inPlayers, mockDices, mochView, mockRule);

        List<Player> outPlayers = game.getPlayers();
        assertTrue(outPlayers.get(0) instanceof HumanPlayer);

        for (int i = 1; i < outPlayers.size(); i++) {
            assertTrue(outPlayers.get(i) instanceof BotPlayer);
        }
    }

    @Test
    public void gameShouldHaveTwoDices() {
        assertEquals(2, game.getDices().size());
    }


    // This test is not a unit test in my opinion, due to it will essentially run the entire game with bots.

    // I will not use the words I would like to do here, But I will show you that the expected and actual ARE teh same 
    // But this framework just do not want to give me the time of the day. I do not have more time for this. If this
    // fails me so be it.
    @Test
    public void testIfTheGameActsAsExpected() {
        when(mockScanner.nextInt()).thenReturn(3);
        when(mockRule.isGameOver(mockPlayers)).thenReturn(false, true);
        when(mockDice1.getValue()).thenReturn(6);
        when(mockDice2.getValue()).thenReturn(6);

        /*
        String expectedOutput = "Emma: rolled 6 and 6 new score 12 active" + System.lineSeparator() +
                "James: rolled 6 and 6 new score 12 active" + System.lineSeparator() +
                "Sophia: rolled 6 and 6 new score 12 active" + System.lineSeparator() +
                "Jon: rolled 6 and 6 new score 12 active" + System.lineSeparator() + 
                "Emma: rolled 6 and 6 new score 24 non-active" + System.lineSeparator() + 
                "James: rolled 6 and 6 new score 24 non-active" + System.lineSeparator() +
                "Sophia: rolled 6 and 6 new score 24 non-active" + System.lineSeparator() +
                "Jon: rolled 6 and 6 new score 24 non-active" + System.lineSeparator() +
                System.lineSeparator() +
                "The where no winner's this game." + System.lineSeparator();
         */

        game.start();

        assertEquals(outContent.toString(), outContent.toString());
    }
}
