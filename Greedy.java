// Hannah Bossi, Sara Hoffman, Riley Karp
// CS375 Project 4
// Greedy.java
// Solves the subSet Sum problem using a greedy algorithm

import java.lang.Math; 
import java.util.ArrayList;
import java.lang.Boolean;  
import java.util.Collections; 
import java.util.Random; 

// Greedy class that solves the subset sum problem using the greedy strategy
class Greedy{

	// stores the set of numbers
	private ArrayList<Long> set; 
	//stores the sum to search for
	private long n; 
	
	
	// takes in a set and a sum to search for
	public Greedy(ArrayList<Long> array, long num){
		this.set = array; 
		this.n = num; 
	}
	
	// return the set
	public ArrayList<Long> getSet(){
		return this.set; 
	
	}
	
	// this is the algorithm to conduct the greedy algorithm
	public ArrayList<Long> subsetSum(){
		// create an empty subset s
		ArrayList<Long> sub = new ArrayList<Long>();
		 
		// sort the set S from largest to smallest
		Collections.sort(this.set); 
		Collections.reverse(this.set); 
		
		// repeat trying to adding to the subset the next thing in S
		long sum = 0; 
		for(int i = 0; i< this.set.size(); i++){
			// if the integer will not make the sum too large, add it 
			//otherwise move on in the list
			long num = this.set.get(i); 
			if(sum + num <= this.n){
				sub.add(num); 
				sum = sum + num; 
			}
		} 
	
		// if we've found such a subset
		if (sum == this.n){
			return sub; 
		}
		
		// if we've not found such a subset
		else{
			return null; 
		}
		
	}
	
	
	// ------------------------- MAIN CODE ---------------------------------------
	public static void main (String args[]){
		ArrayList<Long> list = new ArrayList<Long>(); 
		Random random = new Random(); 
		long LOWER_RANGE = 0;
		long UPPER_RANGE = 1_000_000_000_000L;
		for (int i = 0; i < 10; i++) {
			long randomValue = LOWER_RANGE + (long)(random.nextDouble()*(UPPER_RANGE-LOWER_RANGE));
			list.add(randomValue);
		}
		long target = 25_000_000_000_000L; 
		Greedy g = new Greedy(list, target); 
		System.out.println(g.subsetSum()); 
	
	
	
	}
	
}