package com.davidjlynn.codekata.kata20.game.components;

import com.davidjlynn.codekata.kata20.cardmodel.Card;
import com.davidjlynn.codekata.kata20.cardmodel.CardNumber;
import com.davidjlynn.codekata.kata20.game.CardCannotBeAdded;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.Getter;

@Getter
public class FinishedPile {

  private final Integer CARDS_PER_SUIT = CardNumber.values().length;

  private List<Card> cards = new ArrayList<>();

  public Boolean hasAllCards() {
    return cards.size() == CARDS_PER_SUIT;
  }

  public Boolean cardCanBeAdded(Card card) {
    if (cards.size() == 0) {
      return card.getCardNumber() == CardNumber.ACE;
    }
    Card leadCard = cards.get(cards.size() - 1);
    return card.canBeAddedToCardFinishing(leadCard);
  }

  public void addCard(Card card) {
    if (!cardCanBeAdded(card)) {
      throw new CardCannotBeAdded();
    }
    cards.add(card);
  }

  public Card getCard() {
    Card card = peekCard().orElseThrow(() -> new IllegalStateException("Whoops"));
    cards.remove(cards.size() - 1);
    return card;
  }

  public Optional<Card> peekCard() {
    if (cards.size() == 0) {
      return Optional.empty();
    }
    Card card = cards.get(cards.size() - 1);
    return Optional.of(card);
  }
}
