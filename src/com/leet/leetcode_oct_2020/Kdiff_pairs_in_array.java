package com.leet.leetcode_oct_2020;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Kdiff_pairs_in_array {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [] nums = {1,3,1,1,1,1,5,4,4,4,4,6,6,5,5,4};
		int k = 1;
		
		System.out.println(findPairs(nums, k));
	}
	
	public static int findPairs(int[] nums, int k) {
		int res = 0;
        
        Arrays.sort(nums);
        
        //set to remove duplicate
        Set<Integer> set = new HashSet<>();
        
        int cur = 10000001;
        boolean same = false;
        
        for(int i : nums) {
        	
            if(i != cur){
                cur = i;
                same = false;
            }else{
            	//if the cur number the same as prev number, to avoid duplicate pairs, where k >= 0;
                if(same)continue;
           
                same = true;
            }
            
        	//if the set contains the i number, remove the i from set to avoid duplicate
        	if(set.contains(i)) {
        		set.remove(i);
        		res++;
        	}
        	
        	//add the number + k diff to the set
        	set.add(i+k);
        }
        
        return res;
    }
}
