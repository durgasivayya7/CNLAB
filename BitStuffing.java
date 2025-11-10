import java.util.*;

class BitStuffing {

    static String bitStuff(String data) {
        String flag = "01111110";  
        StringBuilder stuffedData = new StringBuilder();
        stuffedData.append(flag);  
        int count = 0;

        for (int i = 0; i < data.length(); i++) {
            char bit = data.charAt(i);
            stuffedData.append(bit);

            if (bit == '1') {
                count++;
                if (count == 5) {
                
                    stuffedData.append('0');
                    count = 0;
                }
            } else {
                count = 0;
            }
        }

        stuffedData.append(flag); 
        return stuffedData.toString();
    }

   
    static String bitUnstuff(String stuffedData) {
        String flag = "01111110";
       
        String data = stuffedData.substring(flag.length(), stuffedData.length() - flag.length());

        StringBuilder unstuffed = new StringBuilder();
        int count = 0;

        for (int i = 0; i < data.length(); i++) {
            char bit = data.charAt(i);
            unstuffed.append(bit);

            if (bit == '1') {
                count++;
                if (count == 5) {
             
                    i++;
                    count = 0;
                }
            } else {
                count = 0;
            }
        }

        return unstuffed.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of frames to send: ");
        int n = sc.nextInt();
        sc.nextLine();  
        

        for (int i = 1; i <= n; i++) {
            System.out.print("\nEnter bit data for frame " + i + ": ");
            String data = sc.nextLine();

            String stuffed = bitStuff(data);
            String unstuffed = bitUnstuff(stuffed);

            System.out.println("\nFrame " + i + " → Original Data : " + data);
            System.out.println("Frame " + i + " → Stuffed Data  : " + stuffed);
            System.out.println("Frame " + i + " → Unstuffed Data: " + unstuffed);
        }

        sc.close();
    }
}
