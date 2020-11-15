package com.threads;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/*
 * CyclicBarrier Example
 */
public class BarDemo {

	
	
	public static void main(String args[]) {
		CyclicBarrier cb = new CyclicBarrier(3, () -> System.out.println("Barrier Reached!"));
		System.out.println("Starting");
		Thread A = new Thread(new MyThread(cb, "A"));
		Thread B = new Thread(new MyThread(cb, "B"));
		Thread C = new Thread(new MyThread(cb, "C"));
		A.start();
		B.start();
		C.start();
		
	}
}

class MyThread implements Runnable{
	CyclicBarrier cbar;
	String name;
	
	MyThread(CyclicBarrier c,String n){
		this.name = n;
		this.cbar = c;
	}
	
	@Override
	public void run() {
		System.out.println(name);
		
		try {
			cbar.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
