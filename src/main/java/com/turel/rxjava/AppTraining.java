package com.turel.rxjava;

import com.turel.rxjava.advanced.Timers;
import com.turel.rxjava.basic.CreateObservableExample;
import com.turel.rxjava.basic.ThreadExample;
import com.turel.rxjava.basic.TransformationExamples;
import com.turel.rxjava.error.ErrorExample;
import com.turel.rxjava.iterable.LoopData;

/**
 * Created by chaimturkel on 9/30/16.
 */
public class AppTraining {

    public static void main(String[] args) {

        //before rx
        LoopData.main(args);
        //question how will you write tests?

        //how to create an observable with subscription
        CreateObservableExample.main(args);
        //see BasicTest

        //From here you need to implement the methods for each class, and validate using
        //the predefinied tests

        //Transformations
        TransformationExamples.main(args);
        //see TransformationExamplesTest

        //MultiThreading
        ThreadExample.main(args);
        // see ThreadExampleTest

        //Error handling
        ErrorExample.main(args);
        // see ErrorExampleTest

        //advanced
        Timers.main(args);

        //Spring Web server
        //implement DeferedExample
        //see Application,DeferedExampleTest

        //Kafka Consumer

        //jpa problems

    }
}
