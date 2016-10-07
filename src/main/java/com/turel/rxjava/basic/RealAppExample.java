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

    public static void realApp() {
        final JsonParser jsonParser = new JsonParser();

        Observable<String> urlRequest1 = Observable.just("http://jsonplaceholder.typicode.com/posts/1");
        Observable<String> urlRequest2 = Observable.just("http://jsonplaceholder.typicode.com/posts/2");
        Observable<String> urlRequest3 = Observable.just("http://jsonplaceholder.typicode.com/error/2");

        Observable<String> allRequests = Observable.concat(urlRequest1, urlRequest2, urlRequest3);

        allRequests
                .map(url -> Utils.getResponse(url))
                .retry(2)
                .filter(response -> response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
                .map(response -> jsonParser.parse(Utils.httpEntityToString(response.getEntity())))
                .toList()
                .subscribe(
                        jsonElementList -> {
                            log.info("********************************");
                            for (JsonElement jsonElement : jsonElementList) {
                                log.info("element - " + jsonElement.toString());
                            }
                        },
                        error -> log.severe("error"),
                        () -> log.info("completed")
                );

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
