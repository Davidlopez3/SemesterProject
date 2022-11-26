package application;

public class Bets { // We're going to assume this casino runs on a 2:1 return
    protected double currentBet;

    // We still need to attach this to the Game method and then to UIController's
    // betTextArea
    public void setCurrentBet(int bet) {
        currentBet = bet;
    }

    public double getCurrentBet() {
        return currentBet;
    }

    public double settlement(boolean win, boolean loss,
                        boolean natural, boolean doubleDown, boolean insurance) {
        double currentValue = currentBet;
        double modifierValue = currentValue;
        double returnedValue;
        // setting the modifier value based on winning

        if (win) {
            modifierValue = currentValue * 2.0;
        }

        // setting  the endValue based on special booleans like natural and doubleDown
        double endValue = modifierValue;
        if (natural) {
            endValue = modifierValue * 1.5;
        }
        if (doubleDown) {
            endValue = modifierValue * 2.0;
        }

        // totaling calculations based on all bets
        if (loss) {
            returnedValue = currentValue - endValue;
        }
        else if (win) {
            returnedValue = endValue;
        }
        else {
            returnedValue = currentValue;
        }

        // calculated with insurance
        if(insurance) {
            returnedValue = returnedValue/2.0;
        }

        return returnedValue;
    }
}

// SAM
// created on 11/25/2022