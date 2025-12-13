package model;

import java.util.ArrayList;
import java.util.List;

/* 
    Purpose: 
    * holds card/event data (doesn't accept input so static)
    * supplies lists of cards for CardManager
*/

public class CardData {
    
    // helper method to create different types of cards
    /* createCard parameters
        * situation
        * title
        * icon
        * leftText
        * leftStatsChange
        * rightText
        * rightStatsChange
        * type
     */

    private static Card createCard(
        String situation, 
        String title, 
        String icon, 
        String leftText, StatsChange leftStatsChange, 
        String rightText, StatsChange rightStatsChange, 
        CardType type){
        CardChoice left = new CardChoice(leftText, leftStatsChange);
        CardChoice right = new CardChoice(rightText, rightStatsChange);

        switch(type){
            case INTRO:
                return new IntroCard(situation, title, icon, left, right);
            case RANDOM:
                return new RandomCard(situation, title, icon, left, right);
            case PLOT:
                return new PlotCard(situation, title, icon, left, right);
            default:
                throw new IllegalArgumentException("Unknown card type");
        }
    }
    
    private enum CardType{
        INTRO, RANDOM, PLOT
    }

    // overload createCard method for intro branching
    private static Card createBranchingIntroCard(
        String situation, 
        String title, 
        String icon, 
        String leftText, Card leftNextCard, 
        String rightText, Card rightNextCard){
        CardChoice left = new CardChoice(leftText, null, leftNextCard);
        CardChoice right = new CardChoice(rightText, null, rightNextCard);

        return new IntroCard(situation, title, icon, left, right);
    }

    public static List<Card> getIntroDeck(){
        List<Card> introCards = new ArrayList<>();

        /* 
        introCards.add(createCard(
            "",
            "Reporter",
            "reporter.jpg",
            "Ignore. It's just a typhoon", null,
            "Prepare for it", null,
            CardType.INTRO
        ));
        */
       Card intro1_choice1 = createBranchingIntroCard(
            "Thank you.",
            "Lola Emelda | Grandma",
            "grandma_v2.png",
            "Is this a dream?", null,                    
            "How come you’re alive?", null
        );

        Card intro1_choice2 = createBranchingIntroCard(
            "You will do just fine. Gido will be there to guide you.",
            "Lola Emelda | Grandma",
            "grandma_v2.png",
            "Who is he? (wala kang silbeng apo)", null,
            "I hope so…", null
        );

        Card intro1 = createBranchingIntroCard(
            "I am now at peace knowing that you’re going to take care of my farm.",
            "Lola Emelda | Grandma",
            "grandma_v2.png",
            "I will do my best", intro1_choice1,
            "I don’t think I’m ready...", intro1_choice2
        );
        introCards.add(intro1);

        introCards.add(createCard(
            "You woke up feeling the sweat dripping down your forehead.",
            "You",
            "",
            "What a weird dream.", null,
            "Maybe I should talk to our caretaker.", null,
            CardType.INTRO
        ));

        // dream; deja vu
        Card dream_choice1 = createBranchingIntroCard(
            "Thank you.",
            "Lola Emelda | Grandma",
            "grandma_v2.png",
            "I should talk to our caretaker once I see him.", null,                    
            "I don’t want to talk to him. I think I’m going to be fine.", null
        );

        Card dream_choice2 = createBranchingIntroCard(
            "You will do just fine. Gido will be there to guide you.",
            "Lola Emelda | Grandma",
            "grandma_v2.png",
            "I should talk to our caretaker once I see him.", null,
            "I don’t want to talk to him. I think I’m going to be fine.", null
        );

        Card dream = createBranchingIntroCard(
            "I am now at peace knowing that you’re going to take care of my farm.",
            "Lola Emelda | Grandma",
            "grandma_v2.png",
            "Deja vu.", dream_choice1,
            "Again, I don’t think I’m ready.", dream_choice2
        );

        // tutorial
        Card tutorial4 = createBranchingIntroCard(
            "Your farm will face various threats daily, and you have to maintain everything in balance. May luck be ever in your favor.",
            "Mang Gido | Caretaker",
            "caretaker_v1.png",
            "Okay. Thanks.", null,      // game proper                 
            "I’m ready.", null        // game proper
        );

        Card tutorial3 = createBranchingIntroCard(
            "Survive the daily encounter with nature. Defeat the pests, weather, and all that threatens the farm’s condition. I wish you luck.",
            "Mang Gido | Caretaker",
            "caretaker_v1.png",
            "I understand", null,   // game proper                 
            "I don’t understand", tutorial4
        );

        Card tutorial2 = createBranchingIntroCard(
            "We have three factors to manage: land prosperity, reputation, and finance. Maintain balance across all factors; do not let any factor be too high or too low compared to the others. The effect indicator can be found above the factors’ icons.",
            "Mang Gido | Caretaker",
            "caretaker_v1.png",
            "So all I have to do is maintain it?", tutorial3,                    
            "What am I going to do with these?", tutorial3
        );

        Card tutorial1 = createBranchingIntroCard(
            "Your grandmother tried to maintain the farm’s condition until her last breath.",
            "Mang Gido | Caretaker",
            "caretaker_v1.png",
            "I can see that.", tutorial2,                    
            "Okay…", tutorial2
        );
        // back to dream
        // game proper

        Card intro2_choice1 = createBranchingIntroCard(
            "I am going to walk you through the basics of taking care of what your grandmother left you.",
            "Mang Gido | Caretaker",
            "caretaker_v1.png",
            "I was about to ask you about the same thing.", tutorial1, // tutorial                   
            "I think I can handle it on my own.", null //game proper
        );

        Card intro2_choice2 = createBranchingIntroCard(
            "I just need 5 minutes of your time.",
            "Lola Emelda | Grandma",
            "grandma_v2.png",
            "Let’s just talk some other day.", dream,    // dream
            "You don’t have to worry about me.", null   // game proper
        );

        Card intro2 = createBranchingIntroCard(
            "Do you have time?",
            "Mang Gido | Caretaker",
            "caretaker_v1.png",
            "Yes", intro2_choice1,
            "I guess…", intro2_choice2
        );
        introCards.add(intro2);

        return introCards;
    }

