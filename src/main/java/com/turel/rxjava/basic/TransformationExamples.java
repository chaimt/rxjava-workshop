package com.turel.rxjava.basic;

import com.turel.rxjava.utils.Utils;
import rx.Observable;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by chaimturkel on 10/1/16.
 */
public class TransformationExamples {
    static Logger log = Logger.getLogger(TransformationExamples.class.getCanonicalName());



    /**
     * use a range from 1-5
     * multiply value by 2
     * multiply value by 10
     * @return Observable<Integer>
     */
    public static Observable<Integer> mapExample(){
        log.info("mapExample");

        return Observable.range(1, 5)
                .map(i -> i * 2)
                .map(i -> i * 10);
    }

    /**
     * use a range from 1-5
     * filter out only values above 2
     * multiply value by 2
     * multiply value by 10
     * filter out values below 100
     * @return Observable<Integer>
     */
    public static Observable<Integer> filterExample(){
        log.info("filterExample");

        return Observable.range(1, 5)
                .filter(i -> i > 2)
                .map(i -> i * 2)
                .map(i -> i * 10)
                .filter(i -> i < 100);
    }

    /**
     * use a range from 1-5
     * multiply value by 2
     * multiply value by 10
     * return an Observable with the list of all integers
     * @return Observable<List<Integer>>
     */
    public static Observable<List<Integer>> toListExample(){
        log.info("listExample");
        return Observable.range(1, 5)
                .map(i -> i * 2)
                .map(i -> i * 10)
                .toList();
    }

    /**
     * use a range from 1-5
     * multiply value by 2
     * multiply value by 10
     * return an Observable with the map of all integers (key= entry: i, value = i)
     * @return Observable<Map<String, Integer>>
     */
    public static Observable<Map<String, Integer>> toMapExample(){
        log.info("toMapExample");
        return Observable.range(1, 5)
                .map(i -> i * 2)
                .map(i -> i * 10)
                .toMap(i -> "entry:" + i, i -> i);
    }

    /**
     * use a range from 1-5
     * multiply value by 2
     * multiply value by 10
     * return an Observable with the count of items
     * @return Observable<Integer>
     */
    public static Observable<Integer> countExample(){
        log.info("countExample");
        return Observable.range(1, 5)
                .map(i -> i * 2)
                .map(i -> i * 10)
                .count();
    }

    /**
     * use a range from 1-2
     * for each value (i) create a new tuple (you can use "just") with values: i*3,i*4
     * @return
     */
    public static Observable<Integer> flatMapExample(){
        log.info("flatMapExample");
         return Observable.range(1, 2)
                .flatMap(i -> Observable.just(i*3,i*4));
    }


    public static void main(String[] args) {
        Utils.runWithSubscription(log,mapExample());
        Utils.runWithSubscription(log,filterExample());
        Utils.runWithSubscription(log,toListExample());
        Utils.runWithSubscription(log,toMapExample());
        Utils.runWithSubscription(log,countExample());
        Utils.runWithSubscription(log,flatMapExample());
    }
}
