package com.threads;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

class Producer implements Runnable{
	
	private final BlockingQueue<Integer> qu;
	
	Producer(BlockingQueue<Integer> q){
		this.qu = q;
	}
	
	public void run() {
		for(int i=0;i<10;i++) {
			try {
				System.out.println("Produced "+i);
				qu.put(i);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
}

class Consumer implements Runnable{
	private final BlockingQueue<Integer> queue;
	
	Consumer(BlockingQueue<Integer> q){
		this.queue = q;
	}
	
	public void run() {
		while(true) {
			try {
				System.out.println("Consumed "+queue.take());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

public class ProducerConsumerProblem {
	
	

	public static void main(String[] args) {
		
		BlockingQueue<Integer> q = new LinkedBlockingQueue<>();
		Thread producer = new Thread(new Producer(q));
		Thread consumer = new Thread(new Consumer(q));
		producer.start();
		consumer.start();

	}

}
