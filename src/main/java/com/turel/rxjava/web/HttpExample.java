package com.turel.rxjava.web;

import com.turel.rxjava.utils.Utils;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.nio.client.methods.HttpAsyncMethods;
import org.apache.http.nio.protocol.HttpAsyncRequestProducer;
import rx.Observable;
import rx.apache.http.ObservableHttp;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

/**
 * Created by chaimturkel on 10/14/16.
 */
public class HttpExample {
    static Logger log = Logger.getLogger(HttpExample.class.getCanonicalName());

    private CloseableHttpAsyncClient buildClient(){
        final RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(5000)
                .setConnectTimeout(500).build();
        return HttpAsyncClients.custom()
                .setDefaultRequestConfig(requestConfig)
                .setMaxConnPerRoute(20)
                .setMaxConnTotal(50)
                .build();
    }
    private CloseableHttpAsyncClient httpClient;


    public HttpExample(){
        httpClient = buildClient();
        httpClient.start();
    }

    public void close(){
        try {
            httpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Observable<String> getSite(){
        return Observable.empty();
    }

    public static void main(String[] args) {
        HttpExample httpExample = new HttpExample();
        Utils.runWithSubscription(log,httpExample.getSite(),1000);
        httpExample.close();
    }
}
