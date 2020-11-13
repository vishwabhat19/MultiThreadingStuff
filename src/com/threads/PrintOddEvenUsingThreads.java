package com.threads;

public class PrintOddEvenUsingThreads {
	
	int counter = 0;
	int N = 10;
	
	void printEven() {
		synchronized (this) {
			
			while(counter<N) {
				while(counter%2!=0) {
					try {
						this.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				System.out.println(Thread.currentThread().getName()+" is printing "+counter);
				counter++;
				notifyAll();
			}
		}
	}
	
	void printOdd() {
		synchronized (this) {
			while(counter<N) {
				while(counter%2!=1) {
					try {
						this.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println(Thread.currentThread().getName()+" is printing "+counter);
				counter++;
				notifyAll();
			}
		}
	}

	public static void main(String[] args) {
		
		PrintOddEvenUsingThreads t = new PrintOddEvenUsingThreads();
		Thread evenThread = new Thread(() ->  {
			t.printEven();
		}, "Even Thread");
	
	
		Thread oddThread = new Thread(() ->  {
			t.printOdd();
		}, "Odd Thread");
		
		evenThread.start();
		oddThread.start();
	}
}
