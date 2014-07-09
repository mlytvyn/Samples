package com.mlytvyn.threads;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class UnsafeTask implements Runnable {

	private Date startDate;

	@Override
	public void run() {
		startDate = new Date();
		System.out.printf("[Unsafe] Starting Thread: %s : %s\n", Thread
				.currentThread().getId(), startDate);
		try {
			TimeUnit.SECONDS.sleep((int) Math.rint(Math.random() * 10));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("[Unsafe] Thread Finished: %s : %s\n", Thread
				.currentThread().getId(), startDate);
	}

}
