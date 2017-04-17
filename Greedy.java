// Hannah Bossi, Sara Hoffman, Riley Karp
// CS375 Project 4
// Greedy.java
// Solves the subSet Sum problem using a greedy algorithm

import java.lang.Math; 
import java.util.ArrayList;
import java.lang.Boolean;  
import java.util.Collections; 

// Greedy class that solves the subset sum problem using the greedy strategy
class Greedy{

	// stores the set of numbers
	private ArrayList<Integer> set; 
	//stores the sum to search for
	private int n; 
	
	
	// takes in a set and a sum to search for
	public Greedy(ArrayList<Integer> array, int num){
		this.set = array; 
		this.n = num; 
	}
	
	// return the set
	public ArrayList<Integer> getSet(){
		return this.set; 
	
	}
	
	// this is the algorithm to conduct the greedy algorithm
	public Boolean subsetSum(){
		// create an empty subset s
		ArrayList<Integer> sub = new ArrayList<Integer>();
		 
		// sort the set S from largest to smallest
		Collections.sort(this.set); 
		Collections.reverse(this.set); 
		
		// repeat trying to adding to the subset the next thing in S
		int sum = 0; 
		for(int i = 0; i< this.set.size(); i++){
			// if the integer will not make the sum too large, add it 
			//otherwise move on in the list
			int num = this.set.get(i); 
			if(sum + num <= this.n){
				sub.add(num); 
				sum = sum + num; 
			}
		} 
	
		// if we've found such a subset
		if (sum == this.n){
			return true; 
		}
		
		// if we've not found such a subset
		else{
			return false; 
		}
		
	}
	
	
	// ------------------------- MAIN CODE ---------------------------------------
	public static void main (String args[]){
		ArrayList<Integer> list = new ArrayList<Integer>(); 
		list.add(7);
		list.add(2);
		list.add(4);
		list.add(9);
		int n = 16; 
		Greedy g = new Greedy(list, n); 
		System.out.println(g.subsetSum()); 
	
	
	
	}
	
}