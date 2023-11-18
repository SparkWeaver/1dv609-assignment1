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

    @Test
    public void testStayDecision() {
        assertEquals(Rule.Decision.STAY, rule.makeDecision(10, 5));
    }

    @Test
    public void testBustDecision() {
        assertEquals(Rule.Decision.BUST, rule.makeDecision(10, 12));
    }

    @Test
    public void testHoldDecision() {
        assertEquals(Rule.Decision.HOLD, rule.makeDecision(10, 9));
    }
}
