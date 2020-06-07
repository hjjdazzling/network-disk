package com.hjj.www.message.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author haojunjie
 * @create 2019-09-29 14:18
 */
@Configuration
public class ThreadExecutorConfig {
	@Bean
	public ThreadPoolExecutor threadPoolExecutor() {
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
				4, 10, 1000, TimeUnit.MICROSECONDS,
				new LinkedBlockingQueue<Runnable>(), Executors.defaultThreadFactory(),
				new ThreadPoolExecutor.AbortPolicy()
		);

		return threadPoolExecutor;
	}
}
