package com.davidjlynn.codekata.kata20.game;

import com.davidjlynn.codekata.kata20.cardmodel.Card;
import com.davidjlynn.codekata.kata20.game.components.Deck;
import com.davidjlynn.codekata.kata20.game.components.FinishedPile;
import com.davidjlynn.codekata.kata20.game.components.Move;
import com.davidjlynn.codekata.kata20.game.components.MoveDestination;
import com.davidjlynn.codekata.kata20.game.components.MoveSource;
import com.davidjlynn.codekata.kata20.game.components.PileEnum;
import com.davidjlynn.codekata.kata20.game.components.PlayPile;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GameState {

  private Deck deck = new Deck();

  private PlayPile pile1 = new PlayPile();
  private PlayPile pile2 = new PlayPile();
  private PlayPile pile3 = new PlayPile();
  private PlayPile pile4 = new PlayPile();
  private PlayPile pile5 = new PlayPile();
  private PlayPile pile6 = new PlayPile();
  private PlayPile pile7 = new PlayPile();

  private Map<Integer, PlayPile> cardPileMap =
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

  private Map<Integer, FinishedPile> finishPileMap =
      Map.ofEntries(
          new AbstractMap.SimpleEntry<>(1, finishedPile1),
          new AbstractMap.SimpleEntry<>(2, finishedPile2),
          new AbstractMap.SimpleEntry<>(3, finishedPile3),
          new AbstractMap.SimpleEntry<>(4, finishedPile4));

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
    deck.flipCard();
  }

  public Boolean hasWon() {
    return finishedPile1.hasAllCards()
        && finishedPile2.hasAllCards()
        && finishedPile3.hasAllCards()
        && finishedPile4.hasAllCards();
  }

  public void moveFromDeckToPile(PlayPile playPile) {
    playPile.addCard(deck.getCard());
  }

  public void moveFromDeckToFinishedPile(FinishedPile finishedPile) {
    finishedPile.addCard(deck.getCard());
  }

  public void moveFromFinishedPileToPile(FinishedPile finishedPile, PlayPile playPile) {
    playPile.addCard(finishedPile.getCard());
  }

  public void moveFromPileToFinished(PlayPile playPile, FinishedPile finishedPile) {
    finishedPile.addCard(playPile.getCard());
  }

  public void moveFromPileToPile(
      PlayPile playPile, Integer cardNumber, PlayPile destinationPlayPile) {
    List<Card> cards = playPile.getCards(cardNumber);
    destinationPlayPile.addCards(cards);
  }

  public List<Move> getPossibleMoves() {
    // Find all end destinations
    List<MoveDestination> destinations =
        cardPileMap.keySet().stream()
            .map(pileNumber -> new MoveDestination(PileEnum.PLAY_PILE, pileNumber))
            .collect(Collectors.toList());
    destinations.addAll(
        finishPileMap.keySet().stream()
            .map(pileNumber -> new MoveDestination(PileEnum.FINISHED_PILE, pileNumber))
            .collect(Collectors.toList()));

    // Find all sources
    List<MoveSource> sources =
        finishPileMap.keySet().stream()
            .map(pileNumber -> new MoveSource(PileEnum.FINISHED_PILE, pileNumber))
            .collect(Collectors.toList());
    sources.add(new MoveSource(PileEnum.DECK));
    sources.addAll(
        cardPileMap.keySet().stream()
            .map(
                pileNumber ->
                    IntStream.range(0, cardPileMap.get(pileNumber).getFlippedCards().size())
                        .boxed()
                        .map(i -> new MoveSource(PileEnum.PLAY_PILE, pileNumber, i))
                        .collect(Collectors.toList()))
            .flatMap(Collection::stream)
            .collect(Collectors.toList()));

    // Find all valid moves
    List<Move> moves =
        destinations.stream()
            .flatMap(destination -> sources.stream().map(source -> new Move(source, destination)))
            .filter(this::isValidMove)
            .collect(Collectors.toList());

    if (!deck.noMoreCards()) {
      moves.add(new Move(new MoveSource(PileEnum.FLIP_CARD)));
    }

    return moves;
  }

  private Boolean isValidMove(Move move) {
    Card card;
    switch (move.getSource().getPileType()) {
      case DECK:
        card = deck.peekCard().orElseThrow(IllegalStateException::new);
        break;
      case PLAY_PILE:
        card =
            cardPileMap
                .get(move.getSource().getPileNumber())
                .peekCard(move.getSource().getCardNumber());
        break;
      case FINISHED_PILE:
        Optional<Card> optionalCard =
            finishPileMap.get(move.getSource().getPileNumber()).peekCard();
        if (optionalCard.isEmpty()) {
          return false;
        }
        card = optionalCard.get();
        break;
      default:
        throw new RuntimeException("IMPOSSIBLE");
    }

    switch (move.getDestination().getPileType()) {
      case FINISHED_PILE:
        return finishPileMap.get(move.getDestination().getPileNumber()).cardCanBeAdded(card);
      case PLAY_PILE:
        return cardPileMap.get(move.getDestination().getPileNumber()).cardCanBeAdded(card);
    }
    throw new RuntimeException("Naaaah");
  }

  public void doMove(Move move) {
    switch (move.getSource().getPileType()) {
      case FLIP_CARD:
        deck.flipCard();
        break;
      case DECK:
        switch (move.getDestination().getPileType()) {
          case PLAY_PILE:
            moveFromDeckToPile(cardPileMap.get(move.getDestination().getPileNumber()));
            break;
          case FINISHED_PILE:
            moveFromDeckToFinishedPile(finishPileMap.get(move.getDestination().getPileNumber()));
            break;
          default:
            throw new IllegalStateException("I fooked it 3");
        }
        break;
      case FINISHED_PILE:
        moveFromFinishedPileToPile(
            finishPileMap.get(move.getSource().getPileNumber()),
            cardPileMap.get(move.getDestination().getPileNumber()));
        break;
      case PLAY_PILE:
        switch (move.getDestination().getPileType()) {
          case FINISHED_PILE:
            moveFromPileToFinished(
                cardPileMap.get(move.getSource().getPileNumber()),
                finishPileMap.get(move.getDestination().getPileNumber()));
            break;
          case PLAY_PILE:
            moveFromPileToPile(
                cardPileMap.get(move.getSource().getPileNumber()),
                move.getSource().getCardNumber(),
                cardPileMap.get(move.getDestination().getPileNumber()));
            break;
          default:
            throw new IllegalStateException("I fooked it 2");
        }
        break;
      default:
        throw new IllegalStateException("I fooked it");
    }
  }
}
