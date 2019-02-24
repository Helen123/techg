package com.techgig.pro.appstore;

import lombok.Builder;
import lombok.Data;

@Data
public class AppData {

  private String name;
  private Double rating;
  private Integer id;

  @Builder
  public static AppData buildAppData(String name, Double rating, Integer id) {
    AppData appData = new AppData();
    appData.setId(id);
    appData.setName(name);
    appData.setRating(rating);
    return appData;
  }
}
