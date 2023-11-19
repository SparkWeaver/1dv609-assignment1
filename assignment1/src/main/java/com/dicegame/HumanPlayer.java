package com.dicegame;

import java.util.List;

import com.dicegame.Rule.Decision;

public class HumanPlayer extends Player {

    private View view;

    public HumanPlayer(String name, View view) {
        super(name);
        this.view = view;
    }

    @Override
    public void rollDice(List<Dice> dices) {
        
        int diceSum = 0;
        for(int i = 0; i < 3; i++) {
            diceSum = 0;
            for(Dice dice : dices) {
                diceSum += dice.roll();
            } 
            
            Decision decision;
            if(score + diceSum > 21) {
                decision = view.promptForPlayerBustDecision(score, diceSum);
                state = State.NON_ACTIVE;
                break;
            } else {
                decision = view.promptForPlayerDecision(score, diceSum);
                if(decision == Decision.HOLD) {
                    state = State.NON_ACTIVE;
                    break;
                } else if (decision == Decision.STAY) {
                    break;
                }
            }
        }
        score += diceSum;
    }
    
}
