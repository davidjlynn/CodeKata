package com.davidjlynn.codekata.kata20.game.components;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Move {
  @NonNull private MoveSource source;
  private MoveDestination destination;
}
