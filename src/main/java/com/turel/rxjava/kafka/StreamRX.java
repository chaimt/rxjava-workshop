package com.turel.rxjava.kafka;

import kafka.consumer.ConsumerIterator;
import org.apache.kafka.common.serialization.Deserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;

/**
 * Created by chaimturkel on 9/1/16.
 */
public class StreamRX {
    private static Logger logger = LoggerFactory.getLogger(StreamRX.class);

    /**
     * create an Observable for the Kafka Stream
     * @param it
     * @param topic
     * @param deserializer
     * @param <V>
     * @return
     */
    public static <V> Observable<V> create(ConsumerIterator<byte[], byte[]> it, String topic, Deserializer<V> deserializer) {
        return Observable.empty();
    }

}
