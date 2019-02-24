package com.techgig.pro;

import io.reactivex.Observable;

import static com.techgig.pro.CreatingObservables.basicStringObservableV2;
import static com.techgig.pro.Introduction.integerObservable;
import static com.techgig.pro.Introduction.stringObservableAnother;

public class Operators {
  public static void main(String[] args) {
//    // I just want to print
//    basicStringObservableV2().subscribe(System.out::println);

    // print length
    Observable.range(0,10).filter(integer -> integer%2==0).skip(1).reduce((i1,i2)-> i1*i2)
        .subscribe(
            val -> {
              System.out.println(val.toString());
            });

    // Hello techgig
    // another another .....
    // Hello techgig another another...
    // 1, 2, 3,
    Observable.zip(
            basicStringObservableV2().mergeWith(stringObservableAnother()),
            integerObservable(),
            (a, b) -> a.length() * b)
        .subscribe(System.out::println);
  }
}
