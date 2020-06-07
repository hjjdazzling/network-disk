package com.hjj.user.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author haojunjie
 * @create 2020-05-09 11:31
 */
@Service
@FeignClient(name = "task-service")
public interface TaskFeignClient {
	@GetMapping("/task/{id}")
	String testId(@PathVariable("id") Integer id);
}
