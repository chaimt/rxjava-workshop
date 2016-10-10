package com.turel.rxjava.basic;

import com.turel.rxjava.utils.Utils;
import rx.Observable;
import rx.schedulers.Schedulers;

import java.util.logging.Logger;

/**
 * Created by chaimturkel on 6/28/16.
 * Schedulers.computation(),Schedulers.Schedulers.io(),Schedulers.immediate()
 * please read: http://tomstechnicalblog.blogspot.co.il/2016/02/rxjava-understanding-observeon-and.html
 * Short summary:
 * subscribeOn:
 * subscribeOn() instructs the source Observable which thread to emit items on, and this thread will push items all the way to the Subscriber.
 * However, if it encounters an observeOn() somewhere in the chain (discussed shortly), it will then pass emissions to another thread for the remaining operations at that point
 * observeOn:
 * from the point that the observeOn was used the continuation of observalbes will be on the new thread.
 */
public class ThreadExample {
    static Logger log = Logger.getLogger(ThreadExample.class.getCanonicalName());


    /**
     * create a simple Observable from range 1-5
     *
     * @return
     */
    public static Observable<Integer> noThread() {
        log.info("noThread");
        return Observable.range(1, 5);
    }

    /**
     * create a simple Observable from range 1-5
     * on map method multiple by 2, and print the curent thread
     * add to subscribeOn - Schedulers.newThread()
     *
     * @return
     */
    public static Observable<Integer> subscriptionThread() {
        log.info("subscriptionThread");

        return Observable.range(1, 5)
                .map(i -> {
                    log.info("map:" + Thread.currentThread().getName() + " - " + i);
                    return i * 2;
                })
                .subscribeOn(Schedulers.newThread());
    }

    /**
     * create a simple Observable from range 1-5
     * on map method multiple by 2, and print the curent thread
     * add to subscribeOn - Schedulers.newThread()
     * add to observeOn - Schedulers.computation()
     * run main add see log messages for threads
     *
     * @return
     */
    public static Observable<Integer> observerThread() {
        log.info("observerThread");
        return Observable.range(1, 5)
                .map(i -> {
                    log.info("map:" + Thread.currentThread().getName() + " - " + i);
                    return i * 2;
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.computation());

    }

    /**
     * create a simple Observable from range 1-5
     * on map method multiple by 2, and print the curent thread
     * add to observeOn - Schedulers.computation()
     * make sure that map is run on more than 1 thread
     * run main add see log messages for threads
     *
     * @return
     */
    public static Observable<Integer> multipleObserveon() {
        log.info("threadPerItem");

        return Observable.range(1, 5)
                .observeOn(Schedulers.computation())
                .map(i -> {
                    log.info("map:" + Thread.currentThread().getName() + " - " + i);
                    return i * 2;
                })
                .observeOn(Schedulers.io())
                .map(i -> {
                    log.info("map2 :" + Thread.currentThread().getName() + " - " + i);
                    return i * 2;
                });


    }

    /**
     * create a simple Observable from range 1-5
     * on map method multiple by 2, and print the curent thread
     * add to observeOn - Schedulers.computation()
     * make sure that map is run on more than 1 thread
     * run main add see log messages for threads
     *
     * @return
     */
    public static Observable<Integer> threadPerItem() {
        log.info("threadPerItem");

        return Observable.range(1, 5)
                .flatMap(item -> Observable.just(item)
                        .observeOn(Schedulers.computation())
                        .map(i -> {
                            log.info("map:" + Thread.currentThread().getName() + " - " + i);
                            return i * 2;
                        }));

    }


    public static void main(String[] args) {
        Utils.runWithSubscription(log, noThread());
        Utils.runWithSubscription(log, subscriptionThread(), 1000, true);
        Utils.runWithSubscription(log, observerThread(), 1000, true);
        Utils.runWithSubscription(log, threadPerItem(), 1000, true);
    }
}
