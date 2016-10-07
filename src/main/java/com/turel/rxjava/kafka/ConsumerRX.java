package com.turel.rxjava.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;

/**
 * Created by chaimturkel on 8/1/16.
 */
public class ConsumerRX {
    private static Logger logger = LoggerFactory.getLogger(ConsumerRX.class);

    /**
     * create an observable for the KafkaConsumer
     * @param consumer
     * @param pollTime
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> Observable create(KafkaConsumer<K, V> consumer, int pollTime) {
        return Observable.<V>create(subscriber -> {
            logger.info("poll kafka consumer");
            while (!subscriber.isUnsubscribed()) {
                final ConsumerRecords<K, V> messages = consumer.poll(pollTime);
                if (messages != null) {
                    boolean errorFound = false;
                    if (!errorFound) {
                        for (ConsumerRecord<K, V> message : messages) {
                            subscriber.onNext(message.value());
                        }
                    }
                }
            }
            if (!subscriber.isUnsubscribed()) {
                subscriber.onCompleted();
            }
            logger.info("end kafka consumer");
        });
    }

}
