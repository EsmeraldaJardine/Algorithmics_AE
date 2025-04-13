package wordladder;
import java.io.*;
import java.util.*;

/**
 program to find word ladder with shortest path (i.e. minimum number edges
 */
 
public class Main {

	public static void main(String[] args) throws IOException {

		long start = System.currentTimeMillis(); 

		// Read input arguments:
		String inputFileName = args[0]; 
		String word1 = args[1];
		String word2 = args[2];
		ArrayList<String> dictionary = new ArrayList<>(); 
		
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

		// Create a graph representation of the dictionary
		Graph dGraph = new Graph(dictionary.size(), dictionary); 
		int index = 0;

		// Populate the graph with vertices and edges
		for (String word : dictionary) { 
			dGraph.setVertex(index, word); 
			dGraph.getVertex(index).setWord(word); 

			// Compare the current word with every other word in the dictionary
			for (String comparisonWord : dictionary) {
				int mismatches = 0;
				// Count the number of differing characters
				for (int letter = 0; letter < word.length(); letter++) {
					if (word.charAt(letter) != comparisonWord.charAt(letter)) {
						mismatches++;
					if (mismatches > 1) {
						break; 
					}
				}
			}
				// If there is exactly one mismatch, add the edge to the adjacency list
				if (mismatches == 1) {
					int adjacentVertex = dGraph.getIndexAtWord(comparisonWord);
					dGraph.getVertex(index).addToAdjList(adjacentVertex, comparisonWord); // add the adjacent vertex to the adjacency list
				}
			}
			index++;
		}
		
		// Find the shortest path (word ladder) between the two word
		LinkedList<String> shortestPath = dGraph.wordladder(dGraph.getIndexAtWord(word1), dGraph.getIndexAtWord(word2));
		int edges = shortestPath.size() -1;


		// Write the results to an output file
		FileWriter writer = new FileWriter("output.txt");
		writer.write("word1 = " + word1 + "\n");
		writer.write("word2 = " + word2 + "\n");

		if (shortestPath.size() == 0) {
			writer.write("no path exists\n");
		} else {
		writer.write("shortest word ladder of length " + edges + "\n");

		for (String word : shortestPath.reversed()) {
			writer.write(word + "\n");
			}
		}
		writer.close();

		long end = System.currentTimeMillis();
		System.out.println("\nElapsed time: " + (end - start) + " milliseconds");
	}

}
