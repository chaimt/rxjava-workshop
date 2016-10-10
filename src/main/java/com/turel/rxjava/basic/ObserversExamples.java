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


    }

    public static void main(String[] args) {
        subscription();
        unsubscribe();
        multipleSubscription();
        multipleSubscriptionSingleRun();
    }
}
