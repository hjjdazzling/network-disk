package com.hjj.www.message.controller;

import com.hjj.www.message.service.ProducerService;
import com.hjj.www.message.service.TestService;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author haojunjie
 * @create 2020-05-25 12:01
 */
@RestController
public class TestController {
	@Autowired
	private ProducerService producerService;

	@Autowired
	private TestService testService;

	@GetMapping("/send")
	public String sendMessage() {
		return producerService.send();
	}


	@GetMapping("/send2")
	public String sendMessage2() {


		return producerService.send2();
	}


	@GetMapping("/send3")
	public String sendMessage3() {

		producerService.send3();
		return "success";
	}
}
