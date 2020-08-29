package com.davidjlynn.codekata.kata21;

public interface KataList {

  KataNode find(String value);

  String[] values();

  void add(String value);

  void delete(KataNode node);
}
