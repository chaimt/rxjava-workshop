package com.turel.rxjava.web;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.turel.rxjava.utils.Utils;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;
import rx.Observable;
import rx.apache.http.ObservableHttp;

/**
 * Created by chaimturkel on 10/4/16.
 */
@Controller
@RequestMapping({"/api/v1/example"})
public class DeferedExample {

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

    protected HttpHeaders getHeaders() {
        HttpHeaders responseHeaders = new HttpHeaders();
        return responseHeaders;
    }

    protected <T> ResponseEntity<T> getResponse(T response) {
        return new ResponseEntity<T>(response, getHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value="/post", method= RequestMethod.GET)
    public @ResponseBody DeferredResult<ResponseEntity<StatusDto>> getPostSite(
            @RequestParam Long postId
    ) {
        final DeferredResult<ResponseEntity<StatusDto>> result = new DeferredResult<>();

        final CloseableHttpAsyncClient httpClient = buildClient();
        httpClient.start();

//        ObservableHttp.createGet("http://jsonplaceholder.typicode.com/posts/" + postId,httpClient).toObservable()

        return result;
    }


}
