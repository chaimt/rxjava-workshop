package com.turel.rxjava.utils.mvc;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.core.ResolvableType;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import rx.Observable;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * {@link HandlerMethodArgumentResolver} that can provide an {@link Observable} that works on an custom bean
 * containing various parts of the request.
 *
 * @author Raniz
 * @since 1.0
 */
public class ObservableHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    private final ConversionService conversionService;

    private Collection<HandlerMethodArgumentResolver> argumentResolvers;

    @Autowired
    public ObservableHandlerMethodArgumentResolver(ConversionService conversionService) {
        this.conversionService = conversionService;
        this.argumentResolvers = Collections.emptyList();
    }

    public void setArgumentResolvers(Collection<HandlerMethodArgumentResolver> argumentResolvers) {
        this.argumentResolvers = argumentResolvers;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return Observable.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        ResolvableType type = ResolvableType.forMethodParameter(parameter);
        Constructor<?> constructor = findConstructor(type.getGeneric(0).getRawClass());
        constructor.setAccessible(true);
        ArrayList<Object> parameters = new ArrayList<>();
        for(int i = 0; i < constructor.getParameterCount(); i++) {
            MethodParameter methodParameter = MethodParameter.forMethodOrConstructor(constructor, i);
            for(HandlerMethodArgumentResolver resolver : argumentResolvers) {
                if(resolver.supportsParameter(methodParameter)) {
                    Object value = resolver.resolveArgument(methodParameter, mavContainer, webRequest, binderFactory);
                    if(value != null && !methodParameter.getParameterType().isAssignableFrom(value.getClass())) {
                        value = conversionService.convert(value, TypeDescriptor.forObject(value), new TypeDescriptor(methodParameter));
                    }
                    parameters.add(value);
                    break;
                }
            }
        }
        Object containerBean = constructor.newInstance(parameters.toArray());
        return Observable.just(containerBean);
    }

    private static Constructor<?> findConstructor(Class<?> type) {
        final Constructor<?>[] constructors = type.getConstructors();
        if(constructors.length == 1) {
            return constructors[0];
        }
        for(Constructor<?> constructor : constructors) {
            if(constructor.getAnnotation(Autowired.class) != null) {
                return constructor;
            }
        }
        throw new IllegalArgumentException("Could not find either single or @Autowired-annotated constructor of class " + type);
    }
}
