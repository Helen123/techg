package com.techgig.pro;

import com.techgig.pro.appstore.AppAPI;

public class AppStore {

  public static void main(String[] args) throws InterruptedException {
    AppAPI appAPI = new AppAPI();
    appAPI.processData();
  }
}
