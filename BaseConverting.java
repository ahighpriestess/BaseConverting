import java.util.Scanner;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class BaseConversion {


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        String theValue = "";
        int initialBase = 0;
        int desiredBase = 0;
        

        if (args.length > 0) {
         try {
           theValue = (args[0]).toUpperCase();
           initialBase = Integer.parseInt(args[1]);
           desiredBase = Integer.parseInt(args[2]);
         }
         catch (Exception e) {
           System.out.println("Error.");
         }
         
        }

        else {
          try {
           System.out.println("Welcome to the Base Conversion Calculatonr!");
           System.out.println("Enter the initial value that you would like to be converted: ");
           theValue = input.nextLine().toUpperCase();

           System.out.println("Enter the base of the entered value: ");
           initialBase = Integer.parseInt(input.nextLine());

           System.out.println("Enter the desired base: ");
           desiredBase = Integer.parseInt(input.nextLine());
          }
          catch (Exception e) {
           System.out.println("Error.");
          }

        }

        

        if (!isValidInteger(theValue, initialBase) || !isValidBase(initialBase, desiredBase)) {
            System.out.println("Invalid entry.");
            System.exit(0);
        }

        System.out.println(convertInteger(theValue, initialBase, desiredBase));

        input.close();
    }
    
    public static boolean isValidInteger(String theValue, int theBase) {
        List<Character> validNumbers = new ArrayList<Character>();

        for (int i = 0; i <= 9 && i < theBase; i++) {
            validNumbers.add((char) ('0' + i));
        }
        if (theBase >= 10) {
            for (int i = 0; i <= theBase - 10; i++) {
                validNumbers.add((char) ('A' + i));
            }
        }
        for (char x : theValue.toCharArray()) {
            if (!validNumbers.contains(x)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidBase(int initialBase, int finalBase) {

        if (initialBase > 36 || initialBase < 2 || finalBase > 36 || initialBase < 2) {
           return false;
        }
        return true;
    }
  
    public static String convertInteger(String theValue, int initialBase, int finalBase) {
        BigInteger firstValue = BigInteger.ZERO;

        for (char theChar : theValue.toCharArray()) {
            BigInteger fullValue = firstValue.multiply(new BigInteger(String.valueOf(initialBase)));
            firstValue = fullValue.add(new BigInteger(String.valueOf(getChar(theChar))));
        }

        
        String result = "";

        BigInteger finalValue = new BigInteger(String.valueOf(finalBase));

        while (!firstValue.equals(BigInteger.ZERO)) {
            BigInteger remainder = firstValue.mod(finalValue);
            result = getInt(remainder.intValue()) + result;
            firstValue = firstValue.divide(finalValue);
        }

        return result;
    }
    
    private static int getChar(char theChar) {
        if (theChar <= '9' && theChar >= '0') {
            return theChar - '0';
        }
        return theChar - 'A' + 10;
    }

    private static char getInt(int theNum) {
        if (theNum <= 9 && theNum >= 0) {
            return (char) (theNum + '0');
        }
        return (char) ((theNum - 10) + 'A');
    }

}
