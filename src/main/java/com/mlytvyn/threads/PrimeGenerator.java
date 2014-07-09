package com.mlytvyn.threads;

public class PrimeGenerator extends Thread {

	@Override
	public void run() {
		long number = 1L;
		while (true) {
			if (isInterrupted()) {
				System.out.printf("The Prime Generator has been Interrupted");
				return;
			}
			if (isPrime(number)) {
				System.out.printf("Number %d is Prime\n", number);

			}
			number++;
		}
	}

	private boolean isPrime(long number) {
		if (number <= 2) {
			return true;
		}
		for (long i = 2; i < number; i++) {
			if ((number % i) == 0) {
				return false;
			}
		}
		return true;
	}
}
