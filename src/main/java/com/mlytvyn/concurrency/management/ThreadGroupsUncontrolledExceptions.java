package com.mlytvyn.concurrency.management;

import com.mlytvyn.threads.MyThreadGroup;
import com.mlytvyn.threads.ThreadGroupTask;

/**
 * A very important aspect in every programming language is the mechanism that
 * provides management of error situations in your application. Java language,
 * as almost all modern programming languages, implements an exception-based
 * mechanism to manage error situations. It provides a lot of classes to
 * represent different errors. Those exceptions are thrown by the Java classes
 * when an error situation is detected. You can also use those exceptions, or
 * implement your own exceptions, to manage the errors produced in your classes.<br>
 * Java also provides a mechanism to capture and process those exceptions. There
 * are exceptions that must be captured or re-thrown using the throws clause of
 * a method. These exceptions are called checked exceptions. There are
 * exceptions that don't have to be specified or caught. These are the unchecked
 * exceptions.<br>
 * In the recipe, <i>Controlling the interruption of a Thread</i>, you learned
 * how to use a generic method to process all the uncaught exceptions that are
 * thrown in a Thread object.<br>
 * Another possibility is to establish a method that captures all the uncaught
 * exceptions thrown by any Thread of the ThreadGroup class.<br>
 * In this recipe, we will learn to set this handler using an example.
 */
public class ThreadGroupsUncontrolledExceptions {

	public static void main(String... args) {
		MyThreadGroup threadGroup = new MyThreadGroup("MyThreadGroup");
		ThreadGroupTask task = new ThreadGroupTask();
		for (int i = 0; i < 2; i++) {
			Thread t = new Thread(threadGroup, task);
			t.start();
		}
	}
}
