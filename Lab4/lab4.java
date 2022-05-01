
// Mehmet Anıl İrfanoğlu
import java.util.Arrays;
import java.util.Random;

public class lab4
{
    public static void main(String [] args)
    {
        int array_size = 65536;
        int [] A = new int[array_size];
        int [] test_A = new int[5];
        int [] diff_test_A = new int[4];
        int [] diff_A = new int[array_size-1];
        int [] outputs = new int[3];
        int best_left_index = 0;
        int best_right_index = 0;
        int max_difference = 0;

        long start_time, end_time, elapsed_time;
        long elapsed_time_brute_force, elapsed_time_divide_and_conquer;

        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());

        //part 1(b)
        test_A[0] = 10;
        test_A[1] = 11;
        test_A[2] = 7;
        test_A[3] = 10;
        test_A[4] = 6;

        //testing brute force
        System.out.println("Brute Force Test:");
        brute_force(test_A);
        System.out.println("");

        //initialize elements of array with random integers and compute difference array
        for (int i = 0; i < A.length; i++)
        {
            A[i] = rand.nextInt(100);
            if (i > 0)
                diff_A[i-1] = A[i] - A[i-1];
        }

        //part 1(c) compute the elapsed time for brute-force algorithm
        start_time = System.nanoTime();
        brute_force(A);
        end_time = System.nanoTime();
        elapsed_time = end_time - start_time;
        System.out.println("Running time of brute force algorithm is "+elapsed_time);
        System.out.println("");

        //part 2(b)
        diff_test_A[0] = 1;
        diff_test_A[1] = -4;
        diff_test_A[2] = 3;
        diff_test_A[3] = -4;

        System.out.println("Max Subarray Test:");
        int result[] = find_maximum_subarray(diff_test_A,0,diff_test_A.length-1);
        System.out.println("left index= "+(result[0]+1)+" right index= "+(result[1]+1)+" maximum difference= "+result[2]);


        //print the left index, right index and maximum difference (i.e. net profit)

        //part 2(c) compute the elapsed time for divide and conquer algorithm
        start_time = System.nanoTime();
        int solution[] =find_maximum_subarray(diff_A,0,diff_A.length-1);
        end_time = System.nanoTime();
        elapsed_time = end_time - start_time;
        System.out.println("Running time of max subarray algorithm is "+elapsed_time);
        System.out.println("left index= "+(solution[0]+1)+" right index= "+(solution[1]+2)+" maximum difference= "+solution[2]);
        System.out.println("");

        //print the left index, right index and maximum difference (i.e. net profit)

        //part 3

        int max_array_size = 1000;

        for (array_size = 2; array_size <= max_array_size; array_size++)
        {
            int [] A_2 = new int[array_size];
            int [] diff_A_2 = new int[array_size-1];
            long brute_elapsed,max_sub_elapsed;
            //initialize elements of array with random integers and compute difference array
            for (int i = 0; i < A_2.length; i++)
            {
                A_2[i] = rand.nextInt(100);
                if (i > 0)
                    diff_A_2[i-1] = A_2[i] - A_2[i-1];
            }

            start_time = System.nanoTime();
            brute_force_2(A_2,A_2.length-1);
            end_time = System.nanoTime();
            brute_elapsed= end_time - start_time;

            start_time = System.nanoTime();
            find_maximum_subarray(diff_A_2,0,diff_A_2.length-1);
            end_time = System.nanoTime();
            max_sub_elapsed= end_time - start_time;

            if(max_sub_elapsed<brute_elapsed){
                break;
            }
        }
        System.out.println("\nn0 (cross point) is "+array_size);
    }

    //part 1(a) implementing the brute-force algorithm
    public static void brute_force(int [] A)
    {
        int left_index= 0;
        int right_index = 0;
        int max_sum = Integer.MIN_VALUE;

        for(int i=0;i<A.length;i++){
            for (int j=i+1;j<A.length;j++){
                if(A[j]-A[i]>max_sum){
                    left_index = i;
                    right_index = j;
                    max_sum =A[j]-A[i];

                }
            }
        }
        System.out.println("Left index is "+(left_index+1)+" right index is "+(right_index+1)+" maximum profit is "+max_sum);

        //print the left index, right index and maximum difference (i.e. net profit)

    }

    public static void brute_force_2(int [] A, int array_size)
    {

        int left_index= 0;
        int right_index = 0;
        int max_sum = Integer.MIN_VALUE;

        for(int i=0;i<A.length;i++){
            for (int j=i+1;j<A.length;j++){
                if(A[j]-A[i]>max_sum){
                    left_index = i;
                    right_index = j;
                    max_sum =A[j]-A[i];

                }
            }
        }
    }

    //part 2(a) implementing the recursive algorithm that uses divide and conquer and finds the maximum subarray
    public static int[] find_maximum_subarray(int [] diff_A, int low, int high)
    {       int outputs[];
        //print the left index, right index and maximum difference (i.e. net profit)
        if(high==low){
            outputs = new int[]{low,high,diff_A[low]};
            return outputs;
        }
        else {
            int mid = (int) Math.floor((low+high)/2);
            int left[] = find_maximum_subarray(diff_A,low,mid);

            int right[] = find_maximum_subarray(diff_A,mid+1,high);

            int cross[] = find_max_crossing_subarray(diff_A,low,mid,high);

            if(left[2]>=right[2] && left[2]>=cross[2]){
                outputs = new int[]{left[0],left[1],left[2]};
                return outputs;
            }
            else if(right[2]>=left[2] && right[2]>=cross[2]){
                outputs = new int[]{right[0],right[1],right[2]};
                return outputs;
            }
            else {
                outputs = new int[]{cross[0],cross[1],cross[2]};
                return outputs;
            }
        }

    }

    public static int[] find_max_crossing_subarray(int [] diff_A, int low, int mid, int high)
    {
        int outputs[];
        int left_sum = Integer.MIN_VALUE;
        int right_sum = Integer.MIN_VALUE;
        int sum = 0;
        int max_left = 0;
        int max_right = 0;
        for (int i=mid;low<=i;i--){
            sum = sum + diff_A[i];
            if(sum > left_sum){
                left_sum = sum;
                max_left = i;
            }
        }
        sum =0;
        for(int j=mid+1;j<=high;j++){
            sum = sum + diff_A[j];
            if(sum > right_sum){
                right_sum = sum;
                max_right = j;
            }
        }
        outputs = new int[]{max_left,max_right,left_sum+right_sum};
        return outputs;
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
