package com.dicegame;

import java.util.List;

import com.dicegame.Rule.Decision;

public class BotPlayer extends Player {

    private Rule rule;

    public BotPlayer(String name) {
        super(name);
        rule = new Rule();
    }

    @Override
    public void throwDice(List<Dice> dices) {
        
        int diceSum = 0;
        for(int i = 0; i < 3; i++) {
            diceSum = 0;
            for(Dice dice : dices) {
                diceSum += dice.roll();
            } 
            Decision decision = rule.makeDecision(score, diceSum);

            if(decision == Decision.BUST || decision == Decision.HOLD) {
                state = State.NON_ACTIVE;
                break;
            } else if (decision == Decision.STAY) {
                break;
            }
        }
        score += diceSum;
    }
    
}
