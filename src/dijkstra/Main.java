package dijkstra;
import java.io.*;
import java.util.*;
public class Main {
 
	public static void main(String[] args) throws IOException {

		long start = System.currentTimeMillis();

		String inputFileName = args[0]; // dictionary
		String word1 = args[1]; // first word
		String word2 = args[2]; // second word
		ArrayList<String> dictionary = new ArrayList<>(); // dictionary of words
		
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
				char mismatchLetter = ' ';
				int mismatchIndex = 0;
				int mismatches = 0;
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
			if (mismatches == 1) {
				int adjacentVertex = dGraph.getIndexAtWord(comparisonWord);
				int weight = dGraph.calculateEdgeWeight(word.charAt(mismatchIndex), mismatchLetter);
				dGraph.getVertex(index).addToAdjList(adjacentVertex, comparisonWord); // add the adjacent vertex to the adjacency list
				dGraph.getVertex(index).getAdjList().getLast().setWeight(weight); // set the weight of the edge
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
		

		LinkedList<String> shortestPath = dGraph.dijkstraPath(dGraph.getIndexAtWord(word1), dGraph.getIndexAtWord(word2));
		//int edges = shortestPath.size() -1;
		System.out.println("path: " + shortestPath);


		int totalWeight = 0;
		
		for (int i = 0; i < shortestPath.size()-1; i++) {
			String word = shortestPath.get(i);
			int nextIndex = dGraph.getIndexAtWord(shortestPath.get(i + 1));
			int weight  = 0;

			System.out.println("Processing edge: " + word + " -> " + shortestPath.get(i + 1));
			for (AdjListNode node : dGraph.getVertex(dGraph.getIndexAtWord(word)).getAdjList()) {
				if (node.getVertexIndex() == nextIndex) {
					weight = node.getWeight();
					break;
				}
			}
			totalWeight += weight;

			System.out.println("Weight: " + weight);
		}
		System.out.println("Total weight: " + totalWeight);
			


		// print the path of predecessors
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
