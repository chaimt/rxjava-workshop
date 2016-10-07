package com.turel.rxjava;

import com.turel.rxjava.basic.CreateObservableExample;
import org.junit.Test;
import rx.Observable;
import rx.Subscription;
import rx.observers.TestSubscriber;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by chaimturkel on 10/1/16.
 */
public class BasicTest {

    @Test
    public void simpleTest(){
        TestSubscriber subscriber = new TestSubscriber<>();

        final Observable factory = CreateObservableExample.factory();
        factory.subscribe(subscriber);

        subscriber.assertNoErrors();

        List<String> expected = new LinkedList<String>();
        for (int ii = 0; ii < 10; ii++) {
            expected.add("Pushed value " + ii);

        }
        subscriber.assertReceivedOnNext(expected);
        subscriber.assertTerminalEvent();

    }
}
