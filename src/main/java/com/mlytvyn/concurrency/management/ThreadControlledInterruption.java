package com.mlytvyn.concurrency.management;

import java.util.concurrent.TimeUnit;

import com.mlytvyn.threads.FileSearch;

/**
 * In the previous recipe, you learned how you can interrupt the execution of a
 * thread and what you have to do to control this interruption in the Thread
 * object. The mechanism shown in the previous example can be used if the thread
 * that can be interrupted is simple. But if the thread implements a complex
 * algorithm divided into some methods, or it has methods with recursive calls,
 * we can use a better mechanism to control the interruption of the thread. Java
 * provides the InterruptedException exception for this purpose. You can throw
 * this exception when you detect the interruption of the thread and catch it in
 * the run() method. In this recipe, we will implement Thread that looks for
 * files with a determined name in a folder and in all its subfolders to show
 * how to use the InterruptedException exception to control the interruption of
 * a thread.
 */
public class ThreadControlledInterruption {

	public static void main(String... args) {
		FileSearch searcher = new FileSearch("/", "log.txt");
		Thread thread = new Thread(searcher);
		thread.start();
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		thread.interrupt();
	}

}
