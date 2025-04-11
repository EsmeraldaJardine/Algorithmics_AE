package dijkstra;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
/**
 class to represent an undirected graph using adjacency lists
 */
public class Graph {

	private Vertex[] vertices; // the (array of) vertices
	private int numVertices = 0; // number of vertices
	private HashMap<String, Integer> vertexWordMap = new HashMap<String, Integer>(); // map of words to vertex indices


	public Graph(int n, ArrayList<String> dictionary) {
		numVertices = n;
		vertices = new Vertex[n];
		vertexWordMap = new HashMap<String, Integer>();
		int index = 0;
		for (String word : dictionary) {
			vertices[index] = new Vertex(index, ""); // don't have string upon initialization
			vertexWordMap.put(word, index); // map the word to the vertex index
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

	/**
	 visit vertex v, with predecessor index p,
	 during a depth first traversal of the graph
	*/
	private void Visit(Vertex v, int p) {
		v.setVisited(true);
		v.setPredecessor(p);
		LinkedList<AdjListNode> L = v.getAdjList(); // L is the list of neighbors of v
		for (AdjListNode node : L) {  // each node represents a neighbor of v
			int n = node.getVertexIndex(); // n is the index of a neighbor of v
			if (!vertices[n].getVisited()) { 
				Visit(vertices[n], v.getIndex()); // recursively visit if neighbor has not been visited
			}
		}
	}

	/**
     carry out a depth first search/traversal of the graph
	*/
	public void dfs() {
		for (Vertex v : vertices)
			v.setVisited(false); // set all vertices to unvisited
		for (Vertex v : vertices)
			if (!v.getVisited())
				Visit(v, -1);
				// -1 is the predecessor of the root vertex
				/*
				For each neighbor of the current vertex, 
				the Visit method is called recursively, 
				passing the current vertex's index (v.getIndex()) as the predecessor (p) 
				 */

	}

	/**
	 carry out a breadth first search/traversal of the graph
	 */


	public LinkedList<String> dijkstraPath(int startIndex, int endIndex) {
		int[] dist = new int[numVertices]; 
		boolean[] visited = new boolean[numVertices];
		int[] pred = new int[numVertices]; // predecessor array
		for (int i = 0; i < numVertices; i++) {
			dist[i] = Integer.MAX_VALUE; // set all distances to infinity
			visited[i] = false; // set all vertices to unvisited
			pred[i] = -1; // set all predecessors to -1
		}
		dist[startIndex] = 0; 
		
		for (int i = 0; i < numVertices - 1; i++) { 
			int u = minDist(dist, visited); // get the vertex with the minimum distance
			visited[u] = true; 

			for (AdjListNode node : vertices[u].getAdjList()) { // for each neighbor of u
				int v = node.getVertexIndex(); // get the index of the neighbor
				if (!visited[v] && dist[u] != Integer.MAX_VALUE && dist[u] + node.getWeight() < dist[v]) {
					dist[v] = dist[u] + node.getWeight(); // update the distance to the neighbor
					pred[v] = u; // update the predecessor of the neighbor
				}
			}


		}
		LinkedList<String> path = new LinkedList<>(); 
		int currentIndex = endIndex; 
		while (currentIndex != -1) { // while there are still predecessors
			path.add(vertices[currentIndex].getWord()); // add the word to the path
			currentIndex = pred[currentIndex]; // move to the predecessor
			
		}
		return path; // return the path

		
		
	}

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
		return indexA - indexB;
	}
}



