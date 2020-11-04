package com.threads;

public class Bitwise {
	
	static int precomputedParity[];
	
	static void populateParityCache() {
		System.out.println(countBits(2));
	}
	
	static int parity(int n) {
		int parityFlag = 0;
		while(n!=0) {
			parityFlag = parityFlag ^ 1;
			n = n & (n-1); //Keep dropping the lower set bits untill n is 0
		}
		return parityFlag;
	}

	public static void main(String[] args) {
		populateParityCache();
		
		
	}
	
	static int countBits(int n) {
		int count = 0;
		while(n!=0) {
			count++;
			n = n >>>1;
		}
		return count;
	}

}
