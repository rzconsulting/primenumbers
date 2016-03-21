package primenumbers;

import java.util.Set;
import java.util.TreeSet;

public class AlgoContainer {
	public final static IPrimesGenerator runHeuresticSearch = (lowerBound, upperBound) -> { 
		Set<Integer> primes = new TreeSet<Integer>();
		for (int i = lowerBound; i < upperBound; i++) {
			boolean isPrime = true; 
			// check to see if the number is prime
			for (int j = 2; j < i; j++) {
				if (i % j == 0) {
					isPrime = false;
					break;
				}
			}
			if (isPrime)
				primes.add(i);
    	}
    	return primes; 
	};
	
	public final static IPrimesGenerator runEratosthenesSieve = (lowerBound, upperBound) -> {
		Set<Integer> primes = new TreeSet<Integer>();
		
		int upperBoundSquareRoot = (int) Math.sqrt(upperBound);
		boolean[] isComposite = new boolean[upperBound + 1];
		for (int m = lowerBound; m <= upperBoundSquareRoot; m++) {
			if (!isComposite[m]) {
				primes.add(m);
				for (int k = m * m; k <= upperBound; k += m)
					isComposite[k] = true;
			}
		}
		for (int m = upperBoundSquareRoot; m <= upperBound; m++) {
			if (!isComposite[m])
				primes.add(m);
		}
		
		return primes;
	};
	
	public static void main(String[] args){
		//Set<Integer> primes = runEratosthenesSieve.computePrimes(250001, 500001);
		Set<Integer> primes = runHeuresticSearch.computePrimes(250001, 500001);
		
		System.out.println("Number of primes: " + primes.size());
	}
}