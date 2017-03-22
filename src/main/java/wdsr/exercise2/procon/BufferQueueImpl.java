package wdsr.exercise2.procon;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Task: implement Buffer interface using one of *Queue classes from java.util.concurrent package.
 */
public class BufferQueueImpl implements Buffer {

	private BlockingDeque queue = new LinkedBlockingDeque();
	public void submitOrder(Order order) throws InterruptedException {
		// TODO
		queue.add(order);
	}
	
	public Order consumeNextOrder() throws InterruptedException {
		// TODO
		return (Order) queue.remove();
	}
}
