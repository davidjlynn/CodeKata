package com.davidjlynn.codekata.kata21;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
public class SingleLinkedNode implements KataNode {

  @NonNull private final String value;

  @Setter private SingleLinkedNode nextNode;
}
