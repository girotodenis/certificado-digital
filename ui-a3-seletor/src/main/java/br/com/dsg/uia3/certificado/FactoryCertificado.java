package br.com.dsg.uia3.certificado;

import org.bouncycastle.crypto.RuntimeCryptoException;

import br.com.dsg.driver.DriverService;
import br.com.dsg.driver.IDriverServices;
import br.com.dsg.uia3.KeyStoreHandler;
import br.gov.dsg.certificado.CertificadoService;

public class FactoryCertificado{
	
	private static FactoryCertificado fac = new FactoryCertificado();
	private IDriverServices driverServices = new DriverService(); 
	private KeyStoreHandler keyStoreHandler;
	
	private FactoryCertificado() {
	}
	
	public static FactoryCertificado get() {
		return fac;
	}
	
	public CertificadoService getCertificadoServiceA3Token() {
		try {
			return new CertificadoService( keyStoreHandler );
		} catch (Exception e) {
			throw new RuntimeCryptoException(e.getLocalizedMessage());
		}
	}

	public void setKeyStoreHandler(KeyStoreHandler keyStoreHandler) {
		this.keyStoreHandler = keyStoreHandler;
	}

	public IDriverServices getDriverServices() {
		return driverServices;
	}

	public boolean isKeyStoreLoad() {
		return keyStoreHandler!=null;
	}

	
	
}
