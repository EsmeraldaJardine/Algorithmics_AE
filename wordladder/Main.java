import java.io.*;
import java.lang.reflect.Array;
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
		ArrayList<String> dictionary = new ArrayList<>(); // dictionary of words
		
		// read in the data here
		try {
			FileReader reader = new FileReader(inputFileName);
			Scanner inputScanner = new Scanner(reader);
			while (inputScanner.hasNextLine()) {
				dictionary.add(inputScanner.nextLine());
			}				
			reader.close();
			
			System.out.println(dictionary.size());
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + inputFileName);
			System.exit(0);
		}
	

		//build graph of dictionary
		/*
		 * each word is a vertex
		 * words that differ by one letter are adjacent and connected by an edge
		 */
		Graph dGraph = new Graph(dictionary.size(), dictionary); // create a new graph with the size of the dictionary
		System.out.println(dGraph.size());
		
		int index = 0;
		for (String word : dictionary) { 
			dGraph.setVertex(index, word); // set the vertex at index to the word
			dGraph.getVertex(index).setWord(word); // set the word at the vertex
			System.err.println("word: " + word);
		
			// for every vertex word, compare the string sets to every other word in the dictionary
			// do this by iterating through each character in the vertex word and compare it to all the words in the dictionary?

			// adjacency list logic - verify if string differ by one letter
			
			for (String comparisonWord : dictionary) {
				int mismatches = 0;
				for (int letter = 0; letter < word.length(); letter++) {
					if (word.charAt(letter) != comparisonWord.charAt(letter)) {
						mismatches++;
					
					if (mismatches == 0){
						System.out.println("same word");
						break;
					}
					if (mismatches > 1) {
						break; 
						}
					}
				}
				if (mismatches == 1) {
					int adjacentVertex = dGraph.getIndexAtWord(comparisonWord);
					dGraph.getVertex(index).addToAdjList(adjacentVertex, comparisonWord); // add the adjacent vertex to the adjacency list
				}
				mismatches = 0; // reset mismatch
			}
			index++;
		}

		// Print the adjacency list for each vertex
		// for (int i = 0; i < dGraph.size(); i++) {
		// 	Vertex vertex = dGraph.getVertex(i);
		// 	System.out.print("Vertex " + vertex.getWord() + " -> ");
		// 	for (AdjListNode adjNode : vertex.getAdjList()) {
		// 		System.out.print(adjNode.getWord() + " ");
		// 	}
		// 	System.out.println();
		// }
		
		

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
