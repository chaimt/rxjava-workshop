package com.turel.rxjava.basic;

import org.junit.Assert;
import org.junit.Test;
import rx.observers.TestSubscriber;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by chaimturkel on 10/6/16.
 */
public class ThreadExampleTest {

    @Test
    public void noThread(){
        TestSubscriber subscriber = new TestSubscriber<>();

        ThreadExample.noThread().subscribe(subscriber);

        List<Integer> expected = new LinkedList<>();
        IntStream.range(1, 6).forEach(value -> expected.add(value));

        subscriber.assertReceivedOnNext(expected);
        subscriber.assertNoErrors();
        subscriber.assertTerminalEvent();
    }

    @Test
    public void subscriptionThread(){
        TestSubscriber subscriber = new TestSubscriber<>();

        ThreadExample.subscriptionThread().subscribe(subscriber);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<Integer> expected = new LinkedList<>();
        IntStream.range(1, 6).forEach(value -> expected.add(value*2));

        subscriber.assertReceivedOnNext(expected);
        subscriber.assertNoErrors();
        subscriber.assertTerminalEvent();

    }

    @Test
    public void observerThread(){
        TestSubscriber subscriber = new TestSubscriber<>();

        ThreadExample.observerThread().subscribe(subscriber);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<Integer> expected = new LinkedList<>();
        IntStream.range(1, 6).forEach(value -> expected.add(value*2));

        subscriber.assertReceivedOnNext(expected);
        subscriber.assertNoErrors();
        subscriber.assertTerminalEvent();

    }

    @Test
    public void multipleObserveon(){
        TestSubscriber subscriber = new TestSubscriber<>();

        ThreadExample.multipleObserveon().subscribe(subscriber);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<Integer> expected = new LinkedList<>();
        IntStream.range(1, 6).forEach(value -> expected.add(value*4));

        subscriber.assertReceivedOnNext(expected);
        subscriber.assertNoErrors();
        subscriber.assertTerminalEvent();

    }


    @Test
    public void threadPerItem(){
        TestSubscriber subscriber = new TestSubscriber<>();

        ThreadExample.threadPerItem().subscribe(subscriber);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<Integer> expected = new LinkedList<>();
        IntStream.range(1, 6).forEach(value -> expected.add(value*2));

        //since it is multithreading we don't know the order
        expected.stream().forEach(value -> {
            final int i = subscriber.getOnNextEvents().indexOf(value);
            Assert.assertNotEquals(-1,i);
        });

        subscriber.assertNoErrors();
        subscriber.assertTerminalEvent();

    }


}
