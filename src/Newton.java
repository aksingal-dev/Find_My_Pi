//Logic reference: https://www.youtube.com/watch?v=CKl1B8y4qXw

public class Newton {

	fact fact = new fact();
	
	public double pi() {
		double result = Front() + Rest();
		return result;
	}
	
	private double Front() {
		double front = (3.0 * Math.sqrt(3.0))/4.0;
		
		
		return front;
	}
	
	private double Rest() {
		double rest = 24.0;
		
		double series = 0.0;
		double numer = 0.0;
		double denom = 0.0;
		
		for (int i = 1; i < 35; i++) {
			numer = -1 * fact.Fact( (2*i) - 2 );
			denom = Math.pow( 2.0, (4*i - 2) ) * Math.pow(fact.Fact(i - 1),2) * (2*i - 3) * (2*i + 1);
			series += (numer/denom);
		}
		return rest*series;
	}
}
