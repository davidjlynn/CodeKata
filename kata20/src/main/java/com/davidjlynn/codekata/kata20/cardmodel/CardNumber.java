package com.davidjlynn.codekata.kata20.cardmodel;

import lombok.Getter;

@Getter
public enum CardNumber {
  ACE,
  TWO,
  THREE,
  FOUR,
  FIVE,
  SIX,
  SEVEN,
  EIGHT,
  NINE,
  TEN,
  JACK,
  QUEEN,
  KING;

  static {
    ACE.nextCardNumber = TWO;
    TWO.nextCardNumber = THREE;
    THREE.nextCardNumber = FOUR;
    FOUR.nextCardNumber = FIVE;
    FIVE.nextCardNumber = SIX;
    SIX.nextCardNumber = SEVEN;
    SEVEN.nextCardNumber = EIGHT;
    EIGHT.nextCardNumber = NINE;
    NINE.nextCardNumber = TEN;
    TEN.nextCardNumber = JACK;
    JACK.nextCardNumber = QUEEN;
    QUEEN.nextCardNumber = KING;
    KING.nextCardNumber = null;

    ACE.previousCardNumber = null;
    TWO.previousCardNumber = ACE;
    THREE.previousCardNumber = TWO;
    FOUR.previousCardNumber = THREE;
    FIVE.previousCardNumber = FOUR;
    SIX.previousCardNumber = FIVE;
    SEVEN.previousCardNumber = SIX;
    EIGHT.previousCardNumber = SEVEN;
    NINE.previousCardNumber = EIGHT;
    TEN.previousCardNumber = NINE;
    JACK.previousCardNumber = TEN;
    QUEEN.previousCardNumber = JACK;
    KING.previousCardNumber = QUEEN;
  }

  private CardNumber nextCardNumber;
  private CardNumber previousCardNumber;
}
