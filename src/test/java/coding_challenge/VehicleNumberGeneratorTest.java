package coding_challenge;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


import common.AppConfig;

import static org.junit.Assert.assertEquals;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class VehicleNumberGeneratorTest {
	private static final Logger LOGGER = LogManager.getLogger(VehicleNumberGeneratorTest.class);

	@Autowired
	VehicleNumberGenerator gen;
	
	
	@Test
    public void testNextNumberOfTen() {
		assertEquals(VehicleNumberGenerator.getNextNumberOfTen(0), 10);
		assertEquals(VehicleNumberGenerator.getNextNumberOfTen(2), 10);
		assertEquals(VehicleNumberGenerator.getNextNumberOfTen(9), 10);
		assertEquals(VehicleNumberGenerator.getNextNumberOfTen(17), 20);
		assertEquals(VehicleNumberGenerator.getNextNumberOfTen(36), 40);
		assertEquals(VehicleNumberGenerator.getNextNumberOfTen(20), 20);
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
		assertEquals(gen.appendCheckDigitToNumber("91811001250"), "918110012500"); // special case if n is already an next of 10: (30-30) = 0
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
		LOGGER.debug("Number with check digit: " + vehicleNumber);
		assertEquals(gen.appendCheckDigitToNumber(vehicleNumberWithoutCheckDigit), vehicleNumber);
	}
	
	private void generateVehicleNumber11() {
		String vehicleNumber = gen.generateVehicleNumber11();
		String vehicleNumberWithoutCheckDigit = vehicleNumber.substring(0, vehicleNumber.length()-1);
		LOGGER.debug("Number with check digit: " + vehicleNumber);
		assertEquals(gen.appendCheckDigitToNumber(vehicleNumberWithoutCheckDigit), vehicleNumber);
	}
}
