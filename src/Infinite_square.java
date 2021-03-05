//Logic reference: https://www.youtube.com/watch?v=S26_O2B8h8k


public class Infinite_square { 
	double iterations;
	public Infinite_square() {
		iterations = 1000000;
	}
	
	public Infinite_square(int iter) {
		try{
			iterations = iter;
		}catch (Exception e) {
			iterations = 1000000; 
		}
	}
	protected double pi() {
		double result = 0.0;
		double sum= 0.0;
		for(double i = 1.0;i<iterations;i++) {
			sum += (1.0/Math.pow(i, 2));
		}
		sum *= 6.0;
		result = Math.sqrt(sum);
		return result;
	}
}
