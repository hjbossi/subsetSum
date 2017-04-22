import java.lang.Math;
import java.util.Random;
import java.util.ArrayList;
import java.lang.Long;

public class DriverTimer {
    public static void main(String[] args) throws Exception {
    
    	Random rand = new Random();
    	//make subset
    	ArrayList<Long> S = new ArrayList<Long>();
    	ArrayList<Long> D = new ArrayList<Long>();
    	long low = 0;
    	long upper = 1_000_000_000_000L;
    	long upper2 = 500_000L; 
    	for( int i = 0; i < 300; i++ ) {
    		long randVal = low + (long)( rand.nextDouble()*(upper-low) );
    		long randVal2 = low + (long)( rand.nextDouble()*(upper2-low) );
    		S.add( randVal );
    		D.add(randVal2); 
    	}
    	
    	long k = Double.valueOf( 25*Math.pow(10,12) ).longValue();
    	int reps = 100;
    	
    	System.out.println("Set: " + S);
        System.out.println("Sum: " + k);
    
    	//make SubsetSum finders
        ExhaustiveSearch exhaustive = new ExhaustiveSearch( S,k );
       	DynamicSubset dynamic = new DynamicSubset( D,k );
        Greedy greedy = new Greedy( S,k );
        RandomSubset random = new RandomSubset( S, k, reps );
        AnnealingSubset annealing = new AnnealingSubset( S, k, reps );
        HillSubset hill = new HillSubset( S, k, reps );
                
        //exhaustive
        System.out.println( "---------------------Exhaustive--------------------");
        //warm-up twice
        ArrayList<ArrayList<Long>> a = exhaustive.getPowerSet(exhaustive.getSet()); 
        exhaustive.subSetSum(a);
        a = exhaustive.getPowerSet(exhaustive.getSet());
        exhaustive.subSetSum(a);
        //time
        long startTime = System.nanoTime();
        a = exhaustive.getPowerSet(exhaustive.getSet()); 
        ArrayList<Long> output = exhaustive.subSetSum(a);
        long endTime = System.nanoTime();
        System.out.println( "Result: " + output ); 
        long exhaustiveTime = (endTime - startTime);
        System.out.println( "Exhaustive time: " + exhaustiveTime );
        
        
        //dynamic
        System.out.println( "---------------------Dynamic--------------------");
        //warm-up twice
        dynamic.subSetSum();
        dynamic.subSetSum();
        //time
        startTime = System.nanoTime();
        boolean result = dynamic.subSetSum();
        endTime = System.nanoTime();
        System.out.println( "Result: " + result );
        long time = (endTime - startTime);
        //output the speedup
        Long num = new Long(time); 
        System.out.println("Run Time: " + num.toString()); 
        System.out.printf("Speed-up: %.2f\n", exhaustiveTime / 1.0 / time);
        
     
        //greedy
        System.out.println( "---------------------Greedy--------------------");
        //warm-up twice
        greedy.subsetSum();
        greedy.subsetSum();
        //time
        startTime = System.nanoTime();
        long out = greedy.subsetSum();
        endTime = System.nanoTime();
        System.out.println( "Result: " + out );
        time = (endTime - startTime);
        num = new Long(time); 
        System.out.println("Run Time: " + num.toString()); 
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
        time = (endTime - startTime);
        num = new Long(time); 
        System.out.println("Run Time: " + num.toString()); 
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
        time = (endTime - startTime);
        num = new Long(time); 
        System.out.println("Run Time: " + num.toString()); 
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
        time = (endTime - startTime);
        num = new Long(time); 
        System.out.println("Run Time: " + num.toString()); 
    	// output the speedup
        System.out.printf("Speed-up: %.2f\n", exhaustiveTime / 1.0 / time);
    }
}