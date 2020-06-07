package com.hjj.www.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author haojunjie
 * @create 2020-05-08 19:46
 */
@RestController
@Slf4j
public class TaskController {

	@GetMapping("/task/{id}")
	public String testId(@PathVariable("id") Integer id) {
		log.info(id.toString());
		return "task success";
	}
}
