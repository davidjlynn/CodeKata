package com.davidjlynn.codekata.kata20.cardmodel;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Card {

  private final CardNumber cardNumber;
  private final CardSuit cardSuit;

  public Boolean canBeAddedToCardFinishing(Card card) {
    return card.cardNumber.getNextCardNumber() == cardNumber && card.cardSuit == cardSuit;
  }

  public Boolean canBeAddedToCardInProgress(Card card) {
    return card.cardNumber.getPreviousCardNumber() == cardNumber
        && card.cardSuit.getCardColour() != cardSuit.getCardColour();
  }
}
