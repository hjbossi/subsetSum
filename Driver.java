// Hannah Bossi, Sara Hoffman, Riley Karp
// CS375 - Algorithms
// 04-21-17

// import statements
import java.lang.Math;
import java.util.Random;
import java.util.ArrayList;

// class definition
public class Driver {
    // main method
    public static void main(String[] args) throws Exception {
    
        // create random variable
    	Random rand = new Random();

    	//make subset
    	ArrayList<Long> S = new ArrayList<Long>();
    	long low = 0;
    	long upper = 1_000_000_000_000L;
    	for( int i = 0; i < 100; i++ ) {
    		long randVal = low + (long)( rand.nextDouble()*(upper-low) );
    		S.add( randVal );
    	}
    	
    	long k =  25_000_000_000_000L;
    	int reps = 100;
    	
        // print set information
    	System.out.println("Set: " + S);
        System.out.println("Sum: " + k);
    
    	//make SubsetSum finders
        ExhaustiveSearch exhaustive = new ExhaustiveSearch( S,k );
       	DynamicSubset dynamic = new DynamicSubset( S,k );
        Greedy greedy = new Greedy( S,k );
        RandomSubset random = new RandomSubset( S, k, reps );
        AnnealingSubset annealing = new AnnealingSubset( S, k, reps );
        HillSubset hill = new HillSubset( S, k, reps );
                
        //exhaustive
        System.out.println( "---------------------Exhaustive--------------------");
        //warm-up twice
        System.out.println( "-------------Warming up---------------");
        ArrayList<ArrayList<Long>> a = exhaustive.getPowerSet(exhaustive.getSet()); 
        exhaustive.subSetSum(a);
        a = exhaustive.getPowerSet(exhaustive.getSet()); 
        exhaustive.subSetSum(a);
        //time
        long startTime = System.nanoTime();
        a = exhaustive.getPowerSet(exhaustive.getSet()); 
        ArrayList<Long> residue = exhaustive.subSetSum(a);
        long endTime = System.nanoTime();
        System.out.println( "Result: " + residue ); 
        long exhaustiveTime = (endTime - startTime) / 1000000;
        System.out.println( "Exhaustive time: " + exhaustiveTime );
        
        
        //dynamic
        System.out.println( "---------------------Dynamic--------------------");
        //warm-up twice
        System.out.println( "-------------Warming up---------------");
        dynamic.subSetSum();
        dynamic.subSetSum();
        //time
        startTime = System.nanoTime();
        ArrayList<Long> output = dynamic.subSetSum();
        endTime = System.nanoTime();
        System.out.println( "Result: " + output );
        long time = (endTime - startTime) / 1000000;
        //output the speedup
        System.out.printf("Speed-up: %.2f\n", exhaustiveTime / 1.0 / time);
        
        
        //greedy
        System.out.println( "---------------------Greedy--------------------");
        //warm-up twice
        System.out.println( "-------------Warming up---------------");
        greedy.subsetSum();
        greedy.subsetSum();
        //time
        startTime = System.nanoTime();
        long greedyOutput = greedy.subsetSum();
        endTime = System.nanoTime();
        System.out.println( "Result: " + greedyOutput );
        time = (endTime - startTime) / 1000000;
        // output the speedup
        System.out.printf("Speed-up: %.2f\n", exhaustiveTime / 1.0 / time);
        
        
        //random
        System.out.println( "---------------------Random--------------------");
        //warm-up twice
        System.out.println( "-------------Warming up---------------");
        random.findSets();
        random.findSets();
        //time
        startTime = System.nanoTime();
        random.findSets();
        endTime = System.nanoTime();
        time = (endTime - startTime) / 1000000;
        // output the speedup
        System.out.printf("Speed-up: %.2f\n", exhaustiveTime / 1.0 / time);
        
        
        //annealing
        System.out.println( "---------------------Annealing--------------------");
        //warm-up twice
        System.out.println( "-------------Warming up---------------");
        annealing.findSets();
        annealing.findSets();
        //time
        startTime = System.nanoTime();
        annealing.findSets();
        endTime = System.nanoTime();
        time = (endTime - startTime) / 1000000;
        // output the speedup
        System.out.printf("Speed-up: %.2f\n", exhaustiveTime / 1.0 / time);
        
        
        //hill
        System.out.println( "---------------------Hill Climbing--------------------");
        //warm-up twice
        System.out.println( "-------------Warming up---------------");
        hill.findSets();
        hill.findSets();
        //time
        startTime = System.nanoTime();
        hill.findSets();
        endTime = System.nanoTime();
        time = (endTime - startTime) / 1000000;
    	// output the speedup
        System.out.printf("Speed-up: %.2f\n", exhaustiveTime / 1.0 / time);
    }
}