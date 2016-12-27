package com.kafka.example;


import com.kafka.commons.KafkaUtil;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * kafka message producer
 * Created by wushenjun on 16-12-27.
 */
public class KafkaProducerExample {
    private static final Logger logger = LoggerFactory.getLogger(KafkaProducerExample.class);

    public static void main(String[] s) {

        try {
            //构建消息生产者
            Producer<String, String> producer = KafkaUtil.getProducer();
            int i = 0;
            while(true) {
                ProducerRecord<String, String> record = new ProducerRecord<String, String>("my-replicated-topic", String.valueOf(i), "this is message"+i);
                producer.send(record, new Callback() {
                    public void onCompletion(RecordMetadata metadata, Exception e) {
                        if (e != null) {
                            e.printStackTrace();
                        }
                        logger.info("message send to partition " + metadata.partition() + ", offset: " + metadata.offset());
                    }
                });
                i++;
                Thread.sleep(1000);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
