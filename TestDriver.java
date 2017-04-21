// Hannah Bossi, Sara Hoffman, Riley Karp
// CS375 - Algorithms
// 04-21-17
// TestDriver.java - Tests the algorithms on a set for which there is a subset sum
// This file tests the accuracy of the our individual algorithms
// validates that the exact algorithms return exact soltions

// import statements
import java.lang.Math;
import java.util.Random;
import java.util.ArrayList;


// class definition
public class TestDriver {
    // main method
    public static void main(String[] args) throws Exception {
    
        // create random variable
    	Random rand = new Random();

    	//make subset
    	ArrayList<Long> S = new ArrayList<Long>();
    	long low = 0;
    	long upper = 1_000L;
    
    	// create a subset that we know should return true 
		// the subset that works is 
		// [97, 240, 887, 496, 131, 693, 456]
		
    	S.add((long) 295); 
    	S.add((long) 97);
    	S.add((long) 240);
    	S.add((long) 887);
    	S.add((long) 119);
    	S.add((long) 280);
    	S.add((long) 496);
    	S.add((long) 131);
    	S.add((long) 693);
    	S.add((long) 456);

    	long k =  3000;
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

        long exhaustiveTime = 0;
                
        //exhaustive
        System.out.println( "---------------------Exhaustive--------------------");
        ArrayList<ArrayList<Long>> a = exhaustive.getPowerSet(exhaustive.getSet()); 
        ArrayList<Long> result = exhaustive.subSetSum(a);
        System.out.println( "Result: " + result );
    
        
        
		//dynamic
        System.out.println( "---------------------Dynamic--------------------");
        boolean output = dynamic.subSetSum();
        System.out.println( "Result: " + output );


        //greedy
        System.out.println( "---------------------Greedy--------------------");
        long greedyOutput = greedy.subsetSum();
        System.out.println( "Residue: " + greedyOutput );
        
        
        //random
        System.out.println( "---------------------Random--------------------");
        random.findSets();
      
        
        
        //annealing
        System.out.println( "---------------------Annealing--------------------");
        annealing.findSets();
    

        
        //hill
        System.out.println( "---------------------Hill Climbing--------------------");
        hill.findSets();
    
    }
}