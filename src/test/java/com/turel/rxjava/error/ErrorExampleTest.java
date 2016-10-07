package com.turel.rxjava.error;

import org.junit.Assert;
import org.junit.Test;
import rx.observers.TestSubscriber;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by chaimturkel on 10/7/16.
 */
public class ErrorExampleTest {

    @Test
    public void onError(){
        TestSubscriber subscriber = new TestSubscriber<>();

        ErrorExample.onError().subscribe(subscriber);

        List<Integer> expected = new LinkedList<>();

        subscriber.assertReceivedOnNext(expected);
        final List<Throwable> onErrorEvents = subscriber.getOnErrorEvents();
        Assert.assertEquals(1,onErrorEvents.size());
        Assert.assertEquals("my error",onErrorEvents.get(0).getMessage());
        Assert.assertEquals("java.lang.RuntimeException",onErrorEvents.get(0).getClass().getCanonicalName());
        subscriber.assertTerminalEvent();

    }

    @Test
    public void propagateError(){
        TestSubscriber subscriber = new TestSubscriber<>();

        ErrorExample.propagateError().subscribe(subscriber);

        List<Integer> expected = new LinkedList<>();

        subscriber.assertReceivedOnNext(expected);
        final List<Throwable> onErrorEvents = subscriber.getOnErrorEvents();
        Assert.assertEquals(1,onErrorEvents.size());
        Assert.assertEquals("java.io.IOException: No File",onErrorEvents.get(0).getLocalizedMessage());
        Assert.assertEquals("java.lang.RuntimeException",onErrorEvents.get(0).getClass().getCanonicalName());
        Assert.assertEquals("No File",onErrorEvents.get(0).getCause().getMessage());
        Assert.assertEquals("java.io.IOException",onErrorEvents.get(0).getCause().getClass().getCanonicalName());

        subscriber.assertTerminalEvent();
    }

    @Test
    public void divisionError(){
        TestSubscriber subscriber = new TestSubscriber<>();

        ErrorExample.divisionError().subscribe(subscriber);

        List<Integer> expected = Arrays.asList(100,50);

        subscriber.assertReceivedOnNext(expected);
        final List<Throwable> onErrorEvents = subscriber.getOnErrorEvents();
        Assert.assertEquals(1,onErrorEvents.size());
        Assert.assertEquals("/ by zero",onErrorEvents.get(0).getLocalizedMessage());
        Assert.assertEquals("java.lang.ArithmeticException",onErrorEvents.get(0).getClass().getCanonicalName());
        Assert.assertEquals("OnError while emitting onNext value: Integer.class",onErrorEvents.get(0).getCause().getMessage());
        Assert.assertEquals("rx.exceptions.OnErrorThrowable.OnNextValue",onErrorEvents.get(0).getCause().getClass().getCanonicalName());

        subscriber.assertTerminalEvent();
    }

    @Test
    public void onErrorReturn(){
        TestSubscriber subscriber = new TestSubscriber<>();

        ErrorExample.onErrorReturn().subscribe(subscriber);

        List<String> expected = Arrays.asList("Hello!.","Empty result");

        subscriber.assertReceivedOnNext(expected);
        subscriber.assertNoErrors();
        subscriber.assertTerminalEvent();
    }



    @Test
    public void onExceptionResumeNext(){
        TestSubscriber subscriber = new TestSubscriber<>();

        ErrorExample.onExceptionResumeNext().subscribe(subscriber);

        List<String> expected = Arrays.asList("Hello!.");

        subscriber.assertReceivedOnNext(expected);
        subscriber.assertNoErrors();
        subscriber.assertTerminalEvent();
    }

    @Test
    public void onErrorResumeNext(){
        TestSubscriber subscriber = new TestSubscriber<>();

        ErrorExample.onErrorResumeNext().subscribe(subscriber);

        List<String> expected = Arrays.asList("Hello!.","new","flow","go");

        subscriber.assertReceivedOnNext(expected);
        subscriber.assertNoErrors();
        subscriber.assertTerminalEvent();
    }



}
