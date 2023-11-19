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
            
            if(score + diceSum > 100) {

            } else {
                Decision decision = view.promptForPlayerDecision(score, diceSum);
                if(decision == Decision.HOLD) {
                    state = State.NON_ACTIVE;
                    break;
                } else if (decision == Decision.STAY) {
                    break;
                } else if (decision == Decision.BUST) {
                    System.exit(0);
                }
            }
        }
        score += diceSum;
    }
    
}
