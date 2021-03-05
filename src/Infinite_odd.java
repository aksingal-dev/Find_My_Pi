//Logic Reference: https://www.youtube.com/watch?v=HrRMnzANHHs

public class Infinite_odd {
	double iterations;
	public Infinite_odd() {
		iterations = 1000000000;
	}
	
	public Infinite_odd(int iter) {
		try {
			iterations = iter;
		}catch (Exception e) {
			iterations = 1000000000;
		}
	}
	
	protected double pi() {
		
		double result = 0.0;
		
		for (double i = 0; i < iterations; i += 1.0) {
			result += (Math.pow((-1.0), i))/(2.0*i + 1.0);
		}
		result = result * 4.0;
		
		return result;
	}
	
	
}
