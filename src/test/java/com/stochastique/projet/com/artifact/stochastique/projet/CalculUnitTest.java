package com.stochastique.projet.com.artifact.stochastique.projet;

import junit.framework.Assert;

import org.junit.Test;

public class CalculUnitTest {

	@SuppressWarnings("deprecation")
	@Test
	public void should_create_validate_the_dataEntry_for_each_case(){
		DataEntry dataEntry = new DataEntry(0.7, 0.3, 0.4,0.6);
		Assert.assertEquals(0.7, dataEntry.getLastNodeFalse().getFirst());
		Assert.assertEquals(0.3, dataEntry.getLastNodeFalse().getSecond());
		Assert.assertEquals(0.4, dataEntry.getLastNodeTrue().getFirst());
		Assert.assertEquals(0.6, dataEntry.getLastNodeTrue().getSecond());
		
		Assert.assertEquals(0.7, dataEntry.getCurrentNodeFalse().getFirst());
		Assert.assertEquals(0.4, dataEntry.getCurrentNodeFalse().getSecond());
		Assert.assertEquals(0.3, dataEntry.getCurrentNodeTrue().getFirst());
		Assert.assertEquals(0.6, dataEntry.getCurrentNodeTrue().getSecond());
	}
	

	
	@Test
	public void should_calcule_the_probabilities_for_the_current_node_is_true(){
		DataEntry dataNext = new DataEntry(0.7, 0.3, 0.4,0.6);
		Probability proba = new Probability(dataNext);
		Assert.assertEquals(0.6, proba.ObtainTheCurrentNodeValueIsTrue());
		Assert.assertEquals(0.3, proba.ObtainTheCurrentNodeValueIsFalse());
	}
	
	@Test
	public void should_calcule_the_probabilities_for_the_current_node_is_false(){
		DataEntry dataNext = new DataEntry(0.7, 0.3, 0.4,0.6);
		Probability proba = new Probability(dataNext);
		Assert.assertEquals(0.4, proba.ObtainTheFalseCurrentNodeValue());
		Assert.assertEquals(0.7, proba.ObtainTheFalseCurrentValues());
	}
	
	@Test
	public void should_obtain_the_last_node_values_True_or_false(){
		DataEntry dataEntry = new DataEntry(0.7, 0.3);
		Node root = new Node("A",dataEntry);
		
		DataEntry dataNext = new DataEntry(0.7, 0.3, 0.4,0.6);
		root.next(root,new Node("B",dataNext));
		
		NodeEngine engine = new NodeEngine(root);
		Assert.assertEquals(0.7, NodeEngine.Root.getDataEntry().getLastNodeFalse().getFirst());
		Assert.assertEquals(0.3, NodeEngine.Root.getDataEntry().getLastNodeFalse().getSecond());
	}
	
	
	
	@Test
	public void should_verify_The_result_for_the_node_b(){
		DataEntry dataEntry = new DataEntry(0.7, 0.3);
		Node root = new Node("A",dataEntry);
		
		DataEntry dataNext = new DataEntry(0.7, 0.3, 0.4,0.6);
		root.next(root,new Node("B",dataNext));
		
		NodeEngine engine = new NodeEngine(root);
		engine.run();
		ResultDatas result = engine.ObtainResult(0);
		Assert.assertEquals(0.39,result.getTrueResult());
		Assert.assertEquals(0.61,result.getFalseResult());
		
	}
	
	@Test
	public void should_obtain_the_last_node_added(){
		DataEntry dataEntry = new DataEntry(0.7, 0.3);
		Node root = new Node("A",dataEntry);
		
		
		DataEntry dataNext = new DataEntry(0.7, 0.3, 0.4,0.6);
		root.next(root,new Node("B",dataNext));
		
		DataEntry dataNextForC = new DataEntry(0.6, 0.4, 0.3,0.7);
		Node lastNodeAdded = new Node("C",dataNextForC);
		root.next(root,lastNodeAdded);
		
		NodeEngine engine = new NodeEngine(root);
		
		Assert.assertEquals(lastNodeAdded,engine.ObtainTheLastRightNode());
	}
	
	@Test
	public void should_initialize_all_the_lambda_to_zero_for_true_and_one_for_false(){
		DataEntry dataEntry = new DataEntry(0.7, 0.3);
		Node root = new Node("A",dataEntry);
		
		Assert.assertEquals(root.getLamdaTrue(),0.0);
		Assert.assertEquals(root.getLamdaFalse(),1.0);
	}
	
	@Test
	public void should_calcul_the_message_propagation_node_D_to_C_for_true(){
		DataEntry dataNext = new DataEntry(0.25, 0.7, 0.75,0.3);
		Node node = new Node("D",dataNext);
		NodeEngine engine = new NodeEngine(null);
		Assert.assertEquals(0.7,engine.MakeCalculForTruePropagation(node,dataNext));
	}
	
	@Test
	public void should_select_the_values(){
		DataEntry dataNext = new DataEntry(0.25, 0.7, 0.75,0.3);
		Probability proba = new Probability(dataNext);
		Assert.assertEquals(0.75,proba.ObtainTheFalseCurrentNodeValue());
		Assert.assertEquals(0.25,proba.ObtainTheFalseCurrentValues());
	}
	
	
	@Test
	public void should_calcul_the_message_propagation_node_D_to_C_for_false(){
		DataEntry dataNext = new DataEntry(0.25, 0.7, 0.75,0.3);
		Node node = new Node("D",dataNext);
		NodeEngine engine = new NodeEngine(null);
		Assert.assertEquals(0.25,engine.MakeCalculForFalsePropagation(node,dataNext));
	}
	
	@Test
	public void should_found_the_D_node(){
		DataEntry dataEntry = new DataEntry(0.7, 0.3);
		Node root = new Node("A",dataEntry);
		DataEntry dataNextForB = new DataEntry(0.7, 0.3, 0.4,0.6);
		root.next(root,new Node("B",dataNextForB));
		DataEntry dataNextForC = new DataEntry(0.6, 0.4, 0.3,0.7);
		root.next(root, new Node("C",dataNextForC));
		NodeEngine engine = new NodeEngine(root);
		Node lastNodeAdded = engine.ObtainTheLastRightNode();
		DataEntry dataNextForD = new DataEntry(0.25, 0.75, 0.7,0.3);
		Node nodeD = new Node("D",dataNextForD);
		lastNodeAdded.next(lastNodeAdded, nodeD);
		
		Assert.assertEquals(engine.ObtainSpecificNode("D"),nodeD);
	}
	
	@Test
	public void should_verify_the_previous_node_is_not_verify(){
		DataEntry dataEntry = new DataEntry(0.7, 0.3);
		Node root = new Node("A",dataEntry);
		DataEntry dataNextForB = new DataEntry(0.7, 0.3, 0.4,0.6);
		root.next(root,new Node("B",dataNextForB));
		DataEntry dataNextForC = new DataEntry(0.6, 0.4, 0.3,0.7);
		root.next(root, new Node("C",dataNextForC));
		NodeEngine engine = new NodeEngine(root);
		Node lastNodeAdded = engine.ObtainTheLastRightNode();
		DataEntry dataNextForD = new DataEntry(0.25, 0.75, 0.7,0.3);
		Node nodeD = new Node("D",dataNextForD);
		lastNodeAdded.next(lastNodeAdded, nodeD);
		
		Assert.assertEquals(false,nodeD.previousNodeAlreadyChecked());
		
	}
	
	
	
}
