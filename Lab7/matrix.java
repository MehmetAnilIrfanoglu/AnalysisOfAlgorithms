package lab7;

import java.util.Arrays;
import java.util.Random;

public class matrix
{
	public static void main(String [] args)
	{
		//write a for loop and try different matrix sizes
		for(int i=2;i<=128;i*=2) {
			
			
			int size = i;

			int [][] A = new int[size][size];
	                int [][] B = new int[size][size];
	                int [][] C = new int[size][size];
		
	                long start_time, end_time, elapsed_time;

			Random rand = new Random();
			rand.setSeed(System.currentTimeMillis());

			//initialize elements of matrices with random integers 
			initialize_2d_array_random(A);
	                initialize_2d_array_random(B);

	                //reset the C matrix to zero
	                initialize_2d_array(C);

	                //compute the elapsed time for recursive matrix multiplication algorithm
	                start_time = System.nanoTime();
	                square_matrix_multiply(A, B, C);
	                end_time = System.nanoTime();
	                elapsed_time = end_time-start_time;
	                System.out.printf("Input size: %d, Elapsed time in nanoseconds for square matrix multiply algorithm: %d\n", size, elapsed_time);

			//reset the C matrix to zero
	                initialize_2d_array(C);

	                //compute the elapsed time for recursive matrix multiplication algorithm
	                start_time = System.nanoTime();
	                square_matrix_multiply_recursive(A, B, 0, size-1, 0, size-1, 0, size-1, 0, size-1, 0, size-1, 0, size-1, C);
	                end_time = System.nanoTime();
	                elapsed_time = end_time-start_time;
	                System.out.printf("Input size: %d, Elapsed time in nanoseconds for recursive square matrix multiply algorithm: %d\n", size, elapsed_time);

	                //reset the C matrix to back to zero
	                initialize_2d_array(C);

	                //compute the elapsed time for Strassen's algorithm
	                start_time = System.nanoTime();
	                strassen_matrix_multiply(A, B, C);
	                end_time = System.nanoTime();
	                elapsed_time = end_time-start_time;
	                System.out.printf("Input size: %d, Elapsed time in nanoseconds for strassens square matrix multiply algorithm: %d\n", size, elapsed_time);


		}
		//creating A matrix
		int [][] a = {
				{1,1,1},
				{2,2,2},
				{3,3,3}
		};
		//creating B matrix
		int [][] b = {
				{1,1,1},
				{2,2,2},
				{3,3,3}
		};
		
		//creating result C matrix
		int [][] c = new int[b.length][b.length];
		
		//initialize matrix C
		initialize_2d_array(c);
		
		//calling strassen multiply
		strassen_matrix_multiply(a,b,c);
		
		System.out.println("----Strassen Result----");
		print_2d_array(c);
		
		
	}

	public static void square_matrix_multiply(int [][] A, int [][] B, int [][] C)
	{
                for (int i = 0; i < A.length; i++)
                {
                        for (int j = 0; j < B[0].length; j++)
                        {
                                C[i][j] = 0;
                                for (int k = 0; k < A[0].length; k++)
                                        C[i][j] += A[i][k]*B[k][j];
                        }
                }
	}

