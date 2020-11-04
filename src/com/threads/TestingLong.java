package com.threads;

public class TestingLong {

	public static void main(String[] args) {
		
		//Program to reverse a binary number		

		int x= 9;
		int size= 0 ;
		int temp = x;
		
		//First calculate the size of the binary number
		while(x!=0) {
			size++;
			x = x & (x-1);
		}
		
		System.out.println(size);
		
	}
	
	static int swapBits(int x,int i,int j) {
		int a = 1 << i;
		int b = 1 << j;
		int mask = a | b;
		x = x ^ mask;
		return x;
	}

}
