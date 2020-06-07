package com.hjj.www.controller;

/**
 * @author haojunjie
 * @create 2020-05-06 10:35
 */
public class ThreadMain {
	private boolean flag = false;

	public static void main(String[] args) {
		ThreadMain threadMain = new ThreadMain();

		Thread thread = new Thread(() -> {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			threadMain.flag = true;
			System.out.println("设置flag = true");
		});

		thread.start();

		Thread thread2 = new Thread(() -> {
			while (true) {
				if (threadMain.flag) {
					System.out.println("终于读到" + threadMain.flag);

					break;
				}
			}
		});

		thread2.start();
	}
}
