package com.hjj.www.message;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author haojunjie
 * @create 2020-05-28 14:16
 */
public class KafkaMain {
	public static void main(String[] args) {
		Map<String, Object> configProps = new HashMap<>();
		configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.10.103:9092");
		configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "haojunjiezz235555");
		configProps.put(ConsumerConfig.MAX_PARTITION_FETCH_BYTES_CONFIG, 17825792);
		configProps.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, "300000");
		// 关闭自动提交
		configProps.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
		// earliest  不提交偏移量每次都从最早开始
		configProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		//configProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
		KafkaConsumer<String, String> kafkaConsumer = null;
		 try {
			 kafkaConsumer = new KafkaConsumer<>(configProps);

			 kafkaConsumer.subscribe(Collections.singletonList("haojunjie"));

			 while (true) {
				 ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(Duration.ofSeconds(1));

				 consumerRecords.forEach(consumerRecord -> {
					 String value = consumerRecord.value();

					 System.out.println(value);
				 });

				 //kafkaConsumer.commitSync();
			 }
		 } catch (Exception e) {

		 } finally {
			 kafkaConsumer.close();
		 }

	}
}
