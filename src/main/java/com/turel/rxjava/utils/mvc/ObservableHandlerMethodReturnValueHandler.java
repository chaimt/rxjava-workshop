package com.turel.rxjava.utils.mvc;

import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncUtils;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import rx.Observable;

/**
 * {@link HandlerMethodReturnValueHandler} for handling {@link Observable Observables}.
 * Creates a {@link DeferredResult} and subscribes to the returned {@link Observable} with a
 * subscriber that calls {@link DeferredResult#setResult(Object)} or
 * {@link DeferredResult#setErrorResult(Object)} depending on the subscriber method called.
 *
 * @author Chaim Turkel
 * @since 1.0
 */
public class ObservableHandlerMethodReturnValueHandler implements HandlerMethodReturnValueHandler {

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return Observable.class.isAssignableFrom(returnType.getParameterType());
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        Observable<?> observable = (Observable<?>) returnValue;
        DeferredResult deferredResult = new DeferredResult();
        WebAsyncUtils.getAsyncManager(webRequest).startDeferredResultProcessing(deferredResult);
        observable.subscribe(deferredResult::setResult, deferredResult::setErrorResult);
    }
}

