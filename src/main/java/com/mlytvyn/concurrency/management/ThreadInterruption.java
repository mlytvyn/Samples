package com.mlytvyn.concurrency.management;

import com.mlytvyn.threads.PrimeGenerator;

/**
 * A Java program with more than one execution thread only finishes when the
 * execution of all of its threads end (more specifically, when all its
 * non-daemon threads end its execution or when one of the threads use the
 * System.exit() method). Sometimes, you will need to finish a thread, because
 * you want to terminate a program, or when a user of the program wants to
 * cancel the tasks that a Thread object is doing. Java provides the
 * interruption mechanism to indicate to a thread that we want to finish it. One
 * peculiarity of this mechanism is that Thread has to check if it has been
 * interrupted or not, and it can decide if it responds to the finalization
 * request or not. Thread can ignore it and continue with its execution. In this
 * recipe, we will develop a program that creates Thread and, after 5 seconds,
 * will force its finalization using the interruption mechanism.
 */
public class ThreadInterruption {

	public static void main(String... args) {
		Thread task = new PrimeGenerator();
		task.start();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		task.interrupt();
	}
}
