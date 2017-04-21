import java.util.ArrayList;

//Dynamic Programming solution to the Subset Sum Problem
public class DynamicSubset {

	private ArrayList<Long> S;
	private long k;
	private long total = 0;
	private boolean[][] Q;
	private int n;
	
	//sets fields for the DynamicSubset Class
	public DynamicSubset( ArrayList<Long> S, long k ) {
		this.S = S;
		this.k = k;
		n = S.size();
	}
	
	//returns true if there's a subset of S whose elements add to k
	//returns false otherwise
	public boolean subSetSum() {
		//find total sum of elements in S
		for( int i = 0; i < n; i++ ) {
			total += S.get(i);
		}
		
		//base case: total sum of elements in S < k
		if ( k > total ) {
			return false;
		}
		
		//create empty 2D array Q
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
// 		String s = "";
// 		for (int x = 0; x < Q.length; x++) { 
// 			for (int y = 0; y < Q[x].length; y++) {
// 				if( Q[x][y] ) {
// 					s = "T";
// 				}
// 				else {
// 					s = "F";
// 				}
// 				System.out.print( s + " ");
// 			}
// 			System.out.println();
// 		}

		//return the k-th item in the last row of Q
		return Q[n-1][Long.valueOf(k).intValue()];
	}
	
	//tests algorithm on small set S
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