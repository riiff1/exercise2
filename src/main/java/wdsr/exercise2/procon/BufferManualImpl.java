package wdsr.exercise2.procon;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Task: implement Buffer interface without using any *Queue classes from java.util.concurrent package.
 * Any combination of "synchronized", *Lock, *Semaphore, *Condition, *Barrier, *Latch is allowed.
 */
public class BufferManualImpl implements Buffer {

	private Queue<Order> orders = new LinkedList<>();

	public synchronized void submitOrder(Order order) throws InterruptedException {
		// TODO
		orders.add(order);
	}
	
	public synchronized Order consumeNextOrder() throws InterruptedException {
		// TODO
		return orders.poll();
	}
}
