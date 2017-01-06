package com.kafkas.service;

import com.kafkas.processor.MessageConsumer;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wushenjun on 17-1-3.
 * kafka consumer
 */
@Service
public class KafkaConsumerService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ConsumerConnector consumer;

    private ExecutorService executor;

    private Map<String, Integer> topicCountMap;
    private Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap;
    private List<KafkaStream<byte[], byte[]>> streams;

    // 启动多线程的Kafka consumer
    @PostConstruct
    public void init() {
        String topic = "my-replicated-topic";
        int threads = 4;

        topicCountMap = new HashMap<>();
        topicCountMap.put(topic, new Integer(threads));

        consumerMap = consumer.createMessageStreams(topicCountMap);
        streams = consumerMap.get(topic);

        //now launch all the threads
        executor = Executors.newFixedThreadPool(threads);

        //now create an object to consume the messages
        int threadNumber = 0;
        for(final KafkaStream<byte[], byte[]> stream : streams) {
            executor.submit(new MessageConsumer(stream, threadNumber));
            threadNumber++;
        }
        logger.info("{} threads/consumers started.", threadNumber);
    }


    @PreDestroy
    public void destroy() {
        if (consumer != null){
            consumer.shutdown();
        }
        if (executor != null){
            executor.shutdown();
        }
        logger.info("Consumer and executor shutdown completed.");
    }

}
