package com.threads;

public class RunnableImpl implements Runnable{
	
	public void run() {
		System.out.println("Inside "+Thread.currentThread().getName()+" thread");
	}

	public static void main(String[] args) {
		Thread thread = new Thread(new RunnableImpl());
		thread.start();
		System.out.println("Inside "+Thread.currentThread().getName()+" thread");
		
	}

}
