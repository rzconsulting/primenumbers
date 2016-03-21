package primenumbers;

import java.util.Set;

@FunctionalInterface
public interface IPrimesGenerator {
	public Set<Integer> computePrimes(int lowerBound, int upperBound);
}
