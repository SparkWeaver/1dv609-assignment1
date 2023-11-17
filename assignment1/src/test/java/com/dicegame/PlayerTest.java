package com.dicegame;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class PlayerTest {
    
    @Test
    public void getNameReturnCorrectName() {
        String name = "Jon Goodman";
        Player player = new Player(name);
        assertEquals(name, player.getName());
    }
}
