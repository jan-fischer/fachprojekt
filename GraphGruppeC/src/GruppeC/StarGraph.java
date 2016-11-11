package GruppeC;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collection;

public class StarGraph implements Graph<Integer> {

	/**
	 * @author Nina, Martin, Franka
	 */
	
	private String[] vlabel;
	private int[] point;
	private int[] tail;
	private int[] head;
	private String[] elabel;
	private int[] rpoint;
	private int[] trace;

	// Constructor for empty graph
	public StarGraph() {
		vlabel = new String[0];
		point  = new int[0];
		tail   = new int[0];
		head   = new int[0];
		elabel = new String[0];
		rpoint = new int[0];
		trace  = new int[0];
	}



	// adds edge between two existing vertices
	private void addEdge(String label, int v1, int v2) {
		if (v1 > v2) // v1 has to be smaller than v2
		{
			int m = v1;
			v1 = v2;
			v2 = m;
		}		
		int position;
		if(tail.length==0){
			position=0;
		}
		else{	
			position = point[v1];
			if (position == 0 && tail[0] != v1) // if vertex doesn't have edges,
												// search for position to insert
			{
				position = searchPoint(v1);
			}
		}
		int[] tempTail = new int[tail.length + 1];
		int[] tempHead = new int[head.length + 1];
		String[] tempLabel = new String[elabel.length + 1];
		int[] tempTrace = new int[trace.length+1];

		int i = 0;
		while (i < position) // don't need to change the array there
		{
			tempTail[i] = tail[i];
			tempHead[i] = head[i];
			tempLabel[i] = elabel[i];
			i++;
		}
		tempTail[i] = v1; // adding the edge
		tempHead[i] = v2;
		tempLabel[i] = label;
		i++;
		while (i < tempTail.length) // other edges get shifted
		{
			tempTail[i] = tail[i - 1];
			tempHead[i] = head[i - 1];
			tempLabel[i] = elabel[i - 1];
			i++;

		}
		tail = tempTail;
		head = tempHead;
		elabel = tempLabel;
		trace=tempTrace;
		// update point, rpoint and trace
		fillpoint(); 
		filltrace();
		fillrpoint();
		//System.out.println("add Edge");
	}

	// searches position to insert edge
	private int searchPoint(int v) {
		for (int i = 0; i < tail.length; i++) {
			if (tail[i] > v) {
				return i;
			}
		}
		return tail.length;
	}
	
	// fills the pointarray
	public void fillpoint() {
		for (int i = 0; i < point.length; i++) {
			for (int j = 0; j < tail.length; j++) {
				if (tail[j] == i) {
					point[i] = j;
					break;
				}
			}
		}
	}

	// fills the tracearray
	public void filltrace() {
		int position=0;
		for(int i=0; i<rpoint.length;i++)
		{
			for(int j=0; j<head.length;j++)
			{
				if(head[j]==i)
				{
					trace[position]=j;
					position++;
				}
			}
		}
	}

	// fills the rpointarray
	public void fillrpoint() {
		for (int i = 0; i < rpoint.length; i++) {
			for (int j = 0; j < trace.length; j++) {
				if (head[trace[j]] == i) {
					rpoint[i] = j;
					break;
				}
			}
		}
	}

	@Override
	public Iterator<Edge<Integer>> getAllEdges() {
		ArrayList<Edge<Integer>> edges = new ArrayList<Edge<Integer>>();
		for (int i = 0; i < tail.length; i++) {
			edges.add(new Edge<Integer>(tail[i],head[i], elabel[i]));
		}
		Iterator<Edge<Integer>> it = edges.iterator();
		return it;
	}

	@Override
	public Iterator<Integer> getAllVertices() {
		ArrayList<Integer> vertices = new ArrayList<Integer>();
		for (int i = 0; i < vlabel.length; i++) {
			vertices.add(i);
		}
		Iterator<Integer> it = vertices.iterator();
		return it;
	}

	@Override
	public Iterator<Edge<Integer>> edgesOf(Integer v) { 
		ArrayList<Integer> neighbours = getAllNeighbours(v);
		ArrayList<Edge<Integer>> edges = new ArrayList<Edge<Integer>>();
		for (Integer u : neighbours) {
			if(containsEdge(u,v))
			{
				edges.add(getEdge(v, u));
			}
			else
			{
				System.out.println("Hier stimmt was nicht");
			}
		}
		Iterator<Edge<Integer>> it = edges.iterator();
		return it;
	}
	
