package primenumbers;

import java.util.Set;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class GeneratePrimes extends RecursiveTask<Set<Integer>> {
    private static final int SEQUENTIAL_THRESHOLD = 10000;

    private int low;
    private int high;
    private IPrimesGenerator pg;

    public GeneratePrimes(IPrimesGenerator pg, int start, int end) {
        low = start;
        high = end;
        this.pg = pg;
    }
    
    private Set<Integer> computeDirectly(int start, int end){
    	System.out.println("computeDirectly - start: " + start + " end: " + end);
    	return pg.computePrimes(start, end);
    }

    protected Set<Integer> compute() {
    	// don't parallelise algorithm if running sieve
    	if (pg == AlgoContainer.runEratosthenesSieve)
	         return computeDirectly(low, high);
    	else{
	        if(high - low <= SEQUENTIAL_THRESHOLD) {
	            return computeDirectly(low, high);
	         } else {
	            int mid = low + (high - low) / 2;
	            GeneratePrimes left  = new GeneratePrimes(pg, low, mid);
	            GeneratePrimes right = new GeneratePrimes(pg, mid, high);
	            
	            left.fork();
	            Set<Integer> rightAns = right.compute();
	            Set<Integer> leftAns  = left.join();
	            leftAns.addAll(rightAns);
	            
	            return leftAns;
	         }
    	}
     }

     public static Set<Integer> generatePrimes(IPrimesGenerator pg, int limit) {
         return ForkJoinPool.commonPool().invoke(new GeneratePrimes(pg, 2, limit));
     }
     
     public static void main(String[] args){
    	 Set<Integer> primes = generatePrimes(AlgoContainer.runEratosthenesSieve, 1000000);
    	 //Set<Integer> primes = generatePrimes(AlgoContainer.runHeuresticSearch, 500000);
    	 
    	 System.out.println("Number of primes: " + primes.size());
     }
}