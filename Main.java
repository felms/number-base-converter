import java.util.Scanner;

public class Main {

    private static Scanner scanner;

    public static void main(String[] args) {

        scanner = new Scanner(System.in);
        String command = "";
        do {
            System.out.print("\nDo you want to convert /from decimal or /to decimal? (To quit type /exit) ");
            command = scanner.nextLine();
            
            switch(command) {
                case "/from":
                    convertFromDec();
                    break;
                case "/to":
                    convertToDec();
                    break;
                default:
                    continue;

            }
            System.out.println();
        } while ("/exit".compareTo(command) != 0);
        
        scanner.close();

    }

    public static void convertFromDec() {
        System.out.print("Enter number in decimal system: ");
        int decNumber = scanner.nextInt();
        System.out.print("Enter target base: ");
        int targetBase = scanner.nextInt();

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

        System.out.printf("Conversion result: %s", result);
    }

    public static void convertToDec() {
        System.out.print("Enter source number: ");
        String sourceNumber = scanner.nextLine();
        System.out.print("Enter source base: ");
        int sourceBase = scanner.nextInt();

        int result = 0;
        int power = 0;
        sourceNumber = new StringBuilder(sourceNumber).reverse().toString();
        for(char c : sourceNumber.toCharArray()) {
            
            int digit = 0;
            switch(c) {
                case 'A':
                    digit = 10;
                    break;
                case 'B':
                    digit = 11;
                    break;
                case 'C':
                    digit = 12;
                    break;
                case 'D':
                    digit = 13;
                    break;
                case 'E':
                    digit = 14;
                    break;
                case 'F':
                    digit = 15;
                    break;
                default:
                    digit = Character.getNumericValue(c);
            }

            int multiplicator = (int) Math.pow(sourceBase, power);
            digit *= multiplicator;
            result += digit;
            power++;
        }

        System.out.printf("Conversion to decimal result: %d", result);
    }
}
