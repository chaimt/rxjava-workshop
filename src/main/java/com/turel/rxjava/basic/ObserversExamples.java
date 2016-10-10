package com.turel.rxjava.basic;

import rx.Observable;
import rx.Subscription;
import rx.observables.ConnectableObservable;
import rx.schedulers.Schedulers;

import java.util.logging.Logger;

/**
 * Created by chaimturkel on 10/10/16.
 */
public class ObserversExamples {
    static Logger log = Logger.getLogger(ObserversExamples.class.getCanonicalName());

    /**
     * simple example for how to do a subscription.
     */
    public static void subscription(){
        log.info("subscription");

        final Observable<Integer> data = Observable.range(1, 5)
                .map(i -> i * 2)
                .map(i -> i * 10);

        final Subscription subscribe = data.subscribe(
                number -> {
                    log.info("onNext -  " + number.toString());
                },
                error -> log.severe("error"),
                () -> log.info("completed")
        );


        subscribe.unsubscribe();
    }

    /**
     * use a range 1-5 to create an observable
     * you need to add subscribeOn(Schedulers.newThread()) so it will run on another thread.
     * add sleep in map after 3 items
     * add subscription
     * unsubscribe before stream finishes
     */
    public static void unsubscribe(){
        log.info("unsubscribe");

        final Observable<Integer> data = Observable.range(1, 5)
                .subscribeOn(Schedulers.newThread())
                .map(i -> {
                    try {
                        if (i>2) Thread.sleep(700);
                    } catch (InterruptedException e) {
                    }
                    return i;
                });

        final Subscription subscribe = data.subscribe(
                number -> {
                    log.info("onNext -  " + number.toString());
                },
                error -> log.severe("error"),
                () -> log.info("completed")
        );


        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
        }

        subscribe.unsubscribe();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
    }

    /**
     * use a range 1-5 to create an observable
     * add doOnNext to print the current item
     * add map transformation
     * add 2 subscriptions
     *
     * see in log how many times the original stream was run?
     *
     */
    public static void multipleSubscription(){
        log.info("subscription");

        final Observable<Integer> data = Observable.range(1, 5)
                .doOnNext(i -> log.info("doOnNext - " + i))
                .map(i -> i * 2)
                .map(i -> i * 10);

        final Subscription subscribe = data.subscribe(
                number -> {
                    log.info("onNext -  " + number.toString());
                },
                error -> log.severe("error"),
                () -> log.info("completed")
        );

        final Subscription subscribe1 = data.subscribe(
                number -> {
                    log.info("onNext1 -  " + number.toString());
                },
                error -> log.severe("error"),
                () -> log.info("completed")
        );

        subscribe.unsubscribe();
        subscribe1.unsubscribe();
    }

    /**
     * use a range 1-5 to create an observable
     * add doOnNext to print the current item
     * add map transformation
     * add 2 subscriptions
     *
     * Find a way so that original stream is run only once
     * see: https://medium.com/@p.tournaris/rxjava-one-observable-multiple-subscribers-7bf497646675#.y9npmnxhk
     */

    public static void multipleSubscriptionSingleRun(){
        log.info("subscription");

        final Observable<Integer> data = Observable.range(1, 5)
                .doOnNext(i -> log.info("doOnNext - " + i))
                .map(i -> i * 2)
                .map(i -> i * 10);


        final ConnectableObservable<Integer> publish = data.publish();


        final Subscription subscribe = publish.subscribe(
                number -> {
                    log.info("onNext -  " + number.toString());
                },
                error -> log.severe("error"),
                () -> log.info("completed")
        );

        final Subscription subscribe1 = publish.subscribe(
                number -> {
                    log.info("onNext1 -  " + number.toString());
                },
                error -> log.severe("error"),
                () -> log.info("completed")
        );

        publish.connect();

        subscribe.unsubscribe();
        subscribe1.unsubscribe();
    }

    public static void main(String[] args) {
        subscription();
        unsubscribe();
        multipleSubscription();
        multipleSubscriptionSingleRun();
    }
}
