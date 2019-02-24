package com.techgig.pro;

import io.reactivex.Observable;

public class CreatingObservables {
  private static String[] strings = {"Hello", "techgig"};

  protected static Observable<String> basicStringObservable() {
    return Observable.fromArray(strings);
  }

  protected static Observable<String> basicStringObservableV2() {
    return Observable.create(
        subscriber -> {
          try {
            for (String s : strings) {
              subscriber.onNext(s);
            }
          } catch (Exception e) {
            subscriber.onError(e);
          }
        });
  }

  protected static Observable<String> basicStringObservableV3() {
    return Observable.just("Hello");
  }

  private static void subscriber() {
    Item item = new Item();
    Observable<String> value = item.getValueObservable();
    item.setValue("Hello");
    value.subscribe(System.out::println, throwable -> System.out.println(throwable.getMessage()));
  }

  public static void main(String[] args) {
    subscriber();

  }

  static class Item {
    private String value = "Initial Value";

    public void setValue(String val) {
      this.value = val;
    }

    public Observable<String> getValueObservable(){
      return Observable.defer(() -> Observable.just(value));
    }
  }
}
