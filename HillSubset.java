// Hannah Bossi, Sara Hoffman, Riley Karp
// CS375 - Algorithms
// 04-21-17
// hill climbing alg

// import statements
import java.util.Random;
import java.util.ArrayList;
import java.lang.Math;
import java.util.Collections;

// class definition
public class HillSubset {
	private ArrayList<Integer> set;
	private ArrayList<Integer> currentSub;
	private int target;
	private int reps;
	private int residue;

	// constructor
	public HillSubset(ArrayList<Integer> s, int t, int r) {
		this.set = s;
		this.target = t;
		this.reps = r;
		this.residue = Integer.MAX_VALUE;
		this.currentSub = new ArrayList<Integer>();
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

	// function: findNeighbor
	// input: none
	// output: none
	// desc: finds neighbor subset
	// and updates current subset
	private void findNeighbor() {
		ArrayList<Integer> t = (ArrayList<Integer>) this.currentSub.clone();

		Random r = new Random();
		int i = r.nextInt(this.set.size());
		int j = r.nextInt(this.set.size());

		if (this.currentSub.contains(this.set.get(i))) {
			t.remove(this.set.get(i));
		} else {
			t.add(this.set.get(i));
		}


		float f = r.nextFloat();
		if (this.currentSub.contains(this.set.get(j))) {
			if (f < 0.5) {
				t.remove(this.set.get(j));
			} 
		} else {
			if (f < 0.5) {
				t.add(this.set.get(j));
			}
		}

		System.out.println("Current subset: "+this.currentSub);
		System.out.println("Neighbor subset: "+t);
		int cRes = this.findResidue(this.currentSub);
		System.out.println("Current residue: "+cRes);
		int nRes = this.findResidue(t);
		System.out.println("Neighbor residue: "+nRes);
		if (nRes < cRes) {
			this.currentSub = t;
			System.out.println("---Swapped---");
		}
	}

	// function: findSets
	// input: null
	//.output: null
	// desc: executes subset process
	private void findSets() {
		Collections.sort(this.set);
		System.out.println("Sorted set: "+this.set);

		this.currentSub = this.genSubset();
		System.out.println("Current subset: "+this.currentSub);

		for (int i = 0; i < this.reps; i++) {
			this.findNeighbor();
		}

		this.residue = this.findResidue(this.currentSub);
		System.out.println("Residue found for current subset: " + this.residue);
	}


	//------------------------- MAIN CODE ---------------------------------------
	public static void main (String args[]){
		ArrayList<Integer> set = new ArrayList<Integer>();
		set.add(1);
		set.add(2);
		set.add(9);
		set.add(3);

		int target = 4;
		int reps = 8;

		HillSubset s = new HillSubset(set, target, reps);

		System.out.println("Set: " + set);
		System.out.println("Target: " + target);
		System.out.println("Reps: " + reps);

		s.findSets();
	}
}