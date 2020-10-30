package com.leet.leetcode_oct_2020;

public class Number_of_Longest_Increasing_Subsequence {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [] nums = {2,2,2,2,2};
		System.out.println(findNumberOfLIS(nums));
	}
	
	public static int findNumberOfLIS(int[] nums) {
        //if array empty return 0;
		if(nums.length == 0)return 0;
		
		//result 
		int res = 0; 
		//longest to keep track current longest subsequence
		int longest = 0;
		
		//to use dp method to keep track each number info, 
		//length is to keep track longest sub until this n number and freq to keep tract number of times this longest sub appear
		int [] length = new int[nums.length];
		int [] freq = new int[nums.length];
		
		for(int i = 0; i < nums.length; i++) {
			//initialize each number length with 1 as the shortest subs is the number itself and the freq is only 1 time
			length[i] = 1;
			freq[i] = 1;
			
			//loop check with all prev number, 
			//if prev number is smaller and the length is longer than current length of subs, 
			//replace current length with the smaller number length + 1 and 
			//update the current freq with the smaller number freq
			//if the smaller number have same length with current length, add up the freq to current freq
			for(int j = 0; j < i ; j++) {
				if(nums[i]>nums[j]) {
					if(length[j]+1 > length[i]) {
						length[i] = length[j]+1;
						freq[i] = freq[j];
					}else if(length[j] + 1 == length[i]) {
						freq[i] += freq[j];
					}
				}
			}
			
			//if the current length is longer than universal longest; update the universla longest and frequency
			if(length[i] > longest) {
				longest = length[i];
				res = freq[i];
			//else if the length is same as universal longest, add up their freq
			}else if(length[i] == longest) {
				res += freq[i];
			}
		}
		
		return res;
    }
}
