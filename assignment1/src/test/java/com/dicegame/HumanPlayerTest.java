package com.dicegame;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class HumanPlayerTest {

    private HumanPlayer humanPlayer;
    private String name;
    private Dice mockDice1;
    private Dice mockDice2;
    private List<Dice> mockedDices;
    private InputStream originalIn;


    @Before
    public void setUp() {
        name = "TED";
        humanPlayer = new HumanPlayer(name);

        mockDice1 = Mockito.mock(Dice.class);
        mockDice2 = Mockito.mock(Dice.class);

        mockedDices = Arrays.asList(mockDice1, mockDice2);

        originalIn = System.in;
    }
    
    @Test
    public void testHumanDecisionToRollAgain() {
        String simulatedUserInput = "1";
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));


        when(mockDice1.roll()).thenReturn(1,2,3,4);
        when(mockDice2.roll()).thenReturn(1,2,3,4);

        humanPlayer.throwDice(mockedDices);

        assertEquals(Player.State.ACTIVE, humanPlayer.getState());
        assertEquals(6, humanPlayer.getScore());

        System.setIn(originalIn);
    }

}
