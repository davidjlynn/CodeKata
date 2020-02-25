package com.davidjlynn.codekata.kata19;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class WordChainsTest {

  @Test
  public void findWordChain_smallWords() throws IOException, URISyntaxException {
    WordChains wordChains = new WordChains();

    Instant start = Instant.now();

    List<String> words = wordChains.findWordChain("cat", "dog");

    Instant end = Instant.now();
    System.out.println("Time taken to run word chain: " + Duration.between(start, end));
    System.out.println("The words: " + words.toString());

    Assert.assertEquals(4, words.size());
  }

  @Test
  public void findWordChain_mediumWords() throws IOException, URISyntaxException {
    WordChains wordChains = new WordChains();

    Instant start = Instant.now();

    List<String> words = wordChains.findWordChain("lead", "gold");

    Instant end = Instant.now();
    System.out.println("Time taken to run word chain: " + Duration.between(start, end));
    System.out.println("The words: " + words.toString());

    Assert.assertEquals(4, words.size());
  }

  @Test
  public void findWordChain_mediumWordsReversed() throws IOException, URISyntaxException {
    WordChains wordChains = new WordChains();

    Instant start = Instant.now();

    List<String> words = wordChains.findWordChain("gold", "lead");

    Instant end = Instant.now();
    System.out.println("Time taken to run word chain: " + Duration.between(start, end));
    System.out.println("The words: " + words.toString());

    Assert.assertEquals(4, words.size());
  }

  @Test
  public void findWordChain_bigWords() throws IOException, URISyntaxException {
    WordChains wordChains = new WordChains();

    Instant start = Instant.now();

    List<String> words = wordChains.findWordChain("funniest", "tastiest");

    Instant end = Instant.now();
    System.out.println("Time taken to run word chain: " + Duration.between(start, end));
    System.out.println("The words: " + words.toString());

    Assert.assertEquals(7, words.size());
  }
}
