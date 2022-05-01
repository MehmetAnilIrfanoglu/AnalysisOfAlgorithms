package lab2;

import java.util.Arrays;
import java.util.Random;

public class q2
{
	public static void main(String [] args)
	{
		int array_size = 1048576;
		int [] array = new int[array_size];
		long start_time, end_time, elapsed_time, elapsed_time_insertion, elapsed_time_merge;

		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());

		for (int i = 0; i < array_size; i++)
			array[i] = rand.nextInt(100);
	
		//part 2(b) 
		/*
		print_array(array);
		merge_sort(array,0,array.length-1);
		print_array(array);
	*/
		//part 3(a)	
		
		long startTime = System.currentTimeMillis();
		merge_sort(array,0,array.length-1);
		long endTime = System.currentTimeMillis();
		long elapsed = endTime - startTime; 
		
		

		long startTime1 = System.currentTimeMillis();
		insertion_sort(array);
		long endTime1 = System.currentTimeMillis();
		long elapsed1 = endTime1 - startTime1; 
		
		System.out.println("Time for merge sort is "+elapsed);
		System.out.println("time for insertion sort is "+elapsed1);

		//part 3(b)

	}

	//indices p and r can start from 0
	public static void merge_sort(int [] A, int p, int r)
	{
		if(p<r){
			int q = (p+r)/2;
			merge_sort(A,p,q);
			merge_sort(A,q+1,r);
			merge(A,p,q,r);
		}
	}

	//Part 2(a)
	public static void merge(int [] A, int p, int q, int r)
	{
		int n1 = q-p +1;
		int n2 = r-q;
		boolean x =false;
		boolean y = false;
		
		int L[] = new int[n1];
		int R[] = new int[n2];
		for(int i=0;i<n1;i++){
			L[i]= A[p+i];
		}
		for(int j=0;j<n2;j++){
			R[j]= A[q+j+1];
		}
		
		
		int i=0;
		int j=0;
		for(int k=p;k<=r;k++){
			if(x){
				A[k]= R[j];
				j ++;
			}else{
				if(y){
					A[k]= L[i];
					
					i ++;
				}else{
					if(L[i]<=R[j]){
						A[k]= L[i];
						i ++;
						if(i>=L.length)x=true;
					}
					else{
						A[k]= R[j];
						j ++;
						if(j>=R.length)y=true;
					}
				}
			}
			
			
		}
		
		
	
		
	}	

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
