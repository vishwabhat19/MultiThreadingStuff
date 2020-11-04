package com.threads;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPoolExample {
	
	
	public static void main(String args[]) {
		
		ExecutorService service = null;
		
		service = Executors.newCachedThreadPool();
		
		
		Runnable task1 = () -> System.out.println("Task 1. Current system time is: "+LocalDateTime.now()
	       .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
		Runnable task2 = () -> System.out.println("Task 2. Current system time is "+LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
		
		
		System.out.println("CPUs: "+Runtime.getRuntime().availableProcessors());
		service.submit(task1);
		service.submit(task2);
	}

}
