public class question1 {
    public static void main(String[] args) {

        long n = 1;
        //There is a basic comparision while n value is increasing
        while (true) {
            n += 1;
            long a = (long) (100 * Math.pow(n, 2));
            long b = (long) Math.pow(2, n);
            if (a <= b) {
                System.out.println("The value of n is "+n);
                break;
            }
        }
    }
}
