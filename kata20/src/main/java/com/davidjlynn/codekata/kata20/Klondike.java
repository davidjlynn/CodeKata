package com.davidjlynn.codekata.kata20;

import com.davidjlynn.codekata.kata20.cardmodel.Card;
import com.davidjlynn.codekata.kata20.cardmodel.CardNumber;
import com.davidjlynn.codekata.kata20.cardmodel.CardSuit;
import com.davidjlynn.codekata.kata20.game.GameState;
import com.davidjlynn.codekata.kata20.game.PlayGame;
import com.davidjlynn.codekata.kata20.game.PlayMode;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class Klondike {

  public void runGame() {

    for (int i = 0; i < 100; i++) {
      GameState gameState = setupGame();

      GameState completedGame = new PlayGame().play(gameState, PlayMode.ORDER_BY_DIRECTION);
    }
  }

  private GameState setupGame() {
    List<Card> cardDeck = createDeck();

    Collections.shuffle(cardDeck);

    Queue<Card> shuffledDeck = new LinkedList<>(cardDeck);

    return new GameState(shuffledDeck);
  }

  private List<Card> createDeck() {
    return Arrays.stream(CardNumber.values())
        .flatMap(
            cardNumber ->
                Arrays.stream(CardSuit.values()).map(cardSuit -> new Card(cardNumber, cardSuit)))
        .collect(Collectors.toList());
  }
}
