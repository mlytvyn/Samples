package com.mlytvyn.concurrency.synchronization;

import com.mlytvyn.pojo.Account;
import com.mlytvyn.threads.Bank;
import com.mlytvyn.threads.Company;

/**
 * In this recipe, we will learn how to use one of the most basic methods for
 * synchronization in Java, that is, the use of the synchronized keyword to
 * control the concurrent access to a method. Only one execution thread will
 * access one of the methods of an object declared with the synchronized
 * keyword. If another thread tries to access any method declared with the
 * synchronized keyword of the same object, it will be suspended until the first
 * thread finishes the execution of the method.<br>
 * In other words, every method declared with the synchronized keyword is a
 * critical section and Java only allows the execution of one of the critical
 * sections of an object.<br>
 * Static methods have a different behavior. Only one execution thread will
 * access one of the static methods declared with the synchronized keyword, but
 * another thread can access other non- static methods of an object of that
 * class. You have to be very careful with this point, because two threads can
 * access two different synchronized methods if one is static and the other one
 * is not. If both methods change the same data, you can have data inconsistency
 * errors.<br>
 * To learn this concept, we will implement an example with two threads
 * accessing a common object. We will have a bank account and two threads; one
 * that transfers money to the account and another one that withdraws money from
 * the account. Without synchronization methods, we could have incorrect
 * results. Synchronization mechanisms ensures that the final balance of the
 * account will be correct.
 */
public class SyncMethod {

	public static void main(String... args) {
		Account account = new Account();
		account.setBalance(1000);
		Company company = new Company(account);
		Thread companyThread = new Thread(company);
		Bank bank = new Bank(account);
		Thread bankThread = new Thread(bank);
		System.out.printf("Account : Initial Balance: %f\n",
				account.getBalance());
		companyThread.start();
		bankThread.start();
		try {
			companyThread.join();
			bankThread.join();
			System.out.printf("Account : Final Balance: %f\n",
					account.getBalance());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
