package coding_challenge;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class VehicleNumberGeneratorTest {

	VehicleNumberGenerator gen = new VehicleNumberGenerator();
	
	@Test
    public void testVehicleNumberGenerator() {
		assertEquals(gen.appendCheckDigitToNumber("91811216141"), "918112161412");
		generateVehicleNumber();
		generateVehicleNumber();
		generateVehicleNumber();
    }
	
	public void generateVehicleNumber() {
		String vehicleNumber = gen.generateVehicleNumber();
		String vehicleNumberWithoutCheckDigit = vehicleNumber.substring(0, vehicleNumber.length()-1);
		System.out.println(vehicleNumber);
		assertEquals(gen.appendCheckDigitToNumber(vehicleNumberWithoutCheckDigit), vehicleNumber);
	}
}
