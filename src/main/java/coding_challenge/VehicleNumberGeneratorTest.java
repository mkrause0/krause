package coding_challenge;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class VehicleNumberGeneratorTest {

	VehicleNumberGenerator gen = new VehicleNumberGenerator();
	
	@Test
    public void testNextNumberOfTen() {
		assertEquals(VehicleNumberGenerator.getNextNumberOfTen(0), 10);
		assertEquals(VehicleNumberGenerator.getNextNumberOfTen(2), 10);
		assertEquals(VehicleNumberGenerator.getNextNumberOfTen(9), 10);
		assertEquals(VehicleNumberGenerator.getNextNumberOfTen(17), 20);
		assertEquals(VehicleNumberGenerator.getNextNumberOfTen(36), 40);
		assertEquals(VehicleNumberGenerator.getNextNumberOfTen(40), 50); // TODO Ist die nächste vielfache in diesem Fall 50?
	}
	
	@Test
    public void testCrossSum() {
		assertEquals(VehicleNumberGenerator.getCrossSum(9), 9);
		assertEquals(VehicleNumberGenerator.getCrossSum(19), 10);
		assertEquals(VehicleNumberGenerator.getCrossSum(25), 7);
		assertEquals(VehicleNumberGenerator.getCrossSum(37), 10);
		assertEquals(VehicleNumberGenerator.getCrossSum(45), 9);
	}
	
	@Test
    public void testAppendCheckDigitToNumber() {
		assertEquals(gen.appendCheckDigitToNumber("1014005"), "10140051");
		assertEquals(gen.appendCheckDigitToNumber("1116064"), "11160645");
		assertEquals(gen.appendCheckDigitToNumber("1142606"), "11426061");
		assertEquals(gen.appendCheckDigitToNumber("91811216141"), "918112161412");
		assertEquals(gen.appendCheckDigitToNumber("91811001250"), "9181100125010"); //TODO was wenn prüffziffer 2 stellig ist?
	}
	
	@Test
    public void testGenerateVehicleNumber7() {
		generateVehicleNumber7();
	}
	
	@Test
    public void testGenerateVehicleNumber11() {
		generateVehicleNumber11();
	}
	
	private void generateVehicleNumber7() {
		String vehicleNumber = gen.generateVehicleNumber7();
		String vehicleNumberWithoutCheckDigit = vehicleNumber.substring(0, vehicleNumber.length()-1);
		System.out.println("Number with check digit: " + vehicleNumber);
		assertEquals(gen.appendCheckDigitToNumber(vehicleNumberWithoutCheckDigit), vehicleNumber);
	}
	
	private void generateVehicleNumber11() {
		String vehicleNumber = gen.generateVehicleNumber11();
		String vehicleNumberWithoutCheckDigit = vehicleNumber.substring(0, vehicleNumber.length()-1);
		System.out.println("Number with check digit: " + vehicleNumber);
		assertEquals(gen.appendCheckDigitToNumber(vehicleNumberWithoutCheckDigit), vehicleNumber);
	}
}
