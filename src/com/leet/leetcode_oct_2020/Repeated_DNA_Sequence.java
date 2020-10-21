package com.leet.leetcode_oct_2020;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Repeated_DNA_Sequence {
	
	public static void main (String[] args) {
		String s = "AAAAAAAAAAAAA";
		System.out.println(findRepeatedDnaSequences(s));
	}
	
	public static List<String> findRepeatedDnaSequences(String s) {
        
        Map<String,Integer> hm = new HashMap<String,Integer>();
        List<String> result = new ArrayList<String>();
        
        System.out.println(s.length());
        
        String cut;
        for(int i = 0; i < s.length() - 9; i++){
        	cut = s.substring(i, i+10);
        	
            if(hm.containsKey(cut)){
            	int count = hm.get(cut);
            	count++;
            	hm.put(cut, count);
            }else {
            	hm.put(cut, 1);
            }
        }
        
        for(java.util.Map.Entry<String,Integer> e : hm.entrySet()) {
        	if(e.getValue()>1) {
        		result.add(e.getKey());
        	}
        }
        
        return result;
        
        
        
    }
}
