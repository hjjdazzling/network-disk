package com.hjj.www.message;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author haojunjie
 * @create 2020-06-03 14:52
 */
public class AtomicReferenceTestMain {
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();

		list.add("main");
		System.out.println(list);

		AtomicReference<List<String>> atomicReference = new AtomicReference<>(list);

		new Thread( () -> {
			List<String> list2 = atomicReference.get();
			System.out.println("1     " + list2);


			list2.add("thread1");

			atomicReference.set(list2);
		}).start();


		new Thread(() -> {
			List<String> list3 = atomicReference.get();
			System.out.println("2    " + list3);


			list3.add("thread2");
			atomicReference.set(list3);
		}).start();

		System.out.println("3    " +atomicReference.get());
	}
}
