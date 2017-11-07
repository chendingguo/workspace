package multithread.resturant;

import java.util.ArrayList;
import java.util.List;

/**
 * 两种食物，两个厨师，两个服务员，多个顾客，每个顾客点一种食物，每个服务员服务点专门的一种食物的顾客
 * 
 * @author 蓝桥
 *
 */
public class ThreadTest {
	public static void main(String[] args) {
		Food food1 = new Food(0, "包子");
		Food food2 = new Food(0, "馒头");
		final Cook cook1 = new Cook("王师傅", food1);
		final Cook cook2 = new Cook("李师傅", food2);
		List<Customer> customers1 = new ArrayList<Customer>();
		List<Customer> customers2 = new ArrayList<Customer>();
		for (int i = 1; i <= 5; i++) {
			Customer customer = new Customer(i, food1);
			customers1.add(customer);
		}
		for (int i = 6; i <= 10; i++) {
			Customer customer = new Customer(i, food2);
			customers2.add(customer);
		}
		final Waiter waiter1 = new Waiter(customers1, cook1, food1);
		final Waiter waiter2 = new Waiter(customers2, cook2, food2);
		Thread thread1 = new Thread() {
			public void run() {
				cook1.produce();
			}
		};
		
		Thread thread3 = new Thread() {
			public void run() {
				waiter1.server();
			}
		};
		Thread thread2 = new Thread() {
			public void run() {
				cook2.produce();
			}
		};
		
		Thread thread4 = new Thread() {
			public void run() {
				waiter2.server();
			}
		};
		thread3.start();
		thread1.start();
		thread2.start();
		thread4.start();
	}
}