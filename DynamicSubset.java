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
	}
	
	public boolean buildArray() {
		//create empty 2D array Q
		for( int i = 0; i < n; i++ ) {
			total += S.get(i);
		}
		
		if ( k > total ) {
			return false;
		}
		
		Q = new boolean[n][Long.valueOf(total).intValue()];
		
		//fill array Q
		for( int i = 0; i < n; i++ ) {
			for( int j = 0; j < Long.valueOf(total).intValue(); j++ ) {
				Q[i][j] = ( S.get(i) == j );
				if( (i >= 1)  && (Q[i][j]==false) ) {
					Q[i][j] = Q[i-1][j];
					if( ( j-S.get(i) >= 0 ) && (Q[i][j]==false) ) {
						Q[i][j] = Q[i-1][Long.valueOf(j-S.get(i)).intValue()];
					}
				}
			}
		}
		
		//print Q
		for (int i = 0; i < Q.length; i++) { 
			for (int j = 0; j < Q[i].length; j++) {
				System.out.print(Q[i][j] + " ");
			}
			System.out.println();
		}
		
		//return the k-th item in the last row of Q
		return Q[n-1][Long.valueOf(k).intValue() - 1];
	}
	
	public ArrayList<Long> subSetSum() {
		if( this.buildArray() ) {
			System.out.println( "yes" );
			return new ArrayList<Long>(); //temporarily empty. wil be subset
		}
		else {
			System.out.println( "no" );
			return null;
		}
	}
	
	public static void main( String[] args ) {
		ArrayList<Long> input = new ArrayList<Long>();
		input.add(Long.valueOf(1));
		input.add(Long.valueOf(2));
		input.add(Long.valueOf(3));
		input.add(Long.valueOf(9));
		
		DynamicSubset dyn = new DynamicSubset( input, 7 );
		
		System.out.println( dyn.subSetSum() );
	}
}