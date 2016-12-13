package com.turel.rxjava.advanced;

import com.turel.rxjava.utils.Utils;
import rx.Observable;
import rx.Scheduler;
import rx.schedulers.Schedulers;

import java.util.List;
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
        return Observable.empty();
    }

    /**
     * create a random interval, and collect every 1 secound
     *
     * @param scheduler
     * @return
     */
    static public Observable<List<Integer>> randombuffer(Scheduler scheduler) {
        return Observable.empty();
    }

    public static void main(String[] args) {
        Utils.runWithSubscription(log, buffer(Schedulers.computation()), 1000,true);
        Utils.runWithSubscription(log, randombuffer(Schedulers.computation()), 3000,true);
    }
}
