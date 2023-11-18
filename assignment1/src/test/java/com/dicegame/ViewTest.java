package com.dicegame;

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
    public void promptForPlayerNameDoesNotReturnEmptyNameString() {
        Scanner mockScanner = Mockito.mock(Scanner.class);
        when(mockScanner.nextLine()).thenReturn("", "Jon");
        view.setScanner(mockScanner);

        String result = view.promptForPlayerName();
        assertEquals("Jon", result);
    }
}
