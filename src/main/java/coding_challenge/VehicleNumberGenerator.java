package coding_challenge;

import java.util.ArrayList;
import java.util.Random;

public class VehicleNumberGenerator {
	
	public String generateVehicleNumber7()
	{
		Random r = new Random();
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		numbers.add(r.nextInt(10)); // Reihennummer (Elektrolokomotive = 1)
		numbers.add(r.nextInt(10)); // Reihennummer
		numbers.add(r.nextInt(10)); // Reihennummer
		numbers.add(r.nextInt(10)); // Reihennummer
		numbers.add(r.nextInt(10)); // Ordnungsnummer
		numbers.add(r.nextInt(10)); // Ordnungsnummer
		numbers.add(r.nextInt(10)); // Ordnungsnummer
		String number = numbers.get(0)+ "" +
						numbers.get(1)+ "" +
						numbers.get(2)+ "" +
						numbers.get(3)+ "" +
						numbers.get(4)+ "" +
						numbers.get(5)+ "" +
						numbers.get(6)+ "";
		return appendCheckDigitToNumber(number);
	}
	
	public String generateVehicleNumber11()
	{
		Random r = new Random();
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		numbers.add(9); // Bauartcode (Triebfahrzeug = 9)
		numbers.add(1); // Bauartcode
		numbers.add(8); // Ländercode
		numbers.add(1); // Ländercode
		numbers.add(1); // Reihennummer (Elektrolokomotive = 1)
		numbers.add(2); // Reihennummer
		numbers.add(1); // Reihennummer
		numbers.add(6); // Reihennummer
		numbers.add(r.nextInt(10)); // Ordnungsnummer
		numbers.add(r.nextInt(10)); // Ordnungsnummer
		numbers.add(r.nextInt(10)); // Ordnungsnummer
		String number = numbers.get(0)+ "" +
						numbers.get(1)+ "" +
						numbers.get(2)+ "" +
						numbers.get(3)+ "" +
						numbers.get(4)+ "" +
						numbers.get(5)+ "" +
						numbers.get(6)+ "" +
						numbers.get(7)+ "" +
						numbers.get(8)+ "" +
						numbers.get(9)+ "" +
						numbers.get(10)+ "";
		return appendCheckDigitToNumber(number);
	}

	/*
	 * Jedes Zeichen wird abwechselnd mal 1 oder mal 2 genommen und eine Quersumme gebildet. Anschließend werden alle Quersummen addiert.
	 */
	public String appendCheckDigitToNumber(String number)
	{
		int total = 0;
		for (int i = 0; i < number.length(); i++) {
			total += berechneEndquersumme(Character.getNumericValue(number.charAt(i)) * (i % 2 == 0 ? 2 : 1));
			
		}
		int diffToNext10 = ermittleNaechstGroessereZehnerzahl(total)-total;
		return number + "" + diffToNext10;
	}
	
    public static int ermittleNaechstGroessereZehnerzahl(int n) {
        return ((n / 10) + 1) * 10;
    }
    
    
    public static int berechneEndquersumme(int zahl) {
        int summe = 0;
        while (zahl != 0) {
            summe += zahl % 10;
            zahl /= 10;
        }
        return summe;
    }
}
