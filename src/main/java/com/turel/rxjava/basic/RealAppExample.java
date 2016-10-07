package com.turel.rxjava.basic;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.turel.rxjava.utils.Utils;
import org.apache.http.HttpStatus;
import rx.Observable;

import java.util.logging.Logger;

/**
 * Created by chaimturkel on 10/7/16.
 */
public class RealAppExample {
    static Logger log = Logger.getLogger(RealAppExample.class.getCanonicalName());

    /**
     * combine all requests
     * map them to requests using: Utils.getResponse(url)
     * add a retry of 2 in case of network failure
     * filer out all request that are not HttpStatus.SC_OK
     * aggregate to list
     * on subscribe print elements
     */
    public static void realApp() {
        final JsonParser jsonParser = new JsonParser();

        Observable<String> urlRequest1 = Observable.just("http://jsonplaceholder.typicode.com/posts/1");
        Observable<String> urlRequest2 = Observable.just("http://jsonplaceholder.typicode.com/posts/2");
        Observable<String> urlRequest3 = Observable.just("http://jsonplaceholder.typicode.com/error/2");

        Observable<String> allRequests = Observable.concat(urlRequest1, urlRequest2, urlRequest3);


        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        realApp();
    }
}
