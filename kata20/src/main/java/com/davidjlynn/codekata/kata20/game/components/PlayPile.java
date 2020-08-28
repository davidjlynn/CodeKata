package com.davidjlynn.codekata.kata20.game.components;

import com.davidjlynn.codekata.kata20.cardmodel.Card;
import com.davidjlynn.codekata.kata20.cardmodel.CardNumber;
import com.davidjlynn.codekata.kata20.game.CardCannotBeAdded;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.Getter;

@Getter
public class PlayPile {
  private final List<Card> unflippedCards = new ArrayList<>();
  private final List<Card> flippedCards = new ArrayList<>();

  public void flipCard() {
    Card card = unflippedCards.get(unflippedCards.size() - 1);
    unflippedCards.remove(unflippedCards.size() - 1);
    flippedCards.add(card);
  }

  public Boolean cardCanBeAdded(Card card) {
    if (flippedCards.size() == 0) {
      return card.getCardNumber() == CardNumber.KING;
    }
    Card leadCard = flippedCards.get(flippedCards.size() - 1);
    return card.canBeAddedToCardInProgress(leadCard);
  }

  public void addCard(Card card) {
    if (!cardCanBeAdded(card)) {
      throw new CardCannotBeAdded();
    }
    flippedCards.add(card);
  }

  public void addCards(List<Card> cards) {
    cards.forEach(this::addCard);
  }

  public List<Card> getCards(Integer cardNumber) {

    List<Card> cards = new ArrayList<>();
    for (int i = 0; i <= cardNumber; i++) {
      Card card = getCard();
      cards.add(card);
    }

    Collections.reverse(cards);

    return cards;
  }

  public Card getCard() {
    Card card = peekCard(0);
    flippedCards.remove(card);

    if (flippedCards.size() == 0 && unflippedCards.size() > 0) {
      flipCard();
    }
    return card;
  }

  public Card peekCard(Integer cardNumber) {
    return flippedCards.get(flippedCards.size() - 1 - cardNumber);
  }
}
