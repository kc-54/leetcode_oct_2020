package com.leet.leetcode_oct_2020;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Pattern_123 {
	
	
	public static void main (String [] args) {
		
		int[] nums = {1,4,0,-1,-2,-3,-1,-2};
		
		long startTime = System.nanoTime();
		System.out.println(find132pattern_sol2(nums));
		long stopTime = System.nanoTime();
		System.out.println(stopTime - startTime);
		startTime = System.nanoTime();
		System.out.println(find132pattern_sol1(nums));
		stopTime = System.nanoTime();
		System.out.println(stopTime - startTime);
	}
	
	//faster solution
	//Status : accepted
	//runtime : 3 ms, Memory usage : 39.1 MB
	public static boolean find132pattern_sol1(int[] nums) {

		int n = nums.length;
		int [] mins = new int[n];
		if(n<3) {
			return false;
		}
		
		mins[0] = nums[0];
		
		for(int i = 1; i < n; i++) {
			mins[i] = Math.min(mins[i-1], nums[i]);
		}
		
		Stack<Integer> s = new Stack<>();
		
		for(int i = n-1; i >= 0; i--) {
			if(nums[i] > mins[i]) {
				
				while(!s.isEmpty() && s.peek() <= mins[i]) {
					s.pop();
				}
				
				if(!s.isEmpty() && s.peek() < nums[i])return true;
				
				s.push(nums[i]);
			}
			
		}
		
		
		return false;
	}
	
	//slow solution
	//Status : accepted
	//runtime : 912 ms, Memory usage : 39.9 MB
	public static boolean find132pattern_sol2(int[] nums) {

		int n = nums.length;
		if(n<3) {
			return false;
		}
		int min = nums[0];
		int max = nums[0];
		
		//use set of int pair class to store the range for number 1 and number 3
		Set<IntPair> double_num = new HashSet<>();

		for(int i = 1; i < n; i++) {
			//if new min found, store previous number 1 and number 3
			if(nums[i] < min) {
				double_num.add(new IntPair(min,max));
				min = nums[i];
				max = nums[i];
			}//if new max found, update max
			else if(nums[i] > max) {
				max = nums[i];
			}
			//check if the current number is in current min and max / number 1 and number 3
			if(nums[i] > min && nums[i] < max) {
				return true;
			}
			
			//check if the current number in between any of the int pair
			for(IntPair pair : double_num) {
				if(pair.x < nums[i] && pair.y > nums[i]) {
					return true;
				}
			}
		
		}
		
		return false;
	}
	
	public static class IntPair{
		final int x;
		final int y;
		IntPair(int x, int y) {
			this.x = x;
			this.y = y;
		}
		@Override
		public boolean equals(Object obj) {
			IntPair pair = (IntPair)obj;
			return this.x == pair.x && this.y == pair.y;
		}
	}
	
}
