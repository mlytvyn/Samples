package com.mlytvyn.concurrency.management;

import java.util.concurrent.TimeUnit;

import com.mlytvyn.pojo.Result;
import com.mlytvyn.threads.SearchTask;

/**
 * An interesting functionality offered by the concurrency API of Java is the
 * ability to group the threads. This allows us to treat the threads of a group
 * as a single unit and provides access to the Thread objects that belong to a
 * group to do an operation with them. For example, you have some threads doing
 * the same task and you want to control them, irrespective of how many threads
 * are still running, the status of each one will interrupt all of them with a
 * single call.<br>
 * Java provides the ThreadGroup class to work with groups of threads. A
 * ThreadGroup object can be formed by Thread objects and by another ThreadGroup
 * object, generating a tree structure of threads.<br>
 * In this recipe, we will learn to work with ThreadGroup objects developing a
 * simple example. We will have 10 threads sleeping during a random period of
 * time (simulating a search, for example) and, when one of them finishes, we
 * are going to interrupt the rest.
 */
public class ThreadGroups {

	public static void main(String... args) {
		ThreadGroup threadGroup = new ThreadGroup("Searcher");
		Result result = new Result();
		SearchTask searchTask = new SearchTask(result);
		for (int i = 0; i < 5; i++) {
			Thread thread = new Thread(threadGroup, searchTask);
			thread.start();
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.printf("Number of Threads: %d\n", threadGroup.activeCount());
		System.out.printf("Information about the Thread Group\n");
		threadGroup.list();
		Thread[] threads = new Thread[threadGroup.activeCount()];
		threadGroup.enumerate(threads);
		for (int i = 0; i < threadGroup.activeCount(); i++) {
			System.out.printf("Thread %s: %s\n", threads[i].getName(),
					threads[i].getState());
		}
		waitFinish(threadGroup);

		threadGroup.interrupt();
	}

	private static void waitFinish(ThreadGroup threadGroup) {
		while (threadGroup.activeCount() > 9) {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
