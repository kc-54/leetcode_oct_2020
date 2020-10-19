package com.leet.leetcode_oct_2020;


public class Max_Profit {
	
	public static void main (String [] args) {
		int k = 2;
//		int [] prices = {2,6,8,7,8,7,9,4,1,2,4,5,8,2,9};
//		int [] prices = {8,6,4,3,3,2,3,5,8,3,8,2,6};
//		int [] prices = {9,5,7,4,2,4,1,6,4};
//		int [] prices = {3,4,6,0,3,7,5,8,2,9,1,6,6,2};
		int [] prices = {6,1,3,2,4,7};
		
		System.out.println(maxProfit(k,prices));
	}
	
	public static int maxProfit(int k, int[] prices) {
		
        int rs = 0;
        
        
		int min_pos = 0;
		int max_pos = 0;
		
		int[] mins = new int[prices.length];
		int[] maxs = new int[prices.length];
		int counter = 0;
		
		
		for(int i = 0; i < prices.length; i++) {
			
			if(prices[i] <= prices[max_pos]) {
				if(min_pos != max_pos) {
					mins[counter] = min_pos;
					maxs[counter] = max_pos;
					counter++;
				}
				
				min_pos = i;
				max_pos = i;
			}else {
				max_pos = i;
			}
		}
		if(min_pos != max_pos) {
			mins[counter] = min_pos;
			maxs[counter] = max_pos;
			counter++;
		}
		rs = findMaxWithK(prices, mins, maxs, k);
		for(int i = 0; i < counter; i++) {
			System.out.println("a pos " + mins[i] + " = " +prices[mins[i]] + " pos " + maxs[i] + " = " +prices[maxs[i]]);
		}
		for(int i = 0; i < counter-1; i++) {
			int temp = findMax(prices,mins,maxs,i,counter,k);
			rs = rs>temp?rs:temp;
		}
//		System.out.println();
		return rs;
		
    }
	
	public static int findMax(int[] prices, int[] min, int [] max, int pos, int limit, int k) {
		int rs = 0;
		System.out.println();
		System.out.println(" entered details ");
		System.out.println("pos " + pos);
		System.out.println("arrays : ");

		for(int i = 0; i < limit; i++) {
			System.out.println("x pos " + min[i] + " = " +prices[min[i]] + " pos " + max[i] + " = " +prices[max[i]]);
		}
		
		if(pos < min.length && prices[min[pos]] < prices[min[pos+1]] && prices[max[pos]] <= prices[max[pos+1]]) {
			System.out.println("satisfied");
			int[] temp_min = new int[limit-1];
			int[] temp_max = new int[limit-1];
			int pointer = 0;
			for(int i = 0; i < limit; i++) {
				
				if(i == pos) {
					temp_min[pointer] = min[i];
					temp_max[pointer] = max[i+1];
					i++;
				}else {
					temp_min[pointer] = min[i];
					temp_max[pointer] = max[i];
				}
				pointer++;
			}
			System.out.println("new array");
			for(int i = 0; i < limit-1; i++) {
				System.out.println("x pos " + temp_min[i] + " = " +prices[temp_min[i]] + " pos " + temp_max[i] + " = " +prices[temp_max[i]]);
			}

			rs = findMaxWithK(prices, temp_min, temp_max, k);
			System.out.println("rs from new array = " + rs);
			for(int i = pos; i < temp_min.length-1; i++) {
				System.out.println("entering recursion");
				int curr = findMax(prices,temp_min, temp_max, i, limit-1, k);
				System.out.println("leaving recursion, curr rs = " +rs + " obtained rs = " +curr + " cur pos " + i + " " + (temp_min.length-1));
				
				rs = rs>curr?rs:curr;
			}
		}else {
			int temp_rs = findMaxWithK(prices, min, max, k);
			rs = rs>temp_rs?rs:temp_rs;
			
		}
		System.out.println("return rs = " + rs);
		
		return rs;
	}
	
	public static int findMaxWithK(int[] prices, int[] min, int [] max, int k) {
		
		int rs = 0;
		int[] temp_diffs = new int[min.length];
		for(int i = 0; i < min.length; i++) {
			temp_diffs[i] = prices[max[i]] - prices[min[i]];
		}
		
		int limit = k<min.length?k:min.length;
		temp_diffs = sort_array(temp_diffs);
		
		for(int i = 0; i < limit; i++) {
			rs+=temp_diffs[i];
		}
		
		return rs;
	}
	public static int[] sort_array (int[] list) {

		for (int i = 0; i < list.length; i++) {
			int pos = i;
			for (int j = i; j < list.length; j++) {
				if (list[j] > list[pos]) {
					pos = j;
				}
			}
			if (pos != i) {
				int temp = list[pos];
				list[pos] = list[i];
				list[i] = temp;
			}
		}
		return list;
	}
//	if(k >= counter) {
//	for(int i = 0; i < counter; i++) {
//		rs += (prices[maxs[i]] - prices[mins[i]]);
//	}
//}else {
//	System.out.println("counter " + counter);
//	int temp_counter = counter;
//	int[] temp_mins = new int[counter];
//	int[] temp_maxs = new int[counter];
//	int[] temp_diffs = new int[counter];
//	int temp_i = 0;
//	
//	
//	for(int i = 0; i < counter; i++) {
//		
//		temp_mins[temp_i] = mins[i];
//		temp_maxs[temp_i] = maxs[i];
//		
//		System.out.println("test test " + i + " " + temp_i);
//		
//		while(k < temp_counter && prices[mins[temp_i]] < prices[mins[i+1]] && prices[maxs[temp_i]] <= prices[maxs[i+1]]) {
//
//			if(i>=counter-1)break;
////			int [] prices = {9,5,7,4,2,4,1,6,4};
//			System.out.println("entered " + temp_i);
//			System.out.println("test 2a " + mins[temp_i] + " " + mins[i+1]+ " b " + maxs[temp_i]+ " " +  maxs[i+1]);
//			System.out.println("test a " + prices[mins[temp_i]] + " " + prices[mins[i+1]] + " b " + prices[maxs[temp_i]] + " " +  prices[maxs[i+1]]);
//			if(temp_counter == k)break;
//			
//			temp_mins[temp_i] = mins[temp_i];
//			temp_maxs[temp_i] = maxs[i+1];
//			i++;
//			temp_counter--; 
//			
//		}
//		temp_diffs[temp_i] = prices[temp_maxs[temp_i]] - prices[temp_mins[temp_i]];
//		temp_i ++;
//	}
//	
//	System.out.println("temp " + temp_counter);
//	if(temp_counter != k) {
//		temp_diffs = sort_array(temp_diffs);
//		for(int i : temp_diffs) {
//			System.out.println( " test " + i);
//		}
//	}
//	for(int i = 0; i < k; i++) {
//		System.out.println("b pos " + temp_mins[i] + " = " +prices[temp_mins[i]] + " pos " + temp_maxs[i] + " = " + + prices[temp_maxs[i]] );
//		rs += temp_diffs[i];
//	}
//	
//}
}
