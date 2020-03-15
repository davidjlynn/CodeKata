package com.davidjlynn.codekata.kata20.game.components;

import lombok.Getter;

@Getter
public class MoveSource {
  private PileEnum pileType;
  private Integer pileNumber;
  private Integer cardNumber;

  public MoveSource(PileEnum pileType) {
    if (!(pileType == PileEnum.DECK || pileType == PileEnum.FLIP_CARD)) {
      throw new RuntimeException("Wrong Type");
    }
    this.pileType = pileType;
  }

  public MoveSource(PileEnum pileType, Integer pileNumber) {
    if (pileType != PileEnum.FINISHED_PILE) {
      throw new RuntimeException("Wrong Type");
    }
    this.pileType = pileType;
    this.pileNumber = pileNumber;
  }

  public MoveSource(PileEnum pileType, Integer pileNumber, Integer cardNumber) {
    if (pileType != PileEnum.PLAY_PILE) {
      throw new RuntimeException("Wrong Type");
    }
    this.pileType = pileType;
    this.pileNumber = pileNumber;
    this.cardNumber = cardNumber;
  }
}
