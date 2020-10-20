package com.leet.leetcode_oct_2020;


public class Max_Profit {
	
	public static void main (String [] args) {
		int k = 2;
		
		//test cases
		int [] prices = {2,6,8,7,8,7,9,4,1,2,4,5,8,2,9};
//		int [] prices = {8,6,4,3,3,2,3,5,8,3,8,2,6};
//		int [] prices = {9,5,7,4,2,4,1,6,4};
//		int [] prices = {3,4,6,0,3,7,5,8,2,9,1,6,6,2};
//		int [] prices = {6,1,3,2,4,7};
		
		System.out.println(maxProfitWithMerging(k,prices));
		System.out.println(maxProfitWithDP(k,prices));
		System.out.println(maxProfitWithDP_Compressed_Array(k,prices));
	}
	
	//dp solution with state array compressed
	//Runtime 2ms, Memory 39.2MB
	//Status Accepted
    public static int maxProfitWithDP_Compressed_Array(int k, int[] prices) {
    	
        int n = prices.length;
        
        if(k<=0 || n<=0) {
        	return 0;
        }

        if(n < 2*k) {
        	int rs = 0;
        	for (int i = 1; i < n; i++) {
        		rs += Math.max(0, prices[i]-prices[i-1]);
        	}
        	return rs;
        	
        }
        
        //dp[i][j][k]
        //i for ith transactions
        //j for number of transaction used
        //k 0 for not hold stock, 1 for hold stock
        int [][][] dp = new int[2][k+1][2];

        // initialize the array with -inf
        // we use -1e9 here to prevent overflow
        for(int i = 0; i < 2; i++) {
        	for(int j = 0; j <= k; j++) {
        		dp[i][j][0] = -1000000000;
        		dp[i][j][1] = -1000000000;
        	}
        }
        
        //start value
        dp[0][0][0] = 0;
        dp[0][1][1] = -prices[0];

        for(int i = 1; i < n; i++) {
        	for(int j = 0; j <= k; j++) {
        		
        		//transition value calculation
        		dp[1][j][0] = Math.max(dp[0][j][0], dp[0][j][1] + prices[i]);
        		//can't hold stock without any transaction
        		if(j>0) {
        			dp[1][j][1] = Math.max(dp[0][j][1], dp[0][j-1][0]-prices[i]);
        		}
        	}
        	//replace previous result with the latest as the previous result is useless
        	for(int j = 0; j <= k; j++) {
        		dp[0][j][0] = dp[1][j][0];
        		dp[0][j][1] = dp[1][j][1];
        	}
        	
        }

    	int rs = 0;
        for(int i = 0; i <= k; i++) {
        	rs = Math.max(rs, dp[1][i][0]);
        }
        
    	return rs;
    }
	
    //dp basic solution
	//Runtime 5ms, Memory 40.8MB
	//Status Accepted
	public static int maxProfitWithDP(int k, int[] prices) {
		int n = prices.length;
        
        if(k<=0 || n<=0) {
        	return 0;
        }

        if(n < 2*k) {
        	int rs = 0;
        	for (int i = 1; i < n; i++) {
        		rs += Math.max(0, prices[i]-prices[i-1]);
        	}
        	return rs;
        	
        }
        
        
        //dp[i][j][k]
        //i for ith transactions
        //j for number of transaction used
        //k 0 for not hold stock, 1 for hold stock
        int [][][] dp = new int[n][k+1][2];

        // initialize the array with -inf
        // we use -1e9 here to prevent overflow
        for(int i = 0; i < n; i++) {
        	for(int j = 0; j <= k; j++) {
        		dp[i][j][0] = -1000000000;
        		dp[i][j][1] = -1000000000;
        	}
        }
        
        //start value
        dp[0][0][0] = 0;
        dp[0][1][1] = -prices[0];

        for(int i = 1; i < n; i++) {
        	for(int j = 0; j <= k; j++) {
        		//transition value calculation
        		dp[i][j][0] = Math.max(dp[i-1][j][0], dp[i-1][j][1] + prices[i]);
        		//can't hold stock without any transaction
        		if(j>0) {
        			dp[i][j][1] = Math.max(dp[i-1][j][1], dp[i-1][j-1][0]-prices[i]);
        		}
        	}
        }

    	int rs = 0;
        for(int i = 0; i <= k; i++) {
        	rs = Math.max(rs, dp[n-1][i][0]);
        }
        
    	return rs;
	}
	
	
	//inefficent way, merging with brute force and recursion
	//Runtime N/A, Memory N/A
	//Status Time Limit Exceeded
	public static int maxProfitWithMerging(int k, int[] prices) {
		
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
		for(int i = 0; i < counter-1; i++) {
			int temp = findMax(prices,mins,maxs,i,counter,k);
			rs = rs>temp?rs:temp;
		}
//		System.out.println();
		return rs;
		
    }
	
	public static int findMax(int[] prices, int[] min, int [] max, int pos, int limit, int k) {
		int rs = 0;
		
		if(pos < min.length && prices[min[pos]] < prices[min[pos+1]] && prices[max[pos]] <= prices[max[pos+1]]) {
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

			rs = findMaxWithK(prices, temp_min, temp_max, k);
			for(int i = pos; i < temp_min.length-1; i++) {
				int curr = findMax(prices,temp_min, temp_max, i, limit-1, k);
				
				rs = rs>curr?rs:curr;
			}
		}else {
			int temp_rs = findMaxWithK(prices, min, max, k);
			rs = rs>temp_rs?rs:temp_rs;
			
		}
		
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
}
