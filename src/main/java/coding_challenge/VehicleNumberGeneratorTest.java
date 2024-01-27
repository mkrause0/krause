package coding_challenge;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class VehicleNumberGeneratorTest {

	VehicleNumberGenerator gen = new VehicleNumberGenerator();
	
	@Test
    public void testVehicleNumberGenerator() {
		assertEquals(gen.appendCheckDigitToNumber(91811216141l).longValue(), 918112161412l);
		generateVehicleNumber();
		generateVehicleNumber();
		generateVehicleNumber();
    }
	
	public void generateVehicleNumber() {
		Long vehicleNumber = gen.generateVehicleNumber();
		String vehicleNumberAsString = String.valueOf(vehicleNumber);
		Long vehicleNumberWithoutCheckDigit = Long.valueOf(vehicleNumberAsString.substring(0, vehicleNumberAsString.length()-1));
		System.out.println(vehicleNumber);
		assertEquals(gen.appendCheckDigitToNumber(vehicleNumberWithoutCheckDigit), vehicleNumber);
	}
}
