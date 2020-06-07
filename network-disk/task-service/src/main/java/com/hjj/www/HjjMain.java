package com.hjj.www;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author haojunjie
 * @create 2020-05-11 17:05
 */
public class HjjMain {
	public static void main(String[] args) {
//		int cpuNumber = Runtime.getRuntime().availableProcessors();
//
//		System.out.println(cpuNumber);


		List<String> list = new ArrayList<>();

		list.add("1");
		list.add("2");

		Iterator<String> iterator = list.iterator();

//		while (iterator.hasNext()) {
//			String item = iterator.next();
//
//			if ("2".equals(item)) {
//				iterator.remove();
//			}
//		}

		for (String item : list) {
			if ("2".equals(item)) {
				list.remove(item);
			}
		}


		System.out.println(list);
	}
}
