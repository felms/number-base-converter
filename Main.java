import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;

public class Main {

    private static Scanner scanner;

    public static void main(String[] args) {

        scanner = new Scanner(System.in);
        String command = "";
        boolean exit = false;
        while(!exit) {
            System.out.print("\nEnter two numbers in format: {source base} {target base} (To quit type /exit) ");
            command = scanner.nextLine();

            if ("/exit".equals(command)) {
                exit = true;
            } else {
                
                String[] s = command.split("\\s+");
                boolean back = false;
                while (!back) {
                    System.out.printf("\n\nEnter number in base %s to convert to base %s (To go back type /back) ", s[0], s[1]);
                    String c = scanner.nextLine();
                    if (c.equals("/back")) {
                        back = true;
                    } else {
                        String result;
                        if (s[1].equals("10")) {
                            result = Converter.convertToDec(c, Integer.valueOf(s[0])).toString();
                        } else if (s[0].equals("10")) {
                            result = Converter.convertFromDec(new BigDecimal(c), Integer.parseInt(s[1]));
                        } else {
                            BigDecimal aux = Converter.convertToDec(c, Integer.parseInt(s[0]));
                            result = Converter.convertFromDec(aux, Integer.parseInt(s[1]));
                        }                        
                        System.out.printf("Conversion result: %s", result);
                    }
                }
            }
            
        }
        
        scanner.close();

    }
    
}
