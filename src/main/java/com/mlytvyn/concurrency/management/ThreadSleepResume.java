package com.mlytvyn.concurrency.management;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.mlytvyn.threads.FileClock;

/**
 * Sometimes, you'll be interested in interrupting the execution of Thread
 * during a determined period of time. For example, a thread in a program checks
 * a sensor state once per minute. The rest of the time, the thread does
 * nothing. During this time, the thread doesn't use any resources of the
 * computer. After this time, the thread will be ready to continue with its
 * execution when the JVM chooses it to be executed. You can use the sleep()
 * method of the Thread class for this purpose. This method receives an integer
 * as the parameter indicates the number of milliseconds that the thread
 * suspends its execution. When the sleeping time ends, the thread continues
 * with its execution in the instruction, after the sleep() method calls, when
 * the JVM assigns them CPU time. Another possibility is to use the sleep()
 * method of an element of the TimeUnit enumeration. This method uses the
 * sleep() method of the Thread class to put the current thread to sleep, but it
 * receives the parameter in the unit that it represents and converts it to
 * milliseconds In this recipe, we will develop a program that uses the sleep()
 * method to write the actual date every second.
 */
public class ThreadSleepResume {

	public static void main(String... args) throws IOException {
		FileClock clock = new FileClock();
		Thread thread = new Thread(clock);
		thread.start();
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		thread.interrupt();
	}
}
