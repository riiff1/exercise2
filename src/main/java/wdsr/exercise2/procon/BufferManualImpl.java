package wdsr.exercise2.procon;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Task: implement Buffer interface without using any *Queue classes from java.util.concurrent package.
 * Any combination of "synchronized", *Lock, *Semaphore, *Condition, *Barrier, *Latch is allowed.
 */
public class BufferManualImpl implements Buffer {

	// czy musialem dac taka pojemnosc jak ilosc zamowien na jeden watek? bo uzywajac czegos takiego placeIndex = (placeIndex + 1) % orders.length;
	// mam gwarancje kolejki modelu pierscien. to rownie dobrze moge dac mniejsza tablice ale wtedy jesli beda same zamowienia bez consume to chyba wtedy duzo submitow bedzie czekalo w kolejce
	// wiec w jaki sposob najlepiej dobrac ta tablice zamowien??
	private Order[] orders = new Order[100000];
	private final Lock lock = new ReentrantLock();
	private final Condition isFull = lock.newCondition();
	private final Condition isEmpty = lock.newCondition();
	private int current = 0;
	private int placeIndex = 0;
	private int removeIndex = 0;

	public void submitOrder(Order order) throws InterruptedException {
		// TODO
		lock.lock();
		try {
			while(current >= orders.length) {
				isFull.await();
			}
			orders[placeIndex] = order;
			placeIndex = (placeIndex + 1) % orders.length;
			++current;
			isEmpty.signal();
		} finally {
			lock.unlock();
		}
	}
	
	public Order consumeNextOrder() throws InterruptedException {
		// TODO
		lock.lock();
		try {
			Order order;
			while(current <= 0) {
				isEmpty.await();
			}
			order = orders[removeIndex];
			removeIndex = (removeIndex + 1) % orders.length;
			--current;
			isFull.signal();
			return order;
		} finally {
			lock.unlock();
		}
	}
}
