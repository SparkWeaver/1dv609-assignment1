package com.dicegame;

import java.util.List;

import com.dicegame.Rule.Action;

public class BotPlayer extends Player {

    public BotPlayer(String name) {
        super(name);
    }

    @Override
    public void rollDice(List<Dice> dices) {
        
        int diceSum = 0;
        for(int i = 0; i < 3; i++) {
            diceSum = 0;
            for(Dice dice : dices) {
                diceSum += dice.roll();
            } 
            Action decision = rule.decideAction(score, diceSum);

            if(decision == Action.BUST || decision == Action.HOLD) {
                state = State.NON_ACTIVE;
                break;
            } else if (decision == Action.STAY) {
                break;
            }
        }
        score += diceSum;
    }
    
}
