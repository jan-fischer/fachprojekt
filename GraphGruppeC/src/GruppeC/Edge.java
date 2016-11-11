package GruppeC;

import java.util.HashSet;

import GruppeC.HashGraph.Node;

/**
 * Hilfsklasse zum Auslesen von Graphinhalten. Stellt eine Kante dar. Beinhaltet
 * beide Knoten, sowie das Label der Kante.
 * 
 * @author Richard, Andi, Jan, Pandi, Nina
 *
 * @param <V>
 */
public class Edge<V> {
	HashSet<Node> nodes;
	private String label;

	/**
	 * 
	 */
	public Edge() {

	}

	/**
	 * 
	 */
	public Edge(Node a, Node b) {
		nodes.add(a);
		nodes.add(b);
		
	}

	/**
	 * 
	 */
	public Edge(Node a, Node b, String label) {
		nodes.add(a);
		nodes.add(b);
		setLabel(label);
	}

	/**
	 * 
	 */
	

	/**
	 * 
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * 
	 */
	
	/**
	 * 
	 */
	public String getLabel() {
		return label;
	}

}