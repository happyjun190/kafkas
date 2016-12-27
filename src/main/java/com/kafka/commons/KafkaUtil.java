package com.kafka.commons;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;

import java.util.Properties;

/**
 * Created by wushenjun on 16-12-27.
 */
public class KafkaUtil {

    private static KafkaProducer<String, String> kafkaProducer;
    private static KafkaConsumer<String, String> kafkaConsumer;

    /**
     * 构建消息生产者
     * @return
     */
    public static KafkaProducer<String, String> getProducer() {
        if (kafkaProducer == null) {
            Properties properties = new Properties();
            properties.put("bootstrap.servers", "127.0.0.1:9092,127.0.0.1:9093,127.0.0.1:9094");
            properties.put("acks", "0");
            properties.put("retries", 0);
            properties.put("batch.size", 16384);
            properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            kafkaProducer = new KafkaProducer<>(properties);
        }
        return kafkaProducer;
    }


    /**
     * 构建消息消费者
     * @return
     */
    public static KafkaConsumer<String, String> getConsumer() {
        if(kafkaConsumer == null) {
            Properties properties = new Properties();

            properties.put("bootstrap.servers", "127.0.0.1:9091,127.0.0.1:9092,127.0.0.1:9093");
            properties.put("group.id", "12");
            properties.put("enable.auto.commit", "true");
            properties.put("auto.commit.interval.ms", "1000");
            properties.put("session.timeout.ms", "30000");
            properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
            properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
            kafkaConsumer = new KafkaConsumer<>(properties);
        }

        return kafkaConsumer;
    }
}
