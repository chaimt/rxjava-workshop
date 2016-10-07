package com.turel.rxjava.iterable;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

/**
 * Created by chaimturkel on 8/22/16.
 */
public class LoopData {
    static Logger log = Logger.getLogger(LoopData.class.getCanonicalName());

    public static final List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    /**
     * write a loop that prints all numbers from the array, with the following conditions:
     * 1. above 5
     * 2. skip the first one
     * 3. double the value
     *
     * Use an Iterator
     */
    public static void loopIterable(){
        int skipCount = 0 ;
        int skip = 1 ;
        final Iterator<Integer> iterator = intList.iterator();
        while (iterator.hasNext()){
            final Integer nextInt = iterator.next();
            if (nextInt > 5) {
                if (skipCount < skip){
                    skipCount++;
                }
                else {
                    log.info(String.valueOf(nextInt*2));
                }
            }
        }
    }

    /**
     * write a loop that prints all numbers from the array, with the following conditions:
     * 1. above 5
     * 2. skip the first one
     * 3. double the value
     *
     * Use an Stream
     */
    public static void loopStream(){
        intList.stream()
                .filter(i -> i > 5)
                .map(i -> i*2)
                .skip(1)
                .forEach(nextInt -> log.info(String.valueOf(nextInt)));
    }


    /**
     * write a loop that prints all numbers from the array, with the following conditions:
     * 1. above 5
     * 2. skip the first one
     * 3. double the value
     *
     * Use an Stream with parellel
     */
    public static void loopStreamParallel(){
        intList.stream()
                .parallel()
                .filter(i -> i > 5)
                .map(i -> i*2)
                .skip(1)
                .forEach(nextInt -> log.info(nextInt.toString()));
    }

    /**
     * write a loop that prints all numbers from the array, with the following conditions:
     * 1. above 5
     * 2. skip the first one
     * 3. double the value
     *
     * Use an Stream with parellel with executor
     */
    public static void loopStreamThread(){
        final ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.execute(() -> {
            intList.stream()
                    .filter(i -> i > 5)
                    .map(i -> i*2)
                    .skip(1)
                    .forEach(nextInt -> log.info(nextInt.toString()));
        });
        executor.shutdown();

    }

    public static void main(String[] args) {
        loopIterable();
        loopStream();
        loopStreamParallel();
        loopStreamThread();
    }



}
