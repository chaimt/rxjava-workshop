package com.turel.rxjava.advanced;

import org.junit.Assert;
import org.junit.Test;
import rx.Observable;
import rx.observers.TestSubscriber;
import rx.schedulers.TestScheduler;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Created by chaimturkel on 10/10/16.
 */
public class TimersTest {

    @Test
    public void tick(){

        TestScheduler scheduler = new TestScheduler();
        TestSubscriber testSubscriber = new TestSubscriber<>();

        TimersExample.ticks(scheduler).observeOn(scheduler).subscribeOn(scheduler).subscribe(testSubscriber);
        scheduler.advanceTimeBy(200, TimeUnit.MILLISECONDS);
        testSubscriber.assertReceivedOnNext(Arrays.asList("Success 0", "Success 1"));

        testSubscriber.assertNoErrors();
    }

    @Test
    public void mergeFlows(){

        TestScheduler scheduler = new TestScheduler();
        TestSubscriber testSubscriber = new TestSubscriber<>();

        TimersExample.mergeFlows(scheduler).observeOn(scheduler).subscribeOn(scheduler).subscribe(testSubscriber);
        scheduler.advanceTimeBy(200, TimeUnit.MILLISECONDS);
        testSubscriber.assertReceivedOnNext(Arrays.asList("yellow 0", "blue 0","yellow 1", "blue 1"));

        testSubscriber.assertNoErrors();
    }
}
