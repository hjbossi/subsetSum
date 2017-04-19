// Hannah Bossi, Sara Hoffman, Riley Karp
// CS375 - Algorithms
// 04-21-17

// import statements
import java.util.Random;
import java.util.ArrayList;
import java.lang.Math;

// class definition
public class RandomSubset {
	private ArrayList<Integer> set;
	private int target;
	private int reps;
	private int residue;

	// constructor
	public RandomSubset(ArrayList<Integer> s, int t, int r) {
		this.set = s;
		this.target = t;
		this.reps = r;
		this.residue = 100;
	}

	// function: findResidue
	// input: subset
	//.output: residue
	// desc: finds total of subset and sets residue
	private int findResidue(ArrayList<Integer> subset) {
		int total = 0;
		for (Integer i : subset) {
			total += i;
		}
		int res = Math.abs(total - this.target);
		if (res < this.residue) {
			this.residue = res;
		}
		return res;
	}

	// function: genSubset
	// input: null
	//.output: subset
	// desc: finds random subset of set
	private ArrayList<Integer> genSubset() {
		ArrayList<Integer> sub = new ArrayList<Integer>();
		Random r = new Random();
		int numItems = r.nextInt(this.set.size());
		System.out.println("Num items: "+numItems);
		for (int i = 0; i < numItems; i++) {
			int index = r.nextInt(this.set.size());
			while (sub.contains(this.set.get(index))) {
				index = r.nextInt(this.set.size());
			}
			sub.add(this.set.get(index));
		}
		return sub;
	}

	// function: findSets
	// input: null
	//.output: null
	// desc: executes subset process
	private void findSets() {
		for (int i = 0; i < this.reps; i++) {
			ArrayList<Integer> subSet = this.genSubset();
			int nextRes = this.findResidue(subSet);
			System.out.println("Subset: " + subSet);
			System.out.println("Residue: "+ nextRes);
		}
		System.out.println("Lowest residue found: " + this.residue);
	}


	//------------------------- MAIN CODE ---------------------------------------
	public static void main (String args[]){
		ArrayList<Integer> set = new ArrayList<Integer>();
		set.add(1);
		set.add(2);
		set.add(3);
		set.add(9);
		int repetitions = 8;
		int target = 4;
		RandomSubset s = new RandomSubset(set, target, repetitions);
		System.out.println("Set: " + set);
		System.out.println("Target: " + target);
		s.findSets();
		// System.out.println(s.subSetSum(a)); 
	}
}