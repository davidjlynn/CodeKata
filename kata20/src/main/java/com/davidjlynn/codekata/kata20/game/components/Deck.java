package com.davidjlynn.codekata.kata20.game.components;

import com.davidjlynn.codekata.kata20.cardmodel.Card;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.Getter;

@Getter
public class Deck {
  private List<Card> unflippedCards = new ArrayList<>();
  private List<Card> flippedCards = new ArrayList<>();

  public boolean noMoreCards() {
    return unflippedCards.size() + flippedCards.size() == 0;
  }

  public void flipCard() {
    if (unflippedCards.size() != 0) {
      Card card = unflippedCards.get(unflippedCards.size() - 1);
      unflippedCards.remove(unflippedCards.size() - 1);
      flippedCards.add(card);
    } else if (flippedCards.size() != 0) {
      unflippedCards = flippedCards;
      flippedCards = new ArrayList<>();
      flipCard();
    }
  }

  public Card getCard() {
    Card card = peekCard().orElseThrow(() -> new IllegalStateException());
    flippedCards.remove(flippedCards.size() - 1);

    // We could have used the first card, try and replace it.
    if (flippedCards.isEmpty()){
      flipCard();
    }

    return card;
  }

  public Optional<Card> peekCard() {
    if (flippedCards.size() == 0) {
      return Optional.empty();
    }
    Card card = flippedCards.get(flippedCards.size() - 1);
    return Optional.of(card);
  }
}
