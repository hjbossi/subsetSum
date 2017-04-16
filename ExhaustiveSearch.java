// Hannah Bossi 
// CS 375 Project 4 
// ExhaustiveSearch.java
// Solves the Subset Sum Problem using exhaustive search 
import java.lang.Math; 
import java.util.ArrayList;
import java.lang.Boolean;  

class ExhaustiveSearch{

	// stores the set of numbers
	private ArrayList<Integer> set; 
	//stores the sum to search for
	private int n; 
	
	
	// takes in a set and a sum to search for
	public ExhaustiveSearch(ArrayList<Integer> array, int num){
		this.set = array; 
		this.n = num; 
	}
	
	// return the set
	public ArrayList<Integer> getSet(){
		return this.set; 
	
	}

	// generate the power set of the given set
	// output: set of  2^n elements
	public ArrayList<ArrayList<Integer>> getPowerSet(ArrayList<Integer> set){
		int length = set.size(); 
		ArrayList<ArrayList<Integer>> powerSet = new ArrayList<ArrayList<Integer>>(); 
		for(int i = 0; i < (1<<length); i++){
			ArrayList<Integer> subSet = new ArrayList<Integer>(); 
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
	public Boolean subSetSum(ArrayList<ArrayList<Integer>> powerSet){
		int size = powerSet.size(); 
		for(int i=0;i<size;i++){
			ArrayList<Integer> subNums = powerSet.get(i);
			int subSize = subNums.size(); 
			int sum = 0; 
			for(int k=0;k<subSize;k++){
				sum += subNums.get(k); 		
			}
			if(sum == this.n){
				return true; 
			}
			sum = 0; 
		
		}
		return false; 
	
	
	}	
	
	// ------------------------- MAIN CODE ---------------------------------------
	public static void main (String args[]){
		ArrayList<Integer> list = new ArrayList<Integer>(); 
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(9);
		int n = 8; 
		ExhaustiveSearch s = new ExhaustiveSearch(list, n); 
		ArrayList<ArrayList<Integer>> a = s.getPowerSet(s.getSet()); 
		System.out.println(s.subSetSum(a)); 
	
	
	
	}













} 