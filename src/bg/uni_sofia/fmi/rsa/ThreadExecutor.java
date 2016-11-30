package bg.uni_sofia.fmi.rsa;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadExecutor {
	
	int maxThreadsNum;
	int maxIndex;
	
	ThreadExecutor(int maxThreadNum, int maxIndex) {
		this.maxIndex = maxIndex;
		this.maxThreadsNum = maxThreadNum;
	}
	
	public void calculateE(boolean quietMode) throws FileNotFoundException, UnsupportedEncodingException {
		ExecutorService executor = Executors.newFixedThreadPool(maxThreadsNum);
		 for (int i=0; i<maxIndex; i++) {
	            Runnable calculator = new CalculationThread(i, quietMode);
	            executor.execute(calculator);
		 }
		 executor.shutdown();
	     while (!executor.isTerminated()) {
	     }
	     
	}

	
}