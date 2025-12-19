package view;
import annotation.ClassInfo;

@ClassInfo(
    mainAuthor = "",
    className = "SwipeListener",
    pillarsUsed = {},
    solidUsed = {}
)

// for user-input only, Presenter implements the interface
public interface SwipeListener {
    void onSwipeLeft();
    void onSwipeRight();
}
