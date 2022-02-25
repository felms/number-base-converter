import java.math.BigInteger;

public class Converter {
    public static String convertFromDec(BigInteger decNumber, BigInteger targetBase) {
        
        String result = "";
        BigInteger number = new BigInteger(decNumber.toString());
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
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

    public static BigInteger convertToDec(String sourceNumber, BigInteger sourceBase) {
        
        BigInteger result = BigInteger.ZERO;
        int power = 0;
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
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
}
