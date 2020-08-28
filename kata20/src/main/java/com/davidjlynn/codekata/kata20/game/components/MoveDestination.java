package com.davidjlynn.codekata.kata20.game.components;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MoveDestination {
  private final PileEnum pileType;
  private final Integer pileNumber;
}
