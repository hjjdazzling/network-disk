package com.hjj.www;

import com.googlecode.concurrentlinkedhashmap.ConcurrentLinkedHashMap;
import com.googlecode.concurrentlinkedhashmap.EvictionListener;
import com.googlecode.concurrentlinkedhashmap.Weighers;

import java.util.Map;

/**
 * @author haojunjie
 * @create 2020-04-27 11:08
 */
public class TestMain {
	public static void main(String[] args) {
		EvictionListener<String, String> listener =
				(key, value) -> System.out.println("delete:key = " + key + " value = " + value);

		Map<String, String> map = new ConcurrentLinkedHashMap.Builder<String,String>()
				.weigher(Weighers.singleton())
				.maximumWeightedCapacity(3)
				.listener(listener)
				.build();

		map.put("1", "1");
		System.out.println(map);
		map.put("2", "2");
		System.out.println(map);
		map.put("3", "3");
		System.out.println(map);
		map.get("1");
		System.out.println(map);
		map.put("4", "4");
		System.out.println(map);
		map.put("5", "5");
		System.out.println(map);
		map.put("6", "6");
		System.out.println(map);
	}
}
