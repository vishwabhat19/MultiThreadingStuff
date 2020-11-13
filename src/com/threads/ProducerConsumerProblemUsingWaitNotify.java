package com.threads;

import java.util.ArrayDeque;
import java.util.Queue;

class ProducerClass extends Thread {
	
	private Queue<Integer> sharedQueue;
	
	ProducerClass(Queue<Integer> sharedQueue){
		this.sharedQueue = sharedQueue;
	}
	
	public void run() {
		synchronized (sharedQueue) {
			for(int i=1;i<=10;i++) {
				try {
					Thread.sleep(1000);
				
				
				boolean offerSuccessfull = sharedQueue.offer(i);
					if(offerSuccessfull) {
						
						System.out.println("Added "+i+". Notify. ");
						sharedQueue.notifyAll();
						Thread.sleep(1000);
					
					}
					
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
		
	}}
}

class ConsumerClass extends Thread {
	
	private Queue<Integer> sharedQueue;
	
	ConsumerClass(Queue<Integer> sharedQueue){
		this.sharedQueue = sharedQueue;
	}
	
	public void run() {
		synchronized (sharedQueue) {
		for(int i=0;i<10;i++) {
			while(sharedQueue.isEmpty()) {
				try {
					System.out.println("Waiting since Queue is empty");
					sharedQueue.wait();
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			}
			System.out.println("Consumed: "+sharedQueue.poll());
		}}
	}
}

public class ProducerConsumerProblemUsingWaitNotify {
	
	

	public static void main(String[] args) {
		
		Queue<Integer> sharedQueue = new ArrayDeque<Integer>();
		
		ProducerClass prodThread = new ProducerClass(sharedQueue);
		ConsumerClass consumerThread = new ConsumerClass(sharedQueue);
		
		consumerThread.start();
		
		prodThread.start();
		
		
		

	}

}
