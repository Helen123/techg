package com.techgig.pro.appstore;

import io.reactivex.Observable;

import java.util.HashMap;
import java.util.Map;

public class AppService {
  private Map<String, AppData> cache = new HashMap<>();

  private Map<Integer, AppData> appService = new HashMap<>();

  protected void init() {
    cache.put("user1", AppData.builder().id(1).name("Name1").build());
    cache.put("user2", AppData.builder().id(2).name("Name2").rating(3.45).build());
    cache.put("user3", AppData.builder().id(3).name("Name3").build());
    cache.put("user4", AppData.builder().id(4).name("Name4").rating(4.13).build());

    appService.put(1, AppData.builder().id(1).name("Name1").build());
    appService.put(2, AppData.builder().id(2).rating(3.45).build());
    appService.put(3, AppData.builder().id(3).name("Name3").build());
    appService.put(4, AppData.builder().id(4).name("Name4").rating(4.13).build());
    appService.put(5, AppData.builder().id(5).name("Name5").build());
    appService.put(6, AppData.builder().id(6).name("Name6").rating(4.13).build());
    appService.put(7, AppData.builder().id(7).name("Name7").rating(4.13).build());
    appService.put(8, AppData.builder().id(8).name("Name8").build());
    appService.put(9, AppData.builder().id(9).name("Name9").rating(4.13).build());
    appService.put(10, AppData.builder().id(10).name("Name10").rating(4.13).build());
  }

    /**
     * List of appData personlized by user.
     * @param user
     * @return  An Observable with data
     */
  protected Observable<AppData> getAppDataByUser(String user) {
    return Observable.create(
        subscriber -> {
          if (cache.containsKey(user)) {
            subscriber.onNext(cache.get(user));
          } else {
            // Make a network call
            AppData appData = getDataFromNetworkCall(user);
            cache.put(user, appData);
            subscriber.onNext(AppData.builder().id(5).name("Name5").rating(4.13).build());
          }
          subscriber.onComplete();
        });
  }

    /**
     * Ratings associated with an app
     * @param id
     * @return An Observable with rating
     */
  protected Observable<Double> getRatings(Integer id) {
    return Observable.create(
        subscriber -> {
          subscriber.onNext(getRatingFromNetworkCall(id));
          subscriber.onComplete();
        });
  }

    /**
     * Name of the APP
     * @param id
     * @return An Observable with name
     */
  protected Observable<String> getNames(Integer id) {
    return Observable.create(
        subscriber -> {
          subscriber.onNext(getNameFromNetworkCall(id));
          subscriber.onComplete();
        });
  }

    /**
     * List of all the apps ever.
     * @return An Observable with data
     */
  protected Observable<AppData> getAppData() {
    return Observable.create(
        subscriber -> {
          appService.forEach(
              (id, appData) -> {
                subscriber.onNext(appData);
              });
          subscriber.onComplete();
        });
  }

  private AppData getDataFromNetworkCall(String user) throws InterruptedException {
    Thread.sleep(500);
    return AppData.builder().id(5).name("Name5").rating(4.13).build();
  }

  private Double getRatingFromNetworkCall(Integer id) throws InterruptedException {
    Thread.sleep(500);
    return appService.get(id).getRating();
  }

  private String getNameFromNetworkCall(Integer id) throws InterruptedException {
    Thread.sleep(500);
    return appService.get(id).getName();
  }
}
