package GruppeC;
import java.util.Collection;
import java.util.Iterator;

/**
 * Ein ungerichteter Graph
 * 
 * @author Richard, Andi, Jan, Pandi, Nina
 *
 * @param <V>
 */
public interface Graph<V> {
	/**
	 * Zum einmaligen Bef�llen eines sehr gro�en Graphen.
	 */
	void populate(Collection<VertexData<V>> vertices, Iterator<Edge<V>> edges);
	
	/**
	 * Zum einmaligen Bef�llen eines Graphen.
	 */
	void populate(Collection<VertexData<V>> vertices, Collection<Edge<V>> edges);

	/**
	 * Liefert die Menge aller Kanten.
	 */
	Iterator<Edge<V>> getAllEdges();

	/**
	 * Liefert die Menge aller Knoten.
	 */
	Iterator<V> getAllVertices();
	
	/**
	 * Liefert die Menge aller anliegenden Kanten des Knoten v.
	 */
	Iterator<Edge<V>> edgesOf(V v);

	
	/**
	 * Liefert die Kante zwischen v und u, bzw. null sonst
	 */
	Edge<V> getEdge(V v, V u);

	
	/**
	 * Pr�ft, ob eine Kante zwischen v und u existiert
	 */
	boolean containsEdge(V v, V u);

	/**
	 * Pr�ft, ob Knoten v im Graphen enthalten ist.
	 */
	boolean containsVertex(V v);
	
	/**
	 * Liefert den Grad des Knotens v
	 */
	int getDegree(V v);

	/**
	 * Label der Kante zwischen v und u
	 */
	String getEdgeLabel(V v, V u);

	/**
	 * Label des Knotens v
	 */
	String getVertexLabel(V v);
}