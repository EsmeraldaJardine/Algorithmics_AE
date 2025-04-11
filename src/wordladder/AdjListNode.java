package wordladder;
/**
 class to represent an entry in the adjacency list of a vertex
 in a graph
 */
public class AdjListNode {

	private int vertexIndex;
	private String word;
	// could be other fields, for example representing
	// properties of the edge - weight, capacity, ...

    /* creates a new instance */
	public AdjListNode(int n, String w){
		vertexIndex = n;
		word = w;
	}

	public int getVertexIndex(){ 
		return vertexIndex;
	}

	public void setVertexIndex(int n){
		vertexIndex = n;
	}

	public String getWord(){
		return word;
	}
	public void setWord(String w){
		word = w;
	}
}
