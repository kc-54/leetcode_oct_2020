package com.leet.leetcode_oct_2020;

public class Asteroid_Collision {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [] asteroids = {1,-1,-2,-2};
		int [] rs = asteroidCollision(asteroids);
		
		for(int i : rs) {
			System.out.print(i+ " ");
		}
		
	}
	public static int[] asteroidCollision(int[] asteroids) {
        
		if (asteroids == null || asteroids.length == 0) {
            return null;
        }
		
		int size = asteroids.length;
		int finalsize = asteroids.length;
		
		int [] temp_asteroids = new int[size];
		
		//copy the asteroids
		for(int i = 0; i < size; i++) {
			temp_asteroids[i] = asteroids[i];
		}
		
		while(true) {
			
			// for check, if the size is the same after the check, break the loop and return result
			int cur_size = finalsize;
			int counter = 0;
			
			//loop for the check if any collision
			for(int i = 0; i < finalsize; i++) {
				
				//if its not the last 2nd item in the list and if its colliding with the next item
				if(i < finalsize -1 && temp_asteroids[i] > 0 && temp_asteroids[i+1] < 0) {
					int rs = temp_asteroids[i] + temp_asteroids[i+1];
					//check the result of collision, if it is Exupulosion (:>) or it is only 1 meteor destroyed
			
					//if current asteroid stronger than next one, ignore the next one
					if(rs > 0) {
						cur_size--;
						counter++;
					} //if it is weaker than the next one, replace current one with the next one
					else if(rs < 0) {
						temp_asteroids[counter] = temp_asteroids[i+1];
						cur_size--;
						counter++;
					}//if both destroyed.....
					else {
						cur_size -=2;
					}
					//skip the next one, since we already use it to compare
					i++;
				}else {
					//if it is not colliding
					temp_asteroids[counter] = temp_asteroids[i];
					counter++;
				}
				
			}
			
			//if total of the asteroids is the same in the end, break
			if(finalsize==cur_size)break;
			//else, update the finalsize
			finalsize = cur_size;
		}
		
		//create a new array for the answer
		int[] rs = new int[finalsize];
		
		for(int i = 0; i < finalsize; i++) {
			rs[i] = temp_asteroids[i];
		}
		
		return rs;
    }
}