	//returns all attached vertices
	public ArrayList<Integer> getAllNeighbours(int v) {
		ArrayList<Integer> neighbours = new ArrayList<Integer>();
		//v is in head
		for (int i = rpoint[v]; i < trace.length && head[trace[i]] == v;i++){
			neighbours.add(tail[i]);
		}
		
		//v is in tail
		for (int i = point[v]; (i < tail.length) && tail[i] == v; i++) {
			neighbours.add(head[i]);
		}
		
		//Sortieren?
		return neighbours;

	}

	@Override
	public Edge<Integer> getEdge(Integer v, Integer u) {
		if (v > u) // v has to be smaller than u
		{
			int m = v;
			v = u;
			u = m;
		}
		if (containsEdge(v, u)) {
			String label = "";
			for (int i = point[v]; i < tail.length && tail[i] == v; i++) {
				if (head[i] == u) {
					label = elabel[i];
				}
			}
			return new Edge<Integer>(v, u, label);
		} else {
			throw new RuntimeException("Kante existiert nicht");
		}
	}

	@Override
	public boolean containsEdge(Integer v1, Integer v2) {
		if (v1 > v2) //v1 has to be smaller than v2
		{
			int m = v1;
			v1 = v2;
			v2 = m;
		}
		for (int i = point[v1]; i < tail.length && tail[i] == v1; i++) {
			if (head[i] == v2) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean containsVertex(Integer v) {

		if (v >= 0 && v < vlabel.length) {
			return true;
		}
		return false;
	}

	@Override
	public int getDegree(Integer v) {
		int count = 0;
		//v is in head
		for (int i = rpoint[v]; (i < trace.length) && head[trace[i]] == v; i++){
			count++;
		}
		
		
		//v is in tail
		for (int i = point[v]; (i < tail.length) && tail[i] == v; i++) {
			count++;
		}
		return count;
	}

	@Override
	//Smaller vertex must be v1
	public String getEdgeLabel(Integer v1, Integer v2) {

		if (containsEdge(v1, v2)) {
			return getEdge(v1, v2).getLabel();
		}
		throw new RuntimeException("Kante existiert nicht");
	}

	@Override
	public String getVertexLabel(Integer v) {

		if (v >= 0 && v < vlabel.length) {
			return vlabel[v];
		} else {
			throw new RuntimeException("Knoten existiert nicht");
		}
	}

	//fills the graph
	
	@Override
	public void populate(Collection<VertexData<Integer>> vertices, Iterator<Edge<Integer>> edges) {
		vlabel = new String[vertices.size()];
		point = new int[vertices.size()];
		rpoint = new int[vertices.size()];

		for (VertexData<Integer> v : vertices) {
			vlabel[v.getV()] = v.getLabel();
		}
		while (edges.hasNext()) {
			Edge<Integer> e = edges.next();
			addEdge(e.getLabel(), e.getA(), e.getB());
		}

	}

	@Override
	public void populate(Collection<VertexData<Integer>> vertices, Collection<Edge<Integer>> edges) {
		populate(vertices, edges.iterator());

	}
	
//to delete
	
//	// adds Vertex to graph
//	public void addVertex(String label) {
//		String[] tempLabel = new String[vlabel.length + 1];
//		for (int i = 0; i < vlabel.length; i++) {
//			tempLabel[i] = vlabel[i];
//		}
//		tempLabel[tempLabel.length - 1] = label;
//		vlabel = tempLabel;
//		int[] tempPoint = new int[point.length + 1];
//		for (int i = 0; i < point.length; i++) {
//			tempPoint[i] = point[i];
//		}
//		point = tempPoint;
//	}

	// initialising graph
//	public StarGraph(int numberOfV, int numberOfE) {
//		vlabel = new String[numberOfV];
//		point = new int[numberOfV];
//		tail = new int[numberOfE];
//		head = new int[numberOfE];
//		elabel = new String[numberOfE];
//	}

	// returns true, if vertex could be inserted
//	public boolean fillVertices(String label) {
//		int i = 0;
//		while (i < vlabel.length) // search first empty space
//		{
//			if (vlabel[i] == null) {
//				vlabel[i] = label;
//				return true;
//			}
//			i++;
//		}
//		return false;
//	}

	// for adding the edges in sorted order
//	public boolean fillEdge(int v1, int v2, String label) {
//		int i = 0;
//		while (i < elabel.length) // search first empty space
//		{
//			if (elabel[i] == null) {
//				elabel[i] = label;
//				head[i] = v2;
//				tail[i] = v1;
//				return true;
//			}
//			i++;
//		}
//		return false;
//
//	}

}
