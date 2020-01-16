package com.davidjlynn.codekata.kata06;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

public class AnagramFinderTest {

  private AnagramFinder anagramFinder = new AnagramFinder();

  @Test
  public void loadWordList_ok() throws IOException, URISyntaxException {
    // RUN TEST
    List<String> strings = anagramFinder.loadWordList();

    // CHECK RESULTS
    Assert.assertNotNull(strings);
    Assert.assertEquals(338882, strings.size());
  }

  @Test
  public void buildIntoMap_ok() throws IOException, URISyntaxException {
    // RUN TEST
    List<String> strings = anagramFinder.loadWordList();

    Map<String, List<String>> stringListMap = anagramFinder.buildIntoMap(strings);

    // CHECK RESULTS
    Assert.assertNotNull(stringListMap);
    Assert.assertNotNull(stringListMap.keySet());
    Assert.assertEquals(0, stringListMap.keySet().size());
  }

  @Test
  public void findAnagrams_ok() throws IOException, URISyntaxException {
    // RUN TEST
    List<List<String>> anagramLists = anagramFinder.findAnagrams();

    // CHECK RESULTS
    Assert.assertNotNull(anagramLists);
    Assert.assertEquals(0, anagramLists.size());
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
