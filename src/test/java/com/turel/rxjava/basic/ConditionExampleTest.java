package com.turel.rxjava.basic;

import com.turel.rxjava.error.ErrorExample;
import org.junit.Assert;
import org.junit.Test;
import rx.observers.TestSubscriber;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by chaimturkel on 10/10/16.
 */
public class ConditionExampleTest {

    @Test
    public void repeat(){
        TestSubscriber subscriber = new TestSubscriber<>();

        ConditionExamples.repeat().subscribe(subscriber);

        List<Integer> expected = new LinkedList<>();
        IntStream.range(1, 6).forEach(value -> expected.add(value));
        IntStream.range(1, 6).forEach(value -> expected.add(value));

        subscriber.assertReceivedOnNext(expected);
        final List<Throwable> onErrorEvents = subscriber.getOnErrorEvents();
        Assert.assertEquals(0,onErrorEvents.size());
        subscriber.assertTerminalEvent();

    }


    @Test
    public void whileAbove(){
        TestSubscriber subscriber = new TestSubscriber<>();

        ConditionExamples.whileAbove().subscribe(subscriber);

        List<Integer> expected = new LinkedList<>();
        IntStream.range(1, 4).forEach(value -> expected.add(value));

        subscriber.assertReceivedOnNext(expected);
        final List<Throwable> onErrorEvents = subscriber.getOnErrorEvents();
        Assert.assertEquals(0,onErrorEvents.size());
        subscriber.assertTerminalEvent();

    }
}
