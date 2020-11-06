package com.threads;

public class ThreadNotifyExample {

	static int sum = 0;
	public static void main(String[] args) {
		
		
		
		ThreadNotifyExample threadN = new ThreadNotifyExample();
		threadN.calculate();
		try {
			
			synchronized (threadN) {
				threadN.wait();
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("sum = "+sum);

	}
	
	void calculate() {
		Runnable task = () -> {
				synchronized (this) {
					for(int i=0;i<1000000;i++) {
						sum = sum+i;
					}
					notify();
					
				}
				
			
			
		};
		
		Thread t = new Thread(task);
		t.start();
	}

}


