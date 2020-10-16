package com.leet.leetcode_oct_2020;

public class Search_a_2D_Matrix {

	public static void main(String[] args) {
		int[][] matrix = {{1,3,5,7},{10,11,16,20},{23,30,34,50}};
		int target = 3;
		
		System.out.println(searchMatrix(matrix, target));
		
	}
	
	
	public static boolean searchMatrix(int[][] matrix, int target) {

		//if empty matrix, return false
		if(matrix.length==0 || matrix[0].length==0) return false;
		
		int m = matrix.length;
		int n = matrix[0].length;
		
		
		int target_m = -1;
		
		//find the row the target in
		for(int i = 0; i < m; i++) {
			if(matrix[i][0] > target) {
				break;
			}
			target_m = i;
		}
		
		//if target not in any row, return false
		if(target_m < 0) {
			return false;
		}
		
		//if target outside the expected row, return false
		if(target < matrix[target_m][0] || target > matrix[target_m][n-1]) {
			return false;
		}
		
		//linear search target in expected row
		for(int i : matrix[target_m]) {
			if(i == target)return true;
		}
		
        return false;
        
    }

}
