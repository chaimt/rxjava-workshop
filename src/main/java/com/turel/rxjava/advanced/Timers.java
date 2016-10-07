package com.turel.rxjava.advanced;

import com.turel.rxjava.utils.Utils;
import rx.Observable;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Created by chaimturkel on 10/7/16.
 */
public class Timers {
    static Logger log = Logger.getLogger(Timers.class.getCanonicalName());

    static public Observable<String> ticks() {
        return Observable.interval(100, TimeUnit.MILLISECONDS)
                .map(input ->  "Success " + input);
    }

    static public Observable<String> retryOnError() {
        return Observable.interval(100, TimeUnit.MILLISECONDS)
                .map(input -> {
                    if (Math.random() < .5) {
                        throw new RuntimeException();
                    }
                    return "Success " + input;
                })
                .retry(3);
    }

    public static void main(String[] args) {
        Utils.runWithSubscription(log, ticks(),1000);
//        Utils.runWithSubscription(log, retryOnError(),2000);
    }

}
