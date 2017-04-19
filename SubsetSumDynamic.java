import java.util.ArrayList;

public class SubsetSumDynamic {

	private ArrayList<Long> S;
	private long k;
	private long total = 0;
	private boolean[][] Q;
	private int n;
	
	public SubsetSumDynamic( ArrayList<Long> S, long k ) {
		this.S = S;
		this.k = k;
		n = S.size();
		
		for( int i = 0; i < n; i++ ) {
			total += S.get(i);
		}
		
		Q = new boolean[n][total];
	}
	
	public boolean subSet() {
		for( int i = 0; i < n; i++ ) {
			for( int j = 0; j < total.intValue(); j++ ) {
				if( i == 1 ) {
					Q[i][j] = ( S.get(i) == j );
				}
				else {
					Q[i][j] = Q[i-1][j]; //or S.get(i) == j or Q[i-1][j-S.get(i)]
				}
			}
		}
		
		return Q[n][k.intValue()];
	}
	
	public static void main( String[] args ) {
		ArrayList<Long> input = new ArrayList<Long>();
		input.add(Long.valueOf(1));
		input.add(Long.valueOf(2));
		input.add(Long.valueOf(3));
		input.add(Long.valueOf(9));
		
		SubsetSumDynamic dyn = new SubsetSumDynamic( input, 4 );
		
		System.out.println( dyn.subSet() );
	}
}