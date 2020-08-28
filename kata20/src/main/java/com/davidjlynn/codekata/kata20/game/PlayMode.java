package com.davidjlynn.codekata.kata20.game;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PlayMode {
  DUMB_SEQUENTIAL(false),
  DUMB_SEQUENTIAL_NO_BACKTRACK(false),
  RANDOM_MOVE(false),
  RANDOM_MOVE_NO_BACKTRACK(true),
  ORDER_BY_DIRECTION(false),
  ORDER_BY_DIRECTION_NO_BACKTRACK(true);

  private final Boolean runGame;
}
