package com.hjj.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author haojunjie
 * @create 2020-05-07 11:21
 */
@RestController
public class ServiceController {
	@Autowired
	private DiscoveryClient discoveryClient;

	@GetMapping("/service-instance/{service-name}")
	public List<ServiceInstance> showInfo(@PathVariable("service-name") String serviceName) {
		return discoveryClient.getInstances(serviceName);
	}
}
