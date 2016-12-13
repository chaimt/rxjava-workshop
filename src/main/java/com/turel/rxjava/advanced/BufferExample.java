package com.turel.rxjava.advanced;

import com.turel.rxjava.utils.Utils;
import rx.Observable;
import rx.Scheduler;
import rx.schedulers.Schedulers;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Created by chaimturkel on 12/13/16.
 * This example should be done after the TimersExample
 */
public class BufferExample {
    static Logger log = Logger.getLogger(BufferExample.class.getCanonicalName());

    /**
     * using interval, 100 every MILLISECONDS
     * collect every 5 ticks to a buffer
     *
     * @param scheduler
     * @return
     */
    static public Observable<List<Long>> buffer(Scheduler scheduler) {
        return Observable.interval(100, TimeUnit.MILLISECONDS, scheduler)
                .buffer(5);
    }

    /**
     * create a random interval, and collect every 1 secound
     *
     * @param scheduler
     * @return
     */
    static public Observable<List<Integer>> randombuffer(Scheduler scheduler) {
        Random random = new Random();

        return Observable.range(1, 1000)
                .doOnNext(number -> Utils.quietSleep(random.nextInt(10) * 10))
                .buffer(1, TimeUnit.SECONDS, scheduler);
    }

    public static void main(String[] args) {
        Utils.runWithSubscription(log, buffer(Schedulers.computation()), 1000,true);
        Utils.runWithSubscription(log, randombuffer(Schedulers.computation()), 3000,true);
    }
}
