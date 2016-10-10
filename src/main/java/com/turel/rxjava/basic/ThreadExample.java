package com.turel.rxjava.basic;

import com.turel.rxjava.utils.Utils;
import rx.Observable;
import rx.schedulers.Schedulers;

import java.util.logging.Logger;

/**
 * Created by chaimturkel on 6/28/16.
 * Schedulers.computation(),Schedulers.Schedulers.io(),Schedulers.immediate()
 */
public class ThreadExample {
    static Logger log = Logger.getLogger(ThreadExample.class.getCanonicalName());


    /**
     * create a simple Observable from range 1-5
     * @return
     */
    public static Observable<Integer> noThread() {
        log.info("noThread");
        return Observable.empty();
    }

    /**
     * create a simple Observable from range 1-5
     * on map method multiple by 2, and print the curent thread
     * add to subscribeOn - Schedulers.newThread()
     * @return
     */
    public static Observable<Integer> subscriptionThread() {
        log.info("subscriptionThread");
        return Observable.empty();
    }

    /**
     * create a simple Observable from range 1-5
     * on map method multiple by 2, and print the curent thread
     * add to subscribeOn - Schedulers.newThread()
     * add to observeOn - Schedulers.computation()
     * run main add see log messages for threads
     * @return
     */
    public static Observable<Integer> observerThread() {
        log.info("observerThread");
        return Observable.empty();
    }


    /**
     * create a simple Observable from range 1-5
     * on map method multiple by 2, and print the curent thread
     * add to observeOn - Schedulers.computation()
     * make sure that map is run on more than 1 thread
     * run main add see log messages for threads

     * @return
     */
    public static Observable<Integer> threadPerItem() {
        log.info("threadPerItem");
        return Observable.empty();
    }


    public static void main(String[] args) {
        Utils.runWithSubscription(log,noThread());
        Utils.runWithSubscription(log,subscriptionThread(),1000,true);
        Utils.runWithSubscription(log,observerThread(),1000,true);
        Utils.runWithSubscription(log,threadPerItem(),1000,true);
    }
}