	public static void square_matrix_multiply_recursive(int [][] A, int [][] B, int A_row_index_start, int A_row_index_end, int A_col_index_start, int A_col_index_end, int B_row_index_start, int B_row_index_end, int B_col_index_start, int B_col_index_end, int C_row_index_start, int C_row_index_end, int C_col_index_start, int C_col_index_end, int [][] C)
	{
		int A_row_index_mid, A_col_index_mid, B_row_index_mid, B_col_index_mid;
		
		A_row_index_mid = (A_row_index_end+A_row_index_start)/2;
                A_col_index_mid = (A_col_index_end+A_col_index_start)/2;
                B_row_index_mid = (B_row_index_end+B_row_index_start)/2;
                B_col_index_mid = (B_col_index_end+B_col_index_start)/2;
		
		if (A_row_index_end-A_row_index_start+1 == 1)
			C[C_row_index_start][C_col_index_start] += A[A_row_index_start][A_col_index_start]*B[B_row_index_start][B_col_index_start];
		else
		{
                        //implement the recursive part here
                        //Recursive call 1 for C11
                        square_matrix_multiply_recursive(A, B, A_row_index_start, A_row_index_mid, A_col_index_start, A_col_index_mid, B_row_index_start, B_row_index_mid, B_col_index_start, B_col_index_mid, A_row_index_start, A_row_index_mid, A_col_index_start, A_col_index_mid, C);

                        //Recursive call 2 for C11
                        square_matrix_multiply_recursive(A, B, A_row_index_start, A_row_index_mid, A_col_index_mid+1, A_col_index_end, B_row_index_mid+1, B_row_index_end, B_col_index_start, B_col_index_mid, A_row_index_start, A_row_index_mid, A_col_index_start, A_col_index_mid, C);

                        //Recursive call 1 for C12
                        square_matrix_multiply_recursive(A, B, A_row_index_start, A_row_index_mid, A_col_index_start, A_col_index_mid, B_row_index_start, B_row_index_mid, B_col_index_mid+1, B_col_index_end, A_row_index_start, A_row_index_mid, A_col_index_mid+1, A_col_index_end, C);

                        //Recursive call 2 for C12
			square_matrix_multiply_recursive(A, B, A_row_index_start, A_row_index_mid, A_col_index_mid+1, A_col_index_end, B_row_index_mid+1, B_row_index_end, B_col_index_mid+1, B_col_index_end, A_row_index_start, A_row_index_mid, A_col_index_mid+1, A_col_index_end, C);

                        //Recursive call 1 for C21
                        square_matrix_multiply_recursive(A, B, A_row_index_mid+1, A_row_index_end, A_col_index_start, A_col_index_mid, B_row_index_start, B_row_index_mid, B_col_index_start, B_col_index_mid, A_row_index_mid+1, A_row_index_end, A_col_index_start, A_col_index_mid, C);

                        //Recursive call 2 for C21
                        square_matrix_multiply_recursive(A, B, A_row_index_mid+1, A_row_index_end, A_col_index_mid+1, A_col_index_end, B_row_index_mid+1, B_row_index_end, B_col_index_start, B_col_index_mid, A_row_index_mid+1, A_row_index_end, A_col_index_start, A_col_index_mid, C);

                        //Recursive call 1 for C22
                        square_matrix_multiply_recursive(A, B, A_row_index_mid+1, A_row_index_end, A_col_index_start, A_col_index_mid, B_row_index_start, B_row_index_mid, B_col_index_mid+1, B_col_index_end, A_row_index_mid+1, A_row_index_end, A_col_index_mid+1, A_col_index_end, C);

                        //Recursive call 2 for C22
                        square_matrix_multiply_recursive(A, B, A_row_index_mid+1, A_row_index_end, A_col_index_mid+1, A_col_index_end, B_row_index_mid+1, B_row_index_end, B_col_index_mid+1, B_col_index_end, A_row_index_mid+1, A_row_index_end, A_col_index_mid+1, A_col_index_end, C);

		}
		

		
	}

        public static void strassen_matrix_multiply(int [][] A, int [][] B, int [][] C)
        {
                //assumes square matrices
        		
                int n = A.length;
                int n_half;
                if(n%2==1) {
        			
        			A = complete_power(A,n+1);
        			B = complete_power(B,n+1);
        			
        			n_half = (n+1)/2;
        		}else {
        			n_half = n/2;
        		}
                

                if (n == 1)
                {
                        C[0][0] = A[0][0]*B[0][0];
                        return;
                }

                int [][] S1 = new int[n_half][n_half];
                int [][] S2 = new int[n_half][n_half];
                int [][] S3 = new int[n_half][n_half];
                int [][] S4 = new int[n_half][n_half];
                int [][] S5 = new int[n_half][n_half];
                int [][] S6 = new int[n_half][n_half];
                int [][] S7 = new int[n_half][n_half];
                int [][] S8 = new int[n_half][n_half];
                int [][] S9 = new int[n_half][n_half];
                int [][] S10 = new int[n_half][n_half];

                int [][] P1 = new int[n_half][n_half];
                int [][] P2 = new int[n_half][n_half];
                int [][] P3 = new int[n_half][n_half];
                int [][] P4 = new int[n_half][n_half];
                int [][] P5 = new int[n_half][n_half];
                int [][] P6 = new int[n_half][n_half];
                int [][] P7 = new int[n_half][n_half];

                int [][] temp = new int[n_half][n_half];

                int A11_row_index_start = 0;
                int A11_col_index_start = 0;

                int A12_row_index_start = 0;
                int A12_col_index_start = n_half;

                int A21_row_index_start = n_half;
                int A21_col_index_start = 0;

                int A22_row_index_start = n_half;
                int A22_col_index_start = n_half;

                int B11_row_index_start = 0;
                int B11_col_index_start = 0;

                int B12_row_index_start = 0;
                int B12_col_index_start = n_half;

                int B21_row_index_start = n_half;
                int B21_col_index_start = 0;

                int B22_row_index_start = n_half;
                int B22_col_index_start = n_half;

                int C11_row_index_start = 0;
                int C11_col_index_start = 0;

                int C12_row_index_start = 0;
                int C12_col_index_start = n_half;

                int C21_row_index_start = n_half;
                int C21_col_index_start = 0;

                int C22_row_index_start = n_half;
                int C22_col_index_start = n_half;

                for (int i = 0; i < n_half; i++)
                {
                        for (int j = 0; j < n_half; j++)
                        {
                                S1[i][j] = B[B12_row_index_start+i][B12_col_index_start+j]-B[B22_row_index_start+i][B22_col_index_start+j];       
                                S2[i][j] = A[A11_row_index_start+i][A11_col_index_start+j]+A[A12_row_index_start+i][A12_col_index_start+j];       
                                S3[i][j] = A[A21_row_index_start+i][A21_col_index_start+j]+A[A22_row_index_start+i][A22_col_index_start+j];
                                S4[i][j] = B[B21_row_index_start+i][B21_col_index_start+j]-B[B11_row_index_start+i][B11_col_index_start+j];       
                                S5[i][j] = A[A11_row_index_start+i][A11_col_index_start+j]+A[A22_row_index_start+i][A22_col_index_start+j];       
                                S6[i][j] = B[B11_row_index_start+i][B11_col_index_start+j]+B[B22_row_index_start+i][B22_col_index_start+j];
                                S7[i][j] = A[A12_row_index_start+i][A12_col_index_start+j]-A[A22_row_index_start+i][A22_col_index_start+j];       
                                S8[i][j] = B[B21_row_index_start+i][B21_col_index_start+j]+B[B22_row_index_start+i][B22_col_index_start+j];       
                                S9[i][j] = A[A11_row_index_start+i][A11_col_index_start+j]-A[A21_row_index_start+i][A21_col_index_start+j];
                                S10[i][j] = B[B11_row_index_start+i][B11_col_index_start+j]+B[B12_row_index_start+i][B12_col_index_start+j];
                        }
                }

                //recursively compute P matrices
                
                copy_submatrix(A, A11_row_index_start, A11_col_index_start, temp);
                strassen_matrix_multiply(temp, S1, P1);

                copy_submatrix(B, B22_row_index_start, B22_col_index_start, temp);
                strassen_matrix_multiply(S2, temp, P2);

                copy_submatrix(B, B11_row_index_start, B11_col_index_start, temp);
                strassen_matrix_multiply(S3, temp, P3);

                copy_submatrix(A, A22_row_index_start, A22_col_index_start, temp);
                strassen_matrix_multiply(temp, S4, P4);

                strassen_matrix_multiply(S5, S6, P5);
                strassen_matrix_multiply(S7, S8, P6);
                strassen_matrix_multiply(S9, S10, P7);

                for (int i = 0; i < n_half; i++)
                {
                        for (int j = 0; j < n_half; j++)
                        {
                        	if(C11_row_index_start+i<C.length && C11_col_index_start+j<C.length) {
                        		C[C11_row_index_start+i][C11_col_index_start+j] = P5[i][j]+P4[i][j]-P2[i][j]+P6[i][j];
                        	}
                        	if(C12_row_index_start+i<C.length && C12_col_index_start+j<C.length) {
                        		C[C12_row_index_start+i][C12_col_index_start+j] = P1[i][j]+P2[i][j];
                        	}
                        	if(C21_row_index_start+i<C.length && C21_col_index_start+j<C.length) {
                        		C[C21_row_index_start+i][C21_col_index_start+j] = P3[i][j]+P4[i][j];
                        	}
                        	if(C22_row_index_start+i<C.length && C22_col_index_start+j<C.length) {
                        		C[C22_row_index_start+i][C22_col_index_start+j] = P5[i][j]+P1[i][j]-P3[i][j]-P7[i][j];
                        	}
                                
                                
                        }
                }
	}

