// Hannah Bossi, Sara Hoffman, Riley Karp
// CS375 - Algorithms
// 04-21-17

// import statements
import java.lang.Math;
import java.util.Random;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// class definition
public class ResidueDriver {
    // main method
    public static void main(String[] args) throws Exception {
        // printWriter variable
        PrintWriter pw = new PrintWriter(new File("out.csv"));
    
        // create random variable
    	Random rand = new Random();

        for (int j = 0; j < 50; j++) {
            StringBuilder sb = new StringBuilder();
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
            Greedy greedy = new Greedy( S,k );
            RandomSubset random = new RandomSubset( S, k, reps );
            AnnealingSubset annealing = new AnnealingSubset( S, k, reps );
            HillSubset hill = new HillSubset( S, k, reps );

            //greedy
            System.out.println( "---------------------Greedy--------------------");
            //warm-up twice
            System.out.println( "-------------Warming up---------------");
            greedy.subsetSum();
            greedy.subsetSum();
            System.out.println( "-------------Running---------------");
            long greedyOutput = greedy.subsetSum();
            sb.append(Long.toString(greedyOutput)+',');
            System.out.println( "Result: " + greedyOutput );
            
            
            //random
            System.out.println( "---------------------Random--------------------");
            //warm-up twice
            System.out.println( "-------------Warming up---------------");
            random.findSets();
            random.findSets();
            System.out.println( "-------------Running---------------");
            long randOutput = random.findSets();
            sb.append(Long.toString(randOutput)+',');
            System.out.println( "Result: " + randOutput );
            
            
            //annealing
            System.out.println( "---------------------Annealing--------------------");
            //warm-up twice
            System.out.println( "-------------Warming up---------------");
            annealing.findSets();
            annealing.findSets();
            System.out.println( "-------------Running---------------");
            long annealOutput = annealing.findSets();
            sb.append(Long.toString(annealOutput)+',');
            System.out.println( "Result: " + annealOutput );
            
            
            //hill
            System.out.println( "---------------------Hill Climbing--------------------");
            //warm-up twice
            System.out.println( "-------------Warming up---------------");
            hill.findSets();
            hill.findSets();
            System.out.println( "-------------Running---------------");
            long hillOutput = hill.findSets();
            sb.append(Long.toString(hillOutput)+',');
            System.out.println( "Result: " + hillOutput );

            sb.append('\n');
            System.out.println("sb: "+sb);
            System.out.println("sb to string: "+sb.toString());
            pw.write(sb.toString());
        }
        pw.close();
    }
}