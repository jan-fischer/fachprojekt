package GruppeC;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

/**
 * Klasse um aus Textdateien Graphen einzulesen
 * 
 * @author Moritz
 *
 * @param <V>
 */

class GraphBuilder{
	/**
	 *  Standardkonstruktor für den Graphbuilder, initialisiert den Übergebenen Pfad als Arrays
	 */
	private ArrayList<Edge<Integer>> edges;
	private ArrayList<VertexData<Integer>> verticles;
	private ArrayList<Graph<Integer>> graphs;
	
	
	public GraphBuilder(String path, Graph<Integer> graph){
		File file = new File(path);
		edges = new ArrayList<Edge<Integer>>();
		verticles = new ArrayList<VertexData<Integer>>();
		graphs = new ArrayList<Graph<Integer>>();
		BufferedReader input= null;
		try {
			input = new BufferedReader(new FileReader(file));
			String line;
			Boolean first = true;
			String klasse = "";
			int graphNumber=0;
			while((line = input.readLine())!=null){
				String[] token = line.split(" ");
				if (token[0].trim().equals("t")){
					if(first)
						first=false;
					else {
						Graph<Integer> newGraph = graph.getClass().newInstance();
						//Graph<Integer> newGraph = new Graph<Integer>();
						newGraph.populate(verticles, edges);
						graphs.add(newGraph);
						edges.clear();
						verticles.clear();
					}
						
					
				}
				if(token[0].trim().equals("c")){
					
				}
				if(token[0].trim().equals("v")){
					int number = Integer.parseInt(cut(token[1]));
					String label;
					try{
						label = (cut(token[2]));
					}catch(ArrayIndexOutOfBoundsException e){
						label = "leer";
					}
					if(label.equals("leer"))
						verticles.add(new VertexData<Integer>(number));
					else
						verticles.add(new VertexData<Integer>(number, label.toString()));
						
				}
				if(token[0].trim().equals("e")){
					int v1 = Integer.parseInt(cut(token[1]));
					int v2 = Integer.parseInt(cut(token[2]));
					String label;
					try{
						label = (cut(token[3]));
					}catch(ArrayIndexOutOfBoundsException e){
						label = "leer";
					}
					if(label.equals("leer"))
						edges.add(new Edge<Integer>(v1,v2));
					else
						edges.add(new Edge<Integer>(v1,v2,label));
				}
			}
			Graph<Integer> newGraph = graph.getClass().newInstance();
			newGraph.populate(verticles, edges);
			graphs.add(newGraph);
			verticles.clear();
			edges.clear();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			if(input!=null){
				try {
					input.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	private String cut(String s){
		/*
		String tmp = s.substring(1, s.length()-1);
		return tmp;
		*/
		return s;
	}
	public ArrayList<Graph<Integer>> getGraphs(){
		return graphs;
	}
} 