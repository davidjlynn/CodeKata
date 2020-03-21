package com.davidjlynn.codekata.kata20.game.components;

import java.util.Collections;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum PileEnum {
  FLIP_CARD,
  DECK,
  PLAY_PILE,
  FINISHED_PILE;

  private List<PileEnum> validDestinationPiles;
  private Boolean supportsAddingMultipleCards;

  static {
    FLIP_CARD.validDestinationPiles = Collections.emptyList();
    DECK.validDestinationPiles = List.of(PLAY_PILE, FINISHED_PILE);
    PLAY_PILE.validDestinationPiles = List.of(PLAY_PILE, FINISHED_PILE);
    FINISHED_PILE.validDestinationPiles = List.of(PLAY_PILE);

    FLIP_CARD.supportsAddingMultipleCards = Boolean.FALSE;
    DECK.supportsAddingMultipleCards = Boolean.FALSE;
    PLAY_PILE.supportsAddingMultipleCards = Boolean.TRUE;
    FINISHED_PILE.supportsAddingMultipleCards = Boolean.FALSE;
  }
}
