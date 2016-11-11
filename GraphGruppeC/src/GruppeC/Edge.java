package GruppeC;

/**
 * Hilfsklasse zum Auslesen von Graphinhalten. Stellt eine Kante dar. Beinhaltet
 * beide Knoten, sowie das Label der Kante.
 * 
 * @author Richard, Andi, Jan, Pandi, Nina
 *
 * @param <V>
 */
public class Edge<V> {
	private V a;
	private V b;
	private String label;

	/**
	 * 
	 */
	public Edge() {

	}

	/**
	 * 
	 */
	public Edge(V a, V b) {
		setA(a);
		setB(b);
	}

	/**
	 * 
	 */
	public Edge(V a, V b, String label) {
		setA(a);
		setB(b);
		setLabel(label);
	}

	/**
	 * 
	 */
	public void setA(V a) {
		this.a = a;
	}

	/**
	 * 
	 */
	public void setB(V b) {
		this.b = b;
	}

	/**
	 * 
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * 
	 */
	public V getA() {
		return a;
	}

	/**
	 * 
	 */
	public V getB() {
		return b;
	}

	/**
	 * 
	 */
	public String getLabel() {
		return label;
	}

}