package com.mlytvyn.concurrency.management;

import java.util.concurrent.TimeUnit;

import com.mlytvyn.threads.SafeTask;
import com.mlytvyn.threads.UnsafeTask;

/**
 * One of the most critical aspects of a concurrent application is shared data.
 * This has special importance in those objects that extend the Thread class or
 * implement the Runnable interface.<br>
 * If you create an object of a class that implements the Runnable interface and
 * then start various Thread objects using the same Runnable object, all the
 * threads share the same attributes. This means that, if you change an
 * attribute in a thread, all the threads will be affected by this change.<br>
 * Sometimes, you will be interested in having an attribute that won't be shared
 * between all the threads that run the same object. The Java Concurrency API
 * provides a clean mechanism called thread-local variables with a very good
 * performance.<br>
 * In this recipe, we will develop a program that has the problem exposed in the
 * first paragraph and another program that solves this problem using the
 * thread-local variables mechanism.
 */
public class ThreadLocalVariables {

	public static void main(String... args) {
		performTask(new UnsafeTask(), 10);
		performTask(new SafeTask(), 10);
	}

	private static void performTask(Runnable task, int repeat) {
		for (int i = 0; i < repeat; i++) {
			Thread thread = new Thread(task);
			thread.start();
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
