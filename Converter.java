import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class Converter {

    private static String alphabet = "abcdefghijklmnopqrstuvwxyz";
    
    public static String convertFromDec(BigDecimal decNumber, int targetBase) {

        BigInteger integerPart = decNumber.toBigInteger();
        BigDecimal fractionalPart = decNumber.remainder(BigDecimal.ONE);

        String intPart = convertIntegerFromDec(integerPart, BigInteger.valueOf(targetBase));

        String result = intPart;

        if (!fractionalPart.equals(BigDecimal.ZERO)) {

            String fracPart = convertFractionalFromDec(fractionalPart, targetBase);
            if (fracPart.length() > 5) {
                fracPart = fracPart.substring(0, 5);
            } else {
				while (fracPart.length() < 5) {
					fracPart += "0";
				}
			}

            result += "." + fracPart;
        }
        

        return result;
    }

    private static String convertIntegerFromDec(BigInteger decNumber, BigInteger targetBase) {
        
        if (decNumber.equals(BigInteger.ZERO)) {
            return "0";
        }

        String result = "";
        BigInteger number = new BigInteger(decNumber.toString());
        while (number.compareTo(BigInteger.ZERO) > 0) {
            int digit = Integer.parseInt(number.mod(targetBase).toString());

            if (digit >= 10) {
                String r = String.valueOf(alphabet.charAt(digit - 10));                
                result = r + result;
            } else {
                result = digit + result;
            }

            number = number.divide(targetBase);
        }

        return result;
    }

    private static String convertFractionalFromDec(BigDecimal decNumber, int targetBase) {
        
        
        String result = "";
        BigInteger fracPart = decNumber.movePointRight(decNumber.scale()).abs().toBigInteger();
        BigDecimal targetB = BigDecimal.valueOf(targetBase);
        int digit = 0;
        BigDecimal number = new BigDecimal(decNumber.toString());
        
        while (!fracPart.equals(BigInteger.ZERO) && result.length() <= 5) {
            number = number.multiply(targetB);
            digit = number.intValue();

            if (digit >= 10) {
                String r = String.valueOf(alphabet.charAt(digit - 10));                
                result = result + r;
            } else {
                result = result + digit;
            }
            number = number.subtract(new BigDecimal(digit));
            fracPart = number.movePointRight(decNumber.scale()).abs().toBigInteger();
        }        

        return result;
    }

    public static BigDecimal convertToDec(String sourceNumber, int sourceBase) {

        String[] parts = sourceNumber.split("\\.");
        String integerPart = parts[0];
        String fractionalPart = parts.length > 1 ? parts[1] : "0";

        BigInteger intPart = convertIntegerToDec(integerPart, BigInteger.valueOf(sourceBase));

        BigDecimal total = new BigDecimal(intPart);

        if (parts.length > 1) {
            BigDecimal fracPart = convertFractionalToDec(fractionalPart, BigDecimal.valueOf(sourceBase));
            total = total.add(fracPart);
            if (total.scale() != 5) {
                total = total.setScale(5, RoundingMode.HALF_EVEN);
            }
        }

        return total;
    }

    private static BigInteger convertIntegerToDec(String sourceNumber, BigInteger sourceBase) {
        
        BigInteger result = BigInteger.ZERO;
        int power = 0;
        
        sourceNumber = new StringBuilder(sourceNumber).reverse().toString().toLowerCase();
        for(char c : sourceNumber.toCharArray()) {
            
            int digit = Character.isDigit(c) ? Character.getNumericValue(c) : alphabet.indexOf(c) + 10;            

            BigInteger multiplicator = sourceBase.pow(power);
            BigInteger d = multiplicator.multiply(BigInteger.valueOf(digit));
            result = result.add(d);
            power++;
        }

        return result;
    }

    private static BigDecimal convertFractionalToDec(String sourceNumber, BigDecimal sourceBase) {

        BigDecimal total = BigDecimal.ZERO;

        sourceNumber = new StringBuilder(sourceNumber).reverse().toString().toLowerCase();
        for (char c : sourceNumber.toCharArray()) {

            int digit = Character.isDigit(c) ? Character.getNumericValue(c) : alphabet.indexOf(c) + 10;
            total = total.add(BigDecimal.valueOf(digit)).divide(sourceBase, 10, RoundingMode.HALF_EVEN);
        }

        return total;
    }
}
