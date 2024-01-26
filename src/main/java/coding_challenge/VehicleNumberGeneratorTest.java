package coding_challenge;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class VehicleNumberGeneratorTest {

	// TODO Code comments
	VehicleNumberGenerator gen = new VehicleNumberGenerator();
	
	@Test
    public void testVehicleNumberGenerator() {
		assertEquals(gen.appendCheckDigitToNumber(1014005).intValue(), 10140051);
		assertEquals(gen.appendCheckDigitToNumber(1116064).intValue(), 11160645);
		assertEquals(gen.appendCheckDigitToNumber(1142606).intValue(), 11426061);
		
		Integer vehicleNumber = gen.generateVehicleNumber();
		String vehicleNumberAsString = String.valueOf(vehicleNumber);
		Integer vehicleNumberWithoutCheckDigit = Integer.valueOf(vehicleNumberAsString.substring(0, vehicleNumberAsString.length()-1));
		System.out.println(vehicleNumber);
		System.out.println(vehicleNumberWithoutCheckDigit);
		assertEquals(gen.appendCheckDigitToNumber(vehicleNumberWithoutCheckDigit).intValue(), vehicleNumber.intValue());

    }
}
