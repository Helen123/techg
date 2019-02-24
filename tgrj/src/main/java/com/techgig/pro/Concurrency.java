package com.techgig.pro;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import static com.techgig.pro.Introduction.stringSleepingObservable;

public class Concurrency {

  public static Observable<String> stringConcurrentObservable() {
    return Observable.create(
        subscriber -> {
          new Thread(
                  () -> {
                    try {
                      int i = 1;
                      while (i < 5) {
                        System.out.println(Thread.currentThread().getName());
                        subscriber.onNext("Hello");
                        Thread.sleep(200);
                        i++;
                      }
                      subscriber.onComplete();
                    } catch (Exception e) {
                      subscriber.onError(e);
                    }
                  })
              .start();
        });
  }

  public static Observable<Object> stringConcurrentSchedulerObservable() {
    return Observable.create(
            subscriber -> {
              try {
                int i = 1;
                while (i < 5) {
                  System.out.println(Thread.currentThread().getName());
                  subscriber.onNext("Hello");
                  Thread.sleep(200);
                  i++;
                }
                subscriber.onComplete();
              } catch (Exception e) {
                subscriber.onError(e);
              }
            })
        .subscribeOn(Schedulers.io());
  }

  public static void main(String[] args) {
//      stringConcurrentObservable()
//        .subscribe(
//            value -> {
//              System.out.println(value + "from 1st, Thread=" + Thread.currentThread().getName());
//            });

//            stringConcurrentSchedulerObservable().observeOn(Schedulers.io()).subscribe(value -> {
//                System.out.println(value + "from 1nd, Thread=" + Thread.currentThread().getName()+
//     " time="+ System.currentTimeMillis());
//            });
    stringSleepingObservable()
        .subscribe(
            value -> {
              System.out.println(value + "from 2nd, Thread=" + Thread.currentThread().getName());
            });
  }
}
