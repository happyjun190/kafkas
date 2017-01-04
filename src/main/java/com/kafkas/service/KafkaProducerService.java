package com.kafkas.service;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by wushenjun on 17-1-4.
 * kafka producer Service
 */
@Service
public class KafkaProducerService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Producer producer;

    @PostConstruct
    public void init() {
        int i=0;
        while(i<1000) {
            logger.info("this is producer send message :{}", i);
            producer.send(new ProducerRecord<>("my-replicated-topic", Integer.toString(i), Integer.toString(i)));
            i++;
        }
    }

}