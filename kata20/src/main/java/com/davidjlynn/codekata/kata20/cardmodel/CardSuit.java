package com.davidjlynn.codekata.kata20.cardmodel;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CardSuit {
  CLUB(CardColour.BLACK),
  HEART(CardColour.RED),
  SPADE(CardColour.BLACK),
  DIAMOND(CardColour.RED);

  private final CardColour cardColour;
}
