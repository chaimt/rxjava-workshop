package com.turel.rxjava.basic;

import rx.Observable;
import rx.Subscriber;

import java.util.logging.Logger;

/**
 * Created by chaimturkel on 7/5/16.
 * Some examples for creation of RXjava
 */
public class CreateObservableExample {

    static Logger log = Logger.getLogger(CreateObservableExample.class.getCanonicalName());

    static public void iterateExample() {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        log.info("Complete!");
                    }

                    @Override
                    public void onError(Throwable e) {
                        log.severe("error: " + e.getMessage());
                    }

                    @Override
                    public void onNext(Integer value) {
                        log.info("onNext: " + value);
                    }
                });
    }

    static public Observable factory(){
        Observable.OnSubscribe<String> subscribeFunction = (subscriber) -> {
            for (int ii = 0; ii < 10; ii++) {
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext("Pushed value " + ii);
                }
            }

            if (!subscriber.isUnsubscribed()) {
                subscriber.onCompleted();
            }
        };

        return Observable.create(subscribeFunction);

    }
    static public void createExample() {
        Observable createdObservable = factory();

        createdObservable.subscribe(
                (incomingValue) -> log.info("incomingValue " + incomingValue),
                (error) -> log.severe("Something went wrong" + ((Throwable) error).getMessage()),
                () -> log.info("This observable is finished")
        );
    }

    public static void rangeExample() {
        Observable.range(1, 5).subscribe(
                number -> log.info(String.valueOf(number)),
                error -> log.severe("error"),
                () -> log.info("completed")
        );

    }

    public static void main(String[] args) {
        CreateObservableExample.iterateExample();

        //how to create an observable with subscription
        CreateObservableExample.createExample();
        //create observable from list
        CreateObservableExample.iterateExample();
        //create observable from range
        CreateObservableExample.rangeExample();

    }
}
