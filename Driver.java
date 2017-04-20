import java.lang.Math;
import java.util.Random;
import java.util.ArrayList;

public class Driver {
    public static void main(String[] args) throws Exception {
    
    	Random rand = new Random();
    	//make subset
    	ArrayList<Long> S = new ArrayList<Long>();
    	long low = 0;
    	long upper = 1_000_000_000_000L;
    	for( int i = 0; i < 100; i++ ) {
    		long randVal = low + (long)( rand.nextDouble()*(upper-low) );
    		S.add( randVal );
    	}
    	
    	long k = Double.valueOf( 25*Math.pow(10,12) ).longValue();
    	int reps = 100;
    	
    	System.out.println("Set: " + S);
        System.out.println("Sum: " + k);
    
    	//make SubsetSum finders
        ExhaustiveSearch exhaustive = new ExhaustiveSearch( S,k );
//        	SubsetSumDynamic dynamic = new SubsetSumDynamic( S,k );
        Greedy greedy = new Greedy( S,k );
        RandomSubset random = new RandomSubset( S, k, reps );
        AnnealingSubset annealing = new AnnealingSubset( S, k, reps );
        HillSubset hill = new HillSubset( S, k, reps );
                
        //exhaustive
        System.out.println( "---------------------Exhaustive--------------------");
        long startTime = System.nanoTime();
        ArrayList<ArrayList<Long>> a = exhaustive.getPowerSet(exhaustive.getSet()); 
        ArrayList<Long> output = exhaustive.subSetSum(a);
        long endTime = System.nanoTime();
        System.out.println( output ); 
        long exhaustiveTime = (endTime - startTime) / 1000000;
        
//         dynamic
//         System.out.println( "---------------------Dynamic--------------------");
//         startTime = System.nanoTime();
//         output = dynamic.subSetSum();
//         endTime = System.nanoTime();
//         System.out.println( output );
//         long time = (endTime - startTime) / 1000000;
//         output the speedup
//         System.out.printf("Speed-up: %.2f\n", exhaustiveTime / 1.0 / time);
        
        
        //greedy
        System.out.println( "---------------------Greedy--------------------");
        startTime = System.nanoTime();
        output = greedy.subsetSum();
        endTime = System.nanoTime();
        System.out.println( output );
        long time = (endTime - startTime) / 1000000;
        // output the speedup
        System.out.printf("Speed-up: %.2f\n", exhaustiveTime / 1.0 / time);
        
        
        //random
        System.out.println( "---------------------Random--------------------");
        startTime = System.nanoTime();
        random.findSets();
        endTime = System.nanoTime();
        time = (endTime - startTime) / 1000000;
        // output the speedup
        System.out.printf("Speed-up: %.2f\n", exhaustiveTime / 1.0 / time);
        
        
        //annealing
        System.out.println( "---------------------Annealing--------------------");
        startTime = System.nanoTime();
        annealing.findSets();
        endTime = System.nanoTime();
        time = (endTime - startTime) / 1000000;
        // output the speedup
        System.out.printf("Speed-up: %.2f\n", exhaustiveTime / 1.0 / time);
        
        
        //hill
        System.out.println( "---------------------Hill Climbing--------------------");
        startTime = System.nanoTime();
        hill.findSets();
        endTime = System.nanoTime();
        time = (endTime - startTime) / 1000000;
    	// output the speedup
        System.out.printf("Speed-up: %.2f\n", exhaustiveTime / 1.0 / time);
    }
}