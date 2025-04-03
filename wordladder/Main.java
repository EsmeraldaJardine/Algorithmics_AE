import java.io.*;
import java.util.*;

/**
 program to find word ladder with shortest path (i.e. minimum number edges
 */
 
public class Main {

	public static void main(String[] args) throws IOException {

		long start = System.currentTimeMillis();

		String inputFileName = args[0]; // dictionary
		String word1 = args[1]; // first word
		String word2 = args[2]; // second word
		HashSet<String> dictionary = new HashSet<String>();
		
		// read in the data here
		try {
			FileReader reader = new FileReader(inputFileName);
			Scanner inputScanner = new Scanner(reader);
			while (inputScanner.hasNextLine()) {
				dictionary.add(inputScanner.nextLine());
				}
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + inputFileName);
			System.exit(0);
		}
		System.out.println(dictionary);
		if (dictionary.contains(word1) && dictionary.contains(word2)) {
			System.out.println("words exist in dictionary");
			// word ladder logic here
			
		} else{
			System.out.println("words do not exist in dictionary");
		}



        // create graph here

		// do the work here

		// end timer and print total time
		long end = System.currentTimeMillis();
		System.out.println("\nElapsed time: " + (end - start) + " milliseconds");
	}

}
