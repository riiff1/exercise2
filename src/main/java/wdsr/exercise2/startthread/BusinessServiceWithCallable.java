package wdsr.exercise2.startthread;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class BusinessServiceWithCallable {
	private final ExecutorService executorService;	
	private final NumericHelper helper;
	ArrayList<Future<Integer>> futures;
	
	public BusinessServiceWithCallable(ExecutorService executorService, NumericHelper helper) {
		this.executorService = executorService;
		this.helper = helper;
	}
	
	/**
	 * Calculates a sum of 100 random numbers.
	 * Random numbers are returned by helper.nextRandom method.
	 * Each random number is calculated asynchronously.
	 * @return sum of 100 random numbers.
	 */
	public long sumOfRandomInts() throws InterruptedException, ExecutionException {	
		long result = 0;
		futures = new ArrayList<>(100);
		// TODO Task: 
		// 1. create 100 Callable objects that invoke helper.nextRandom in their call() method.
		Callable<Integer> callable = new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				return helper.nextRandom();
			}
		};
		for(int i=0; i<100;i++)
		{
			// 2. submit all Callable objects to executorService (executorService.submit or executorService.invokeAll)
			Future<Integer> future = executorService.submit(callable);
			futures.add(future);
			// 3. sum up the results - each random number can be retrieved using future.get() method.
		}
		//trzeba zrobic druga petle ktora iteruje sie po kolekcji futurow i dla kazdego futura wola get
		for(Future<Integer> future : futures)
		{
			result += future.get();

		}
		// 4. return the computed result.
		return result;
	}
}
