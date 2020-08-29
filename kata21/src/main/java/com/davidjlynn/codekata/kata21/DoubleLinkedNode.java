package com.davidjlynn.codekata.kata21;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class DoubleLinkedNode implements KataNode {

  @NonNull private final String value;

  @Getter @Setter private DoubleLinkedNode previousNode;

  @Getter @Setter private DoubleLinkedNode nextNode;

  @Override
  public String value() {
    return value;
  }

  public DoubleLinkedNode getLastNode() {
    if (nextNode == null) {
      return this;
    } else {
      return nextNode.getLastNode();
    }
  }
}
