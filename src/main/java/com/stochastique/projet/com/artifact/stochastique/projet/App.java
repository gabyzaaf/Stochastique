package com.stochastique.projet.com.artifact.stochastique.projet;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	
    	DataEntry dataEntry = new DataEntry(0.7, 0.3);
		Node root = new Node("A",dataEntry);
		
		DataEntry dataNextForB = new DataEntry(0.7, 0.3, 0.4,0.6);
		root.next(root,new Node("B",dataNextForB));
		
		DataEntry dataNextForC = new DataEntry(0.6, 0.4, 0.3,0.7);
		root.next(root, new Node("C",dataNextForC));
		
		NodeEngine engine = new NodeEngine(root);
		
		Node lastNodeAdded = engine.ObtainTheLastRightNode();
		
		DataEntry dataNextForD = new DataEntry(0.25, 0.75, 0.7,0.3);
		
		lastNodeAdded.next(lastNodeAdded, new Node("D",dataNextForD));
		
		engine.run();
		
		engine.Display();
		
		
    }
}
