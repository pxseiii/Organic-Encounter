package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import model.Card;
import model.CardChoice;
import model.CardData;

class CardDataTest {

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


    @Test
    void testNextCardBranching() {
        Card card = CardData.getPlotDeck().get(0);
        CardChoice choice = card.getRightChoice();

        if (choice != null && choice.hasNextCard()) {
            assertNotNull(choice.getNextCard(),
                "If choice hasNextCard(), next card should not be null");
        }
    }


    @Test
    void testDeckLoadingDoesNotThrow() {
        assertDoesNotThrow(() -> {
            CardData.getIntroDeck();
            CardData.getRandomDeck();
            CardData.getPlotDeck();
            CardData.getEndingDeck();
        }, "Loading decks should not throw exceptions");
    }


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

