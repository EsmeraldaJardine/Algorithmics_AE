package wordladder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

/**
 class to represent an undirected graph using adjacency lists
 */

public class Graph {

	private Vertex[] vertices; 
	private int numVertices = 0; 
	private HashMap<String, Integer> vertexWordMap = new HashMap<String, Integer>(); 


	/**
	 creates a new instance of Graph with n vertices
	*/
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

	public LinkedList<String> wordladder(int startIndex, int endIndex) {
		for (Vertex v : vertices){
			v.setVisited(false); 
			v.setPredecessor(-1); 	
		}
  		
		LinkedList<Vertex> queue = new LinkedList<>();
		LinkedList<String> path = new LinkedList<>(); 
		vertices[startIndex].setVisited(true); 
		queue.add(vertices[startIndex]); 

		// Visit the vertices in the queue until it is empty
		while (!queue.isEmpty()) {
			Vertex u = queue.remove(); 

			if (u.getIndex() == endIndex) { 
				int currentIndex = endIndex;

				while (currentIndex != -1) {
					path.add(vertices[currentIndex].getWord()); 
					currentIndex = vertices[currentIndex].getPredecessor();  
				}
				return path; 
			}
			// For each adjacent vertex of u, check if it has been visited
			// If not, mark it as visited and add it to the queue
			for (AdjListNode adjNode : u.getAdjList()){
				Vertex adjVertex = vertices[adjNode.getVertexIndex()];
				
				if (!adjVertex.getVisited()) {
					adjVertex.setVisited(true);
					adjVertex.setPredecessor(u.getIndex());
					queue.add(adjVertex);;
				}
			}
		}
		return path;
	}

}



