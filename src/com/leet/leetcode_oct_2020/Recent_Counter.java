package com.leet.leetcode_oct_2020;

import java.util.LinkedList;

public class Recent_Counter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RecentCounter recentCounter = new RecentCounter();
		System.out.println(recentCounter.ping(1));     // requests = [1], range is [-2999,1], return 1
		System.out.println(recentCounter.ping(100));   // requests = [1, 100], range is [-2900,100], return 2
		System.out.println(recentCounter.ping(3001));  // requests = [1, 100, 3001], range is [1,3001], return 3
		System.out.println(recentCounter.ping(3002));  // requests = [1, 100, 3001, 3002], range is [2,3002], return 3
		
	}
	
	static class RecentCounter {

		LinkedList<Integer> list;
		
	    public RecentCounter() {
	        list = new LinkedList<>();
	    }
	    
	    public int ping(int t) {
	    	
	    	list.add(t);
	    	
	    	while(list.getFirst() < t-3000) {
	    		list.removeFirst();
	    	}
	    	
	        return list.size();
	    }
		
	}
}
