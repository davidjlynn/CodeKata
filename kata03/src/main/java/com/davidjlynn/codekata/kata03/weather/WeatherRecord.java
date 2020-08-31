package com.davidjlynn.codekata.kata03.weather;

import lombok.Getter;

@Getter
public class WeatherRecord {

  private final int dy;
  private final int MxT;
  private final int MnT;

  private final int spread;

  public WeatherRecord(String record) {
    dy = Integer.parseInt(record.substring(0, 5).strip());
    MxT = Integer.parseInt(record.substring(5, 11).strip().replace("*", ""));
    MnT = Integer.parseInt(record.substring(11, 18).strip().replace("*", ""));

    spread = MxT - MnT;
  }
}
