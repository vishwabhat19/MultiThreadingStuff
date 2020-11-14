package com.threads;

import java.util.function.IntConsumer;

public class PrintZeroEvenOdd {
	

    private int n;
    
    public PrintZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        
    	printNumber.accept(n);
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
    	printNumber.accept(n);
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
    	printNumber.accept(n);
    }
    
    static boolean zeroSignal = true;
    static boolean evenSignal,oddSignal;
    
    static Object lock = new Object();
    static int counter = 0;

	public static void main(String[] args) {
		
		PrintZeroEvenOdd p = new PrintZeroEvenOdd(4);
		
		IntConsumer zeroConsumer = (n) -> {
			
			
			synchronized (lock) {
				
					while(counter<n-1) {
					while(!zeroSignal) {
						
						//System.out.println("Inside both while");
						try {
							lock.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					zeroSignal = false;
					lock.notifyAll();
					System.out.println(Thread.currentThread().getName()+" "+0);
					
					
					//Check to turn odd signal or even signal to true
					if(counter==0) {
						oddSignal = true;
						evenSignal = false;
					}
					else if( counter%2==0) {
						//System.out.println("Even signal turned on");
						oddSignal = true;
						evenSignal = false;
					}
					else {
						//System.out.println("Odd signal turned on");
						oddSignal = false;
						evenSignal = true;
					}
					
					
					}
				
			}
		};
		IntConsumer evenConsumer = (n) -> {
					

			synchronized (lock) {
				while(counter<n-1) {
					while(!evenSignal) {
						try {
							lock.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					counter++;
					System.out.println(Thread.currentThread().getName()+" "+counter);
					
					oddSignal = false;
					evenSignal = false;
					zeroSignal = true;
					lock.notifyAll();
					
				}
			}
		
			
				};
		IntConsumer oddConsumer = (n) -> {
			

			

			synchronized (lock) {
				while(counter<n-1) {
					while(!oddSignal) {
						try {
							lock.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					counter++;
					System.out.println(Thread.currentThread().getName()+" "+counter);
					
					zeroSignal = true;
					oddSignal = false;
					evenSignal = false;
					lock.notifyAll();
					
				}
			}
		
			
				
					
				};
		Thread A = new Thread(()->  {
			
		try {
			p.zero(zeroConsumer);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}

	}, "Thread A");
		
		Thread B = new Thread(()->  {
			
			try {
				p.zero(evenConsumer);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}

		}, "Thread B");
		
		Thread C = new Thread(()->  {
			
			try {
				p.zero(oddConsumer);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}

		}, "Thread C");
		
		A.start();
		B.start();
		C.start();
		
		
	}
		
		
		

}
