import java.util.Scanner;

public class Number1 {

    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        int[] arr = new int[5];
        String[] arrItems = scanner.nextLine().split(" ");
        for (int i = 0; i < 5; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }
        miniMaxSum(arr);   
    }

    public static void miniMaxSum(int[] arr){
        long sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        long max = sum - arr[0];
        long min = sum - arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (sum-arr[i] < min) {
                min = sum - arr[i];
            }
            if (sum-arr[i] > max) {
                max = sum - arr[i];
            }
        }
        System.out.println(min+" "+max);

    }

}
