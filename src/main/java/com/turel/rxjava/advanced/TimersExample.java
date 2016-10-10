package com.turel.rxjava.advanced;

import com.turel.rxjava.utils.Utils;
import rx.Observable;
import rx.Scheduler;
import rx.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Created by chaimturkel on 10/7/16.
 */
public class TimersExample {
    static Logger log = Logger.getLogger(TimersExample.class.getCanonicalName());

    static public Observable<String> ticks(Scheduler scheduler) {
        return Observable.interval(100, TimeUnit.MILLISECONDS,scheduler)
                .map(input ->  "Success " + input);
    }


    static public Observable<String> mergeFlows(Scheduler scheduler) {
        final Observable<String> first = Observable.interval(100, TimeUnit.MILLISECONDS,scheduler)
                .map(input -> "yellow " + input);
        final Observable<String> second = Observable.interval(100, TimeUnit.MILLISECONDS,scheduler)
                .map(input -> "blue " + input);
        return first.mergeWith(second);
    }


    public static void main(String[] args) {
        Utils.runWithSubscription(log, ticks(Schedulers.computation()),1000);
        Utils.runWithSubscription(log, mergeFlows(Schedulers.computation()),1000);
    }

}
