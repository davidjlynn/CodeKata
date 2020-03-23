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
    Arrays.stream(PlayMode.values()).filter(PlayMode::getRunGame).forEach(this::runGame);
  }

  public void runGame(PlayMode playMode) {

    // Run games
    List<GameResult> gameResults =
        IntStream.range(0, 100)
            .mapToObj(
                i -> {
                  GameState gameState = setupGame();
                  return new PlayGame().play(gameState, playMode);
                })
            .collect(Collectors.toList());

    // Find statistics
    Map<Boolean, List<GameResult>> winLossGames =
        gameResults.stream()
            .collect(
                Collectors.groupingBy(
                    (gameResult) -> gameResult.getGameStatus() == GameStatus.WON));

    int wonGames = ((winLossGames.get(true) != null) ? winLossGames.get(true).size() : 0);
    int lostGames = ((winLossGames.get(false) != null) ? winLossGames.get(false).size() : 0);
    double winRate = (((double) wonGames / (double) gameResults.size()) * 100);
    System.out.println(
        "For game type "
            + playMode.name()
            + ": "
            + ANSI_GREEN
            + "Games won is "
            + wonGames
            + ". "
            + ANSI_RED
            + "Games lost is "
            + lostGames
            + "."
            + ANSI_RESET
            + " This means a "
            + winRate
            + "% win rate.");

    if (wonGames > 0) {
      double averageNumberOfMoves =
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
