package com.dicegame;

import java.util.List;

import com.dicegame.Rule.Action;

public class HumanPlayer extends Player {

    private View view;

    public HumanPlayer(String name, View view) {
        super(name);
        this.view = view;
    }

    @Override
    public void rollDice(List<Dice> dices) {
        
        int diceSum = 0;
        Action decision = Action.ROLL;
        
        for(int i = 0; i < 3; i++) {
            diceSum = 0;
            for(Dice dice : dices) {
                diceSum += dice.roll();
            } 
            
            if (i == 2) {
                if(score + diceSum > 21) {
                    decision = view.promptForPlayerBustAction(score, diceSum);
                    state = State.NON_ACTIVE;
                    break;
                } else {
                    decision = view.promptForPlayerNoMoreTurnsAction(score, diceSum);
                    if(decision == Action.HOLD) {
                        state = State.NON_ACTIVE;
                        break;
                    }
                }
            } else {
                decision = view.promptForPlayerDefaultAction(score, diceSum);
                if(decision == Action.HOLD) {
                    state = State.NON_ACTIVE;
                    break;
                } else if (decision == Action.STAY) {
                    break;
                }
            }

        }
        score += diceSum;
    }
    
}
