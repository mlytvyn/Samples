package com.mlytvyn.concurrency.synchronization;

import com.mlytvyn.pojo.EventStorage;
import com.mlytvyn.threads.Consumer;
import com.mlytvyn.threads.Producer;

/**
 * A classic problem in concurrent programming is the <b>producer-consumer</b>
 * problem. We have a data buffer, one or more producers of data that save it in
 * the buffer and one or more consumers of data that take it from the buffer.<br>
 * As the buffer is a shared data structure, we have to control the access to it
 * using a synchronization mechanism such as the synchronized keyword, but we
 * have more limitations. A producer can't save data in the buffer if it's full
 * and the consumer can't take data from the buffer if it's empty.<br>
 * For these types of situations, Java provides the wait(), notify(), and
 * notifyAll() methods implemented in the Object class. A thread can call the
 * wait() method inside a synchronized block of code. If it calls the wait()
 * method outside a synchronized block of code, the JVM throws an
 * IllegalMonitorStateException exception. When the thread calls the wait()
 * method, the JVM puts the thread to sleep and releases the object that
 * controls the synchronized block of code that it's executing and allows the
 * other threads to execute other blocks of synchronized code protected by that
 * object. To wake up the thread, you must call the notify() or notifyAll()
 * method inside a block of code protected by the same object.<br>
 * In this recipe, you will learn how to implement the producer-consumer problem
 * using the synchronized keyword and the wait(), notify(), and notifyAll()
 * methods.
 */
public class ConditionsInSyncCode {

	public static void main(String... args) {
		EventStorage storage = new EventStorage();
		Producer producer = new Producer(storage);
		Thread thread1 = new Thread(producer);
		Consumer consumer = new Consumer(storage);
		Thread thread2 = new Thread(consumer);
		thread2.start();
		thread1.start();
	}
}
