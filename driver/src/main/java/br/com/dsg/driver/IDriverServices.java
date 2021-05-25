package br.com.dsg.driver;

import java.util.List;

import br.com.dsg.driver.entidade.DriverA3;

public interface IDriverServices {

	/**
	 * 
	 * @return
	 */
	List<DriverA3> todos();

	/**
	 * 
	 * @return
	 */
	DriverA3 driverPadrao();

	/**
	 * 
	 * @param driver
	 */
	void driverPadrao(DriverA3 driver);
	
//	/**
//	 * @param callbackHandler
//	 * @return
//	 */
//	public KeyStore getKeyStoreA3(CallbackHandler callbackHandler);

	void adicionar(String text);

	void remover(DriverA3 item);

}