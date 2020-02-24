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
  public void test1() throws IOException, URISyntaxException {
    WordChains wordChains = new WordChains();

    Instant start = Instant.now();

    List<String> words = wordChains.findWordChain("cat", "dog");

    Instant end = Instant.now();
    System.out.println("Time taken to run word chain: " + Duration.between(start, end));

    Assert.assertEquals(4, words.size());
  }
}
