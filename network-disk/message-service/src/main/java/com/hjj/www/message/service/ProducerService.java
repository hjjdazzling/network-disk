package com.hjj.www.message.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author haojunjie
 * @create 2020-05-22 11:30
 */
@Service
@Slf4j
public class ProducerService {
	@Autowired
	@Qualifier("transactionalKafkaTemplate")
	private KafkaTemplate<String, String> transactionalKafkaTemplate;

	@Autowired
	@Qualifier("noTransactionalKafkaTemplate")
	private KafkaTemplate<String, String> noTransactionalKafkaTemplate;

	private AtomicInteger countAtomic = new AtomicInteger(0);

	@Transactional
	public String send() {
		log.warn("countAtomic is " + countAtomic.get());

		transactionalKafkaTemplate.send("nsgocEvent", "hello");
		countAtomic.getAndIncrement();

		if (countAtomic.get() % 2 != 0) {
			throw new RuntimeException("fail send");
		}

		return "success";
	}


	public String send2() {
		log.warn("countAtomic is " + countAtomic.get());
		//失败返回null
		String result = noTransactionalKafkaTemplate.executeInTransaction(new KafkaOperations.OperationsCallback<String, String, String>() {
			@Override
			public String doInOperations(KafkaOperations<String, String> kafkaOperations) {
				noTransactionalKafkaTemplate.send("nsgocEvent", "hello");
				countAtomic.getAndIncrement();

				if (countAtomic.get() % 2 != 0) {
					throw new RuntimeException("fail send");
				}
				//throw new RuntimeException("fail");


				return "success";
			}
		});
		return result;
	}


	public void send3() {
		noTransactionalKafkaTemplate.send("ngsocEvent", "hello");
	}
}
