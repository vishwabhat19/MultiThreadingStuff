package com.threads;

class FooBar {
    private int n;
    
    boolean canPrintFoo = true;
    boolean canPrintBar = false;
    
    Object lock = new Object();

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        
        for (int i = 0; i < n; i++) {
        	
        	synchronized (lock) {
        		while(!canPrintFoo) {
        			lock.wait();
        		}
        		printFoo.run();
        		canPrintFoo = false;
        		canPrintBar = true;
        	
        		
        		lock.notify();
        		
			}
        	
            
        	// printFoo.run() outputs "foo". Do not change or remove this line.
        	
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        
        for (int i = 0; i < n; i++) {
            
            // printBar.run() outputs "bar". Do not change or remove this line.
        	synchronized (lock) {
        		while(!canPrintBar) {
        			lock.wait();
        		}
        		
        		
        		printBar.run();
        		canPrintBar = false;
        		canPrintFoo = true;
        		
        		lock.notify();
        		
			}
        	
        }
    }
    
    public static void main(String args[]) throws InterruptedException {
    	
    	FooBar f = new FooBar(2);
    	
    	Runnable printFoo = () -> {
    		System.out.println("foo");
    	};
    	
    	Runnable printBar = () -> {
    		System.out.println("bar");
    	};
    	
    		Thread A = new Thread(() -> {
				try {
					f.foo(printFoo);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
    		Thread B = new Thread(() -> {
				try {
					f.bar(printBar);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
    		
    		A.start();
    		//Thread.sleep(2);
    		B.start();
    	
    	
    	
    	
    }
}