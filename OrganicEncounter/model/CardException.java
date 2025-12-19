package model;

import annotation.ClassInfo;

@ClassInfo(
    mainAuthor = "De Guzman",
    className = "CardException",
    pillarsUsed = {"Inheritance"},
    solidUsed = {"SRP"}
)

class CardException extends RuntimeException{
    public CardException() {}

    public CardException(String message){
        super(message);
    }
}
