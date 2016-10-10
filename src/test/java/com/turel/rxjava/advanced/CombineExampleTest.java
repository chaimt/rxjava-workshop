package com.turel.rxjava.advanced;

import org.junit.Test;
import rx.observers.TestSubscriber;

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
}
