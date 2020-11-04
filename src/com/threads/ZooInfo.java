package com.threads;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ZooInfo {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService service = null;
		try {
			service = Executors.newSingleThreadExecutor();
			Future<?> future = service.submit(() -> System.out.println("Hello world"));
			System.out.println("Is task complete? "+future.get().toString());
		}
		finally {
			if(service!=null) {
				service.shutdown();
				System.out.println(service.isShutdown());
				System.out.println(service.isTerminated());
			}
		}

	}

}
