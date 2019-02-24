package com.techgig.pro.appstore;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class AppAPI {

  AppService appService = new AppService();

  public void processData() throws InterruptedException {
    long start = System.currentTimeMillis();
    appService.init();
      getAppDataObservable().blockingSubscribe(System.out::println);
    System.out.println("Time taken=" + (System.currentTimeMillis() - start));
  }

  private String timeConsumingNetworkStuff(AppData appData) {
    System.out.println(
        "Doing time consuming network stuff on Thread=" + Thread.currentThread().getName());
    // First service call to fetch Ratings 500ms
    Observable<Double> rating = appService.getRatings(appData.getId());
    // Second service call to fetch Names 500ms
    Observable<String> name = appService.getNames(appData.getId());

    // Merge the results
    String result =
        Observable.zip(rating, name, (appRate, appName) -> appName + "_" + appRate)
            .blockingSingle("default_rating");
    return result;
  }

  private Observable<String> getAppDataObservable() {

    return appService.getAppData().map(appData -> timeConsumingNetworkStuff(appData));
  }

  private Observable<String> getAppDataConcurrentObservable() {
    return appService
        .getAppData().take(4)
        .flatMap(
            appData ->
                Observable.just(appData)
                    .subscribeOn(Schedulers.computation())
                    .map(appData1 -> timeConsumingNetworkStuff(appData1)), 1);
  }
}
