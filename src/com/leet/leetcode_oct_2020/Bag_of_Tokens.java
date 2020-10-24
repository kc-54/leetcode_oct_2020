package com.leet.leetcode_oct_2020;

import java.util.Arrays;

public class Bag_of_Tokens {

	public static void main(String[] args) {
		
		int [] tokens = {100,200,300,400};
		int P = 200;
		System.out.println(bagOfTokensScore(tokens, P));
		
	}
	public static int bagOfTokensScore(int[] tokens, int P) {
        
		//sort the tokens array first
        Arrays.sort(tokens);
        
        int n = tokens.length-1;
        
        int i = 0;
        int score = 0;
        
        while(i <= n) {
        	
        	//if tokens[i] less than or equal than P, buy it
        	if(tokens[i] <= P) {
        		P -= tokens[i];
        		score++;
        		i++;
        	}//if tokens[i] more than P, try use score to buy the last tokens[n] available (most value)
        	else {
        		if(score > 0 && tokens[i] < P + tokens[n]) {
        			P += tokens[n];
            		P -= tokens[i];
        			n--;
        			i++;
        		}else {
        			break;
        		}
        	}
        	
        }
        return score;
    }
}
