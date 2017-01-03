package com.kafkas.config;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.javaapi.consumer.ConsumerConnector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * Created by wushenjun on 17-1-3.
 * kafka consumer group config
 */
@Configuration
public class KafkaConsumerConfig {
    @Value("${zookeeper.host1}")
    private String zookeeper1;

    private String groupId = "kafkaCG1";

    @Bean
    public ConsumerConnector cousumer() {
        Properties properties = new Properties();
        properties.put("zookeeper.connect", zookeeper1);
        properties.put("group.id", groupId);
        properties.put("zookeeper.session.timeout.ms", "400");
        properties.put("zookeeper.sync.time.ms", "200");
        properties.put("auto.commit.interval.ms", "1000");

        return Consumer.createJavaConsumerConnector(new ConsumerConfig(properties));
    }

}
