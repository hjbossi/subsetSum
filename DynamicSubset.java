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
		String s = "";
		for (int x = 0; x < Q.length; x++) { 
			for (int y = 0; y < Q[x].length; y++) {
				if( Q[x][y] ) {
					s = "T";
				}
				else {
					s = "F";
				}
				System.out.print( s + " ");
			}
			System.out.println();
		}

		//return the k-th item in the last row of Q
		return Q[n-1][Long.valueOf(k).intValue()];
	}
	
	public ArrayList<Long> subSetSum() {
		if( this.buildArray() ) {
			ArrayList<Long> subSet = new ArrayList<Long>();
			System.out.println( "yes" );
			if( S.contains( k ) ) {
				subSet.add( k );
				return subSet;
			}

			for( int i = 0; i < n; i++ ) {
				if( Q[i][Long.valueOf(k).intValue()] ) {
					subSet.add( S.get(i) );
				}
			}
			return subSet;
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
		
		DynamicSubset dyn = new DynamicSubset( input, 4 );
		
		System.out.println( dyn.subSetSum() );
	}
}