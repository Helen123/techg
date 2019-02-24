package com.techgig.pro;

import static com.techgig.pro.CreatingObservables.basicStringObservableV2;
import static com.techgig.pro.Introduction.stringObservable;

public class ErrorHandling {
  public static void main(String[] args) {
    // I just want to print
    basicStringObservableV2().subscribe(System.out::println);

    // print length
    stringObservable()
        .subscribe(
            val -> {
              System.out.println(val.toString());
            });
  }
}
