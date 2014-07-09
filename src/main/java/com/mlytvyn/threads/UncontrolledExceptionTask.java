package com.mlytvyn.threads;

public class UncontrolledExceptionTask implements Runnable {

	@Override
	public void run() {
		Integer.parseInt("TTT");
	}

}
