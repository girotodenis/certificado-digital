package br.com.dsg.driver;

import java.util.List;

import br.com.dsg.driver.entidade.DriverA3;
import br.com.dsg.driver.resoures.Resource;
import br.com.dsg.driver.resoures.ResourceProperties;
import br.gov.dsg.certificado.entidades.Local;

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
	public List<Local> todos() {

		return resource.todosDrivers();
	}

	/**
	 * 
	 * @return
	 */
	@Override
	public Local driverPadrao() {
		
		List<Local> lista = todos();
		Local driverPadrao = null;
		for (Local driverA3 : lista) {
			if (((DriverA3)driverA3).isPadrao()) {
				driverPadrao = driverA3;
			}
		}
		
		if (driverPadrao == null) {
			driverPadrao = lista.get(0);
			((DriverA3)driverPadrao).defineComoPadrao();
			resource.salvarPadrao(driverPadrao);
		}
		
		return driverPadrao;
	}

	/**
	 * 
	 * @param driver
	 */
	@Override
	public void driverPadrao(Local driver) {
		
		((DriverA3)driver).defineComoPadrao();
		resource.salvarPadrao(driver);
	}
	
	@Override
	public void adicionar(String text) {
		resource.adicionar(text);
	}

	@Override
	public void remover(Local item) {
		if(todos().size()>1) {
			resource.remover(item);
		}
	}
	
	

}
