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
	private ArrayList<Long> set;
	private ArrayList<Long> currentSub;
	private long target;
	private long residue;
	private int reps;

	// constructor
	public HillSubset(ArrayList<Long> s, long t, int r) {
		this.set = s;
		this.target = t;
		this.reps = r;
		this.residue = (long) Integer.MAX_VALUE;
		this.currentSub = new ArrayList<Long>();
	}

	// function: findResidue
	// input: subset
	//.output: residue
	// desc: finds total of subset and sets residue
	private int findResidue(ArrayList<Long> subset) {
		long total = 0;
		for (long i : subset) {
			total += i;
		}
		long res = Math.abs(total - this.target);
		if (res < this.residue) {
			this.residue = res;
		}
		return res;
	}

	// function: genSubset
	// input: null
	//.output: subset
	// desc: finds random subset of set
	private ArrayList<Long> genSubset() {
		ArrayList<Long> sub = new ArrayList<Long>();
		Random r = new Random();
		int numItems = r.nextInt(this.set.size());
		//System.out.println("Num items: "+numItems);
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
		ArrayList<Long> t = (ArrayList<Long>) this.currentSub.clone();

		Random r = new Random();
		int i = r.nextInt(this.set.size());
		int j = r.nextInt(this.set.size());

		if (this.currentSub.contains(this.set.get(i))) {
			t.remove(this.set.get(i));
		} else {
			t.add(this.set.get(i));
		}

		double d = r.nextDouble();
		if (this.currentSub.contains(this.set.get(j))) {
			if (d < 0.5) {
				t.remove(this.set.get(j));
			} 
		} else {
			if (d < 0.5) {
				t.add(this.set.get(j));
			}
		}

		long cRes = this.findResidue(this.currentSub);
		long nRes = this.findResidue(t);

		if (nRes < cRes) {
			this.currentSub = t;
			//System.out.println("---Swapped---");
		}
	}

	// function: findSets
	// input: null
	//.output: null
	// desc: executes subset process
	private void findSets() {
		Collections.sort(this.set);

		this.currentSub = this.genSubset();

		for (int i = 0; i < this.reps; i++) {
			this.findNeighbor();
		}

		this.residue = this.findResidue(this.currentSub);
		System.out.println("Residue found for current subset: " + this.residue);
	}


	//------------------------- MAIN CODE ---------------------------------------
	public static void main (String args[]){
		Random r = new Random();
		
		ArrayList<Long> set = new ArrayList<Long>();

		long LOWER_RANGE = 0;
		long UPPER_RANGE = 1000000000000L;

		for (int i = 0; i < 100; i++) {
			long randomValue = LOWER_RANGE + (long)(r.nextDouble()*(UPPER_RANGE-LOWER_RANGE));
			set.add(randomValue);
		}

		long target = (long) 25 * (long) Math.pow(10,12);
		int reps = 100;

		HillSubset s = new HillSubset(set, target, reps);

		s.findSets();
	}
}