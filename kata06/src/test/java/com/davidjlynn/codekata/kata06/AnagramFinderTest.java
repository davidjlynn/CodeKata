package com.davidjlynn.codekata.kata06;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AnagramFinderTest {

  private AnagramFinder anagramFinder = new AnagramFinder();

  @Test
  public void loadWordList_ok() throws IOException, URISyntaxException {
    // RUN TEST
    List<String> strings = anagramFinder.loadWordList();

    // CHECK RESULTS
    assertNotNull(strings);
    assertEquals(338882, strings.size());
  }

  @Test
  public void buildIntoMap_ok() throws IOException, URISyntaxException {
    // RUN TEST
    List<String> strings = anagramFinder.loadWordList();

    Map<String, List<String>> stringListMap = anagramFinder.buildIntoMap(strings);

    // CHECK RESULTS
    assertNotNull(stringListMap);
    assertNotNull(stringListMap.keySet());
    assertEquals(295640, stringListMap.keySet().size());
  }

  @Test
  public void findAnagrams_ok() throws IOException, URISyntaxException {
    // RUN TEST
    List<List<String>> anagramLists = anagramFinder.findAnagrams();

    // CHECK RESULTS
    assertNotNull(anagramLists);
    assertEquals(25027, anagramLists.size());
  }

  @Test
  public void findMostAnagrams_ok() throws IOException, URISyntaxException {
    // RUN TEST
    anagramFinder.findMostAnagrams();
  }

  @Test
  public void findLongestAnagrams_ok() throws IOException, URISyntaxException {
    // RUN TEST
    anagramFinder.findLongestAnagrams();
  }
}
