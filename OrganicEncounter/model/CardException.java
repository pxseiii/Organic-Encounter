package model;

import annotation.ClassInfo;

@ClassInfo(
    mainAuthor = "Mika",
    className = "CardException",
    pillarsUsed = {},
    solidUsed = {"SRP"}
)

class CardException extends RuntimeException{
    public CardException() {}

    public CardException(String message)
    {
        super(message);
    }
}
