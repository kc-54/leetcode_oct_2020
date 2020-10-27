package com.leet.leetcode_oct_2020;

public class Champagne_Tower {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		champagneTower(16,6,1);
	}
	public static double champagneTower(int poured, int query_row, int query_glass) {
        
		//if queried row = 0, return 1 if poured more than 1, else return poured
		if(query_row == 0)return Math.min(poured, 1);
		
		//if queried row not 0 and poured less than equals 1, others cup will be empty
		if(poured <= 1)return 0;
		
		//let n = 0 for the row
		//to fill fully all glass for n(start from 0) row, you will need at least 2^(n+1)-1 cup
		//for ex. to fill all glass in 3rd row, you will need 2^4-1 cup = 15 cup
		//the first 10 cup will be filled fully and the 5 more will overflow to bottom
		//filled_until is to find until which row have all cup filled.
		//if query_row is less than equals filled_until, return 1
        int filled_until = (int)(Math.log(poured+1)/Math.log(2)+1e-10)-1;
        if(query_row <= filled_until) {
        	return 1;
        }
        

        //else, simulate the tower poured
        double[][] tower = new double[102][102];
        
        tower[0][0] = (double) poured;

        for(int row = 0; row <= query_row; ++row) {
        	for(int col = 0; col <= row; ++col) {
        		
        		//calculate overflow, if there are any
        		double overflow = (tower[row][col] - 1)/2;
        		
        		//if there is overflow, pour to next glass
        		if(overflow > 0) {
        			tower[row+1][col] += overflow;
        			tower[row+1][col+1] += overflow;
        		}
        	}
        }
        
        //return result for the queried
        return Math.min(1, tower[query_row][query_glass]);
    }
}
