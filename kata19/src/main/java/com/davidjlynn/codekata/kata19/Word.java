package com.davidjlynn.codekata.kata19;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Word {

  @NonNull private final String wordText;

  protected List<Word> siblings = new ArrayList<>();

  public void addSibling(Word sibling) {
    siblings.add(sibling);
    sibling.siblings.add(this);
  }

  public Boolean isOneCharacterFrom(Word comparing) {
    int charDiff = 0;
    for (int index = 0; index < wordText.length(); index++) {
      if (wordText.charAt(index) != comparing.wordText.charAt(index)) {
        charDiff++;
      }

      if (charDiff > 1) {
        return false;
      }
    }

    return charDiff == 1;
  }
}
