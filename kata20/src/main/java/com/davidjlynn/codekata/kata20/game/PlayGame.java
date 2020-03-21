package com.davidjlynn.codekata.kata20.game;

import com.davidjlynn.codekata.kata20.game.components.Move;
import com.davidjlynn.codekata.kata20.game.components.PileEnum;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PlayGame {

  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_GREEN = "\u001B[32m";

  public GameState play(GameState gameState, PlayMode playMode) {
    Function<List<Move>, Move> moveFunction;
    switch (playMode) {
      case DUMB_SEQUENTIAL:
        moveFunction = (moveList) -> moveList.get(0);
        break;
      case RANDOM_MOVE:
        moveFunction = (moveList) -> moveList.get(new Random().nextInt(moveList.size()));
        break;
      case RANDOM_MOVE_NO_BACKTRACK:
        moveFunction =
            (moveList) -> {
              List<Move> filteredMoveList =
                  moveList.stream()
                      .filter(move -> move.getSource().getPileType() != PileEnum.FINISHED_PILE)
                      .collect(Collectors.toList());
              return filteredMoveList.get(new Random().nextInt(filteredMoveList.size()));
            };
        break;
      case ORDER_BY_DIRECTION:
        moveFunction =
            (moveList) -> {
              // ORDER MOVES
              // 1. To finished pile from Deck
              // 2. To finished pile from Play
              // 3. Random play piles
              // 4. Deck to play
              // 5. Flip
              // 6. Finished
              moveList.sort((o1, o2) -> toDirected(o1).compareTo(toDirected(o2)));
              return moveList.get(0);
            };
        break;
      default:
        throw new IllegalStateException("Invalid Game State.");
    }

    return playUsingStrategy(gameState, moveFunction);
  }

  private GameState playUsingStrategy(
      GameState gameState, Function<List<Move>, Move> chooseMoveFunction) {
    // Limit the number of moves
    for (int i = 0; i < 1500; i++) {

      if (gameState.hasWon()) {
        System.out.println(ANSI_GREEN + "Took " + (i + 1) + " attempts" + ANSI_RESET + " to win");
        break;
      }

      List<Move> moveList = gameState.getPossibleMoves();

      if (moveList.size() == 0) {
        System.out.println(ANSI_RED + "No more moves left!" + ANSI_RESET);
        break;
      }

      Move move = chooseMoveFunction.apply(moveList);

      gameState.doMove(move);
    }
    System.out.println(ANSI_RED + "Ran out of attempts!" + ANSI_RESET);

    return gameState;
  }

  private Integer toDirected(Move move) {
    switch (move.getSource().getPileType()) {
      case DECK:
        switch (move.getDestination().getPileType()) {
          case FINISHED_PILE:
            return 1;
          case PLAY_PILE:
            return 4;
        }
      case PLAY_PILE:
        switch (move.getDestination().getPileType()) {
          case FINISHED_PILE:
            return 2;
          case PLAY_PILE:
            return 3;
        }
      case FLIP_CARD:
        return 5;
      case FINISHED_PILE:
        return 6;
    }
    throw new IllegalStateException();
  }
}
