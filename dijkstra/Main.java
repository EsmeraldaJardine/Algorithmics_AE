import java.io.*;
import java.util.*;

/**
 program to find word ladder with shortest distance for two words in a dictionary
 distance between elements of the word ladder is the absolute difference in the
 positions of the alphabet of the non-matching letter
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
	

		Graph dGraph = new Graph(dictionary.size(), dictionary); // create a new graph with the size of the dictionary
		System.out.println(dGraph.size());
		
		int index = 0;
		for (String word : dictionary) { 
			dGraph.setVertex(index, word); // set the vertex at index to the word
			dGraph.getVertex(index).setWord(word); // set the word at the vertex
			
			for (String comparisonWord : dictionary) {
				int mismatches = 0;
				for (int letter = 0; letter < word.length(); letter++) {
					if (word.charAt(letter) != comparisonWord.charAt(letter)) {
						mismatches++;
					if (mismatches > 1) {
						break; 
					}
				}
			}
			if (mismatches == 1) {
				int adjacentVertex = dGraph.getIndexAtWord(comparisonWord);
				dGraph.getVertex(index).addToAdjList(adjacentVertex, comparisonWord); // add the adjacent vertex to the adjacency list
				}
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
		
		

		if (!dictionary.contains(word1) || !dictionary.contains(word2)) {
			System.out.println("Does not exist in dictionary");
			System.out.println("word1 exists: " + dictionary.contains(word1));
			System.out.println("word2 exists: " + dictionary.contains(word2));
			System.exit(0);
			
		}
		
		// wordladder logic
		// find the shortest path from word1 to word2 using adjacency lists and bfs
		// try and use the number of predecessors to find the shortest path
		LinkedList<String> shortestPath = dGraph.bfs(dGraph.getIndexAtWord(word1), dGraph.getIndexAtWord(word2));
		int edges = shortestPath.size() -1;


		// print the path of predecessors
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


        // end timer and print total time
		long end = System.currentTimeMillis();
		System.out.println("\nElapsed time: " + (end - start) + " milliseconds");
	}

}
