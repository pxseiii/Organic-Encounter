package presenter;

import annotation.ClassInfo;
import model.*;
import view.*;

@ClassInfo(
    mainAuthor = "Kaindoy",
    className = "Game",
    pillarsUsed = {"Encapsulation, Polymorphism"},
    solidUsed = {"SRP, LSP, DIP"}
)

/* 
    Description / Author Comments

    Purpose: 
    * the Presenter in MVP (connects the Model (game data) and View (UI))
    * manages the game flow (tracks current cards, player stats, and game day)
    * handles user input (swipe left/right) and applies a choice's effects on stats
    * updates UI throughout the game
*/

public class Game implements SwipeListener {
    // ----------- FIELDS --------------
    private CardManager cardManager;                                // manages the card decks and game progression
    private MainWindow ui;
    private Stats playerStats;
    private Card currentCard;                                       // card currently displayed
    private Card endingCard;                                        // stores ending card if game reaches an ending

    private int day;
    private boolean isEnding = false;                               // flag indicating if game reached an ending

    // ----------- CONSTRUCTOR --------------
    public Game(MainWindow ui){
        this.ui = ui;

        // setup start button listener to launch game
        ui.getTitlePanel().setStartListener(e -> startNewGame());
        ui.showTitleScreen();
    }

    // ----------- GAME FLOW METHODS --------------

    // sets ups the game: resets stats, cards, day, and UI
    private void startNewGame(){
        cardManager = new CardManager();                            
        playerStats = new Stats(50, 50, 50);
        day = 0;
        
        ui.getGamePanel().getCardPanel().setSwipeListener(this);    // connects Presenter to the card panel's swipe events 
        isEnding = false;
        
        ui.showGameScreen();
        showNextCard();
    }

    // shows first card to display
    private void showNextCard(){
        currentCard = cardManager.getNextCard();
        if (currentCard != null){
            ui.getGamePanel().update(currentCard, playerStats, day); 
        } else {
            // if no more cards, show final ending
            Card finalEnding = cardManager.getEnding(playerStats);
            if (finalEnding != null){
                endingCard = finalEnding;                       
                isEnding = true;
                ui.getGamePanel().showEndingCard(finalEnding);
            }
        }
    }


    // ----------- INPUT HANDLERS --------------
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

    // when swiping ending card
    private void handleLastSwipe(){     
        ui.showEndingScreen(); 
        
        // reset state
        isEnding = false;
        endingCard = null;

        // auto-return to title after 2 seconds of showing ending screen
        javax.swing.Timer t = new javax.swing.Timer(2000, e -> ui.showTitleScreen());
        t.setRepeats(false);
        t.start();
    }

    // handles a player's choice and updates game state
    private void handleChoice(boolean isRight){
        if (currentCard == null) return;

        CardChoice choice = isRight ? currentCard.getRightChoice() : currentCard.getLeftChoice();

        if (choice != null) {
            // apply the stats change
            choice.applyEffect(playerStats); 
        }           

        // check if this choice has a branching next card
        boolean isBranching = choice != null && choice.hasNextCard();

        // determine next card: either branching card or next card from manager
        currentCard = isBranching ? choice.getNextCard() : cardManager.getNextCard();

        // increments day
        if (!isBranching && currentCard.updatesDay()){
            day += (int) (Math.random() * 7) + 1;
        }

        // update UI
        if (currentCard != null){
            ui.getGamePanel().update(currentCard, playerStats, day); // sends next card to MainWindow
        }

        // check factor-based endings first
        Card factorEnding = cardManager.getFactorEnding(playerStats);
        if (factorEnding != null){
            endingCard = factorEnding;                  
            isEnding = true;
            ui.getGamePanel().showEndingCard(factorEnding);
            return;                                             // stop game flow
        }

        // if no more cards, check for final ending based on average stats
        if (currentCard == null){
            Card finalEnding = cardManager.getEnding(playerStats);
            if (finalEnding != null){
                endingCard = finalEnding;                  
                isEnding = true;
                ui.getGamePanel().showEndingCard(finalEnding);
                return;
            }
        }
    }

}





