package coding_challenge;

import java.util.ArrayList;
import java.util.Random;

// TODO Code comments and at reference to task and check ungültige eingaben

public class VehicleNumberGenerator {
	public Integer generateVehicleNumber()
	{
		Random r = new Random();
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		numbers.add(1);
		for (int i = 0; i < 6; i++) {
			numbers.add(r.nextInt(10));
		}
		String number = numbers.get(0)+ "" +
						numbers.get(1)+ "" +
						numbers.get(2)+ "" +
						numbers.get(3)+ "" +
						numbers.get(4)+ "" +
						numbers.get(5)+ "" +
						numbers.get(6)+ "";
		return appendCheckDigitToNumber(Integer.valueOf(number));
	}
	
	public Integer appendCheckDigitToNumber(Integer numb)
	{
		String number = String.valueOf(numb);
		int total = berechneEndquersumme(Character.getNumericValue(number.charAt(0)) * 2) + 
					berechneEndquersumme(Character.getNumericValue(number.charAt(1)) * 1) + 
					berechneEndquersumme(Character.getNumericValue(number.charAt(2)) * 2) + 
					berechneEndquersumme(Character.getNumericValue(number.charAt(3)) * 1) + 
					berechneEndquersumme(Character.getNumericValue(number.charAt(4)) * 2) + 
					berechneEndquersumme(Character.getNumericValue(number.charAt(5)) * 1) + 
					berechneEndquersumme(Character.getNumericValue(number.charAt(6)) * 2); 
		int diffToNext10 = ermittleNaechstGroessereZehnerzahl(total)-total;
		return Integer.valueOf(number + "" + diffToNext10);
	}
	
    public static int ermittleNaechstGroessereZehnerzahl(int n) {
        return ((n / 10) + 1) * 10;
    }
    
    public static int berechneEndquersumme(int n) {
        int summe = 0;

        // Solange n mehr als eine Ziffer hat
        while (n > 0 || summe > 9) {
            if (n == 0) {
                n = summe;
                summe = 0;
            }
            summe += n % 10;
            n /= 10;
        }

        return summe;
    }
}
