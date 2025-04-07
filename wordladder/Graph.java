import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

/**
 class to represent an undirected graph using adjacency lists
 */

public class Graph {

	private Vertex[] vertices; // the (array of) vertices
	private int numVertices = 0; // number of vertices
	private HashMap<String, Integer> vertexWordMap = new HashMap<String, Integer>(); // map of words to vertex indices

	// possibly other fields representing properties of the graph

	/**
	 creates a new instance of Graph with n vertices
	*/
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
	 pseudocode version
	 */


	public void bfs() {
		for (Vertex v : vertices){
			v.setVisited(false); 
		}
  		// set up an initially empty queue of visited but unprocessed vertices;
		LinkedList<Vertex> queue = new LinkedList<Vertex>();
		
		// Iterate over all vertices in the graph
		for (Vertex v : vertices) {
		// if not visited, set it to visited and add it to the queue
			if (!v.getVisited()) {
				v.setVisited(true);
				v.setPredecessor(-1); // Set predecessor to -1 (no predecessor for root)
				queue.add(v);

				// process the queue
				while (!queue.isEmpty()) {
					Vertex u = queue.remove(); // remove the first vertex from queue
					LinkedList<AdjListNode> adjList = u.getAdjList(); // get the adjacency list of that vertex

					//iterave over all adjacent vertices (casting the recursive net)
					for (AdjListNode node : adjList){
						Vertex w = vertices[node.getVertexIndex()];
						if (!w.getVisited()) {
							w.setVisited(true);
							w.setPredecessor(u.getIndex());
							queue.add(w);
						}
					}	
				}
			}
		}
	}

}
