package presenter;

import model.*;
import view.MainWindow;
import view.SwipeListener;

/* 
    Purpose: 
    * acts as the Presenter in MVP
    * Initialize game logic (model) + UI (view)
    * handles swipe events, applies CardChoice effects to stats
    * updates UI and manages game flow (incl endings)
*/

public class Game implements SwipeListener {
    private CardManager cardManager;
    private MainWindow ui;
    private Stats playerStats;
    private Card currentCard;

    public Game(MainWindow ui){
        this.ui = ui;
        this.cardManager = new CardManager();
        this.playerStats = new Stats(0.5, 0.5, 0.5);      // initial value
        this.ui.getCardPanel().setSwipeListener(this);
        showNextCard();
    }

    @Override 
    public void onSwipeRight() {  
        handleChoice(true); 
    }

    @Override 
    public void onSwipeLeft()  {  
        handleChoice(false);
    }

    private void handleChoice(boolean isRight){
        if (currentCard == null) return;

        CardChoice choice = isRight ? currentCard.getRightChoice() : currentCard.getLeftChoice();

        if (choice != null) {
            // apply the stats change
            choice.applyEffect(playerStats); 
        }           

        // check if there's branching
        if (choice.hasNextCard()){
            currentCard = choice.getNextCard(); // force next card from this choice
        } else {
            currentCard = cardManager.getNextCard(); // otherwise, proceed with normal deck flow
        }

        // check factor-based endings first
        Card factorEnding = cardManager.getFactorEnding(playerStats);
        if (factorEnding != null){
            ui.showEnding(factorEnding);
            return;                                     // stop game flow
        }

        // check if plot cards are finished for final ending
        if (currentCard == null){
            Card finalEnding = cardManager.getEnding(playerStats);
            if (finalEnding != null){
                ui.showEnding(finalEnding);
                return;
            }
        }

        // update UI
        if (currentCard != null){
            ui.update(currentCard, playerStats);
        }
    }
    private void showNextCard(){
        currentCard = cardManager.getNextCard();
        if (currentCard != null){
            ui.update(currentCard, playerStats); // sends first card to MainWindow
        } else {
            // if no more cards, show final ending
            Card finalEnding = cardManager.getEnding(playerStats);
            if (finalEnding != null){
                ui.showEnding(finalEnding);
            }
        }
    }

}


