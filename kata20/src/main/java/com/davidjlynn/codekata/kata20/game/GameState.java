package com.davidjlynn.codekata.kata20.game;

import com.davidjlynn.codekata.kata20.cardmodel.Card;
import com.davidjlynn.codekata.kata20.cardmodel.CardNumber;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.IntStream;
import lombok.Getter;

public class GameState {

  private final Integer CARDS_PER_SUIT = CardNumber.values().length;

  private Deck deck = new Deck();

  private CardPile pile1 = new CardPile();
  private CardPile pile2 = new CardPile();
  private CardPile pile3 = new CardPile();
  private CardPile pile4 = new CardPile();
  private CardPile pile5 = new CardPile();
  private CardPile pile6 = new CardPile();
  private CardPile pile7 = new CardPile();

  private Map<Integer, CardPile> cardPileMap =
      Map.ofEntries(
          new AbstractMap.SimpleEntry<>(1, pile1),
          new AbstractMap.SimpleEntry<>(2, pile2),
          new AbstractMap.SimpleEntry<>(3, pile3),
          new AbstractMap.SimpleEntry<>(4, pile4),
          new AbstractMap.SimpleEntry<>(5, pile5),
          new AbstractMap.SimpleEntry<>(6, pile6),
          new AbstractMap.SimpleEntry<>(7, pile7));

  private FinishedPile finishedPile1 = new FinishedPile();
  private FinishedPile finishedPile2 = new FinishedPile();
  private FinishedPile finishedPile3 = new FinishedPile();
  private FinishedPile finishedPile4 = new FinishedPile();

  public GameState(Queue<Card> cardDeck) {
    // Deal deck
    IntStream.rangeClosed(1, 7)
        .forEach(
            pileNumber -> {
              cardPileMap.get(pileNumber).getFlippedCards().add(cardDeck.poll());
              IntStream.rangeClosed(pileNumber + 1, 7)
                  .forEach(i -> cardPileMap.get(i).getUnflippedCards().add(cardDeck.poll()));
            });
    deck.getUnflippedCards().addAll(cardDeck);
  }

  public Boolean hasWon() {
    return finishedPile1.hasAllCards()
        && finishedPile2.hasAllCards()
        && finishedPile3.hasAllCards()
        && finishedPile4.hasAllCards();
  }

  public void moveFromDeckToPile(CardPile cardPile) {
    cardPile.getFlippedCards().add(deck.getFlippedCards().get(deck.getFlippedCards().size()));
  }

  public void moveFromPileToFinished(CardPile cardPile, FinishedPile finishedPile) {
    finishedPile.cards.add(cardPile.getFlippedCards().get(cardPile.getFlippedCards().size()));
  }

  @Getter
  private class FinishedPile {
    List<Card> cards = new ArrayList<>();

    Boolean hasAllCards() {
      return cards.size() == CARDS_PER_SUIT;
    }

    Boolean cardCanBeAdded(Card card) {
      if (cards.size() == 0) {
        return card.getCardNumber() == CardNumber.ACE;
      }
      Card leadCard = cards.get(cards.size() - 1);
      return card.canBeAddedToCardFinishing(leadCard);
    }
  }

  @Getter
  private class Deck {
    List<Card> unflippedCards = new ArrayList<>();
    List<Card> flippedCards = new ArrayList<>();
  }

  @Getter
  private class CardPile {
    List<Card> unflippedCards = new ArrayList<>();
    List<Card> flippedCards = new ArrayList<>();

    Boolean cardCanBeAdded(Card card) {
      if (flippedCards.size() == 0) {
        return card.getCardNumber() == CardNumber.KING;
      }
      Card leadCard = flippedCards.get(flippedCards.size() - 1);
      return card.canBeAddedToCardInProgress(leadCard);
    }
  }
}
