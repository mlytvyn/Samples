package com.mlytvyn.concurrency.management;

import java.util.ArrayDeque;
import java.util.Deque;

import com.mlytvyn.pojo.Event;
import com.mlytvyn.threads.CleanerTask;
import com.mlytvyn.threads.WriterTask;

/**
 * Java has a special kind of thread called <b>daemon</b> thread. These kind of
 * threads have very low priority and normally only executes when no other
 * thread of the same program is running. When daemon threads are the only
 * threads running in a program, the JVM ends the program finishing these
 * threads.<br>
 * With these characteristics, the daemon threads are normally used as service
 * providers for normal (also called user) threads running in the same program.
 * They usually have an infinite loop that waits for the service request or
 * performs the tasks of the thread. They can't do important jobs because we
 * don't know when they are going to have CPU time and they can finish any time
 * if there aren't any other threads running. A typical example of these kind of
 * threads is the Java garbage collector.<br>
 * In this recipe, we will learn how to create a daemon thread developing an
 * example with two threads; one user thread that writes events on a queue and a
 * daemon one that cleans that queue, removing the events which were generated
 * more than 10 seconds ago.
 */
public class ThreadDaemon {

	public static void main(String... args) {
		Deque<Event> deque = new ArrayDeque<Event>();
		WriterTask writer = new WriterTask(deque);
		for (int i = 0; i < 3; i++) {
			Thread thread = new Thread(writer);
			thread.start();
		}
		CleanerTask cleaner = new CleanerTask(deque);
		cleaner.start();
	}
}
