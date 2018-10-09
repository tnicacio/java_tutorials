package com.ebees.threads.demo;

import java.util.Scanner;

class NotifyProcessor {
	public void produce() throws InterruptedException {
		synchronized (this) {
			System.out.println("Notify processor running...");
			wait();
			System.out.println("Resumed.....");
		}
	}
	
	public void consume() throws InterruptedException {
		
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		Thread.sleep(2000);
		
		synchronized (this) {
			System.out.println("Waiting for return key..");
			scanner.nextLine();
			System.out.println("Return key pressed.");
			notify();
			Thread.sleep(5000);
		}
	}
}

public class Lesson8NotifyWait {

	public static void main(String[] args) throws InterruptedException {
		
		final NotifyProcessor processor = new NotifyProcessor();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					processor.produce();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					processor.consume();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		t1.start();
		t2.start();
		
		t1.join();
		t1.join();
	}

}
