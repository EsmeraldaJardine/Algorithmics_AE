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
		Set<String> dictionary = new HashSet<>(); 
		
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

		//build graph of dictionary
		/*
		 * each word is a vertex
		 * words that differ by one letter are adjacent and connected by an edge
		 */
		Graph dGraph = new Graph(dictionary.size());
		System.out.println(dGraph.size());
		HashMap<String, Vertex> vertexWordMap = new HashMap<>();// map to store words and their corresponding vertices
		
		int index = 0;
		for (String word : dictionary) { //maybe this can be done whilst scanning the document to avoid creating the dictionary
			dGraph.setVertex(index);
			dGraph.getVertex(index).setWord(word); // set the word at the vertex
			vertexWordMap.put(word, dGraph.getVertex(index)); // add word and vertex to map
			
			// figure out adjacency list logic next
			// for every vertex word, compare it to every other word to see if they vary by one letter
			// do this by iterating through each character in the vertex word and compare it to all the words in the dictionary?

			// dGraph.getVertex(index).getWord();
			int mismatches = 0;
			String vertexWord = dGraph.getVertex(index).getWord();
			for (int vertexLetterIndex = 0; vertexLetterIndex < word.length(); vertexLetterIndex++) {
				// compare the vertex word to all the other words in the dictionary/graph?
				// maybe do a depth first search of the graph?

				//	NEXT STEP : ADD WORDS DIRECTLY TO THE GRAPH AS THEY ARE READ IN
				

			index++;
		}
		
		// for (Vertex v : vertexWordMap.values()) {
		// 	System.out.println(v.getIndex() + " " + v.getWord());	
		// }

		// System.out.println(dictionary);

		if (dictionary.contains(word1) && dictionary.contains(word2)) {
			System.out.println("words exist in dictionary");
		;
			
		} else{
			System.out.println("words do not exist in dictionary");
		}


		// end timer and print total time
		long end = System.currentTimeMillis();
		System.out.println("\nElapsed time: " + (end - start) + " milliseconds");
	}

}
