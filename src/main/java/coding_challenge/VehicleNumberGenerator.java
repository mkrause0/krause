package coding_challenge;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class VehicleNumberGenerator {
	
	private static final Logger LOGGER = LogManager.getLogger(VehicleNumberGenerator.class);

	// TODO parameterize Reihennummer  
	/**
	 * generates an 7 digit long vehicle number with Reihennummer 1001 and an random Ordnungsnummer
	 */
	public String generateVehicleNumber7()
	{
		Random r = new Random();
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		numbers.add(1); // Reihennummer (Elektrolokomotive = 1; 1001–1019: Schnellzuglokomotiven)
		numbers.add(0); // Reihennummer 
		numbers.add(0); // Reihennummer
		numbers.add(1); // Reihennummer
		numbers.add(r.nextInt(10)); // Ordnungsnummer
		numbers.add(r.nextInt(10)); // Ordnungsnummer
		numbers.add(r.nextInt(10)); // Ordnungsnummer
		String number = numbers.stream()
	               .map(Object::toString)
	               .collect(Collectors.joining());
		return appendCheckDigitToNumber(number);
	}

	// TODO parameterize Bauartcode, Reihennummer
	/**
	 * generates an 11 digit long vehicle number with Bauartcode 91, Ländercode 81, Reihennummer 1001 and an random Ordnungsnummer
	 */
	public String generateVehicleNumber11()
	{
		Random r = new Random();
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		numbers.add(9); // Bauartcode (Triebfahrzeug = 9)
		numbers.add(1); // Bauartcode
		numbers.add(8); // Ländercode
		numbers.add(1); // Ländercode
		numbers.add(1); // Reihennummer (Elektrolokomotive = 1; 1001–1019: Schnellzuglokomotiven)
		numbers.add(0); // Reihennummer
		numbers.add(0); // Reihennummer
		numbers.add(1); // Reihennummer
		numbers.add(r.nextInt(10)); // Ordnungsnummer
		numbers.add(r.nextInt(10)); // Ordnungsnummer
		numbers.add(r.nextInt(10)); // Ordnungsnummer
		String number = numbers.stream()
	               .map(Object::toString)
	               .collect(Collectors.joining());
		return appendCheckDigitToNumber(number);
	}

	/**
	 * Each character is multiply by 1 or 2 and a cross sum is formed. All the sums are then added together.
	 */
	public String appendCheckDigitToNumber(String number)
	{
		LOGGER.debug("Number without check digit:" + number);
		int total = 0;
		for (int i = 0; i < number.length(); i++) {
			int digit = Character.getNumericValue(number.charAt(i));
			int multiply = (i % 2 == 0 ? 2 : 1);
			int digitWithMultiply = digit * multiply;
			int crossSum = getCrossSum(digitWithMultiply);
			total += crossSum;
			LOGGER.debug(digit + " * " + multiply + " = " + digitWithMultiply + " = " + crossSum + " (crosssum)");
			
		}
		LOGGER.debug("Total: " + total);
		int nextNumberOfTen = getNextNumberOfTen(total);
		int diffToNext10 = nextNumberOfTen-total;
		LOGGER.debug("diffToNext10 ("+nextNumberOfTen+"-"+total+"): " + diffToNext10);
		return number + "" + diffToNext10;
	}
	
	/**
	 * determines the next larger number of 10
	 * diff to the next larger number of 10: (10−9) =  1
	 * special case if n is already an next of 10: (30-30) = 0
	 */
    public static int getNextNumberOfTen(int n) {
    	if (n != 0 && n % 10 == 0)
    		return n;
    	
        return ((n / 10) + 1) * 10;
    }
    
    
    /**
     * calculates the cross sum
     */
    public static int getCrossSum(int zahl) {
        int summe = 0;
        while (zahl != 0) {
            summe += zahl % 10;
            zahl /= 10;
        }
        return summe;
    }
}
