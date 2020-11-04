package com.threads;

import java.util.ArrayList;
import java.util.List;

public class WhaleDataCalculator {
	
	public int processRecord(int input) {
		try {
			System.out.println("inside Process record");
			Thread.sleep(10);
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
		return input+1;
	}
	
	public void processAllData(List<Integer> data) {
		data.stream().map(a -> processRecord(a));
	}

	public static void main(String[] args) {
		
		WhaleDataCalculator calculator = new WhaleDataCalculator();
		
		List<Integer> data = new ArrayList<>();
		for(int i=0;i<4000;i++) {
			data.add(i);
		}
		
		long start = System.currentTimeMillis();
		
		calculator.processAllData(data);
		
		double time = (System.currentTimeMillis() - start)/1000;
		
		System.out.println("Time taken to complete the task: "+time);
		

	}

}
