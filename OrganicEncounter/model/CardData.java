package model;

import java.util.ArrayList;
import java.util.List;

/* 
    Purpose: 
    * holds card/event data (doesn't accept input so static)

    Concepts used:
*/

public class CardData {
    public static List<Card> getCards(){
        List<Card> cards = new ArrayList<>();

        // Sample 1
        CardChoice left1 = new CardChoice("Ignore. It's just a typhoon", -40, 0, -20);
        CardChoice right1 = new CardChoice("Prepare for it", 40, 0, -20);
        Card card1 = new GenCard(
            "There is an incoming typhoon.",
            "Reporter",
            "reporter.jpg",
            left1,
            right1
        );

        // Sample 2
        CardChoice left2 = new CardChoice("Sure!", 40, 30, -30);
        CardChoice right2 = new CardChoice("No lol sorry", 0, -30, 0);
        Card card2 = new GenCard(
            "Can I borrow money?",
            "Marites",
            "marites.jpg",
            left2,
            right2
        );

        cards.add(card1);
        cards.add(card2);

        return cards;
    }
}
