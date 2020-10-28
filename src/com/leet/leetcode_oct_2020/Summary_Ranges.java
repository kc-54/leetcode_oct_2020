package com.leet.leetcode_oct_2020;

import java.util.ArrayList;
import java.util.List;

public class Summary_Ranges {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = {0,2,3,4,6,8,9};
		for(String s: summaryRanges(nums)) {
			System.out.println(s);
		}
	}
	public static List<String> summaryRanges(int[] nums) {
        
		List<String> rs = new ArrayList<String>();
        int n = nums.length;
        
        if(n==0) {
        	return rs;
        }
        	
        StringBuilder sb;
        for(int i = 0; i < n; i++) {
        	int start = i;
        	
        	while( i + 1 < n && nums[i]+1 == nums[i+1]) {
        		i++;
        	}
        	sb = new StringBuilder();
        	if(i==start) {
        		sb.append(nums[i]);
        	}else {
        		sb.append(nums[start]).append("->").append(nums[i]);
        	}
        	
        	rs.add(sb.toString());
        }
        	
        
        
        return rs;
    }
}
