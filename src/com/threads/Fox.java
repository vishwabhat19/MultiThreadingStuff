package com.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Food{
	
}

class Water{
	
	
}
public class Fox {
	
	public void eatAndDrink(Food food,Water water) {
		
		synchronized (food) {
			System.out.println("Got food");
			move();
			synchronized (water) {
				System.out.println("Got water");
			}
		}
		
	}
	
	public void drinkAndEat(Food food,Water water) {
		synchronized(water) {
			System.out.println("Got water");
			move();
			synchronized(food) {
				System.out.println("Got food");
			}
		}
	}
	
	public void move() {
		try {
			Thread.sleep(10000);
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]) {
		
		Fox eater = new Fox();
		Fox drinker = new Fox();
		Food food = new Food();
		Water water = new Water();
		
		ExecutorService service = null;
		try {
			service = Executors.newScheduledThreadPool(10);
			service.submit(() -> eater.eatAndDrink(food, water));
			service.submit(() -> drinker.drinkAndEat(food, water));
			
		}
		finally {
			if(null!=service) service.shutdown();
		}
	}
	

}
