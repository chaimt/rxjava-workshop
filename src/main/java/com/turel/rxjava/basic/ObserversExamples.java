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
     * add map transformation
     * add doOnNext to print the current item
     * add 2 subscriptions
     *
     * see in log how many times the original stream was run?
     *
     */
    public static void multipleSubscription(){
        log.info("multipleSubscription");

        final Observable<Integer> data = Observable.range(1, 5)
                .map(i -> i * 10)
                .doOnNext(i -> log.info("doOnNext - " + i));

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
        log.info("multipleSubscriptionSingleRun");

        final Observable<Integer> data = Observable.range(1, 5)
                .map(i -> i * 10)
                .doOnNext(i -> log.info("doOnNext - " + i));


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
        log.info("refCount: " + publish.refCount());

        subscribe.unsubscribe();
        subscribe1.unsubscribe();
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

        PublishSubject<Integer> publisher = PublishSubject.create();
        final Observable<Integer> data = Observable.range(1, 5)
                .map(i -> i * 10)
                .doOnNext(i -> log.info("doOnNext - " + i));


        final Subscription subscribe = publisher.subscribe(
                number -> {
                    log.info("onNext -  " + number.toString());
                },
                error -> log.severe("error"),
                () -> log.info("completed")
        );

        final Subscription subscribe1 = publisher.subscribe(
                number -> {
                    log.info("onNext1 -  " + number.toString());
                },
                error -> log.severe("error"),
                () -> log.info("completed")
        );

        data.subscribe(publisher);

        subscribe.unsubscribe();
        subscribe1.unsubscribe();

    }

    /**
     * create PublishSubject with two subscriptions
     * use the PublishSubject object to notify all subscriptions by calling the onNext
     *
     */
    public static void publishSubjectNotification(){
        log.info("publishSubjectNotification");

        PublishSubject<Integer> publisher = PublishSubject.create();

        final Subscription subscribe = publisher.subscribe(
                number -> {
                    log.info("onNext -  " + number.toString());
                },
                error -> log.severe("error"),
                () -> log.info("completed")
        );

        final Subscription subscribe1 = publisher.subscribe(
                number -> {
                    log.info("onNext1 -  " + number.toString());
                },
                error -> log.severe("error"),
                () -> log.info("completed")
        );

        publisher.onNext(1);
        publisher.onNext(2);

        subscribe.unsubscribe();
        subscribe1.unsubscribe();

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

        PublishSubject<Integer> publisher = PublishSubject.create();

        final Subscription subscribe = publisher.subscribe(
                number -> {
                    log.info("onNext -  " + number.toString());
                },
                error -> log.severe("error"),
                () -> log.info("completed")
        );


        publisher.onNext(1);

        final Subscription subscribe1 = publisher.subscribe(
                number -> {
                    log.info("onNext1 -  " + number.toString());
                },
                error -> log.severe("error"),
                () -> log.info("completed")
        );
        publisher.onNext(2);

        subscribe.unsubscribe();
        subscribe1.unsubscribe();

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

        ReplaySubject<Integer> publisher = ReplaySubject.create();

        final Subscription subscribe = publisher.subscribe(
                number -> {
                    log.info("onNext -  " + number.toString());
                },
                error -> log.severe("error"),
                () -> log.info("completed")
        );


        publisher.onNext(1);

        final Subscription subscribe1 = publisher.subscribe(
                number -> {
                    log.info("onNext1 -  " + number.toString());
                },
                error -> log.severe("error"),
                () -> log.info("completed")
        );
        publisher.onNext(2);

        subscribe.unsubscribe();
        subscribe1.unsubscribe();

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

        ReplaySubject<Integer> publisher = ReplaySubject.createWithTime(1, TimeUnit.SECONDS,Schedulers.computation());

        final Subscription subscribe = publisher.subscribe(
                number -> {
                    log.info("onNext -  " + number.toString());
                },
                error -> log.severe("error"),
                () -> log.info("completed")
        );


        publisher.onNext(1);

        try {
            Thread.sleep(1100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        final Subscription subscribe1 = publisher.subscribe(
                number -> {
                    log.info("onNext1 -  " + number.toString());
                },
                error -> log.severe("error"),
                () -> log.info("completed")
        );

        publisher.onNext(2);

        subscribe.unsubscribe();
        subscribe1.unsubscribe();

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

        BehaviorSubject<Integer> publisher = BehaviorSubject.create();

        final Subscription subscribe = publisher.subscribe(
                number -> {
                    log.info("onNext -  " + number.toString());
                },
                error -> log.severe("error"),
                () -> log.info("completed")
        );


        publisher.onNext(1);
        publisher.onNext(2);

        final Subscription subscribe1 = publisher.subscribe(
                number -> {
                    log.info("onNext1 -  " + number.toString());
                },
                error -> log.severe("error"),
                () -> log.info("completed")
        );
        publisher.onNext(3);

        subscribe.unsubscribe();
        subscribe1.unsubscribe();

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

        AsyncSubject<Integer> publisher = AsyncSubject.create();

        final Subscription subscribe = publisher.subscribe(
                number -> {
                    log.info("onNext -  " + number.toString());
                },
                error -> log.severe("error"),
                () -> log.info("completed")
        );


        publisher.onNext(1);
        publisher.onNext(2);

        final Subscription subscribe1 = publisher.subscribe(
                number -> {
                    log.info("onNext1 -  " + number.toString());
                },
                error -> log.severe("error"),
                () -> log.info("completed")
        );
        publisher.onNext(3);
        publisher.onCompleted();

        subscribe.unsubscribe();
        subscribe1.unsubscribe();

    }

    public static void main(String[] args) {
//        subscription();
//        unsubscribe();
//        multipleSubscription();
//        multipleSubscriptionSingleRun();
//        publishSubject();
//        publishSubjectNotification();
//        publishSubjectLateSubscription();
//        replaySubject();
//        replaySubjectWithTime();
//        behaviorSubject();
        asyncSubject();
    }
}
