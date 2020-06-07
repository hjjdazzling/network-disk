package com.hjj.user.controller;

import com.hjj.user.feign.TaskFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.Pattern;
import java.util.Set;

/**
 * @author haojunjie
 * @create 2020-05-08 19:35
 */
@RestController
@Slf4j
public class TaskServiceController {
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private LoadBalancerClient loadBalancerClient;

	@Autowired
	private TaskFeignClient taskFeignClient;

	@GetMapping("/feign/user/{id}")
	public String test2(@PathVariable("id") Integer id) {
		return taskFeignClient.testId(id);
	}


	@GetMapping("/user/{id}")
	public String test(@PathVariable("id") Integer id) {
		return restTemplate.getForObject("http://task-service/task/" + id, String.class);
	}

	@GetMapping("/load-balance")
	public void logTaskService() {
		ServiceInstance serviceInstance = loadBalancerClient.choose("task-service");
		Set<String> keySet =  serviceInstance.getMetadata().keySet();

		for (String key : keySet) {
			System.out.println(key + " : " + serviceInstance.getMetadata().get(key));
		}

		log.info("{}:{}:{}", serviceInstance.getServiceId(), serviceInstance.getHost(), serviceInstance.getPort());
	}
}
