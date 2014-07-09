package com.mlytvyn.concurrency.synchronization;

import com.mlytvyn.pojo.Cinema;
import com.mlytvyn.threads.TicketOffice1;
import com.mlytvyn.threads.TicketOffice2;

/**
 * When you use the synchronized keyword to protect a block of code, you must
 * pass an object reference as a parameter. Normally, you will use the this
 * keyword to reference the object that executes the method, but you can use
 * other object references. Normally, these objects will be created exclusively
 * with this purpose. For example, if you have two independent attributes in a
 * class shared by multiple threads, you must synchronize the access to each
 * variable, but there is no problem if there is one thread accessing one of the
 * attributes and another thread accessing the other at the same time.<br>
 * In this recipe, you will learn how to resolve this situation's programming
 * with an example that simulates a cinema with two screens and two ticket
 * offices. When a ticket office sells tickets, they are for one of the two
 * cinemas, but not for both, so the numbers of free seats in each cinema are
 * independent attributes.
 */
public class IndependentAttributesInSyncClasses {

	public static void main(String... args) {
		Cinema cinema = new Cinema();
		TicketOffice1 ticketOffice1 = new TicketOffice1(cinema);
		Thread thread1 = new Thread(ticketOffice1, "TicketOffice1");
		TicketOffice2 ticketOffice2 = new TicketOffice2(cinema);
		Thread thread2 = new Thread(ticketOffice2, "TicketOffice2");
		thread1.start();
		thread2.start();
		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("Room 1 Vacancies: %d\n",
				cinema.getVacanciesCinema1());
		System.out.printf("Room 2 Vacancies: %d\n",
				cinema.getVacanciesCinema2());
	}
}
