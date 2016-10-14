package com.turel.rxjava.advanced;

import rx.Observable;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by chaimturkel on 10/11/16.
 */
public class MultipleFlowsExample {
    static Logger log = Logger.getLogger(MultipleFlowsExample.class.getCanonicalName());

    static public Observable<Integer> httpFlow(){
        final Observable<Integer> first = Observable.range(1, 3);
        final Observable<List<Integer>> buffer = first.buffer(2);
//        first.map()
//        first.concatMap(integer -> )
//        first.switchMap()
        return Observable.empty();
    }
}
