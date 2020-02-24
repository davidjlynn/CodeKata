package com.davidjlynn.codekata.kata19;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordChains {

  public List<String> findWordChain(String startWordText, String endWordText)
      throws IOException, URISyntaxException {

    // Validate Input
    if (startWordText.length() != endWordText.length()) {
      throw new IllegalStateException("Lengths of words are not equal");
    }

    // Read Text File, removing words of incorrect size.
    List<String> wordTexts = readFromInputStream("words_alpha.txt", startWordText.length());

    // Turn into word graph
    List<Word> words = wordTexts.stream().map(Word::new).collect(Collectors.toList());
    List<Word> processedWords = new ArrayList<>();
    words.forEach(
        word -> {
          processedWords.forEach(
              processedWord -> {
                if (word.isOneCharacterFrom(processedWord)) {
                  word.addSibling(processedWord);
                }
              });
          processedWords.add(word);
        });

    // Find start and end word
    Word startWord =
        processedWords.stream()
            .filter(word -> word.getWordText().equals(startWordText))
            .findAny()
            .orElseThrow(() -> new IllegalStateException("Start Word not in wordlist"));
    Word endWord =
        processedWords.stream()
            .filter(word -> word.getWordText().equals(endWordText))
            .findAny()
            .orElseThrow(() -> new IllegalStateException("End Word not in wordlist"));

    // Find traversal
    WordPath foundWordPath =
        findUsingBfsQueue(startWord, endWord)
            .orElseThrow(() -> new IllegalStateException("No Path found"));

    return foundWordPath.getWordPath().stream().map(Word::getWordText).collect(Collectors.toList());
  }

  private Optional<WordPath> findUsingBfsQueue(Word startWord, Word endWord) {
    WordPath wordPath = new WordPath(startWord);

    Queue<WordPath> wordPathQueue = new LinkedList<>();
    wordPathQueue.add(wordPath);
    while (!wordPathQueue.isEmpty()) {
      WordPath currentWord = wordPathQueue.poll();
      if (currentWord.getCurrentWord().equals(endWord)) {
        return Optional.of(currentWord);
      }

      // Add next words to the queue
      currentWord.getCurrentWord().getSiblings().stream()
          .filter(word -> !currentWord.getWordPath().contains(word))
          .map(word -> new WordPath(word, currentWord))
          .forEach(wordPathQueue::add);
    }
    return Optional.empty();
  }

  private List<String> readFromInputStream(String filename, int wordLength)
      throws IOException, URISyntaxException {
    Path path = Paths.get(getClass().getClassLoader().getResource(filename).toURI());

    Stream<String> lines = Files.lines(path);

    List<String> data = lines.filter(s -> s.length() == wordLength).collect(Collectors.toList());

    lines.close();

    return data;
  }
}
