package com.turel.rxjava.web;

import org.junit.Test;
import rx.observers.TestSubscriber;

import java.util.Arrays;

/**
 * Created by chaimturkel on 10/14/16.
 */
public class HttpExampleTest {

    @Test
    public void getSite() throws InterruptedException {
        TestSubscriber testSubscriber = new TestSubscriber<>();

        HttpExample httpExample = new HttpExample();
        httpExample.getSite().subscribe(testSubscriber);
        Thread.sleep(1000);
        testSubscriber.assertReceivedOnNext(Arrays.asList("{\n" +
                "  \"userId\": 1,\n" +
                "  \"id\": 1,\n" +
                "  \"title\": \"sunt aut facere repellat provident occaecati excepturi optio reprehenderit\",\n" +
                "  \"body\": \"quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto\"\n" +
                "}"));
        testSubscriber.assertNoErrors();
    }
}
