package bg.uni_sofia.fmi.rsa;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

public class CalculationThread implements Runnable {
	
	private static final int TWO = 2;
	private BigInteger index;
	private boolean isQuiet;
	
	CalculationThread(int ind, boolean isQuiet) {
		this.index = BigInteger.valueOf(ind);
		this.isQuiet = isQuiet;
	}
    
	@Override
	public void run() {
			if (!isQuiet) {
				System.out.println("Thread-"+ Thread.currentThread().getId()+" started.");
			}
				
			long startTime = System.currentTimeMillis();
			
			BigInteger numerator = index.multiply(BigInteger.valueOf(TWO)).add(BigInteger.ONE);
			BigInteger denominator = factorial((BigInteger.valueOf(TWO)).multiply(index));
			BigDecimal formula = new BigDecimal(numerator).divide((new BigDecimal(denominator)), MathContext.DECIMAL128);
			
			incrementE(formula);
			
			if (!isQuiet) {
				System.out.println("Thread-"+ Thread.currentThread().getId()+" stopped.");
				long endTime = System.currentTimeMillis();
				System.out.println("Thread-"+Thread.currentThread().getId()+" execution time was (millis): "+(endTime-startTime));
			}
	}			
	private BigInteger factorial(BigInteger n) {
	    BigInteger result = BigInteger.ONE;
	    while (!n.equals(BigInteger.ZERO)) {
	        result = result.multiply(n);
	        n = n.subtract(BigInteger.ONE);
	    }
	    return result;
	}
	
    public void incrementE(BigDecimal formula) {
        synchronized (Main.e) {
        	Main.e = Main.e.add(formula);
        } 
    }
	
}


