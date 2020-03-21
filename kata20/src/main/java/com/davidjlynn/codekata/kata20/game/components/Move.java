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

  public Boolean sourceEqualsDestination() {
    return source.getPileType() == destination.getPileType()
        && source.getPileNumber().equals(destination.getPileNumber());
  }

  public Boolean validPiles() {
    return source.getPileType().getValidDestinationPiles().contains(destination.getPileType());
  }

  public Boolean canBeMovedMultipleCards() {
    return source.getCardNumber() == null
        || source.getCardNumber() == 0
        || destination.getPileType().getSupportsAddingMultipleCards();
  }
}
