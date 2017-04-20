import java.util.ArrayList;

public class DynamicSubset {

	private ArrayList<Long> S;
	private long k;
	private long total = 0;
	private boolean[][] Q;
	private int n;
	
	public DynamicSubset( ArrayList<Long> S, long k ) {
		this.S = S;
		this.k = k;
		n = S.size();
		
		for( int i = 0; i < n; i++ ) {
			total += S.get(i);
		}
		
		Q = new boolean[n][Long.valueOf(total).intValue()];
	}
	
	public boolean buildArray() {
		for( int i = 0; i < n; i++ ) {
			for( int j = 0; j < Long.valueOf(total).intValue(); j++ ) {
				if( i == 1 ) {
					Q[i][j] = ( S.get(i) == j );
				}
				else {
					Q[i][j] = Q[i-1][j]; //or S.get(i) == j or Q[i-1][j-S.get(i)]
				}
			}
		}
		
		return Q[n][Long.valueOf(k).intValue()];
	}
	
	public ArrayList<Long> subSetSum() {
		if( this.buildArray() ) {
			return null; //temporarily null. wil be subset
		}
		else {
			return null;
		}
	}
	
	public static void main( String[] args ) {
		ArrayList<Long> input = new ArrayList<Long>();
		input.add(Long.valueOf(1));
		input.add(Long.valueOf(2));
		input.add(Long.valueOf(3));
		input.add(Long.valueOf(9));
		
		DynamicSubset dyn = new DynamicSubset( input, 4 );
		
		System.out.println( dyn.subSetSum() );
	}
}