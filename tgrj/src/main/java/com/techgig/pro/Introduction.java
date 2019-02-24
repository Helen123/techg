package com.techgig.pro;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/** Hello world! */
public class Introduction {
  public static Observable<Object> stringSleepingObservable() {
    return Observable.create(
        subscriber -> {
          try {
            int i = 1;
            while (i < 5) {
              subscriber.onNext("Hello");
              Thread.sleep(200);
              i++;
            }
            subscriber.onComplete();
          } catch (Exception e) {
            subscriber.onError(e);
          }
        });
  }

  /**
   * A Observable which emits items of 'String' type
   *
   * @return
   */
  public static Observable<Object> stringObservable() {
    return Observable.create(
        subscriber -> {
          try {
            subscriber.onNext(null);
            subscriber.onComplete();
          } catch (Exception e) {
            subscriber.onError(e);
          }
        });
  }

  public static Observable<String> stringObservableAnother() {
    return Observable.create(
        subscriber -> {
          try {
            int i = 1;
            while (i < 5) {
              subscriber.onNext("Another" + i);
              Thread.sleep(200);
              i++;
            }
            subscriber.onComplete();
          } catch (Exception e) {
            subscriber.onError(e);
          }
        });
  }

  public static Observable<Integer> integerObservable() {
    return Observable.create(
        subscriber -> {
          try {
            int i = 1;
            while (i < 3) {
              subscriber.onNext(i);
              Thread.sleep(200);
              i++;
            }
            subscriber.onComplete();
          } catch (Exception e) {
            subscriber.onError(e);
          }
        });
  }

  public static void main(String[] args) {
    integerObservable().subscribe(new Observer<Integer>() {
        @Override
        public void onSubscribe(Disposable disposable) {
            System.out.println("Hey I am processing data now. send a JMS event or");
        }

        @Override
        public void onNext(Integer integer) {
            System.out.println(integer);
        }

        @Override
        public void onError(Throwable throwable) {
            System.out.println(throwable.getMessage());
        }

        @Override
        public void onComplete() {
            System.out.println("Done send that jms message also");
        }
    });

    integerObservable()
        .subscribe(
            System.out::println,
            throwable -> System.out.println(throwable.getMessage()),
            () -> System.out.println("DONE"), disposable-> System.out.println("PROCESSING"));
  }


}
