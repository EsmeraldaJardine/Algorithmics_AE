package wordladder;
import java.io.*;
import java.util.*;

/**
 program to find word ladder with shortest path (i.e. minimum number edges
 */
 
public class Main {

	public static void main(String[] args) throws IOException {

		long start = System.currentTimeMillis();

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

		Graph dGraph = new Graph(dictionary.size(), dictionary); 
		int index = 0;

		for (String word : dictionary) { 
			dGraph.setVertex(index, word); 
			dGraph.getVertex(index).setWord(word); 
			
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

		if (!dictionary.contains(word1) || !dictionary.contains(word2)) {
			System.out.println("Does not exist in dictionary");
			System.exit(0);
			
		}

		LinkedList<String> shortestPath = dGraph.wordladder(dGraph.getIndexAtWord(word1), dGraph.getIndexAtWord(word2));
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

		long end = System.currentTimeMillis();
		System.out.println("\nElapsed time: " + (end - start) + " milliseconds");
	}

}
