package GruppeC;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class HashGraph<V> implements Graph<V> {

	/**
	 * @author Jan Fischer
	 */
	
	
	HashMap<V, String> vertexLabel;
	HashMap<V, HashMap<V,String>> edges;

	public HashGraph() {
		
		vertexLabel = new HashMap<>();
		edges = new HashMap<>();
		
		
	}

	@Override
	public void populate(Collection<VertexData<V>> vertices, Iterator<Edge<V>> edges) {
		for (VertexData<V> v : vertices) {
			this.edges.put(v.getV(), new HashMap<V,String>());
			this.vertexLabel.put(v.getV(),v.getLabel());
			}
		while (edges.hasNext()) {
			Edge<V> e = edges.next();
			this.edges.get(e.getA()).put(e.getB(), e.getLabel());
			this.edges.get(e.getB()).put(e.getA(), e.getLabel());
			
			
		}

	}

	@Override
	public void populate(Collection<VertexData<V>> vertices, Collection<Edge<V>> edges) {
		this.populate(vertices, edges.iterator());

	}

	@Override
	public Iterator<Edge<V>> getAllEdges() {
		//Erstelle leeres Hashset
		HashSet<Edge<V>> edges = new HashSet<>();
		
		//Füge nun nach und nach die Kanten aus dem Graphen in das HashSet ein und erstelle dabei Edge Klassen
		for (V v1 : this.edges.keySet()) {
			for(V v2 : this.edges.get(v1).keySet()){
				edges.add(new Edge<V>(v1, v2, this.edges.get(v1).get(v2)));				
			}
		}
			
		
		return edges.iterator();
	}

	//Alle Vertices sind im KeySet von der HashMap vorhanden.
	@Override
	public Iterator<V> getAllVertices() {
		return this.edges.keySet().iterator();
	}

	
	
	@Override
	public Iterator<Edge<V>> edgesOf(V v) {
		HashSet<Edge<V>> set = new HashSet<>();
		for(V v2: this.edges.get(v).keySet()){
			set.add(new Edge<V>(v, v2,this.edges.get(v).get(v2)));
		}
		return set.iterator();
	}

	@Override
	public Edge<V> getEdge(V v, V u) {
		return new Edge<V>(v, u, this.edges.get(v).get(u));
	}

	@Override
	public boolean containsEdge(V v, V u) {
		return this.edges.get(v).containsKey(u);
	}

	@Override
	public boolean containsVertex(V v) {
		return this.edges.containsKey(v);
	}

	@Override
	public int getDegree(V v) {
		return this.edges.get(v).keySet().size();
	}

	@Override
	public String getEdgeLabel(V v, V u) {
		return this.edges.get(v).get(u);
	}

	@Override
	public String getVertexLabel(V v) {
		return this.vertexLabel.get(v);
		
	}

	

}
