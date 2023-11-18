package com.dicegame;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class AppTest {

    @Test
    public void shouldInitializeGameComponents() {
        App app = new App();
        app.startGame();
        assertNotNull("Game should be initialized", app.getGame());
        assertNotNull("View should be initialized", app.getView());
    }

    @Test
    public void gameShouldInitializeWithPlayerNameFromView() {
        View mockView = Mockito.mock(View.class);
        when(mockView.promptForPlayerName()).thenReturn("Jon");

        App app = new App();
        app.setView(mockView);
        app.startGame();

        Game game = app.getGame();

        assertTrue("Game should include player with name from View",
            game.getPlayers().contains(new Player("Jon")));
    }
}
