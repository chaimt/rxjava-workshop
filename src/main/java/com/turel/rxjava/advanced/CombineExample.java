package com.turel.rxjava.advanced;


import com.turel.rxjava.utils.Utils;
import rx.Observable;

import java.util.logging.Logger;

/**
 * Created by chaimturkel on 10/10/16.
 */
public class CombineExample {
    static Logger log = Logger.getLogger(CombineExample.class.getCanonicalName());

    /**
     * create two observable with range: 1-10, 20-30
     * merge the two to one observable
     *
     * @return
     */
    static public Observable<Integer> mergeWith() {
        return Observable.empty();
    }

    /**
     * create three observable with range: 1-10, 20-30, 50-60
     * merge the two to one observable
     *
     * @return
     */
    static public Observable<Integer> merge() {
        return Observable.empty();
    }

    /**
     * create range from 1-5
     * calulate the sum of them all
     *
     * @return
     */
    static public Observable<Integer> reduce() {
        return Observable.empty();
    }

    /**
     * create range from 1-5
     * filter out so only one element is left and check if reduce works
     *
     * @return
     */
    static public Observable<Integer> reduceOne() {
        return Observable.empty();
    }

    /**
     * create range from 1-5
     * filter out all elements and check if reduce works
     *
     * @return
     */
    static public Observable<Integer> reduceNull() {
        return Observable.empty();
    }

    /**
     * create range from 1-5
     * filter out all elements and map to 0 with no error
     *
     * @return
     */
    static public Observable<Integer> reduceNullToZero() {
        return Observable.empty();
    }

    /**
     * create two streams:
     * one from range 1-4
     * second from just a-d
     * zip then to -> s + "-" + i
     *
     * @return
     */
    static public Observable<String> zip() {
        return Observable.empty();
    }


    public static void main(String[] args) {
        Utils.runWithSubscription(log, mergeWith());
    }
}
