package com.mall.template.zookeeper;

import java.util.concurrent.CountDownLatch;

/**
 * Hello world!
 *
 */
public class App {
	
	private long size = 0L;
	
	private synchronized long  get(){
		return size;
	}
	private synchronized long add(){
		return ++size;
	}
	
	private synchronized long sub(){
		return --size;
	}
	
	public static void main(String[] args) throws InterruptedException {
		App app = new App();
		
		int flag = 10000;
		CountDownLatch countDownLatch  = new CountDownLatch(flag * 2 );
		for(int i=0;i<=flag;i++){
			new Thread(new Runnable() {
				public void run() {
//					System.out.println("add");
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
					}
					app.add();
					countDownLatch.countDown();
				}
			}).start();
			
			new Thread(new Runnable() {
				public void run() {
//					System.out.println("sub");
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
					}
					app.sub();
					countDownLatch.countDown();
				}
			}).start();
		}
		countDownLatch.await();
		System.out.println("Hello World!" + app.get());
	}
}
