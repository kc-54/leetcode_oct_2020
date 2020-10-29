package com.leet.leetcode_oct_2020;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Combination_Sum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [] candidates = {2,3,6,7};
		int target = 16;
		
		
		
		for(List<Integer> li : combinationSum_1(candidates, target)) {
			for(int i : li) {
				System.out.print(i + " ");
			}
			System.out.println();
		}

		for(List<Integer> li : combinationSum_2(candidates, target)) {
			for(int i : li) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
		
	}
	//backtrack solution using recursive
	public static List<List<Integer>> combinationSum_2(int[] candidates, int target) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		Arrays.sort(candidates);
		backtrack(res, new ArrayList<Integer>(), candidates, target, 0);
		return res;
		
	}
	public static void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] candidates, int remain, int start) {
		
		if(remain<0)return;
		else if(remain == 0) list.add(new ArrayList<>(tempList));
		else {
			for(int i = start; i < candidates.length; i++) {
				tempList.add(candidates[i]);
				backtrack(list, tempList, candidates, remain-candidates[i],i);
				tempList.remove(tempList.size()-1);
			}
		}
		
		
	}
	
	//using dp to get all possible combination and reconstruct. too slow on reconstruction.
	public static List<List<Integer>> combinationSum_1(int[] candidates, int target) {

		int[][] dp = new int[target+1][candidates.length];
		
		for(int i = 1; i <= target; i++) {
			
			for(int k = 0; k < candidates.length; k++) {
				if(i > candidates[k]) {
					int temp = k;
					while(temp >= 0) {
						dp[i][k] += dp[i-candidates[k]][temp];
						temp--;
					}
				}else if(i== candidates[k]) {
					dp[i][k]++;
				}else {
					break;
				}
			}
			
		}
		
		
		//reconstruction to list
		int total = 0;
		List<List<Integer>> temp = new ArrayList<List<Integer>>();
		List<Integer> list;
		for(int i = 0; i < candidates.length; i ++) {
			total += dp[target][i];
		}
		int [] remainder = new int[total];
		int [] mem = new int[total];
		int k = 0;
		for(int i = 0; i < candidates.length; i ++) {
			int ith = dp[target][i];
			while(ith>0) {
				list = new ArrayList<Integer>();
				list.add(candidates[i]);
				temp.add(list);
				remainder[k] = target-candidates[i];
				mem[k] = i;
				k++;
				ith--;
			}
		}
		
		int temp_total = total;
		while(temp_total > 0) {
			
			int cur = 0;
			int pointer = -1;
			int repeat = 0;
			
			for(int i = 0; i < total; i++) {
				
				int temp_repeat = 0;
				
				if(cur==remainder[i] && pointer == mem[i]) {
					repeat++;
				}else {
					repeat = 0;
				}
				
				cur = remainder[i];
				pointer = mem[i];
				
				for(int j = 0; j <= mem[i]; j++) {
					if(dp[remainder[i]][j] >= 1) {
						if(temp_repeat + dp[remainder[i]][j] > repeat) {
							temp.get(i).add(candidates[j]);
							remainder[i] -= candidates[j];
							if(remainder[i]==0)temp_total--;
							mem[i] = j;
							break;
						}else {
							temp_repeat+= dp[remainder[i]][j];
						}
					}
				}
				
			}

		}
		
		return temp;
    }
	
}
