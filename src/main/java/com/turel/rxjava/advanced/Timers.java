package com.turel.rxjava.advanced;

import com.turel.rxjava.utils.Utils;
import rx.Observable;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Created by chaimturkel on 10/7/16.
 */
public class Timers {
    static Logger log = Logger.getLogger(Timers.class.getCanonicalName());

    /**
     * create Observable that emmits every 100 MILLISECONDS -> "Success " + input
     *
     * @return
     */
    static public Observable<String> ticks() {
        return Observable.empty();
    }

    public static void main(String[] args) {
        Utils.runWithSubscription(log, ticks(),1000);
//        Utils.runWithSubscription(log, retryOnError(),2000);
    }

}
