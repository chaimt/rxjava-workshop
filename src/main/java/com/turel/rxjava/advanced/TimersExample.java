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

    /**
     * using interval, 100 every MILLISECONDS
     * send text "Success " + input
     * @param scheduler
     * @return
     */
    static public Observable<String> ticks(Scheduler scheduler) {
        Observable.empty();
    }


    /**
     * create two flows:
     * using interval, 100 every MILLISECONDS
     * send text "yellow " + input
     * * send text "blue " + input
     * merge them
     * @param scheduler
     * @return
     */
    static public Observable<String> mergeFlows(Scheduler scheduler) {
        Observable.empty();
    }


    public static void main(String[] args) {
        Utils.runWithSubscription(log, ticks(Schedulers.computation()),1000);
        Utils.runWithSubscription(log, mergeFlows(Schedulers.computation()),1000);
    }

}
