package com.turel.rxjava.basic;

import com.turel.rxjava.utils.Utils;
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
public class ConditionExamples {
    static Logger log = Logger.getLogger(ConditionExamples.class.getCanonicalName());

    /**
     * create a range 1-5
     * run the range twice
     */
    public static Observable<Integer> repeat() {
        log.info("subscription");
        return Observable.range(1, 5)
                .repeat(2);
    }


    /**
     * create a range 1-5
     * run while number is below or equal 3 (do not use filter)
     */
    public static Observable<Integer> whileAbove() {
        log.info("subscription");
        return Observable.range(1, 5)
                .takeWhile(i -> i <= 3);
    }

    public static void main(String[] args) {
        Utils.runWithSubscription(log,repeat());
        Utils.runWithSubscription(log, whileAbove());
    }
}
