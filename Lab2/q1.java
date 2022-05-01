package lab2;

import java.util.Arrays;
import java.util.Random;

public class q1
{
	public static void main(String [] args)
	{
		int array_size = 100000;//will be 1000
		long start_time, end_time, elapsed_time;

		int array [] = new int[array_size];

		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());

		//initialize elements of array with random integers
		for (int i = 0; i < array.length; i++)
			array[i] = rand.nextInt(100);
		

		//part 1(c)
		
		long startTime = System.currentTimeMillis();
		insertion_sort(array);
		long endTime = System.currentTimeMillis();
		long elapsed = endTime - startTime; 
		
		

		long startTime1 = System.currentTimeMillis();
		insertion_sort(array);
		long endTime1 = System.currentTimeMillis();
		long elapsed1 = endTime1 - startTime1; 
		
		System.out.println("Time for insertion sort is "+elapsed);
		System.out.println("Best case time for insertion sort is "+elapsed1);

		//part 1(d) 
		
		long startTime2 = System.currentTimeMillis();
		insertion_sort_reverse(array);
		insertion_sort(array);
		long endTime2 = System.currentTimeMillis();
		long elapsed2 = endTime2 - startTime2; 
		
		System.out.println("The worst case time for insertion sort is "+elapsed2);
		//part 1(e)
	}

        //part 1(a) implementing insertion sort algorithm as a method below
        public static void insertion_sort(int [] A)
        {
        	int i,j,key;
        	for(i=1;i<A.length;i++){
        		key = A[i];
        		j = i-1;
        		while(j>=0 && A[j]>key){
        			A[j+1]= A[j];
        			j -=1;
        		}
        		A[j+1]=key;
        	}
        	

        }

        //part 1(b) implementing insertion sort algorithm that sorts in descending order as a method below
        public static void insertion_sort_reverse(int [] A)
        {
 
        	int i,j,key;
        	for(i=1;i<A.length;i++){
        		key = A[i];
        		j = i-1;
        		while(j>=0 && A[j]<key){
        			A[j+1]= A[j];
        			j -=1;
        		}
        		A[j+1]=key;
        	}
        }

	//prints the elements of the array A on the screen
	public static void print_array(int [] A)
	{
		System.out.printf("[");
		for (int i = 0; i < A.length-1; i++)
		{
			System.out.printf("%d, ", A[i]);
		}
		
		System.out.printf("%d]\n", A[A.length-1]);

	}
}
