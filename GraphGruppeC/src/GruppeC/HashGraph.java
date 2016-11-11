package GruppeC;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class HashGraph<V> implements Graph<V> {

	/**
	 * @author Jan Fischer
	 */

	
	HashMap<Node<V>, HashSet<Node<V>>>  edges;

	public HashGraph() {

		edges = new HashMap<>();

	}

	@Override
	public void populate(Collection<VertexData<V>> vertices, Iterator<Edge<Node<V>>> edges) {
		for (VertexData<V> v : vertices) {
			Node<V> node = new Node<V>(null,v.getLabel(), v.getV());
			this.edges.put(node, new HashSet<Node<V>>());
			
		}
		while (edges.hasNext()) {
			Edge<Node<V>> e = edges.next();
			this.edges.get();
			

		}

	}

	@Override
	public void populate(Collection<VertexData<V>> vertices, Collection<Edge<V>> edges) {
		this.populate(vertices, edges.iterator());

	}

	@Override
	public Iterator<Edge<V>> getAllEdges() {
		// Erstelle leeres Hashset
		HashSet<Edge<V>> edges = new HashSet<>();

		// Füge nun nach und nach die Kanten aus dem Graphen in das HashSet ein
		// und erstelle dabei Edge Klassen
		for (V v1 : this.edges.keySet()) {
			for (V v2 : this.edges.get(v1).keySet()) {
				edges.add(new Edge<V>(v1, v2, this.edges.get(v1).get(v2)));
			}
		}

		return edges.iterator();
	}

	// Alle Vertices sind im KeySet von der HashMap vorhanden.
	@Override
	public Iterator<V> getAllVertices() {
		return this.edges.keySet().iterator();
	}

	@Override
	public Iterator<Edge<V>> edgesOf(V v) {
		HashSet<Edge<V>> set = new HashSet<>();
		for (V v2 : this.edges.get(v).keySet()) {
			set.add(new Edge<V>(v, v2, this.edges.get(v).get(v2)));
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

	public class Node {
		private String name;
		

		private String label;
		private int status = 0;
		private V v;

		public Node(String name, String label, V v) {
			this.name = name;
			this.label = label;
			this.v = v;
		}
		
		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

		public String getName() {
			return name;
		}

		public String getLabel() {
			return label;
		}

		public V getV() {
			return v;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((v == null) ? 0 : v.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (v == null) {
				if (other.v != null)
					return false;
			} else if (!v.equals(other.v))
				return false;
			return true;
		}

		private HashGraph getOuterType() {
			return HashGraph.this;
		}
	}

}
