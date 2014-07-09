package com.mlytvyn.concurrency.management;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Thread.State;

import com.mlytvyn.threads.Calculator;

/**
 * The Thread class saves some information attributes that can help us to
 * identify a thread, know its status, or control its priority. <br>
 * These attributes are:
 * <ol>
 * <li>ID: This attribute stores a unique identifier for each Thread.</li>
 * <li>Name: This attribute store the name of Thread.</li>
 * <li>Priority: This attribute stores the priority of the Thread objects.
 * Threads can have a priority between one and 10, where one is the lowest
 * priority and 10 is the highest one. It's not recommended to change the
 * priority of the threads, but it's a possibility that you can use if you want.
 * </li>
 * <li>Status: This attribute stores the status of Thread. In Java, Thread can
 * be in one of these six states: new, runnable, blocked, waiting, time waiting,
 * or terminated.</li>
 * </ol>
 * In this recipe, we will develop a program that establishes the name and
 * priority for 10 threads and then shows information about their status until
 * they finish. The threads will calculate the multiplication table of a number.
 */
public class ThreadInfo {

	private static void writeThreadInfo(PrintWriter pw, Thread thread,
			State state) {
		pw.printf("Main : Id %d - %s\n", thread.getId(), thread.getName());
		pw.printf("Main : Priority: %d\n", thread.getPriority());
		pw.printf("Main : Old State: %s\n", state);
		pw.printf("Main : New State: %s\n", thread.getState());
		pw.printf("Main : ************************************\n");
	}

	public static void main(String... args) throws IOException {
		Thread threads[] = new Thread[10];
		State status[] = new State[10];
		for (int i = 0; i < 10; i++) {
			threads[i] = new Thread(new Calculator(i));
			if ((i % 2) == 0) {
				threads[i].setPriority(Thread.MAX_PRIORITY);
			} else {
				threads[i].setPriority(Thread.MIN_PRIORITY);
			}
			threads[i].setName("Thread " + i);
		}
		try (FileWriter file = new FileWriter(".\\data\\log.txt");
				PrintWriter pw = new PrintWriter(file);) {
			for (int i = 0; i < 10; i++) {
				pw.println("Main : Status of Thread " + i + " : "
						+ threads[i].getState());
				status[i] = threads[i].getState();
			}
			for (int i = 0; i < 10; i++) {
				threads[i].start();
			}
			boolean finish = false;
			while (!finish) {
				for (int i = 0; i < 10; i++) {
					if (threads[i].getState() != status[i]) {
						writeThreadInfo(pw, threads[i], status[i]);
						status[i] = threads[i].getState();
					}
				}
				finish = true;
				for (int i = 0; i < 10; i++) {
					finish = finish
							&& (threads[i].getState() == State.TERMINATED);
				}
			}
		}
	}
}
