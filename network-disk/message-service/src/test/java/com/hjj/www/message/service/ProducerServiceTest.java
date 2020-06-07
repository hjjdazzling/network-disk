package com.hjj.www.message.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author haojunjie
 * @create 2020-05-25 10:00
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProducerServiceTest {

	@Autowired
	private ProducerService producerService;


	@Test
	public void sendTest() {
		try {
			producerService.send();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Test
	public void send2Test() {
		try {
			producerService.send2();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
