package view;

public interface HoverListener {
    void onCardHovered(int factorIndex); // 0=health, 1=rep, 2=money, -1=none
    void onCardHoverEnded();
}