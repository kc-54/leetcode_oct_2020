package com.leet.leetcode_oct_2020;

public class Rotate_Array {
	
	public static void main(String [] args) {
		int[] nums = {1};
		int k = 1;
		rotate(nums, k);
		
	}
	
	
	public static void rotate(int[] nums, int k) {
		//using additional array
        int len = nums.length;
        int[] rs = new int[len];
		
		if(k>=len) {
			k = k%len;
		}
		
		for(int i = 0; i < k; i++) {
			rs[i] = nums[len-k+i];
		}
		
		for(int i = 0; i < len-k; i++) {
			rs[k+i] = nums[i];
		}
		
        for(int i = 0; i < len; i++){
            nums[i] = rs[i];
        }
        //print out result
		for(int i : nums) {
			System.out.print(i + " ");
		}
    }
	
}
