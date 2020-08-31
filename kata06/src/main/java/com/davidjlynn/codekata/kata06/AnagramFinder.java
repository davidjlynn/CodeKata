package com.davidjlynn.codekata.kata06;

import com.google.common.io.Resources;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AnagramFinder {

  public List<String> loadWordList() throws URISyntaxException, IOException {
    URL wordlistUrl = Resources.getResource("wordlist.txt");
    Path path = Paths.get(wordlistUrl.toURI());

    Stream<String> lines = Files.lines(path, StandardCharsets.ISO_8859_1);
    List<String> strings = lines.collect(Collectors.toList());
    lines.close();

    return strings;
  }

  public Map<String, List<String>> buildIntoMap(List<String> words) {
    return words.stream()
        .collect(
            Collectors.groupingBy(
                word -> {
                  String lower = word.toLowerCase();
                  char[] sortedChar = lower.toCharArray();
                  Arrays.sort(sortedChar);
                  return new String(sortedChar);
                }));
  }

  public List<List<String>> findAnagrams() throws URISyntaxException, IOException {
    List<String> wordList = loadWordList();

    // Sanitize *eyerole* -> Remove capitals.
    wordList = wordList.stream().map(String::toLowerCase).distinct().collect(Collectors.toList());

    Map<String, List<String>> wordMap = buildIntoMap(wordList);

    // Strip out occurences of only 1 word
    return wordMap.values().stream()
        .filter(anagramSet -> anagramSet.size() > 1)
        .collect(Collectors.toList());
  }

  public void findMostAnagrams() throws URISyntaxException, IOException {
    List<List<String>> anagramLists = findAnagrams();

    List<String> anagrams = anagramLists.stream().max(Comparator.comparingInt(List::size)).get();

    System.out.println(anagrams.size());
    System.out.println(anagrams.toString());
  }

  public void findLongestAnagrams() throws URISyntaxException, IOException {
    List<List<String>> anagramLists = findAnagrams();

    List<String> anagrams =
        anagramLists.stream()
            .max(Comparator.comparingInt((anagramList -> anagramList.get(0).length())))
            .get();

    System.out.println(anagrams.size());
    System.out.println(anagrams.toString());
  }
}
