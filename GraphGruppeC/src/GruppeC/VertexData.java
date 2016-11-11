package GruppeC;

/**
 * Hilfsklasse zum Befüllen eines Graphen. Beinhaltet einen Knoten mit seinem
 * Label.
 * 
 * @author Richard, Andi, Jan, Pandi, Nina
 *
 * @param <V>
 */
public class VertexData<V> {
	private V v;
	private String label;

	/**
	 * 
	 */
	public VertexData() {

	}

	/**
	 * 
	 */
	public VertexData(V v) {
		setV(v);
	}

	/**
	 * 
	 */
	public VertexData(V v, String label) {
		setV(v);
		setLabel(label);
	}

	/**
	 * 
	 */
	public void setV(V v) {
		this.v = v;
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
	public V getV() {
		return v;
	}

	/**
	 * 
	 */
	public String getLabel() {
		return label;
	}
}