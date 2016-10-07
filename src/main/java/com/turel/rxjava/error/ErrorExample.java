package com.turel.rxjava.error;

import com.turel.rxjava.utils.Utils;
import rx.Observable;
import rx.exceptions.Exceptions;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by chaimturkel on 7/5/16.
 * onErrorResumeNext( ) — instructs an Observable to emit a sequence of items if it encounters an error
 * onErrorReturn( ) — instructs an Observable to emit a particular item when it encounters an error
 * onExceptionResumeNext( ) — instructs an Observable to continue emitting items after it encounters an exception (but not another variety of throwable)
 * retry( ) — if a source Observable emits an error, resubscribe to it in the hopes that it will complete without error
 * retryWhen( ) — if a source Observable emits an error, pass that error to another Observable to determine whether to resubscribe to the source

 */
public class ErrorExample {
    static Logger log = Logger.getLogger(ErrorExample.class.getCanonicalName());

    /**
     * create observable from Observable.just("Hello!")
     * in the map throw new RuntimeException("my error");
     * @return
     */
    static public Observable<Object> onError() {
        return Observable.empty();
    }

    private static String transform(String input) throws IOException {
        throw new IOException("No File");
    }

    /**
     * create observable from Observable.just("Hello!")
     * in the map call transform in try..catch and use Exceptions.propagate
     * @return
     */
    static public Observable<Object> propagateError() {
        return Observable.empty();
    }

    /**
     * create observable from Observable.just(1, 2, 0,3)
     * in the map call 100/v
     * @return
     */
    static public Observable<Integer> divisionError() {
        return Observable.empty();
    }

    /**
     * create observable from Observable.just("Hello!","bad","data")
     * in the map if value is bad, throw new RuntimeException()
     * use onErrorReturn to return "Empty result"
     * @return
     */
    static public Observable<String> onErrorReturn() {
        return Observable.empty();
    }

    /**
     * create observable from Observable.just("Hello!","bad","data")
     * in the map if value is bad, throw new RuntimeException()
     * use onExceptionResumeNext, return empty Observable
     * @return
     */
    static public Observable<String> onExceptionResumeNext() {
        return Observable.empty();
    }

    /**
     * create observable from Observable.just("Hello!","bad","data")
     * in the map if value is bad, throw new RuntimeException()
     * use onErrorResumeNext to check exception type
     * and if RuntimeException return new observables for "new","flow","go"
     * @return
     */
    static public Observable<String> onErrorResumeNext() {
        return Observable.empty();
    }



    public static void main(String[] args) {
        Utils.runWithSubscription(log, onError());
        Utils.runWithSubscription(log, propagateError());
        Utils.runWithSubscription(log, onErrorReturn());
        Utils.runWithSubscription(log, divisionError());
    }


}
