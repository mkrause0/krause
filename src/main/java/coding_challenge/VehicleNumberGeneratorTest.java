package coding_challenge;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class VehicleNumberGeneratorTest {

	VehicleNumberGenerator gen = new VehicleNumberGenerator();
	
	@Test
    public void testVehicleNumberGenerator() {
		assertEquals(gen.appendCheckDigitToNumber("1014005"), "10140051");
		assertEquals(gen.appendCheckDigitToNumber("1116064"), "11160645");
		assertEquals(gen.appendCheckDigitToNumber("1142606"), "11426061");
		assertEquals(gen.appendCheckDigitToNumber("91811216141"), "918112161412");
		generateVehicleNumber7();
		generateVehicleNumber7();
		generateVehicleNumber7();
		generateVehicleNumber11();
		generateVehicleNumber11();
		generateVehicleNumber11();
    }
	
	public void generateVehicleNumber7() {
		String vehicleNumber = gen.generateVehicleNumber7();
		String vehicleNumberWithoutCheckDigit = vehicleNumber.substring(0, vehicleNumber.length()-1);
		System.out.println(vehicleNumber);
		assertEquals(gen.appendCheckDigitToNumber(vehicleNumberWithoutCheckDigit), vehicleNumber);
	}
	
	public void generateVehicleNumber11() {
		String vehicleNumber = gen.generateVehicleNumber11();
		String vehicleNumberWithoutCheckDigit = vehicleNumber.substring(0, vehicleNumber.length()-1);
		System.out.println(vehicleNumber);
		assertEquals(gen.appendCheckDigitToNumber(vehicleNumberWithoutCheckDigit), vehicleNumber);
	}
}
