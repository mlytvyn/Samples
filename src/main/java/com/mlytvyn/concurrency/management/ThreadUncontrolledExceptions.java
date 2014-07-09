package com.mlytvyn.concurrency.management;

import com.mlytvyn.handlers.ExceptionHandler;
import com.mlytvyn.threads.UncontrolledExceptionTask;

/**
 * There are two kinds of exceptions in Java:
 * <ol>
 * <li><b>Checked exceptions</b>: These exceptions must be specified in the
 * throws clause of a method or caught inside them. For example, IOException or
 * ClassNotFoundException.</li>
 * <li><b>Unchecked exceptions</b>: These exceptions don't have to be specified
 * or caught. For example, NumberFormatException.</li>
 * </ol>
 * When a checked exception is thrown inside the run() method of a Thread
 * object, we have to catch and treat them, because the run() method doesn't
 * accept a throws clause. When an unchecked exception is thrown inside the
 * run() method of a Thread object, the default behaviour is to write the stack
 * trace in the console and exit the program.<br>
 * Fortunately, Java provides us with a mechanism to catch and treat the
 * unchecked exceptions thrown in a Thread object to avoid the program ending.<br>
 * In this recipe, we will learn this mechanism using an example.
 */
public class ThreadUncontrolledExceptions {

	public static void main(String... args) {
		UncontrolledExceptionTask task = new UncontrolledExceptionTask();
		Thread thread = new Thread(task);
		thread.setUncaughtExceptionHandler(new ExceptionHandler());
		thread.start();
	}
}
