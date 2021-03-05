//Logic Reference: https://www.youtube.com/watch?v=RZBhSi_PwHU

import java.util.Random;

public class random {
	Random Random = new Random();
	int max;
	double iterations;
	public random() {
		 iterations = 1000;
		 max = 999999;
	}
	
	public random(int Max, double it) {
		try {
			max=Max;
		}catch (Exception e) {
			max = 999999;
		}
		
		try {
			iterations = it;
		}catch (Exception e) {
			iterations = 1000;
		}
		
	}
	
	protected double pi () {
		double result = 0.0;
		int count = 0;
		for(int i = 0; i<iterations;i++) {
			if( gcd( (Random.nextInt( max+1 )+1 ), (Random.nextInt(max+1)+1) ) == 1) {
				count++;
			}
		}
		result = Math.sqrt(6.0/(count/iterations));
		return result;
	}
	
	private int gcd(int a, int b) {
		int i;
        if (a < b)
            i = a;
        else
            i = b;
        
        for(int j = i; j > 1; j--) {
        	 
            if (a % j == 0 && b % j == 0)
                return j;
        }
        
        return 1;
	}
	
}
