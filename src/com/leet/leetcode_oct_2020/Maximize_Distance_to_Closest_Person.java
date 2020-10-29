package com.leet.leetcode_oct_2020;

public class Maximize_Distance_to_Closest_Person {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [] seats = {1,0,0,0,1,0,1};
		System.out.println(maxDistToClosest(seats));
	}
    
	public static int maxDistToClosest(int[] seats) {
		int res = 1;
		int curr = 0;
		
		for(int i : seats) {
			if(i == 1) {
                res = Math.max(res, (curr+1)/2);
				curr = 0;
			}
			else if(i == 0) {
				curr++;
			}
		}
		
		//check from left for cases like 0,0,0,0,1
        for(int i = 0; i < seats.length; i++){
            if(seats[i]==1){
                res = Math.max(res, i);
                break;
            }
        }
        
        //check from right for cases like 1,0,1,0,0,0,0
        for(int i = seats.length-1; i >=0 ; i--){
            if(seats[i]==1){
                res = Math.max(res, seats.length - 1 - i);
                break;
            }
        }
		
		return res;
    }
}
