package com.threads;


class Counter {
	
	public static long counter = 0;

}


class UseCounter implements Runnable {
	
	public void increment() {
		Counter.counter++;
		System.out.println(Counter.counter+ " ");
	}

	@Override
	public void run() {
		
		increment();
		increment();
		increment();
	}

}


public class RaceCondition {

	public static void main(String[] args) {
			
		UseCounter c = new UseCounter();
		Thread t1 = new Thread(c);
		Thread t2 = new Thread(c);
		Thread t3 = new Thread(c);
		t1.start();
		t2.start();
		t3.start();
		

	}

}
