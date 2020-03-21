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
    Optional<Card> leadCard = peekCard();
    if (leadCard.isEmpty()) {
      return card.getCardNumber() == CardNumber.ACE;
    }
    return card.canBeAddedToCardFinishing(leadCard.get());
  }

  public void addCard(Card card) {
    if (!cardCanBeAdded(card)) {
      throw new CardCannotBeAdded();
    }
    cards.add(card);
  }

  public Card getCard() {
    Card card = peekCard().orElseThrow(() -> new IllegalStateException("Whoops"));
    cards.remove(card);
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
