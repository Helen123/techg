package com.techgig.pro.appstore;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import io.reactivex.subscribers.TestSubscriber;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class AppServiceTest{

    private AppService appService = new AppService();
    @Test
    public void observerTest() {
        appService.init();
        TestObserver<AppData> testObserver = appService.getAppDataByUser("user1").test();
        testObserver.assertResult(AppData.builder().id(1).name("Name1").build());


        TestObserver<Double> doubleTestObserver = appService.getRatings(1).test();
        doubleTestObserver.assertFailure(NullPointerException.class);


    }


}