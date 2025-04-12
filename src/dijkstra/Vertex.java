package dijkstra;
import java.util.LinkedList;
/**
 class to represent a vertex in a graph
*/
public class Vertex {

    private LinkedList<AdjListNode> adjList ; 
    private int index; 
    private String word; 
    boolean visited;
    int predecessor; 


    public Vertex(int n, String w){
    	adjList = new LinkedList<AdjListNode>();
    	index = n;
    	word = w;
    	visited = false;
    } 

    public LinkedList<AdjListNode> getAdjList(){
        return adjList;
    }

    public int getIndex(){
    	return index;
    }

    public void setIndex(int n){
    	index = n;
    }

    public boolean getVisited(){
    	return visited;
    }

    public void setVisited(boolean b){
    	visited = b;
    }

    public int getPredecessor(){
    	return predecessor;
    }

    public void setPredecessor(int n){
    	predecessor = n;
    }

    public String getWord(){
    	return word;
    }

    public void setWord(String w){
    	word = w;
    }

    public void addToAdjList(int n, String w){
        adjList.addLast(new AdjListNode(n, w));
    }

    public int vertexDegree(){
        return adjList.size();
    }
}
