package com.hjj.www.message.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.transaction.KafkaTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.HashMap;
import java.util.Map;

/**
 * @author haojunjie
 * @create 2020-05-22 11:31
 */
@RefreshScope
@Configuration
@EnableKafka
public class KafkaProducerConfig {
	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServers;

	/**
	 * 生产者配置
	 * @return
	 */
	@Bean
	public Map<String, Object> kafkaTransactionalConfigs() {
		Map<String, Object> props = new HashMap<>(14);
		// 连接地址
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);

		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
		// 重试，0为不启用重试机制
		props.put(ProducerConfig.RETRIES_CONFIG, 1);
		// 控制批处理大小，单位为字节
		props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
		// 批量发送，延迟为1毫秒，启用该功能能有效减少生产者发送消息次数，从而提高并发量
		props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
		// 生产者可以使用的总内存字节来缓冲等待发送到服务器的记录
		props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 1024000);

		props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);

		return props;
	}

	@Bean
	public ProducerFactory<String, String> transactionProducerFactory() {
		DefaultKafkaProducerFactory<String, String> factory = new DefaultKafkaProducerFactory<>(kafkaTransactionalConfigs());
		// 开启事务
		factory.transactionCapable();
		// 用来生成Transactional.id的前缀
		factory.setTransactionIdPrefix("test.transaction");

		return factory;
	}

	@Bean
	public ProducerFactory<String, String> producerFactory() {
		return new DefaultKafkaProducerFactory(kafkaTransactionalConfigs());
	}

	/**
	 * 事务管理器
	 * @param
	 * @return
	 */
	@Bean("kafkaTransactionManager")
	public KafkaTransactionManager<String, String> kafkaTransactionManager(ProducerFactory<String, String> transactionProducerFactory) {
		KafkaTransactionManager<String, String> manager = new KafkaTransactionManager<>(transactionProducerFactory);
		return manager;
	}

	@Bean("transactionalKafkaTemplate")
	public KafkaTemplate<String, String> transactionalKafkaTemplate(ProducerFactory<String, String> transactionProducerFactory) {
		KafkaTemplate<String, String> template = new KafkaTemplate<>(transactionProducerFactory);

		return template;
	}



	@Bean("noTransactionalKafkaTemplate")
	public KafkaTemplate<String, String> noTransactionalKafkaTemplate(ProducerFactory<String, String> producerFactory) {
		KafkaTemplate<String, String> template = new KafkaTemplate<>(producerFactory);

		return template;
	}
}
