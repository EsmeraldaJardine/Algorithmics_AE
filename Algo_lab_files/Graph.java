import java.util.LinkedList;

/**
 class to represent an undirected graph using adjacency lists
 */

public class Graph {

	private Vertex[] vertices; // the (array of) vertices
	private int numVertices = 0; // number of vertices

	// possibly other fields representing properties of the graph

	/**
	 creates a new instance of Graph with n vertices
	*/
	public Graph(int n) {
		numVertices = n;
		vertices = new Vertex[n];
		for (int i = 0; i < n; i++)
			vertices[i] = new Vertex(i);
	}

	public int size() {
		return numVertices;
	}

	public Vertex getVertex(int i) {
		return vertices[i];
	}

	public void setVertex(int i) {
		vertices[i] = new Vertex(i);
	}

	/**
	 visit vertex v, with predecessor index p,
	 during a depth first traversal of the graph
	*/
	private void Visit(Vertex v, int p) {
		v.setVisited(true);
		v.setPredecessor(p);
		LinkedList<AdjListNode> L = v.getAdjList();
		for (AdjListNode node : L) {
			int n = node.getVertexIndex();
			if (!vertices[n].getVisited()) {
				Visit(vertices[n], v.getIndex());
			}
		}
	}

	/**
     carry out a depth first search/traversal of the graph
	*/
	public void dfs() {
		for (Vertex v : vertices)
			v.setVisited(false);
		for (Vertex v : vertices)
			if (!v.getVisited())
				Visit(v, -1);
	}

	/**
	 carry out a breadth first search/traversal of the graph
	 pseudocode version
	 */


	public void bfs() {
		// assign each vertex to be unvisited;
  		// set up an initially empty queue of visited but unprocessed vertices;
  		// for each vertex v {
    	// 	if v is not visited {
      	// 		assign v to be visited;
      	// 		assign the predecessor of v;
      	// 		add v to the queue;
      	// 		while the queue is not empty {
        // 			remove vertex u from the queue;
        // 			for each vertex w in the adjacency list of u {
        // 				if w is unvisited {
        //    					assign w to be visited;
        //    					assign the predecessor of w;
        //    					add w to the queue;
        //  				}
       	// 			}
       	// 		}
    	// 	}
    	// }
	}


}
