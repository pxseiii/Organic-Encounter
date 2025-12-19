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

    private static Card createBranchingPlotCard(
        String situation, 
        String title, 
        String icon, 
        String leftText, StatsChange leftStatsChange, Card leftNextCard, 
        String rightText, StatsChange rightStatsChange, Card rightNextCard){
        CardChoice left = new CardChoice(leftText, leftStatsChange, leftNextCard);
        CardChoice right = new CardChoice(rightText, rightStatsChange, rightNextCard);

        return new PlotCard(situation, title, icon, left, right);
    }

    // solely for branching in intro
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

        introCards.add(createCard(
            "Are you the new farm owner? Hi, I’m Mayor Edna. It’s a pleasure to welcome you here!",
            "Mayor Edna | Mayor",
            "mayor_v1.png",
            "Nice to meet you.", null,
            "Hello.", null,
            CardType.INTRO
        ));

        introCards.add(createCard(
            "Condolences about your Lola Emelda. She was an integral part of our community.",
            "Mayor Edna | Mayor",
            "mayor_v1.png",
            "Thank you.", null,
            "She was, wasn’t she?", null,
            CardType.INTRO
        ));

        introCards.add(createCard(
            "I wanted to ask you something. We have a park renovation coming up. Would you mind donating to the town? Any amount will do.",
            "Mayor Edna | Mayor",
            "mayor_v1.png",
            "Donate generously.", new StatsChange(0, 20, -20),
            "Ignore donation.", new StatsChange(0, -20, 10),
            CardType.INTRO
        ));

        return introCards;
    }

    public static List<Card> getRandomDeck(){
        List<Card> randomCards = new ArrayList<>();

        randomCards.add(createCard(
            "According to PAGASA, the Typhoon Mira has entered the Philippine Area of Responsibility.",
            "Ahtisa | Reporter",
            "reporter_v1.png",
            "Prepare for it", new StatsChange(10, 0, -20),
            "Let it be", new StatsChange(-20, 0, 0),
            CardType.RANDOM
        ));

        randomCards.add(createCard(
            "The flood is currently at the warning level and is expected to continue rising for the next 24 hours.",
            "Ahtisa | Reporter",
            "reporter_v1.png",
            "Evacuate workers and save equipment", new StatsChange(10, 10, -10),
            "Ignore", new StatsChange(-20, -10, 0),
            CardType.RANDOM
        ));

        randomCards.add(createCard(
            "I heard from the people in town that there is a pest outbreak.",
            "Mang Gido | Caretaker",
            "caretaker_v1.png",
            "Buy emergency pesticides", new StatsChange(10, 0, -10),
            "Take the risk", new StatsChange(-20, 0, 0),
            CardType.RANDOM
        ));

        randomCards.add(createCard(
            "Asensado ka na talaga ah… Baka may 500 ka diyan? Hiram sana ako.",
            "Marites | Neighbor",
            "marites_v3.png",
            "Lend her money", new StatsChange(0, 10, -10),
            "Refuse to lend her money", new StatsChange(0, -20, 0),
            CardType.RANDOM
        ));

        randomCards.add(createCard(
            "I found a fertilizer online that enhances crop production.",
            "Mang Gido | Caretaker",
            "caretaker_v1.png",
            "Purchase", new StatsChange(10, 0, -10),
            "Ignore", new StatsChange(0, 0, 0),
            CardType.RANDOM
        ));

        randomCards.add(createCard(
            "There will be a townhall meeting tomorrow for the town's development.",
            "Mayor Edna | Mayor",
            "mayor_v1.png",
            "Attend", new StatsChange(0, 20, 0),
            "I dislike socializing", new StatsChange(0, -20, 0),
            CardType.RANDOM
        ));

        randomCards.add(createCard(
            "Mars may 1 kilong mangga ako dito. Bibili ka?",
            "Marites | Villager",
            "marites_v3.png",
            "Buy", new StatsChange(0, 10, -10),
            "Dont buy", new StatsChange(0, -10, 0),
            CardType.RANDOM
        ));

        randomCards.add(createCard(
            "Mahina na yung kalabaw natin.",
            "Mang Gido | Caretaker",
            "caretaker_v1.png",
            "Buy kalabaw", new StatsChange(10, 0, -10),
            "Invest in new technology", new StatsChange(20, 0, -20),
            CardType.RANDOM
        ));

        return randomCards;
    }

    public static List<Card> getPlotDeck(){
        List<Card> plotCards = new ArrayList<>();

        plotCards.add(createCard(
            "Mayroon kaming free training for sustainable farming. Gusto niyo bang pumunta?",
            "Sir Jonnel | DA Representative",
            "DArep_v1.png",
            "Let them inspect.", new StatsChange(10, 0, 10),
            "Block them.", new StatsChange(-10, -20, 0),
            CardType.PLOT
        ));

        Card plot1_choice1 = createBranchingPlotCard(
            "Lola Emelda is a good friend of my lola. I’m Anton. I didn’t manage to send my condolences last time, so I thought I’d come by today.",
            "Anton | Friend",
            "lawyer_v1.png",
            "Thank you, I have some questions about her.", null, null,
            "Thank you, but I’m busy right now.", null, null
        );

        Card plot1_choice2 = createBranchingPlotCard(
            "Don't worry, I am not a scammer! I know your lola, and I just want to send my condolences to you and your family. I'll drop by anytime again soon.",
            "Anton | Friend",
            "lawyer_v1.png",
            "Ignore.", null, null,
            "See you.", null, null
        );

        Card plot1 = createBranchingPlotCard(
            "Excuse me, this is Lola Emelda’s house, right?",
            "Anton | Friend",
            "lawyer_v1.png",
            "Yes, may I know who you are?", new StatsChange(0, 10, 0), plot1_choice1,
            "Do I know you? No solicitors, I’m busy right now.", new StatsChange(0, -10, 0), plot1_choice2
        );

        plotCards.add(plot1);

        plotCards.add(createCard(
            "Nakita ng isa sa mga trabahador na may nagkukubli sa sakahan. Gusto mo ba magsampa ng kaso?",
            "Mang Gido | Caretaker",
            "caretaker_v1.png",
            "File a lawsuit.", new StatsChange(0, 10, 20),
            "Ignore.", new StatsChange(-10, 0, 0),
            CardType.PLOT
        ));

        plotCards.add(createCard(
            "The fiesta is fast approaching. We need sponsors for its preparation.",
            "Mayor Edna | Mayor",
            "mayor_v1.png",
            "Donate generously.", new StatsChange(0, 20, -20),
            "Don't donate.", new StatsChange(0, -20, 10),
            CardType.PLOT
        ));

        Card plot3_branch3 = createBranchingPlotCard(
            "I’m just asking out of concern. Since I work in legal management, I can help if you want.",
            "Anton | Friend",
            "lawyer_v1.png",
            "I can handle it myself, thanks.", new StatsChange(-10, 0, 0), null,
            "I’ll keep that in mind, thanks.", new StatsChange(10, 10, 0), null
        );

        Card plot2_branch2 = createBranchingPlotCard(
            "Really? So, have you gotten your routine check for your land title? Or, do you have your Lola’s will that proves the farm is yours?",
            "Anton | Friend",
            "lawyer_v1.png",
            "Why?", null, plot3_branch3,
            "I don’t think I need that.", new StatsChange(-10, 10, 0), null
        );

        Card plot2_branch1 = createBranchingPlotCard(
            "I can see that… Anyway, ever since Lola died, who’s been in charge here?",
            "Anton | Friend",
            "lawyer_v1.png",
            "I am.", new StatsChange(0, 20, 0), plot2_branch2,
            "I’m trying my best to take care of things.", new StatsChange(0, 10, 0), plot2_branch2
        );

        Card plot2 = createBranchingPlotCard(
            "Hey, just dropping by again. How are you settling in?",
            "Anton | Friend",
            "lawyer_v1.png",
            "It’s going really well.", null, plot2_branch1,
            "Honestly, it’s been tough.", null, plot2_branch1
        );

        plotCards.add(plot2);

        plotCards.add(createCard(
            "There will be an increase in land tax effective tomorrow.",
            "Mayor Edna | Mayor",
            "mayor_v1.png",
            "Compromise.", new StatsChange(0, 10, -10),
            "Don't donate.", new StatsChange(0, -20, 10),
            CardType.PLOT
        ));

        plotCards.add(createCard(
            "Hoy, ikaw! Di ka raw nagbabayad sa mga trabahador mo.",
            "Marites | Neighbor",
            "marites_v3.png",
            "Deny.", new StatsChange(0, -10, 0),
            "Oppose.", new StatsChange(0, 10, -10),
            CardType.PLOT
        ));

        plotCards.add(createCard(
            "Farmers are advised to prepare as these birds reportedly carry bird flu.",
            "Ahtisa | Reporter",
            "reporter_v1.png",
            "Prepare for it.", new StatsChange(10, 0, 10),
            "Refuse to believe it.", new StatsChange(-20, 0, -20),
            CardType.PLOT
        ));

        plotCards.add(createCard(
            "I’m here to conduct our quarterly field inspection. Would you mind if I look around for a while?",
            "Sir Jonnel | DA Representative",
            "DArep_v1.png",
            "Let them inspect.", new StatsChange(10, 10, 0),
            "Block them.", new StatsChange(-10, -20, 0),
            CardType.PLOT
        ));
        
        Card plot3_choice2_zoomed = createBranchingPlotCard(
            "“Mrs. Cynthia: I want this resolved immediately, Atty.”",
            "",
            "chat_zoomed.png",
            "...", null, null,
            "...", null, null
        );

        Card plot3_choice2_full = createBranchingPlotCard(
            "...",
            "",
            "chat_full.png",
            "...", null, plot3_choice2_zoomed,
            "...", null, plot3_choice2_zoomed
        );

        Card plot3_choice2_cp = createBranchingPlotCard(
            "...",
            "",
            "cp_blank.png",
            "...", null, plot3_choice2_full,
            "...", null, plot3_choice2_full
        );

        Card plot3_choice1 = createBranchingPlotCard(
            "Yes, we know each other. I told you, I’ve been here before, so I’ve seen her too.",
            "Anton | Friend",
            "lawyer_v1.png",
            "Believe him", new StatsChange(0, 10, 0), null,
            "Be suspicious.", new StatsChange(0, -10, 0), null
        );

        Card plot3_branch1 = createBranchingPlotCard(
            "Tita mo yun diba? Ang panganay ni Lola Emelda?",
             "Mang Gido | Caretaker",
            "caretaker_v1.png",
            "Keep it and confront Anton.", null, plot3_choice1,
            "Keep it and snoop on Anton’s phone.", null, plot3_choice2_cp 
        );

        Card plot3 = createBranchingPlotCard(
            "Sa kaibigan mo ba itong cellphone? May tumatawag kanina, Mrs. Cynthia raw.",
            "Mang Gido | Caretaker",
            "caretaker_v1.png",
            "...", null, plot3_branch1,
            "...", null, plot3_branch1
        );

        plotCards.add(plot3);

        plotCards.add(createCard(
            "Mars wala na akong pambili ng bigas. Pwede ba pautang?",
            "Marites | Neighbor",
            "marites_v3.png",
            "Lend her money.", new StatsChange(0, 10, -10),
            "Decline.", new StatsChange(0, -20, 10),
            CardType.PLOT
        ));

        plotCards.add(createCard(
            "This season, we need to decide how much water to allocate for the farmers. What do you think we should do?",
            "Mayor Edna | Mayor",
            "mayor_v1.png",
            "Share more water.", new StatsChange(-10, 20, 0),
            "Keep full supply.", new StatsChange(20, -10, 10),
            CardType.PLOT
        ));

        plotCards.add(createCard(
            "May seed subsidy po kami for your farm. Gusto niyo ba i-accept?",
            "Sir Jonnel | DA Representative",
            "DArep_v1.png",
            "Accept.", new StatsChange(20, 10, 0),
            "Decline.", new StatsChange(-10, 20, 10),
            CardType.PLOT
        ));

        plotCards.add(createCard(
            "Naririnig ko nung nakaraan sa mga kumare ko na may kamag-anak mo raw na nagbabalak kunin ang lupa niyo. Totoo ba? Paano ka niyan?",
            "Marites | Neighbor",
            "marites_v3.png",
            "Give her information.", new StatsChange(0, 20, 0),
            "Tell her to mind her own business.", new StatsChange(0, -20, 0),
            CardType.PLOT
        ));

        plotCards.add(createCard(
            "May option kayo: chemical pesticide o organic methods. Ano pipiliin niyo?",
            "Sir Jonnel | DA Representative",
            "DArep_v1.png",
            "Use chemical pesticide.", new StatsChange(10, -10, 0),
            "Decline.", new StatsChange(-10, 20, 0),
            CardType.PLOT
        ));

        Card plot4_branch5 = createBranchingPlotCard(
            "HEARING ON-GOING.\nAwaiting the outcome of the lawsuit.",
            "Judge",
            "law_scales.png",
            "", null, null,
            "", null, null
        );

        Card plot4_branch4 = createBranchingPlotCard(
            "HEARING:\n“Case filed: Illegal land ownership. The court will take into account the current condition of the land, the financial capability of the current person-in-charge to maintain it, and the sentiments of the witnesses from the village taking the stand.",
            "Judge",
            "law_scales.png",
            "", null, plot4_branch5,
            "", null, plot4_branch5
        );

        Card plot4_branch3 = createBranchingPlotCard(
            "We already filed a case about this. Magkita na lang tayo sa munisipyo.",
            "Tita Cynthia | Aunt",
            "aunt_v1.png",
            "Agree to attend.", new StatsChange(0, 10, 0), plot4_branch4,
            "Tell her to stop threatening you.", new StatsChange(0, -10, 0), plot4_branch4
        );

        Card plot4_branch2 = createBranchingPlotCard(
            "In the absence of your grandmother’s last will, the farm legally passed to Ma’am Cynthia. Unless it is proven that she cannot take care of it on her own.",
            "Atty. Anton | Attorney",
            "lawyer_v1.png",
            "What the hell, Anton?", null, plot4_branch3,
            "I never expected you to betray me, Anton.", null, plot4_branch3
        );

        Card plot4_branch1 = createBranchingPlotCard(
            "I’m the rightful heiress to this land — Nanay’s firstborn. Lahat ng ari‑arian niya ay legally mine, including your farm. Ikaw? Anak ka lang ng paborito niyang anak. That’s why she only talked and left nothing in writing. Words don’t matter without proof.",
            "Tita Cynthia | Aunt",
            "aunt_v1.png",
            "Lola Emelda left it for me to maintain!", null, plot4_branch2,
            "You’re lying; you weren’t even there when she took her last breath!", null, plot4_branch2
        );

        Card plot4 = createBranchingPlotCard(
            "How’s the farm doing? It’s going to be mine soon—did you at least take care of it?",
            "Tita Cynthia | Aunt",
            "aunt_v1.png",
            "What do you mean?", null, plot4_branch1,
            "How is it your land?", null, plot4_branch1
        );

        plotCards.add(plot4);

        return plotCards;
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
