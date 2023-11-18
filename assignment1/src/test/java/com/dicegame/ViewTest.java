package com.dicegame;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class ViewTest {

    private View view;

    @Before
    public void setUp() {
        view = new View();
    }

    @Test
    public void promptForPlayerNameDoesNotReturnNull() {
        String expected = "Jon";
        Scanner mockScanner = Mockito.mock(Scanner.class);
        when(mockScanner.nextLine()).thenReturn(null, expected);
        view.setScanner(mockScanner);
    }
    
    @Test
    public void promptForPlayerNameDoesNotReturnEmptyNameString() {
        Scanner mockScanner = Mockito.mock(Scanner.class);
        when(mockScanner.nextLine()).thenReturn("", "Jon");
        view.setScanner(mockScanner);

        String result = view.promptForPlayerName();
        assertEquals("Jon", result);
    }

    @Test
    public void promptForPlayerNameDoesNotReturnLargeNameString() {
        String expected = "Jon the Magnificent";
        Scanner mockScanner = Mockito.mock(Scanner.class);
        when(mockScanner.nextLine()).thenReturn("0123456789-0123456789-0123456789", expected);
        view.setScanner(mockScanner);

        String result = view.promptForPlayerName();
        assertEquals(expected, result);
    }
}