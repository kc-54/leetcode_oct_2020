package com.leet.leetcode_oct_2020;

import java.util.LinkedList;
import java.util.Queue;

public class Minimum_Depth_of_Binary_Tree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeNode root = new TreeNode(2);
		root.right = new TreeNode(3);
		root.right.right = new TreeNode(4);
		System.out.println(minDepth_DFS(root));
		System.out.println(minDepth_BFS(root));
	
	}
	
	//BFS is fast because once we find the solution, we no need to calculate other child node
	//result : 
	//runtime : 0 ms, Memory usage : 58.7MB
	public static int minDepth_BFS(TreeNode root) {
		
		int rs = 0;
		if(root == null) {
			return 0;
		}

		if(root.left == null && root.right == null) {
			return 1;
		}
		
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);
		
		while(!queue.isEmpty()) {

			int size = queue.size();
			for(int i = 0; i < size; i++) {
				TreeNode cur = queue.poll();
				if(cur.left == null && cur.right == null) {
					return rs+1;
				}
				if(cur.left != null) {
					queue.add(cur.left);
				}
				if(cur.right != null) {
					queue.add(cur.right);
				}
			}
			rs++;
		}
		
		
		return rs;
	}
	
	//DFS is slow because we need to check down for each child node
	//result : 
	//runtime : 1ms, Memory usage : 59.7MB
	public static int minDepth_DFS(TreeNode root) {
		
		if(root == null) {
			return 0;
		}

		if(root.left == null && root.right == null) {
			return 1;
		}
		
		//initiate with largest impossible to result for each child node
		int rs_left = 10000;
		int rs_right = 10000;
		
		//prune, no need to check others if one of the node is already leaf
		if(root.left != null && root.left.left == null && root.left.right == null) {
			return 2;
		}
		if(root.right != null && root.right.left == null && root.right.right == null) {
			return 2;
		}
		
		//if there is no leaf, go check deeper until found.
		//recursive method, not good
		if(root.left != null) {
			rs_left = minDepth_DFS(root.left) + 1;
		}
		if(root.right != null) {
			rs_right = minDepth_DFS(root.right) + 1;
		}
		
		//only take the shortest
		int rs = rs_left<rs_right?rs_left:rs_right;
		
		return rs;
	
    }
	
	
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode() {
		}

		TreeNode(int val) {
			this.val = val;
		}

		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}
}
