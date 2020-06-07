package com.hjj.www;

import org.quartz.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author haojunjie
 * @create 2020-04-21 19:50
 */
@PersistJobDataAfterExecution
public class HelloJob implements Job {


	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

		System.out.println(LocalDateTime.now() + " ---> hello job" );

	}

}
