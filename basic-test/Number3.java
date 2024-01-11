import java.util.Scanner;

public class Number3 {

    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        String time12Hour = scanner.nextLine();
        timeConversion(time12Hour);   
    }

    public static void timeConversion(String time12Hour){
        String[] timeSplit = time12Hour.split(":");
        int hour = Integer.parseInt(timeSplit[0]);
        int minute = Integer.parseInt(timeSplit[1]);

        StringBuilder sb = new StringBuilder();
        sb.append(timeSplit[2].charAt(0));
        sb.append(timeSplit[2].charAt(1));
        int second = Integer.parseInt(sb.toString());

        if (timeSplit[2].charAt(2) == 'A') {
            if (hour == 12) {
                hour = 0;
            }
        } else{
            if (hour < 12) {
                hour += 12;                
            }
        }
        String hourString =  hour < 10 ? "0" + String.valueOf(hour) : String.valueOf(hour);
        String minuteString = minute < 10 ? "0" + String.valueOf(minute) : String.valueOf(minute);
        String secondString = second < 10 ? "0" + String.valueOf(second) : String.valueOf(second);
        System.out.println(hourString+':'+minuteString+':'+secondString);
    }

}
