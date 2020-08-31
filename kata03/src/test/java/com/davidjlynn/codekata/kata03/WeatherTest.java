package com.davidjlynn.codekata.kata03;

import com.davidjlynn.codekata.kata03.weather.WeatherData;
import java.io.IOException;
import java.net.URISyntaxException;
import org.junit.jupiter.api.Test;

public class WeatherTest {

  private final WeatherData anagramFinder = new WeatherData();

  @Test
  public void loadWordList_ok() throws IOException, URISyntaxException {
    // RUN TEST
    anagramFinder.run();
  }
}
