package presenter;

import javax.swing.SwingUtilities;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import view.MainWindow;

class GameTest {
    private Game game;
    private MainWindow ui;

    @BeforeEach
    void setUp() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            ui = new MainWindow();
            game = new Game(ui);
        });
    }

    @Test // Tests window is present when game is starting
    void testGameInitialization() throws Exception {
        assertNotNull(ui, "No initialization of UI");
        assertNotNull(game, "Game instance should be created");
        assertFalse(game.isEnding(), "Game is starting; hence, not ending");
        assertTrue(ui.getTitlePanel().isVisible(), "Title panel should be visible as the start of the game");
        assertTrue(ui.isVisible(), "UI should be visible to the user");
    }

    @Test // Tests all existing panels
    void testUIPanels() {
        assertNotNull(ui.getTitlePanel(), "No title panel");
        assertNotNull(ui.getGamePanel(), "No game panel");
        assertNotNull(ui.getEndPanel(), "No end panel");
        assertNotNull(ui.getMainPanel(), "No main panel");
        assertTrue(ui.isVisible(), "UI should be visible to the user");
    }

    @Test // Tests when starting a new game
    void testStartNewGame() {
        SwingUtilities.invokeLater(() -> {
            ui.getTitlePanel().setStartListener(e -> testStartNewGame());
            ui.showTitleScreen();
            ui.getGamePanel().getCardPanel().setSwipeListener(game);
            ui.showGameScreen();
        });

        assertFalse(game.isEnding(), "The game is not ending");
        assertNotNull(ui, "UI should exists");
        assertTrue(ui.isVisible(), "UI should be visible to the user");
    }

    @Test // Tests for null image
    void testImageIsNotEmpty() throws Exception {
        ui.showGameScreen();
        
        var icon = ui.getGamePanel().getCardPanel().getIconLabel();
        
        assertNotNull(icon, "Game failed to load an image for the card!");
        assertTrue(icon.isVisible(), "Icon should be visible to the user.");
    }
}
    

/* GETTERS FOR TESTING: to access private attributes
    --------- GAME ----------
    public boolean isEnding() {
        return this.isEnding;
    }
    
    ------ TITLE PANEL ------
    public boolean isVisible() {
        return mainPanel.isVisible();
    }

    ------ CARD PANEL ------
    public JLabel getIconLabel() {
        return this.iconLabel;
    }

    ------ MAIN WINDOW ------
    public boolean isVisible() {
        return frame.isVisible();
    }

 */
