package com.turel.rxjava.basic;

import org.junit.Test;
import rx.observers.TestSubscriber;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by chaimturkel on 10/6/16.
 */
public class TransformationExamplesTest {

    @Test
    public void mapExample(){
        TestSubscriber subscriber = new TestSubscriber<>();

        TransformationExamples.mapExample().subscribe(subscriber);

        List<Integer> expected = new LinkedList<>();
        IntStream.range(1, 6).forEach(value -> expected.add(value*2*10));

        subscriber.assertReceivedOnNext(expected);
        subscriber.assertNoErrors();
        subscriber.assertTerminalEvent();

    }

    @Test
    public void filterExample(){
        TestSubscriber subscriber = new TestSubscriber<>();

        TransformationExamples.filterExample().subscribe(subscriber);

        subscriber.assertReceivedOnNext(Arrays.asList(60,80));
        subscriber.assertNoErrors();
        subscriber.assertTerminalEvent();

    }

    @Test
    public void toListExample(){
        TestSubscriber subscriber = new TestSubscriber<>();

        TransformationExamples.toListExample().subscribe(subscriber);

        List expected = new LinkedList();
        expected.add(Arrays.asList(20,40,60,80,100));

        subscriber.assertReceivedOnNext(expected);
        subscriber.assertNoErrors();
        subscriber.assertTerminalEvent();
    }

    @Test
    public void toMapExample(){
        TestSubscriber subscriber = new TestSubscriber<>();

        TransformationExamples.toMapExample().subscribe(subscriber);

        List expected = new LinkedList();
        expected.add(new HashMap<String, Integer>(){{
            put("entry:40",40);
            put("entry:20",20);
            put("entry:80",80);
            put("entry:60",60);
            put("entry:100",100);
        }});


        subscriber.assertReceivedOnNext(expected);
        subscriber.assertNoErrors();
        subscriber.assertTerminalEvent();
    }

    @Test
    public void countExample(){
        TestSubscriber subscriber = new TestSubscriber<>();

        TransformationExamples.countExample().subscribe(subscriber);

        subscriber.assertReceivedOnNext(Arrays.asList(5));
        subscriber.assertNoErrors();
        subscriber.assertTerminalEvent();
    }

    @Test
    public void flatMapExample(){
        TestSubscriber subscriber = new TestSubscriber<>();

        TransformationExamples.flatMapExample().subscribe(subscriber);

        subscriber.assertReceivedOnNext(Arrays.asList(3,4,6,8));
        subscriber.assertNoErrors();
        subscriber.assertTerminalEvent();
    }
}
