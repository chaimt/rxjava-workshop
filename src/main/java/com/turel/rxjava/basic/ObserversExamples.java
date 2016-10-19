package com.turel.rxjava.basic;

import rx.Observable;
import rx.Subscription;
import rx.observables.ConnectableObservable;
import rx.schedulers.Schedulers;
import rx.subjects.AsyncSubject;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;
import rx.subjects.ReplaySubject;

import java.util.concurrent.TimeUnit;
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
     * add map transformation
     * add doOnNext to print the current item
     * add 2 subscriptions
     *
     * see in log how many times the original stream was run?
     *
     */
    public static void multipleSubscription(){
        log.info("multipleSubscription");
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
        log.info("multipleSubscriptionSingleRun");
    }

    /**
     * use a range 1-5 to create an observable
     * add doOnNext to print the current item
     * add map transformation
     * add 2 subscriptions
     *
     * Now use PublishSubject to Find a way so that original stream is run only once
     */
    public static void publishSubject(){
        log.info("PublishSubject");
    }

    /**
     * create PublishSubject with two subscriptions
     * use the PublishSubject object to notify all subscriptions by calling the onNext
     *
     */
    public static void publishSubjectNotification(){
        log.info("publishSubjectNotification");
    }

    /**
     * create PublishSubject with two subscriptions
     * use the PublishSubject object to notify all subscriptions by calling the onNext
     *
     * Register the second subscriber after calling the onNext once
     * Call the onNext a secound time.
     *
     * Which subscribers got which notifications?
     *
     */
    public static void publishSubjectLateSubscription(){
        log.info("publishSubjectLateSubscription");
    }


    /**
     * create PublishSubject with two subscriptions
     * use the PublishSubject object to notify all subscriptions by calling the onNext
     *
     * Register the second subscriber after calling the onNext once
     * Call the onNext a secound time.
     *
     * Use ReplaySubject to fix previous issue so that second subscriber will get all onNext calls
     *
     * How does it work, what is the expense for the feature?
     */
    public static void replaySubject(){
        log.info("replaySubject");
    }

    /**
     * create ReplaySubject with two subscriptions
     * use the ReplaySubject object to notify all subscriptions by calling the onNext
     *
     * Register the second subscriber after calling the onNext once
     * Call the onNext a secound time.
     *
     * Use ReplaySubject to fix previous issue so that second subscriber will get all onNext calls
     *
     * Limit the time of the cache to 1 secound
     */

    public static void replaySubjectWithTime(){
        log.info("replaySubjectWithTime");
    }

    /**
     * create BehaviorSubject with two subscriptions
     * use the BehaviorSubject object to notify all subscriptions by calling the onNext
     *
     * Register the second subscriber after calling the onNext twice
     * Call the onNext a third time.
     *
     * How is this different than the ReplaySubject or PublishSubject
     *
     */
    public static void behaviorSubject(){
        log.info("behaviorSubject");
    }

    /**
     * create AsyncSubject with two subscriptions
     * use the AsyncSubject object to notify all subscriptions by calling the onNext
     *
     * Register the second subscriber after calling the onNext twice
     * Call the onNext a third time.
     *
     * Make sure to call the onComplete
     *
     * What does this Subject object do?
     *
     */
    public static void asyncSubject(){
        log.info("asyncSubject");
    }

    public static void main(String[] args) {
        subscription();
        unsubscribe();
        multipleSubscription();
        multipleSubscriptionSingleRun();
        publishSubject();
        publishSubjectNotification();
        publishSubjectLateSubscription();
        replaySubject();
        replaySubjectWithTime();
        behaviorSubject();
        asyncSubject();
    }
}
