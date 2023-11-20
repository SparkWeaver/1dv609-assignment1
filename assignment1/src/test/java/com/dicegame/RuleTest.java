package com.dicegame;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class RuleTest {

    private Rule rule;
    private int goal;

    @Before
    public void setUp() {
        goal = 21;
        rule = new Rule(goal);
    }

    @Test
    public void getGoalReturnSetGoal() {
        assertEquals(goal, rule.getGoal());
    }

    @Test
    public void testRollAgainDecision() {
        assertEquals(Rule.Decision.ROLL, rule.makeDecision(5, 5));
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

    @Test(expected = IllegalArgumentException.class)
    public void makeDecisionShouldThrowExceptionPlayerScoreIsNegative() {
        rule.makeDecision(-1, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void makeDecisionShouldThrowExceptionDiceValueIsNegative() {
        rule.makeDecision(2, -1);
    }
}
