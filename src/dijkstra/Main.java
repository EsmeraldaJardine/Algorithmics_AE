package dijkstra;
import java.io.*;
import java.util.*;
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
				char mismatchLetter = ' ';
				int mismatchIndex = 0;
				int mismatches = 0;

				// Count the number of differing characters
				for (int letter = 0; letter < word.length(); letter++) {

					if (word.charAt(letter) != comparisonWord.charAt(letter)) {
						mismatchLetter = comparisonWord.charAt(letter);
						mismatchIndex = letter;
						mismatches++;

						if (mismatches > 1) {
							break; 
						}
					}
				}
				// If there is exactly one mismatch, add the edge to the adjacency list
				if (mismatches == 1) {
					int adjacentVertex = dGraph.getIndexAtWord(comparisonWord);
					int weight = dGraph.calculateEdgeWeight(word.charAt(mismatchIndex), mismatchLetter);
					dGraph.getVertex(index).addToAdjList(adjacentVertex, comparisonWord); 
					dGraph.getVertex(index).getAdjList().getLast().setWeight(weight); 
					}
			}
			index++;
		}
		// Run Dijkstra's algorithm to find the shortest path
		LinkedList<String> shortestPath = dGraph.dijkstraPath(dGraph.getIndexAtWord(word1), dGraph.getIndexAtWord(word2));
		int totalWeight = 0;
		
		// Calculate the total weight of the shortest path
		for (int i = 0; i < shortestPath.size()-1; i++) {
			String word = shortestPath.get(i);
			int nextIndex = dGraph.getIndexAtWord(shortestPath.get(i + 1));
			int weight  = 0;
			for (AdjListNode node : dGraph.getVertex(dGraph.getIndexAtWord(word)).getAdjList()) {
				if (node.getVertexIndex() == nextIndex) {
					weight = node.getWeight();
					break;
				}
			}
			totalWeight += weight;
		}

		// Write the results to an output file
		FileWriter writer = new FileWriter("output.txt");
		writer.write("word1 = " + word1 + "\n");
		writer.write("word2 = " + word2 + "\n");

		if (shortestPath.size() == 0) {
			writer.write("no path exists\n");
		} else {
		writer.write("shortest path weight " + totalWeight + "\n");
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
