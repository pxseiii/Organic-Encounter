package presenter;

import view.MainWindow;
import model.CardManager;

/* 
    Purpose: 
    * acts as the Presenter in MVP
    * Initialize game logic (model) + UI (view)
    * passes events (left n right choices) between view and model
    
    specifically
    * should handle impact of swiping L/R
    * apply choice effects to stats
    * updates ui when model changes
    * gets next card
}
    TO BE IMPLEMENTED HUHUHUHUHUH

public class Game implements CardSwipeListener {
    private CardManager cardManager;
    private MainWindow ui;

    @Override 
    public void onSwipeLeft()  {  
        // handle left  
    }
        
    @Override 
    public void onSwipeRight() {  
        // handle right  
    }

    private void showNextCard() {
        Card next = cardManager.getNextCard();
        ui.update(next);
    }

    public Game() {

    }

}
*/