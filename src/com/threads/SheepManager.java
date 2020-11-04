package com.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class SheepManager {
	
	private AtomicInteger sheepCount = new AtomicInteger(0);

	public static void main(String[] args) {
		
		ExecutorService service = null;
		
		try {
			SheepManager manager = new SheepManager();
			service = Executors.newFixedThreadPool(2);
			
				for(int i=0;i<10;i++) {
					service.submit(() -> manager.incrementAndReport());
				}
			
			
			
		}
		finally {
			if(null!=service) {
				service.shutdown();
			}
		}
		
	}
	
	private void incrementAndReport() {
		synchronized (new Object()) {
			System.out.println(sheepCount.incrementAndGet()+" ");
		}
		
	}


}
