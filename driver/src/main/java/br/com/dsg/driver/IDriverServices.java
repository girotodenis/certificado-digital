package br.com.dsg.driver;

import java.util.List;

import br.gov.dsg.certificado.entidades.Local;

public interface IDriverServices {

	/**
	 * 
	 * @return
	 */
	List<Local> todos();

	/**
	 * 
	 * @return
	 */
	Local driverPadrao();

	/**
	 * 
	 * @param driver
	 */
	void driverPadrao(Local driver);
	
//	/**
//	 * @param callbackHandler
//	 * @return
//	 */
//	public KeyStore getKeyStoreA3(CallbackHandler callbackHandler);

	void adicionar(String text);

	void remover(Local item);

}