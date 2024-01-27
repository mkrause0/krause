package coding_challenge;

import java.util.ArrayList;
import java.util.Random;

public class VehicleNumberGenerator {
	
	public Long generateVehicleNumber()
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
		return appendCheckDigitToNumber(Long.valueOf(number));
	}
	
	public Long appendCheckDigitToNumber(Long numb)
	{
		String number = String.valueOf(numb);
		int total = berechneEndquersumme(Character.getNumericValue(number.charAt(0)) * 2) + 
					berechneEndquersumme(Character.getNumericValue(number.charAt(1)) * 1) + 
					berechneEndquersumme(Character.getNumericValue(number.charAt(2)) * 2) + 
					berechneEndquersumme(Character.getNumericValue(number.charAt(3)) * 1) + 
					berechneEndquersumme(Character.getNumericValue(number.charAt(4)) * 2) + 
					berechneEndquersumme(Character.getNumericValue(number.charAt(5)) * 1) + 
					berechneEndquersumme(Character.getNumericValue(number.charAt(6)) * 2) +
					berechneEndquersumme(Character.getNumericValue(number.charAt(7)) * 1) +
					berechneEndquersumme(Character.getNumericValue(number.charAt(8)) * 2) +
					berechneEndquersumme(Character.getNumericValue(number.charAt(9)) * 1) +
					berechneEndquersumme(Character.getNumericValue(number.charAt(10)) * 2);
		int diffToNext10 = ermittleNaechstGroessereZehnerzahl(total)-total;
		return Long.valueOf(number + "" + diffToNext10);
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
