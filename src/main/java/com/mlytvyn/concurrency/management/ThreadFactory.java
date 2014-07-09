package com.mlytvyn.concurrency.management;

import com.mlytvyn.threads.MyThreadFactory;
import com.mlytvyn.threads.ThreadFactoryTask;

/**
 * The factory pattern is one of the most used design patterns in the
 * object-oriented programming world. It is a creational pattern and its
 * objective is to develop an object whose mission will be creating other
 * objects of one or several classes. Then, when we want to create an object of
 * one of those classes, we use the factory instead of using the new operator.<br>
 * With this factory, we centralize the creation of objects with some
 * advantages:
 * <ol>
 * <li>It's easy to change the class of the objects created or the way we create
 * these objects.</li>
 * <li>It's easy to limit the creation of objects for limited resources. For
 * example, we can only have n objects of a type.</li>
 * <li>It's easy to generate statistical data about the creation of the objects.
 * </li>
 * </ol>
 * Java provides an interface, the ThreadFactory interface to implement a Thread
 * object factory. Some advanced utilities of the Java concurrency API use
 * thread factories to create threads.<br>
 * In this recipe, we will learn how to implement a ThreadFactory interface to
 * create Thread objects with a personalized name while we save statistics of
 * the Thread objects created.
 */
public class ThreadFactory {

	public static void main(String... args) {
		MyThreadFactory factory = new MyThreadFactory("MyThreadFactory");
		ThreadFactoryTask task = new ThreadFactoryTask();
		Thread thread;
		System.out.printf("Starting the Threads\n");
		for (int i = 0; i < 10; i++) {
			thread = factory.newThread(task);
			thread.start();
		}
		System.out.printf("Factory stats:\n");
		System.out.printf("%s\n", factory.getStats());
	}
}
