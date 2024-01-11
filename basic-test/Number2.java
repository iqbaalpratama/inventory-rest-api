import java.util.Scanner;

public class Number2 {
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        int n = scanner.nextInt();
        if (n <= 0 || n > 100) {
            return;
        }
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
                arr[i] = scanner.nextInt();
        }
        plusMinus(arr);   
    }

    public static void plusMinus(int[] arr){
        int countPositive = 0;
        int countNegative = 0;
        int countZero = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 0) {
                countPositive++;
            }else if (arr[i] < 0) {
                countNegative++;
            }else{
                countZero++;
            }
        }
        double positive = ((double)countPositive)/arr.length;
        double negative = ((double)countNegative)/arr.length;
        double zero = ((double)countZero)/arr.length;
        System.out.println(String.format("%.6f", positive));
        System.out.println(String.format("%.6f", negative));
        System.out.println(String.format("%.6f", zero));

    }
}
