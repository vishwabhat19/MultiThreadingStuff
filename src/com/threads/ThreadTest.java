package com.threads;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadTest {
	
	static void performTask(CyclicBarrier c1,CyclicBarrier c2) {
		
		try {
			task1();
			c1.await();
			task2();
			c2.await();
			task3();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	static void task1() {
		System.out.println("Task 1");
	}
	
	static void task2() {
		System.out.println("Task 2");
	}
	
	static void task3() {
		System.out.println("Task 3");
	}
	

	public static void main(String[] args) {
		
		ExecutorService service = null;
		try {
			CyclicBarrier c1 = new CyclicBarrier(2);
			CyclicBarrier c2 = new CyclicBarrier(2, () -> System.out.println("Cyclic Barrier broken"));
			service = Executors.newFixedThreadPool(4);
			for(int i=0;i<4;i++) {
				service.execute(() -> performTask(c1, c2));
			}
			
		}
		finally {
			service.shutdown();
		}
	}

}
