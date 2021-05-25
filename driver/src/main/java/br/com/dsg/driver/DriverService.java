package br.com.dsg.driver;

import java.util.List;

import br.com.dsg.driver.entidade.DriverA3;
import br.com.dsg.driver.resoures.Resource;
import br.com.dsg.driver.resoures.ResourceProperties;

public class DriverService implements IDriverServices {

	private Resource resource;

	/**
	 * @param resource
	 */
	public DriverService(Resource resource) {
		
		super();
		this.resource = resource;
	}

	/**
	 * 
	 */
	public DriverService() {
		
		this(new ResourceProperties());
	}

	/**
	 * 
	 * @return
	 */
	@Override
	public List<DriverA3> todos() {

		return resource.todosDrivers();
	}

	/**
	 * 
	 * @return
	 */
	@Override
	public DriverA3 driverPadrao() {
		
		List<DriverA3> lista = todos();
		DriverA3 driverPadrao = null;
		for (DriverA3 driverA3 : lista) {
			if ( driverA3.isPadrao() ) {
				driverPadrao = driverA3;
			}
		}
		
		if (driverPadrao == null) {
			driverPadrao = lista.get(0);
			driverPadrao.defineComoPadrao();
			resource.salvarPadrao(driverPadrao);
		}
		
		return driverPadrao;
	}

	/**
	 * 
	 * @param driver
	 */
	@Override
	public void driverPadrao(DriverA3 driver) {
		
		driver.defineComoPadrao();
		resource.salvarPadrao(driver);
	}
	
	@Override
	public void adicionar(String text) {
		resource.adicionar(text);
	}

	public void remover(DriverA3 item) {
		if(todos().size()>1) {
			resource.remover(item);
		}
	}
	
	

}
