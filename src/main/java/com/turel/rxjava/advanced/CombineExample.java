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


//    static public Observable<Integer> d(){
//        final Observable<Integer> first = Observable.range(1, 10);
//        final Observable<Integer> secound = Observable.range(20, 10);
//        final Observable<Integer> integerObservable = first.ambWith(secound);
//        return first.
//    }


    public static void main(String[] args) {
        Utils.runWithSubscription(log,mergeWith());
    }
}
