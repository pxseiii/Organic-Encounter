package view;
import annotation.ClassInfo;

@ClassInfo(
    mainAuthor = "Kaindoy",
    className = "SwipeListener",
    pillarsUsed = {"Abstraction"},
    solidUsed = {"SRP, DIP"}
)

/* 
    Description / Author Comments

    Purpose: 
    * defines an abstraction for handling swipe actions (L/R)
    * allows Presenter to implement the behavior
*/

public interface SwipeListener {
    void onSwipeLeft();
    void onSwipeRight();
}
