// Hannah Bossi, Sara Hoffman, Riley Karp
// CS375 - Algorithms
// 04-21-17
// annealing alg

// import statements
import java.util.Random;
import java.util.ArrayList;
import java.lang.Math;
import java.util.Collections;

// class definition
public class AnnealingSubset {
	private ArrayList<Long> set;
	private ArrayList<Long> currentSub;
	private long target;
	private int reps;
	private long residue;
	private long minRes;

	// constructor
	public AnnealingSubset(ArrayList<Long> s, long t, int r) {
		this.set = s;
		this.target = t;
		this.reps = r;
		this.residue = (long)Integer.MAX_VALUE;
		this.minRes = (long)Integer.MAX_VALUE;
		this.currentSub = new ArrayList<Long>();
	}

	// function: findResidue
	// input: subset
	//.output: residue
	// desc: finds total of subset and sets residue
	private long findResidue(ArrayList<Long> subset) {
		long total = 0;
		for (Long i : subset) {
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
	// input: current iteration number
	// output: none
	// desc: finds neighbor subset
	// and updates current subset
	private void findNeighbor(int curIteration) {
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

		// System.out.println("Current subset: "+this.currentSub);
		// System.out.println("Neighbor subset: "+t);
		// System.out.println("Current residue: "+cRes);		
		// System.out.println("Neighbor residue: "+nRes);

		if (nRes < this.minRes) {
			this.minRes = nRes;
		}

		if (nRes < cRes) {
			this.currentSub = t;
			//System.out.println("---Swapped---");
		} else {
			//swap with probability e^-T
			double curIt_Double = (double) curIteration;
			double a = -1 * ( (nRes - cRes) / (10000000000L * Math.pow(0.8, ( (double) (curIt_Double/300.0) ) ) ) );
			double power = Math.exp(a);
			// System.out.println("-T: "+a);
			// System.out.println("e^-T: "+power);
			d = r.nextDouble();
			if (d < power) {
				this.currentSub = t;
				//System.out.println("---Swapped via e^-T---");
			}
		}
	}

	// function: findSets
	// input: null
	//.output: null
	// desc: executes subset process
	private void findSets() {
		Collections.sort(this.set);
		//System.out.println("Sorted set: "+this.set);

		this.currentSub = this.genSubset();
		this.minRes = this.findResidue(this.currentSub);

		for (int i = 0; i < this.reps; i++) {
			this.findNeighbor(i);
		}

		//this.residue = this.findResidue(this.currentSub);
		System.out.println("Smallest residue found: " + this.minRes);
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

		AnnealingSubset s = new AnnealingSubset(set, target, reps);

		System.out.println("Set: " + set);
		System.out.println("Target: " + target);
		System.out.println("Reps: " + reps);

		s.findSets();
	}
}