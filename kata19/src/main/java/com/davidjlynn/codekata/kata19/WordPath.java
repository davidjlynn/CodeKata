package com.davidjlynn.codekata.kata19;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.Getter;

@Getter
public class WordPath {

  public Word getCurrentWord() {
    return wordPath.get(wordPath.size() - 1);
  }

  private List<Word> wordPath;

  public WordPath(Word word) {
    this.wordPath = Collections.singletonList(word);
  }

  public WordPath(Word word, WordPath previousWordPath) {
    // Shallow copy of wordPath from previous object.
    this.wordPath = new ArrayList<>(previousWordPath.getWordPath());
    this.wordPath.add(word);
  }
}
