package Algo_lab_files;
public class AdjListNode {

	private int vertexIndex;
	// could be other fields, for example representing
	// properties of the edge - weight, capacity, ...

    /* creates a new instance */
	public AdjListNode(int n){
		vertexIndex = n;
	}

	public int getVertexIndex(){ 
		return vertexIndex;
	}

	public void setVertexIndex(int n){
		vertexIndex = n;
	}
}
