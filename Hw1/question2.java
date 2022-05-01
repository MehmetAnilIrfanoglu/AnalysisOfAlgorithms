public class question2 {

    //finds n value for nlogn
    //it is a little bit long due to optimization
    //I tried to optimize my code in order to find my answer in a faster way
    public static void nlogn(int coefficent, int power) {
        long y = 0;
        while (true) {
            y += 1000000000;//
            double number = y * (Math.log(y) / Math.log(2));
            if (number > (coefficent * Math.pow(10, power))) {
                break;
            }
        }

        y = y - 1;
        String a = String.valueOf(y);
        char[] string = a.toCharArray();

        for (int j = string.length - 1; j >= 0; j--) {
            if (string[j] == '9') {
                string[j] = '0';
            } else {
                break;
            }
        }
        a = String.valueOf(string);

        long i = Long.parseLong(a);
        while (true) {
            i += 1;//
            double number = i * (Math.log(i) / Math.log(2));
            if (number > (coefficent * Math.pow(10, power))) {
                System.out.println("The value of n is "+(i - 1)+" for time "+coefficent+" * 10^"+power);
                break;
            }
        }
    }

    //main method to find n for n!
    public static void nfactorial(int coefficent,int power){
            int n=0;
            while(true){
                long r = fact(n);
                if (r > (coefficent * Math.pow(10, power))){
                    System.out.println("The value of n is "+(n - 1)+" for time "+coefficent+" * 10^"+power);
                    break;
                }
                n++;
            }
    }

    //basic factorial method
    public static long fact(long n){
            if (n == 0)
                return 1;
            else
                return n * fact(n-1);
    }

    public static void main(String[] args) {

        //printing n values for nlgn
        System.out.println("n values for nlgn function:");
        nlogn(1,6);
        nlogn(6,7);
        nlogn(36,8);
        nlogn(864,8);
        nlogn(2592,9);
        nlogn(31536,9);
        nlogn(31556736,8);
        System.out.println();

        //printing n values for n!
        System.out.println("n values for n! function:");
        nfactorial(1,6);
        nfactorial(6,7);
        nfactorial(36,8);
        nfactorial(864,8);
        nfactorial(2592,9);
        nfactorial(31536,9);
        nfactorial(31556736,8);


    }
}