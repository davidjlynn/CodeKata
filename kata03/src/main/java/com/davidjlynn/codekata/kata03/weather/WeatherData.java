package com.davidjlynn.codekata.kata03.weather;

import com.google.common.io.Resources;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WeatherData {

  public void run() throws URISyntaxException, IOException {
    URL weatherUrl = Resources.getResource("weather.dat");
    Path path = Paths.get(weatherUrl.toURI());

    Stream<String> lines = Files.lines(path, StandardCharsets.ISO_8859_1);
    List<String> strings = lines.collect(Collectors.toList());
    lines.close();

    List<String> allRecords = strings.subList(2, strings.size() - 1);

    List<WeatherRecord> converted =
        allRecords.stream().map(WeatherRecord::new).collect(Collectors.toList());

    Optional<WeatherRecord> found =
        converted.stream().min(Comparator.comparing(WeatherRecord::getSpread));

    if (found.isEmpty()) {
      System.out.println("Missing?!");
    } else {
      System.out.println("Minimum Spread is day " + found.get().getDy());
    }

    System.out.println("Pfft");
  }
}
