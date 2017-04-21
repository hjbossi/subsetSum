// Hannah Bossi, Sara Hoffman, Riley Karp
// CS375 - Algorithms
// 04-21-17

// import statements
import java.util.Random;
import java.util.ArrayList;
import java.lang.Math;

// class definition
public class RandomSubset {
	private ArrayList<Long> set;
	private long target;
	private int reps;
	private long residue;

	// constructor
	public RandomSubset(ArrayList<Long> s, long t, int r) {
		this.set = s;
		this.target = t;
		this.reps = r;
		this.residue = 100;
	}

	// function: findResidue
	// input: subset
	//.output: residue
	// desc: finds total of subset and sets residue
	private long findResidue(ArrayList<Long> subset) {
		int total = 0;
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

	// function: findSets
	// input: null
	//.output: null
	// desc: executes subset process
	public void findSets() {
		for (int i = 0; i < this.reps; i++) {
			ArrayList<Long> subSet = this.genSubset();
			long nextRes = this.findResidue(subSet);
			// System.out.println("Subset: " + subSet);
			// System.out.println("Residue: "+ nextRes);
		}
		System.out.println("Lowest residue found: " + this.residue);
	}


	//------------------------- MAIN CODE ---------------------------------------
	public static void main (String args[]){
		Random r = new Random();
		
		ArrayList<Long> set = new ArrayList<Long>();

		long LOWER_RANGE = 0;
		long UPPER_RANGE = 1_000_000_000_000L;

		for (int i = 0; i < 100; i++) {
			long randomValue = LOWER_RANGE + (long)(r.nextDouble()*(UPPER_RANGE-LOWER_RANGE));
			set.add(randomValue);
		}

		long target = (long) 25 * (long) Math.pow(10,12);
		int reps = 100;

		RandomSubset s = new RandomSubset(set, target, reps);
		System.out.println("Set: " + set);
		System.out.println("Target: " + target);

		s.findSets();
	}
}