package Algo_lab_files;
import java.io.*;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {

		if(args.length < 2 )
		{
			System.out.println("Proper usage is: java Main input.txt output.txt");
			System.exit(0);
		}

		String inputFileName = args[0];
		FileReader reader = new FileReader(inputFileName);
		Scanner inputScanner = new Scanner(reader);
		String line = inputScanner.nextLine(); // The nextLine() method reads the first line of the input file as a String
        Scanner lineScanner = new Scanner(line); //  creates a new scanner to process first line.

		int numVertices = lineScanner.nextInt(); 
        // insert code here to build the graph from the input file
        // create empty graph with correct number of vertices
		Graph g = new Graph(numVertices);
		// then go through input line by line adding edges to the graph
		int row = 0;
		while (inputScanner.hasNext()) {
			String edge = inputScanner.nextLine();
			Scanner edgeScanner = new Scanner(edge);

			int column = 0;
			while (edgeScanner.hasNextInt()) {
				int isVertex = edgeScanner.nextInt();
				if (isVertex == 1) {
					g.getVertex(row).addToAdjList(column);
				}
			column++;
			}
		row++;	
		}
		reader.close();

		// conduct the breadth-first search
		g.bfs();

		String outputFileName = args[1];
		FileWriter writer = new FileWriter(outputFileName);
		// insert code here to output the predecessor information
		for (int i = 0; i < numVertices; i++) {
			int predecessor = g.getVertex(i).getPredecessor();
			if (predecessor == -1){
				predecessor = i;
			}
			writer.write(predecessor + " ");
		}
		writer.close();

	}
}
