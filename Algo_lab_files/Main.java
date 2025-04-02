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
		Scanner in = new Scanner(reader);
		String line = in.nextLine();
        	Scanner lineScanner = new Scanner(line);
		int numVertices = lineScanner.nextInt();

        // insert code here to build the graph from the input file
        // create empty graph with correct number of vertices
        // then go through input line by line adding edges to the graph

		reader.close();

		// conduct the breadth-first search

		String outputFileName = args[1];
		FileWriter writer = new FileWriter(outputFileName);

		// insert code here to output the predecessor information

		writer.close();

	}
}
