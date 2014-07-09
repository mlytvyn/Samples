package com.mlytvyn.concurrency.management;

import java.io.IOException;
import java.util.Date;

import com.mlytvyn.threads.DataSourcesLoader;
import com.mlytvyn.threads.NetworkConnectionsLoader;

/**
 * In some situations, we will have to wait for the finalization of a thread.
 * For example, we may have a program that will begin initializing the resources
 * it needs before proceeding with the rest of the execution. We can run the
 * initialization tasks as threads and wait for its finalization before
 * continuing with the rest of the program. For this purpose, we can use the
 * join() method of the Thread class. When we call this method using a thread
 * object, it suspends the execution of the calling thread until the object
 * called finishes its execution. In this recipe, we will learn the use of this
 * method with the initialization example.
 */
public class ThreadWaitForFinalization {

	public static void main(String... args) throws IOException {
		DataSourcesLoader dsLoader = new DataSourcesLoader();
		Thread thread1 = new Thread(dsLoader, "DataSourceThread");
		NetworkConnectionsLoader ncLoader = new NetworkConnectionsLoader();
		Thread thread2 = new Thread(ncLoader, "NetworkConnectionLoader");
		thread1.start();
		thread2.start();
		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("Main: Configuration has been loaded: %s\n",
				new Date());
	}
}
