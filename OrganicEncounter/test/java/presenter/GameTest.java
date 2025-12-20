package presenter;

import javax.swing.SwingUtilities;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
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
        assertNotNull(ui, "No initialization of UI.");
        assertNotNull(game, "Game instance should be created");
        assertFalse(game.isEnding(), "Game is starting; hence, not ending");
        assertTrue(ui.isVisible(), "UI should be visible to the user.");
    }

    @Test // Tests all existing panels
    void testUIPanels() {
        assertNotNull(ui.getTitle(), "UI title should be set");
        assertNotNull(ui.getTitlePanel(), "No title panel");
        assertNotNull(ui.getGamePanel(), "No game panel");
        assertNotNull(ui.getEndPanel(), "No end panel");
        assertNotNull(ui.getMainPanel(), "No main panel");
        assertTrue(ui.isVisible(), "UI should be visible to the user.");
    }

    @Test // Tests when starting a new game
    void testStartNewGame() {
        SwingUtilities.invokeLater(() -> {
            ui.getGamePanel().getCardPanel().setSwipeListener(game);
            ui.showGameScreen();
        });

        assertNotNull(ui, "Game should show game screen upon new game");
        assertTrue(ui.isVisible(), "UI should be visible to the user.");
    }

    @Test // Tests for swipe right
    void testSwipeRight() {
        assertDoesNotThrow(() -> {
            game.onSwipeRight();
        }, "Swipe right should not have exceptions.");
    }

    @Test // Tests for swipe left
    void testSwipeLeft() {
        assertDoesNotThrow(() -> {
            game.onSwipeLeft();
        }, "Swipe left should not have exceptions.");
    }

    @Test // Tests visiblity for last swipe/ending
    void testLastSwipeToTitle() {
        assertFalse(game.isEnding(), "Game resets because it is ending");
        assertNull(game.getEndingCard(), "Ending card clearing immediately after the game ended");
        assertNotNull(ui.getTitlePanel(), "Ending should be back at the title screen");
        assertNotNull(ui, "UI returns to the title on last swipe after 2 secs");
        assertTrue(ui.isVisible(), "UI should be visible to the user");
    }

    @Test // Tests for null image
    void testImageIsNotEmpty() throws Exception {
        SwingUtilities.invokeAndWait(() -> ui.getTitlePanel());
        
        var icon = ui.getGamePanel().getCardPanel().getImageLabel();
        
        assertNotNull(icon, "Game failed to load an image for the card!");
        assertTrue(icon.isVisible(), "Icon should be visible to the user.");
    }
    
}

