/*
 * Classname: Main (Organic Encounter)
 * Description: Lapse-like game
 * Author Comments: 
 * CMSC Project
 */

package presenter;

import annotation.ClassInfo;
import javax.swing.SwingUtilities;
import view.MainWindow;

@ClassInfo(
    mainAuthor = "",
    className = "Main",
    pillarsUsed = {},
    solidUsed = {}
)

/*
    Purpose: solely to start the game / launch the system
    note: invokeLater launches the GUI on the Event Dispatch Thread 
    (the only thread that should create or update Swing UI components).
 */

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainWindow ui = new MainWindow();           // builds the Jframe and UI
            new Game(ui);                   // create Presenter and pass UI; allows it to update the UI, respond to user actions
        });
    }
}
