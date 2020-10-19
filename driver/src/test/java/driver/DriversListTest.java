package driver;

import static org.testng.Assert.assertNotNull;

import org.testng.annotations.Test;

import br.com.dsg.driver.DriverService;
import br.com.dsg.driver.IDriverServices;

public class DriversListTest {

	@Test
	public void deve_encontrar_drivers() {
		IDriverServices service = new DriverService();
		assertNotNull(service.driverPadrao());
	}
}
