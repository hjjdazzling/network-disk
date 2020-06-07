package com.hjj.www.message.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author haojunjie
 * @create 2020-05-26 11:54
 */
@Slf4j
@Component
public class TestService implements InitializingBean {
	@Override
	public void afterPropertiesSet() throws Exception {
		log.info("TestService    afterPropertiesSet...");
	}
}
