package com.threads;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinExample {
	
	static long N = 1000000;
	static int num_threads = 10;

	public static void main(String[] args) {
		
		
		
		ForkJoinPool forkJoinPool = new ForkJoinPool(num_threads);
		long computedSum = forkJoinPool.invoke(new RecursiveSumN(0, N,N,num_threads));
		
		System.out.println("Computed sum: "+computedSum);
		long actualSum = N*(N+1)/2;
		System.out.println("Actual sum: "+actualSum);
		
	}

}

class RecursiveSumN extends RecursiveTask<Long>{
	
	long from,to,N,num_threads;
	
	public RecursiveSumN(long from,long to,long N,long num_threads) {
		
		this.from = from;
		this.to = to;
		this.N = N;
		this.num_threads = num_threads;
	}

	@Override
	protected Long compute() {
		
		if((to-from)<=(N/num_threads)) {
			long localSum = 0;
			for(long i=from;i<=to;i++) {
				localSum+=i;
			}
			System.out.println("Sum of value range "+from+" to"+to+" is "+localSum);
			return localSum;
		}
		else {
			long mid = (to+from)/2;
			System.out.println("Forking into two ranges: "+from+" to "+mid+" and "+mid+" to "+to);
			RecursiveSumN firstHalf = new RecursiveSumN(from, mid, N, num_threads);
			firstHalf.fork();
			RecursiveSumN secondHalf = new RecursiveSumN(mid+1, to, N, num_threads);
			long resultSecond = secondHalf.compute();
			return firstHalf.join() + resultSecond;
		}
	}
	
}
