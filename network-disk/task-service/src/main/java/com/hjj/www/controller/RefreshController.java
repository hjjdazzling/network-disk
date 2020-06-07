package com.hjj.www.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author haojunjie
 * @create 2020-04-28 15:26
 */
@RestController
@RefreshScope
public class RefreshController {
//	@Value("${hello}")
//	private String hello;
//
//	@GetMapping("/test")
//	public String test() {
//		return hello;
//	}
}
