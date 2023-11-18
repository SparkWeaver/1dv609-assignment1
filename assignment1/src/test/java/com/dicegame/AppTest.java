package com.dicegame;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class AppTest {

    private View mockView;

    @Before
    public void setUp() {
        this.mockView = Mockito.mock(View.class);
        when(mockView.promptForPlayerName()).thenReturn("Jon");
    }

    @Test
    public void shouldInitializeGameComponents() {
        App app = new App();
        app.setView(mockView);
        app.initGame();
        assertNotNull("Game should be initialized", app.getGame());
        assertNotNull("View should be initialized", app.getView());
    }

    @Test
    public void gameShouldInitializeWithPlayerNameFromView() {
        View mockView = Mockito.mock(View.class);
        when(mockView.promptForPlayerName()).thenReturn("Jon");

        App app = new App();
        app.setView(mockView);
        app.initGame();

        Game game = app.getGame();

        assertTrue("Game should include player with name from View",
            game.getPlayers().contains(new Player("Jon")));
    }
}
