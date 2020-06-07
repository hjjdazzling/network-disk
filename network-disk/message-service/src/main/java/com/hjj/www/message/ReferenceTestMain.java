package com.hjj.www.message;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author haojunjie
 * @create 2020-06-03 15:14
 */
public class ReferenceTestMain {
	private List<String> list = new ArrayList<>();

	public static void main(String[] args) {
		ReferenceTestMain referenceTestMain = new ReferenceTestMain();

		referenceTestMain.list.add("main");
		System.out.println(referenceTestMain.list);

		new Thread( () -> {
			System.out.println("1    " + referenceTestMain.list);

			List<String> list3 = new ArrayList<>();
			list3.add("thread1");

			referenceTestMain.list = list3;
		}).start();


		new Thread(() -> {
			System.out.println("2    " + referenceTestMain.list);

			List<String> list4 = new ArrayList<>();
			list4.add("thread2");

			referenceTestMain.list = list4;
		}).start();

		System.out.println("3    " + referenceTestMain.list);
	}
}
