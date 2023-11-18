package com.dicegame;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class RuleTest {
    
    private Rule rule;
    private final int scoreLimit = 21;

    @Before
    public void setUp() {
        rule = new Rule();
    }

    @Test
    public void testRollAgainDecision() {
        assertEquals(Rule.Decision.THROW_AGAIN, rule.makeDecision(10, 5));
    }

}
