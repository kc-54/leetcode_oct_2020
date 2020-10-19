package com.leet.leetcode_oct_2020;

public class Min_Domino_Rotations {

	public static void main(String[] args) {
		
		int [] A = {2,1,2,4,2,2};
		int [] B = {5,2,6,2,3,2};
		
		System.out.println(minDominoRotations(A, B));
	}
    public static int minDominoRotations(int[] A, int[] B) {
        int countA_1 = 0;
        int countA_2 = 1;
        int countB_1 = 0;
        int countB_2 = 1;
        
        boolean failedA_1 = false;
        boolean failedA_2 = false;
        boolean failedB_1 = false;
        boolean failedB_2 = false;
        
        for(int i = 1; i < A.length; i++) {
        
        	if(!failedA_1) {
        		if(A[i] != A[0] && B[i] == A[0]) {
        			countA_1++;
        		}
        		if(A[i] != A[0] && B[i] != A[0]) {
        			failedA_1 = true;
        		}
        	}
        	if(!failedA_2) {
        		if(A[i] != B[0] && B[i] == B[0]) {
        			countA_2++;
        		}
        		if(A[i] != B[0] && B[i] != B[0]) {
        			failedA_2 = true;
        		}
        	}
        	if(!failedB_1) {
        		if(B[i] != B[0] && A[i] == B[0]) {
        			countB_1++;
        		}
        		if(B[i] != B[0] && A[i] != B[0]) {
        			failedB_1 = true;
        		}
        	}
        	if(!failedB_2) {
        		if(B[i] != A[0] && A[i] == A[0]) {
        			countB_2++;
        		}
        		if(B[i] != A[0] && A[i] != A[0]) {
        			failedB_2 = true;
        		}
        	}
        	
        }
        int rs = 20001;
        if(failedA_1 && failedA_2 && failedB_1 && failedB_2)return -1;
        
        if(!failedA_1) rs = Math.min(rs, countA_1);
        if(!failedA_2) rs = Math.min(rs, countA_2);
        if(!failedB_1) rs = Math.min(rs, countB_1);
        if(!failedB_2) rs = Math.min(rs, countB_2);

        return rs;
        
    }

}
