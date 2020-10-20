package com.leet.leetcode_oct_2020;

import java.util.ArrayList;
import java.util.List;

public class Clone_Graph {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Node n1 = new Node(1);
		Node n2 = new Node(2);
		Node n3 = new Node(3);
		Node n4 = new Node(4);
		Node n = new Node(1);
		
        List<Node> n_neighbors1 = new ArrayList<Node>();
        List<Node> n_neighbors2 = new ArrayList<Node>();
        List<Node> n_neighbors3 = new ArrayList<Node>();
        List<Node> n_neighbors4 = new ArrayList<Node>();
        n_neighbors1.add(n2);
        n_neighbors1.add(n4);
        n_neighbors2.add(n1);
        n_neighbors2.add(n3);
        n_neighbors3.add(n2);
        n_neighbors3.add(n4);
        n_neighbors4.add(n1);
        n_neighbors4.add(n3);
        
        n1.neighbors = n_neighbors1;
        n2.neighbors = n_neighbors2;
        n3.neighbors = n_neighbors3;
        n4.neighbors = n_neighbors4;
        boolean [] print = new boolean[5];
        
        //print the node and its neighbours
        printNode(n1, print);
        System.out.println();
        
        //print the clone node and its neighbours
        printNode(cloneGraph(n1), new boolean[5]);
		
	}
	
	
	public static Node cloneGraph(Node node) {
        
		//nodes to keep track of created nodes
        Node[] nodes = new Node[100];

		//first Node
        Node n = new Node(1);
        nodes[0] = n;

		updateArray(n, node, nodes);
        
        return n;
    }
	
	//helper function to update the newly created node, using recursion
	public static void updateArray(Node copy_node, Node ori_node, Node[] nodes) {
		
		
        List<Node> n_neighbors = new ArrayList<Node>();
        
		for(Node neigh : ori_node.neighbors) {
			int val = neigh.val;
			
        	//if nodes havent created, create the node
			if(nodes[val-1] == null) {
        		Node new_node = new Node(val);
        		nodes[val-1] = new_node;
        		n_neighbors.add(new_node);
        		//update the new created nodes, using the original node
        		updateArray(new_node, neigh, nodes);
			}else {
        		//if nodes existed, add to the list of neighbors
				n_neighbors.add(nodes[val-1]);
			}
		}
		copy_node.neighbors = n_neighbors;
	}
	
	//helper function to debug, print all nodes and its neighbours
	public static void printNode(Node n, boolean [] print) {
		
		int val = n.val;
		print[val-1] = true;
		System.out.println("node val = " + val);

		System.out.print("neighbours = ");
		for(Node neigh : n.neighbors) {
			System.out.print(neigh.val + " ");
		}
		System.out.println();

		for(Node neigh : n.neighbors) {
			if(!print[neigh.val-1]) {
				printNode(neigh,print);
			}
		}
		
	}
	
	public static class Node {
	    public int val;
	    public List<Node> neighbors;
	    
	    public Node() {
	        val = 0;
	        neighbors = new ArrayList<Node>();
	    }
	    
	    public Node(int _val) {
	        val = _val;
	        neighbors = new ArrayList<Node>();
	    }
	    
	    public Node(int _val, ArrayList<Node> _neighbors) {
	        val = _val;
	        neighbors = _neighbors;
	    }
	}

}
