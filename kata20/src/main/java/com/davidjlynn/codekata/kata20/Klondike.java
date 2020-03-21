package com.davidjlynn.codekata.kata20;

import static com.davidjlynn.codekata.kata20.game.PlayGame.ANSI_GREEN;
import static com.davidjlynn.codekata.kata20.game.PlayGame.ANSI_RED;
import static com.davidjlynn.codekata.kata20.game.PlayGame.ANSI_RESET;

import com.davidjlynn.codekata.kata20.cardmodel.Card;
import com.davidjlynn.codekata.kata20.cardmodel.CardNumber;
import com.davidjlynn.codekata.kata20.cardmodel.CardSuit;
import com.davidjlynn.codekata.kata20.game.GameResult;
import com.davidjlynn.codekata.kata20.game.GameState;
import com.davidjlynn.codekata.kata20.game.GameStatus;
import com.davidjlynn.codekata.kata20.game.PlayGame;
import com.davidjlynn.codekata.kata20.game.PlayMode;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Klondike {

  public void runGame() {

    // Run games
    List<GameResult> gameResults =
        IntStream.range(0, 100)
            .mapToObj(
                i -> {
                  GameState gameState = setupGame();
                  return new PlayGame().play(gameState, PlayMode.RANDOM_MOVE_NO_BACKTRACK);
                })
            .collect(Collectors.toList());

    // Find statistics
    Map<Boolean, List<GameResult>> winLossGames =
        gameResults.stream()
            .collect(
                Collectors.groupingBy(
                    (gameResult) -> gameResult.getGameStatus() == GameStatus.WON));

    System.out.println(
        ANSI_GREEN
            + "Games won is "
            + winLossGames.get(true).size()
            + ". "
            + ANSI_RED
            + "Games lost is "
            + winLossGames.get(false).size()
            + "."
            + ANSI_RESET
            + " This means a "
            + (((double) winLossGames.get(true).size() / (double) gameResults.size()) * 100)
            + "% win rate.");

    if (winLossGames.get(true).size() > 0) {
      Double averageNumberOfMoves =
          winLossGames.get(true).stream()
              .mapToInt(GameResult::getNumberOfMoves)
              .average()
              .orElseThrow(IllegalStateException::new);
      System.out.println(
          "For won games, the average moves were "
              + ANSI_GREEN
              + averageNumberOfMoves
              + ANSI_RESET
              + ".");
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