	//prints the elements of the array A on the screen
	public static void print_2d_array(int [][] A)
	{
		//System.out.printf("[");
		for (int i = 0; i < A.length; i++)
		{
			for (int j = 0; j < A[0].length; j++)
				System.out.printf("%d ", A[i][j]);
			System.out.printf("\n");
		}
		
		//System.out.printf("%d]\n", A[A.length-1]);

	}

	public static void initialize_2d_array_random(int [][] A)
	{
                Random rand = new Random();
                rand.setSeed(System.currentTimeMillis());

                for (int i = 0; i < A.length; i++)
                {
                        for (int j = 0; j < A[0].length; j++)
                                A[i][j] = rand.nextInt(100);
                }
	}
	
		public static int[][] complete_power(int [][] A,int size) {
			int [][] arr = new int[size][size];
			
			for(int i=0;i<A.length;i++) {
				for(int j=0;j<A.length;j++) {
					arr[i][j] = A[i][j];
				}
			}
			
			for(int k=A.length;k<size;k++) {
				for(int l=A.length;l<size;l++) {
					arr[k][l] = 0;
				}
			}
			
			
			return arr;
		}

        public static void initialize_2d_array(int [][] A)
        {
                Random rand = new Random();
                rand.setSeed(System.currentTimeMillis());

                for (int i = 0; i < A.length; i++)
                {
                        for (int j = 0; j < A[0].length; j++)
                                A[i][j] = 0;
                }
        }
        
        public static void print_2d_arrray(int [][] A)
    	{
    		
    		for (int i = 0; i < A.length; i++)
    		{
    			for (int j = 0; j < A[0].length; j++) {
    				System.out.printf("%d ", A[i][j]);
    			}
    				
    			System.out.printf("\n");
    		}
    		
    		//System.out.printf("%d]\n", A[A.length-1]);

    	}

        public static void copy_submatrix(int [][] A, int row_index_start, int col_index_start, int [][] temp)
        {
                int size = temp.length;

                for (int i = 0; i < size; i++)
                {
                        for (int j = 0; j < size; j++)
                                temp[i][j] = A[row_index_start+i][col_index_start+j];
                }
        }
}