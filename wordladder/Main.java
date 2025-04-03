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
		HashMap<String, char[]> dictionary = new HashMap<>();
		
		// read in the data here
		try {
			FileReader reader = new FileReader(inputFileName);
			Scanner inputScanner = new Scanner(reader);
			while (inputScanner.hasNextLine()) {
				String word = inputScanner.nextLine();
				char[] lettersArray = word.toCharArray();

				dictionary.put(word, lettersArray);
				}
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + inputFileName);
			System.exit(0);
		}

		//build graph of dictionary
		/*
		 * each word is a vertex
		 * words that differ by one letter are adjacent and connected by an edge
		 */
		Graph dGraph = new Graph(dictionary.size());
		System.out.println(dGraph.size());


		dictionary.forEach((key, value) -> System.out.println(key + " " + Arrays.toString(value)));

		if (dictionary.containsKey(word1) && dictionary.containsKey(word2)) {
			System.out.println("words exist in dictionary");
			System.out.println(dictionary.get(word1));
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
