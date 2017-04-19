// Hannah Bossi, Sara Hoffman, Riley Karp
// CS 375 Project 4 
// ExhaustiveSearch.java
// Solves the Subset Sum Problem using exhaustive search 
import java.lang.Math; 
import java.util.ArrayList;
import java.lang.Boolean; 
import java.lang.Long;  
import java.util.Random; 

class ExhaustiveSearch{

	// stores the set of numbers
	private ArrayList<Long> set; 
	//stores the sum to search for
	private long n; 
	
	
	// takes in a set and a sum to search for
	public ExhaustiveSearch(ArrayList<Long> array, long num){
		this.set = array; 
		this.n = num; 
	}
	
	// return the set
	public ArrayList<Long> getSet(){
		return this.set; 
	
	}

	// generate the power set of the given set
	// output: set of  2^n elements
	public ArrayList<ArrayList<Long>> getPowerSet(ArrayList<Long> set){
		int length = set.size(); 
		ArrayList<ArrayList<Long>> powerSet = new ArrayList<ArrayList<Long>>(); 
		for(int i = 0; i < (1<<length); i++){
			ArrayList<Long> subSet = new ArrayList<Long>(); 
			for(int k = 0; k < length; k++){
				if ((i & (1<<k)) > 0){
					subSet.add(set.get(k)); 					
				}
			}
			powerSet.add(subSet); 
		
		}
		return powerSet; 
	}
	
	// perform the subset sum calculation
	// do this by looping through the power set
	public ArrayList<Long> subSetSum(ArrayList<ArrayList<Long>> powerSet){
		int size = powerSet.size(); 
		for(int i=0;i<size;i++){
			ArrayList<Long> subNums = powerSet.get(i);
			int subSize = subNums.size(); 
			long sum = 0; 
			for(int k=0;k<subSize;k++){
				sum += subNums.get(k); 		
			}
			if(sum == this.n){
				return subNums; 
			}
			sum = 0; 
		
		}
		return null; 
	
	
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
		ExhaustiveSearch s = new ExhaustiveSearch(list, target); 
		ArrayList<ArrayList<Long>> a = s.getPowerSet(s.getSet()); 
		System.out.println(s.subSetSum(a)); 
	
	
	
	}













} 