package com.turel.rxjava.advanced;

import org.junit.Assert;
import org.junit.Test;
import rx.observers.TestSubscriber;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by chaimturkel on 10/10/16.
 */
public class CombineExampleTest {

    @Test
    public void mergeWith(){
        TestSubscriber subscriber = new TestSubscriber<>();

        CombineExample.mergeWith().subscribe(subscriber);

        List<Integer> expected = new LinkedList<>();
        IntStream.range(1, 11).forEach(value -> expected.add(value));
        IntStream.range(20, 30).forEach(value -> expected.add(value));


        subscriber.assertReceivedOnNext(expected);
        subscriber.assertNoErrors();
        subscriber.assertTerminalEvent();

    }

    @Test
    public void merge(){
        TestSubscriber subscriber = new TestSubscriber<>();

        CombineExample.merge().subscribe(subscriber);

        List<Integer> expected = new LinkedList<>();
        IntStream.range(1, 11).forEach(value -> expected.add(value));
        IntStream.range(20, 30).forEach(value -> expected.add(value));
        IntStream.range(50, 60).forEach(value -> expected.add(value));


        subscriber.assertReceivedOnNext(expected);
        subscriber.assertNoErrors();
        subscriber.assertTerminalEvent();

    }

    @Test
    public void reduce(){
        TestSubscriber subscriber = new TestSubscriber<>();

        CombineExample.reduce().subscribe(subscriber);

        subscriber.assertReceivedOnNext(Arrays.asList(15));
        subscriber.assertNoErrors();
        subscriber.assertTerminalEvent();
    }

    @Test
    public void reduceOne(){
        TestSubscriber subscriber = new TestSubscriber<>();

        CombineExample.reduceOne().subscribe(subscriber);

        subscriber.assertReceivedOnNext(Arrays.asList(5));
        subscriber.assertNoErrors();
        subscriber.assertTerminalEvent();

    }

    @Test
    public void reduceNull(){
        TestSubscriber subscriber = new TestSubscriber<>();

        CombineExample.reduceNull().subscribe(subscriber);

        subscriber.assertReceivedOnNext(Arrays.asList());
        Assert.assertEquals(1,subscriber.getOnErrorEvents().size());
        subscriber.assertTerminalEvent();

    }

    @Test
    public void reduceNullToZero(){
        TestSubscriber subscriber = new TestSubscriber<>();

        CombineExample.reduceNullToZero().subscribe(subscriber);

        subscriber.assertReceivedOnNext(Arrays.asList(0));
        subscriber.assertNoErrors();
        subscriber.assertTerminalEvent();

    }
    @Test
    public void zip(){
        TestSubscriber subscriber = new TestSubscriber<>();

        CombineExample.zip().subscribe(subscriber);

        subscriber.assertReceivedOnNext(Arrays.asList("a-1","b-2","c-3","d-4"));
        subscriber.assertNoErrors();
        subscriber.assertTerminalEvent();

    }
}
