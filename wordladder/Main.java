import java.io.*;
import java.nio.charset.Charset;
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

		//build graph of dictionary
		/*
		 * each word is a vertex
		 * words that differ by one letter are adjacent and connected by an edge
		 */
		Graph dGraph = new Graph(dictionary.size());
		System.out.println(dGraph.size());
		
		// for every word in the dictionary, find all the other words that differ by one letter
		HashMap<String, HashSet<Character>> wordLettersSets = new HashMap<>(); // Create a map to store each word and its set of unique characters
		// Populate the map with words and their unique characters
		for (String word : dictionary){
			HashSet<Character> letterCombinations = new HashSet<>();
			
			for (char letter : word.toCharArray()){
				letterCombinations.add(letter);
			}
			wordLettersSets.put(word, letterCombinations);
		}

		if (dictionary.contains(word1) && dictionary.contains(word2)) {
			System.out.println("words exist in dictionary");
			System.out.println(wordLettersSets.get(word1));
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
