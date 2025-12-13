/*
 * Classname: Main (Organic Encounter)
 * Description: Lapse-like game
 * Author Comments: 
 * CMSC Project
 */

package presenter;

import javax.swing.SwingUtilities;
import view.MainWindow;

/*
    Purpose: solely to start the game / launch the system
    note: invokeLater launches the GUI on the Event Dispatch Thread.
 */

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainWindow ui = new MainWindow();           // create UI
            Game game = new Game(ui);                   // create Presenter and pass UI
            ui.getCardPanel().setSwipeListener(game);   // connect CardPanel swipes to the 
        });
    }
}