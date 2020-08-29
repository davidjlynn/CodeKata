package com.davidjlynn.codekata.kata21;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class SingleLinkedNode implements KataNode {

  @NonNull private final String value;

  @Getter @Setter private SingleLinkedNode nextNode;

  @Override
  public String value() {
    return value;
  }

  public SingleLinkedNode getLastNode() {
    if (nextNode == null) {
      return this;
    } else {
      return nextNode.getLastNode();
    }
  }
}
