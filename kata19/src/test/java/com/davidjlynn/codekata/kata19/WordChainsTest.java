package com.davidjlynn.codekata.kata19;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class WordChainsTest {
  @Test
  public void test1() throws IOException, URISyntaxException {
    WordChains wordChains = new WordChains();
    List<String> words = wordChains.findWordChain("cat", "dog");

    Assert.assertEquals(4, words.size());
  }

}
