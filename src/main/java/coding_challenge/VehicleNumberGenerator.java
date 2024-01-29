package coding_challenge;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * instead of system.out use an log4j logger
 */
public class VehicleNumberGenerator {
	
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

	// TODO parameterize Bauartcode, Reihennummer and Ordnungsnummer
	/**
	 * generates an 11 digit long vehicle number with Bauartcode 91, Reihennummer 1001 and an random Ordnungsnummer
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
		System.out.println("Number without check digit:" + number);
		int total = 0;
		for (int i = 0; i < number.length(); i++) {
			int digit = Character.getNumericValue(number.charAt(i));
			int multiply = (i % 2 == 0 ? 2 : 1);
			int digitWithMultiply = digit * multiply;
			int crossSum = getCrossSum(digitWithMultiply);
			total += crossSum;
			System.out.println(digit + "*" + multiply + "=" + digitWithMultiply + "=" + crossSum);
			
		}
		System.out.println("Total: " + total);
		int nextNumberOfTen = getNextNumberOfTen(total);
		int diffToNext10 = nextNumberOfTen-total;
		if(diffToNext10 == 10)
		{
			//TODO was soll dann gemacht werden, die Prüfziffer auf 0 gesetz werden?
		}
		System.out.println("diffToNext10 ("+nextNumberOfTen+"-"+total+"): " + diffToNext10);
		return number + "" + diffToNext10;
	}
	
	/**
	 * determines the next larger number of 10
	 * TODO: Differenz zum nächsten Vielfachen von 10: (10−9) =  1
	 * 		 Bei einer Zahl 20 wäre das nächste vielfache 30. Und das Ergebniss dann zweistellig: (30-20) = 10.
	 *       Das Vorgehen wird aus dem Artikel in diesem Fall nicht ersichtbar.
	 */
    public static int getNextNumberOfTen(int n) {
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
