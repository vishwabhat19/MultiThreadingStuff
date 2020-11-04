package com.threads;

import java.util.Arrays;
import java.util.List;

public class CheckingConcurrency {

	public static void main(String[] args) {
		Arrays.asList(1,2,3,4,5,6,7).parallelStream().forEach(
				(e) -> System.out.println(e));
		
		Arrays.asList(1,2,3,4,5,6,7).parallelStream().forEachOrdered(
				(e) ->{
					System.out.println(e);
				}
				);
		
	}

}
