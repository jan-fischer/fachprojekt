package GruppeC;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;


public class BFS<V>{
	Graph<V> g;
	HashSet<V> visitedNodes= new HashSet<>();
	
	public BFS(Graph<V> g) {
		this.g = g;
	}
	public HashSet<V> runBFS(V rootNode)
	{
		//System.out.println("\n\n-------Running BFS----------\n");
		//System.out.println("Root Queued: "+(Integer)rootNode );
		Queue queue = new LinkedList();
		
		queue.add(rootNode);
		
		visitedNodes.add(rootNode);
		//System.out.println("Visited: "+(Integer)rootNode );
		
		int WhileControl=0;
		
		while(!queue.isEmpty() && WhileControl <50000000) {
			WhileControl++;		
			V node = (V)queue.remove();
			//System.out.println("De-Queued: "+(Integer)node );
			
			ArrayList<V> unVistedChildNodes= getUnvisitedChildNodes(node);
			
			if (!unVistedChildNodes.isEmpty())
			{
				for(V child:unVistedChildNodes)
				{
					visitedNodes.add(child);
					//System.out.println("Visited: "+(Integer)child );
					queue.add(child);								
					//System.out.println("Queued: "+(Integer)child );
				}
			}
		}
		return visitedNodes;
	}
	private ArrayList<V> getUnvisitedChildNodes(V node ){
		ArrayList<V> childNodes= new ArrayList<>();
		Iterator<Edge<V>> edges = g.edgesOf(node);
		while(edges.hasNext()){
			Edge<V> edge = edges.next();
			V otherNode =edge.getB();
			if(!visitedNodes.contains(otherNode)){ 
				childNodes.add(otherNode);
				//System.out.println("Child Added: "+(Integer)otherNode );
			}
		}
		return childNodes;
	}
}