    public static List<Card> getRandomDeck(){
        List<Card> randomCards = new ArrayList<>();

        randomCards.add(createCard(
            "There is an incoming typhoon.",
            "Reporter",
            "reporter.jpg",
            "Ignore. It's just a typhoon", new StatsChange(-0.1, 0, -0.2),
            "Prepare for it", new StatsChange(0.2, 0, 0.2),
            CardType.RANDOM
        ));

        randomCards.add(createCard(
            "Can I borrow money?",
            "Marites",
            "marites.jpg",
            "Sure!", new StatsChange(0.4, 0.3, -0.3),
            "No lol sorry", new StatsChange(0, -0.3, 0),
            CardType.RANDOM
        ));

        return randomCards;
    }

    public static List<Card> getPlotDeck(){
        List<Card> randomCards = new ArrayList<>();

        randomCards.add(createCard(
            "There is an incoming typhoon.",
            "Reporter",
            "reporter.jpg",
            "Ignore. It's just a typhoon", new StatsChange(-0.4, 0, -0.2),
            "Prepare for it", new StatsChange(0.4, 0, -0.2),
            CardType.RANDOM
        ));
        return randomCards;
    }

    public static List<Card> getEndingDeck(){
        List<Card> endingCards = new ArrayList<>();
        /* index - factor state
        0 - land prosperity max
        1 - land prosperity min
        2 - rep max
        3 - rep min
        4 - finance max
        5 - finance min
        6 - good ending
        7 - bad ending
        */
        endingCards.add(new EndingCard(
            "Your farm drew too many greedy eyes– relatives, developers, and even political figures all after your land.", 
            null, 
            "lp_max.png",
            null, null
        ));

        endingCards.add(new EndingCard(
            "Your grandmother’s farmland dies– and with it, the last living memory of her.", 
            null, 
            "lp_min.png",
            null, null
        ));

        endingCards.add(new EndingCard(
            "People expected too much from you. The pressure to maintain your reputation wore you down, so you left without a trace, trying to take a breather.", 
            null, 
            "rep_max.png",
            null, null
        ));

        endingCards.add(new EndingCard(
            "Workers abandoned you for your unfair treatment. No one wants to engage with you. Your name’s a dirt in the village.", 
            null, 
            "rep_min.png",
            null, null
        ));

        endingCards.add(new EndingCard(
            "Workers staged a protest because of your profit-over-people mindset. Mayor Edna and a DA rep confronted you about your neglect of responsibility to the environment.", 
            null, 
            "finance_max.png",
            null, null
        ));

        endingCards.add(new EndingCard(
            "Debt crushed you. Your aunt paid it off… and took the farm in return.", 
            null, 
            "finance_min.png",
            null, null
        ));

        endingCards.add(new EndingCard(
            "You managed to maintain the farm’s condition despite all the organic encounters. The court decided to give you the ownership of the land. Yis galing!", 
            null, 
            "good_ending.png",
            null, null
        ));

        endingCards.add(new EndingCard(
            "Your aunt saw how you handled the farm and used it against you in the court case she filed. You lost the case, just like how you lost the last piece of memory of your grandmother.", 
            null, 
            "bad_ending.png",
            null, null
        ));

        return endingCards;
    }
}
