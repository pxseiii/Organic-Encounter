package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import model.Card;
import model.CardChoice;
import model.CardData;

class CardDataTest {

    /* ===============================
       METHODS THAT RETURN VALUES
       =============================== */

    @Test
    void testIntroDeckNotNull() {
        List<Card> intro = CardData.getIntroDeck();
        assertNotNull(intro, "Intro deck should not be null");
        assertFalse(intro.isEmpty(), "Intro deck should not be empty");
    }

    @Test
    void testRandomDeckNotNull() {
        List<Card> random = CardData.getRandomDeck();
        assertNotNull(random, "Random deck should not be null");
        assertFalse(random.isEmpty(), "Random deck should not be empty");
    }

    @Test
    void testPlotDeckNotNull() {
        List<Card> plot = CardData.getPlotDeck();
        assertNotNull(plot, "Plot deck should not be null");
        assertFalse(plot.isEmpty(), "Plot deck should not be empty");
    }

    @Test
    void testEndingDeckNotNull() {
        List<Card> ending = CardData.getEndingDeck();
        assertNotNull(ending, "Ending deck should not be null");
        assertFalse(ending.isEmpty(), "Ending deck should not be empty");
    }

    /* ===============================
       METHODS WITH BRANCHING LOGIC
       =============================== */

    @Test
    void testCardsHaveChoicesOrEnding() {
        for (Card card : CardData.getPlotDeck()) {
            assertNotNull(card, "Card should not be null");

            CardChoice left = card.getLeftChoice();
            CardChoice right = card.getRightChoice();

            // Either both choices exist OR this is an ending card
            assertTrue(
                (left != null && right != null) || (left == null && right == null),
                "Card must either have two choices or be an ending card"
            );
        }
    }

    /* ===============================
       METHODS THAT INTERACT WITH OBJECTS
       (CardChoice → Stats)
       =============================== */

    @Test
    void testCardChoiceAppliesEffect() {
        Card card = CardData.getPlotDeck().get(0);
        CardChoice choice = card.getRightChoice();

        if (choice != null) {
            model.Stats stats = new model.Stats(50, 50, 50);

            assertDoesNotThrow(() -> {
                choice.applyEffect(stats);
            }, "Applying effect should not throw exception");
        }
    }

    /* ===============================
       METHODS THAT CHANGE GAME STATE
       =============================== */

    @Test
    void testNextCardBranching() {
        Card card = CardData.getPlotDeck().get(0);
        CardChoice choice = card.getRightChoice();

        if (choice != null && choice.hasNextCard()) {
            assertNotNull(choice.getNextCard(),
                "If choice hasNextCard(), next card should not be null");
        }
    }

    /* ===============================
       METHODS THAT SHOULD NOT THROW
       =============================== */

    @Test
    void testDeckLoadingDoesNotThrow() {
        assertDoesNotThrow(() -> {
            CardData.getIntroDeck();
            CardData.getRandomDeck();
            CardData.getPlotDeck();
            CardData.getEndingDeck();
        }, "Loading decks should not throw exceptions");
    }

    /* ===============================
       METHODS THAT SHOW IMAGES
       (icon path existence, not rendering)
       =============================== */

    @Test
    void testCardIconPathValidity() {
        for (Card card : CardData.getPlotDeck()) {
            String iconPath = card.getIcon();

            if (iconPath != null && !iconPath.isEmpty()) {
                assertTrue(iconPath.endsWith(".png") || iconPath.endsWith(".jpg"),
                    "Icon path should be an image file");
            }
        }
    }
}
