package presenter;

import annotation.ClassInfo;
import model.*;
import view.*;

@ClassInfo(
    mainAuthor = "",
    className = "Game",
    pillarsUsed = {},
    solidUsed = {}
)

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
    private Card endingCard;

    private boolean isEnding = false;

    public Game(MainWindow ui){
        this.ui = ui;
        ui.getTitlePanel().setStartListener(e -> startNewGame());
        ui.showTitleScreen();
    }

    private void startNewGame(){
        cardManager = new CardManager();
        playerStats = new Stats(50, 50, 50);
        
        ui.getGamePanel().getCardPanel().setSwipeListener(this);
        isEnding = false;
        
        ui.showGameScreen();
        showNextCard();
    }

    @Override 
    public void onSwipeRight() {
        if (isEnding && endingCard != null){
            handleLastSwipe();
        }  else {
            handleChoice(true); 
        }
    }

    @Override 
    public void onSwipeLeft()  {  
        if (isEnding && endingCard != null){
            handleLastSwipe();
        }  else {
            handleChoice(false); 
        }
    }

    private void handleLastSwipe(){      // for swiping ending card
        ui.showEndingScreen(); 
        
        isEnding = false;
        endingCard = null;

        //after 2 seconds, return to title
        javax.swing.Timer t = new javax.swing.Timer(2000, e -> ui.showTitleScreen());
        t.setRepeats(false);
        t.start();
    }

    private void handleChoice(boolean isRight){
        if (currentCard == null) return;

        CardChoice choice = isRight ? currentCard.getRightChoice() : currentCard.getLeftChoice();

        if (choice != null) {
            // apply the stats change
            choice.applyEffect(playerStats); 
        }           

        // check if there's branching
        currentCard = choice.hasNextCard() ? choice.getNextCard() : cardManager.getNextCard();

        // update UI
        if (currentCard != null){
            ui.getGamePanel().update(currentCard, playerStats); // sends next card to MainWindow
        }

        // check factor-based endings first
        Card factorEnding = cardManager.getFactorEnding(playerStats);
        if (factorEnding != null){
            endingCard = factorEnding;                  // stores ending card
            isEnding = true;
            ui.getGamePanel().showEndingCard(factorEnding);
            return;                                     // stop game flow
        }

        // check if plot cards are finished for final ending
        if (currentCard == null){
            Card finalEnding = cardManager.getEnding(playerStats);
            if (finalEnding != null){
                endingCard = finalEnding;                  // stores ending card
                isEnding = true;
                ui.getGamePanel().showEndingCard(finalEnding);
                return;
            }
        }
    }

    private void showNextCard(){
        currentCard = cardManager.getNextCard();
        if (currentCard != null){
            ui.getGamePanel().update(currentCard, playerStats); // sends current card to MainWindow
        } else {
            // if no more cards, show final ending
            Card finalEnding = cardManager.getEnding(playerStats);
            if (finalEnding != null){
                endingCard = finalEnding;                  // stores ending card
                isEnding = true;
                ui.getGamePanel().showEndingCard(finalEnding);
            }
        }
    }

}


