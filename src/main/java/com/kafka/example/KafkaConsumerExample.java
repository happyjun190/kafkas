package com.kafka.example;

import com.kafka.commons.KafkaUtil;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by wushenjun on 16-12-27.
 */
public class KafkaConsumerExample {
    private static final Logger logger = LoggerFactory.getLogger(KafkaProducerExample.class);

    public static void main(String[] s){

        KafkaConsumer<String, String> consumer = KafkaUtil.getConsumer();
        consumer.subscribe(Arrays.asList("my-replicated-topic"));
        consumer.seekToBeginning(new ArrayList<>());

        while(true) {
            ConsumerRecords<String, String> records = consumer.poll(1000);
            for(ConsumerRecord<String, String> record : records) {
                logger.info("fetched from partition " + record.partition() + ", offset: " + record.offset() + ", message: " + record.value());
            }
            //按分区读取数据
//              for (TopicPartition partition : records.partitions()) {
//                  List<ConsumerRecord<String, String>> partitionRecords = records.records(partition);
//                  for (ConsumerRecord<String, String> record : partitionRecords) {
//                      System.out.println(record.offset() + ": " + record.value());
//                  }
//              }

        }

    }
}
