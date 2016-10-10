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
        return Observable.empty();
    }

    /**
     * create three observable with range: 1-10, 20-30, 50-60
     * merge the two to one observable
     * @return
     */
    static public Observable<Integer> merge(){
        return Observable.empty();
    }


    public static void main(String[] args) {
        Utils.runWithSubscription(log,mergeWith());
    }
}
