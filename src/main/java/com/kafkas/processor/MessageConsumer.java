package com.kafkas.processor;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageConsumer implements Runnable {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private KafkaStream<byte[], byte[]> stream;
	private int threadNumber;

	public MessageConsumer(KafkaStream<byte[], byte[]> a_stream, int a_threadNumber) {
		stream = a_stream;
		threadNumber = a_threadNumber;
	}
	
	@Override
	public void run() {
		logger.info("Kafka Consumer started: Thread {}, {}", + threadNumber, Thread.currentThread().getName());
		
		ConsumerIterator<byte[], byte[]> it = stream.iterator();
		while (it.hasNext()) {
			String msg = new String(it.next().message());
			String[] msgLines = msg.split("\n");// 根据换行符分割，然后可以根据第二行判断是否Exception
			logger.info("message consumer read the message is : {}", msgLines);
			/*try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
		}
		logger.info("Shutting down Thread: {}", threadNumber);
	}

}
