import java.math.BigInteger;
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
        BigInteger decNumber = scanner.nextBigInteger();
        System.out.print("Enter target base: ");
        BigInteger targetBase = scanner.nextBigInteger();

        String result = "";
        BigInteger number = new BigInteger(decNumber.toString());
        while (number.compareTo(BigInteger.ZERO) > 0) {
            BigInteger digit = number.mod(targetBase);

            if (targetBase.compareTo(new BigInteger("16")) == 0 && digit.compareTo(BigInteger.TEN) >= 0) {
                String r;
                switch (digit.toString()) {
                    case "10":
                        r = "A";
                        break;
                    case "11":
                        r = "B";
                        break;
                    case "12":
                        r = "C";
                        break;
                    case "13":
                        r = "D";
                        break;
                    case "14":
                        r = "E";
                        break;
                    case "15":
                        r = "F";
                        break;
                    default:
                        throw new IllegalArgumentException();
                }
                result = r + result;
            } else {
                result = digit + result;
            }

            number = number.divide(targetBase);
        }

        System.out.printf("Conversion result: %s", result);
    }

    public static void convertToDec() {
        System.out.print("Enter source number: ");
        String sourceNumber = scanner.nextLine();
        System.out.print("Enter source base: ");
        BigInteger sourceBase = scanner.nextBigInteger();

        BigInteger result = BigInteger.ZERO;
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

            BigInteger multiplicator = sourceBase.pow(power);
            BigInteger d = multiplicator.multiply(BigInteger.valueOf(digit));
            result = result.add(d);
            power++;
        }

        System.out.printf("Conversion to decimal result: %d", result);
    }
}
