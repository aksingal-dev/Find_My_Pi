
public class fact {
	
	public fact() {
		
	}
	
	protected int Fact(int n) {//factorial
		if (n == 0) //exit condition
			return 1;
		else { //recursion
			return n * Fact(n-1);
		}
		
	}
}
