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
     * @return
     */
    static public Observable<Integer> mergeWith(){
        final Observable<Integer> first = Observable.range(1, 10);
        final Observable<Integer> secound = Observable.range(20, 10);
        return first.mergeWith(secound);
    }

    /**
     * create three observable with range: 1-10, 20-30, 50-60
     * merge the two to one observable
     * @return
     */
    static public Observable<Integer> merge(){
        final Observable<Integer> first = Observable.range(1, 10);
        final Observable<Integer> secound = Observable.range(20, 10);
        final Observable<Integer> third = Observable.range(50, 10);
        return Observable.merge(first,secound,third);
    }

    /**
     * create range from 1-5
     * calulate the sum of them all
     * @return
     */
    static public Observable<Integer> reduce(){
        return Observable.range(1, 5)
                .reduce((a, b) -> a+b);
    }

    /**
     * create range from 1-5
     * filter out so only one element is left and check if reduce works
     * @return
     */
    static public Observable<Integer> reduceOne(){
        return Observable.range(1, 5)
                .filter(a -> a>4)
                .reduce((a, b) -> a+b);
    }

    /**
     * create range from 1-5
     * filter out all elements and check if reduce works
     * @return
     */
    static public Observable<Integer> reduceNull(){
        return Observable.range(1, 5)
                .filter(a -> a>5)
                .reduce((a, b) -> a+b);
    }

    /**
     * create range from 1-5
     * filter out all elements and map to 0 with no error
     * @return
     */
    static public Observable<Integer> reduceNullToZero(){
        return Observable.range(1, 5)
                .filter(a -> a>5)
                .reduce(0,(a, b) -> a+b);
    }

    /**
     * create two streams:
     * one from range 1-4
     * second from just a-d
     * zip then to -> s + "-" + i
     * @return
     */
    static public Observable<String> zip(){
        final Observable<Integer> first = Observable.range(1, 4);
        final Observable<String> secound = Observable.just("a","b","c","d");
        return Observable.zip(first,secound,(i, s) -> s + "-" + i);
    }


    public static void main(String[] args) {
        Utils.runWithSubscription(log,mergeWith());
    }
}
