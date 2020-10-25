package com.leet.leetcode_oct_2020;

public class Stone_Game_IV {

	public static void main(String[] args) {
		
		System.out.println(winnerSquareGame_1(16));
		System.out.println(winnerSquareGame_2(16));

	}
	
	
	//dp solution 1
	//calculate backward, using prev result to calculate current n
	//for ex. dp[1] = 1 means if the player get 1 as n, he/she will win,and if dp[0] = -1 means he/she will lose.
	//when i = 5, we check max_square possible which is 1 and 4
	//then check if dp[i-1] and dp[1-4] is -1, which means Bob will lose. if there is a way Bob lose, then Alice win. else Alice lose.
	public static boolean winnerSquareGame_1(int n) {
        int [] dp = new int[n+1];
        
        dp[0] = -1;
        dp[1] = 1;
        
        for(int i=1; i <= n; i ++) {
        	int max_square = (int)Math.floor(Math.sqrt(i));
        	boolean round_win = false;
        	for(int j = max_square; j >= 1; j--) {
        		int remaining = i-j*j;
        		if(dp[remaining] < 0) {
        			round_win = true;
        			break;
        		}
        	}
        	if(round_win) {
        		dp[i] = 1;
        	}else {
        		dp[i] = -1;
        	}
        }
        
        return dp[n] == 1;
    }
	
	//dp solution 2
	//calculate forward
	//if dp[i] is true, means Alice win
	//else if dp[i] is false, then dp[i] + (any number)^2  will be true;
	//for ex. dp[0] is false, then dp[1], dp[4], dp[9] will be true;
	public static boolean winnerSquareGame_2(int n) {
		
		boolean dp[] = new boolean [n+1];
		
		for(int i = 0; i <= n; i++) {
			if(dp[i]) {
				continue;
			}
			for(int k = 1; i + k*k <= n; k++) {
				dp[i+k*k] = true;
			}
		}
		
		return dp[n];
	}
}
