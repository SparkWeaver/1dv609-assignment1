package com.dicegame;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.dicegame.Player.State;

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
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class GameTest {

    private Game game;
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
        Dice mockDice1 = Mockito.mock(Dice.class);
        Dice mockDice2 = Mockito.mock(Dice.class);
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
        inPlayers.add(new HumanPlayer("Emma",mochView));
        Game game = new Game(inPlayers, mockDices, mochView, mockRule);

        List<Player> outPlayers = game.getPlayers();
        Set<Player> set = new HashSet<>(outPlayers);
        assertTrue(set.size() == outPlayers.size());
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
        // Need to use regular player object in this test case
        LinkedList<Player> inPlayers = new LinkedList<>();
        inPlayers.add(new HumanPlayer("Emma",mochView));
        Game game = new Game(inPlayers, mockDices, mochView, mockRule);

        List<Player> outPlayers = game.getPlayers();
        assertTrue(outPlayers.get(0) instanceof HumanPlayer);

        for(int i = 1; i < outPlayers.size(); i++) {
            assertTrue(outPlayers.get(i) instanceof BotPlayer);
        }
    }

    @Test
    public void gameShouldHaveTwoDices() {
        assertEquals(2, game.getDices().size());
    }
}
