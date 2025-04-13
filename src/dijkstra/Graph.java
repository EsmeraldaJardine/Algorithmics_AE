package dijkstra;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
/**
 class to represent an undirected graph using adjacency lists
 */
public class Graph {

	private Vertex[] vertices; 
	private int numVertices = 0; 
	private HashMap<String, Integer> vertexWordMap = new HashMap<String, Integer>(); 


	public Graph(int n, ArrayList<String> dictionary) {
		numVertices = n;
		vertices = new Vertex[n];
		vertexWordMap = new HashMap<String, Integer>();
		int index = 0;
		for (String word : dictionary) {
			vertices[index] = new Vertex(index, ""); 
			vertexWordMap.put(word, index);
			index++;
		}

	}

	public int size() {
		return numVertices;
	}

	public Vertex getVertex(int i) {
		return vertices[i];
	}

	public void setVertex(int i, String w) {
		vertices[i] = new Vertex(i, w);
	}

	public int getIndexAtWord(String w) {
		return vertexWordMap.get(w); 
	}

	public LinkedList<String> dijkstraPath(int startIndex, int endIndex) {
		int[] dist = new int[numVertices]; 
		boolean[] visited = new boolean[numVertices];
		int[] pred = new int[numVertices];

		// Initialize all local variables before traversing the graph
		for (int i = 0; i < numVertices; i++) {
			dist[i] = Integer.MAX_VALUE; 
			visited[i] = false; 
			pred[i] = -1; 
		}
		dist[startIndex] = 0; 
		
		// Traverse the graph using Dijkstra's algorithm
		for (int i = 0; i < numVertices - 1; i++) { 
			int u = minDist(dist, visited); 
			visited[u] = true; 

			for (AdjListNode node : vertices[u].getAdjList()) { 
				int v = node.getVertexIndex(); 
				// Check if the vertex is not visited and if the edge weight is less than the current distance
				if (!visited[v] && dist[u] != Integer.MAX_VALUE && dist[u] + node.getWeight() < dist[v]) {
					dist[v] = dist[u] + node.getWeight(); 
					pred[v] = u; 
				}
			}
		}
		LinkedList<String> path = new LinkedList<>(); 
		int currentIndex = endIndex; 

		// Backtrack to find the path
		while (currentIndex != -1) {
			path.add(vertices[currentIndex].getWord()); 
			currentIndex = pred[currentIndex]; 	
		}
		return path; 		
	}

	// Find the vertex with the minimum distance value
	public int minDist(int[] dist, boolean[] visited) {
		int min = Integer.MAX_VALUE;
		int minIndex = -1;

		for (int vertex = 0; vertex < numVertices; vertex++) {

			if (!visited[vertex] && dist[vertex] <= min) {
				min = dist[vertex];
				minIndex = vertex;
			}
		}
		return minIndex; 
	}
    public int calculateEdgeWeight(char a, char b) {
		LinkedList<Character> alphabet = new LinkedList<>();

		for (char c = 'a'; c <= 'z'; c++) {
			alphabet.add(c);
		}
		int indexA = alphabet.indexOf(a);
		int indexB = alphabet.indexOf(b);
		return Math.abs(indexA - indexB);
	}
}



