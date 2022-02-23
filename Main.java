import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number in decimal system: ");
        int decNumber = scanner.nextInt();
        System.out.print("Enter target base: ");
        int targetBase = scanner.nextInt();
        System.out.printf("Conversion result: %s\n", convert(decNumber, targetBase));

    }

    public static String convert(int decNumber, int targetBase) {

        String result = "";
        int number = decNumber;
        while (number > 0) {
            int digit = number % targetBase;

            if (targetBase == 16 && digit >= 10) {
                String r;
                switch (digit) {
                    case 10:
                        r = "A";
                        break;
                    case 11:
                        r = "B";
                        break;
                    case 12:
                        r = "C";
                        break;
                    case 13:
                        r = "D";
                        break;
                    case 14:
                        r = "E";
                        break;
                    case 15:
                        r = "F";
                        break;
                    default:
                        throw new IllegalArgumentException();
                }
                result = r + result;
            } else {
                result = digit + result;
            }

            number /= targetBase;
        }

        return result;
    }
}
