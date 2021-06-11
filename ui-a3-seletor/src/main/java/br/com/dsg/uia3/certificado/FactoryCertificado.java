package br.com.dsg.uia3.certificado;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import org.bouncycastle.crypto.RuntimeCryptoException;

import br.com.dsg.driver.DriverService;
import br.com.dsg.driver.IDriverServices;
import br.com.dsg.uia3.KeyStoreHandler;
import br.gov.dsg.certificado.CertificadoService;
import br.gov.dsg.certificado.entidades.A3WindowsMY;

public class FactoryCertificado{
	
	private static FactoryCertificado fac = new FactoryCertificado();
	private IDriverServices driverServices = new DriverService(); 
	private KeyStoreHandler keyStoreHandler;
	
	private static String OS = null;
	
	private FactoryCertificado() {
		OS = System.getProperty("os.name"); 
		if(OS.startsWith("Windows")) {
			try {
				this.keyStoreHandler = new KeyStoreHandler( new A3WindowsMY() );
			} catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException e) {
				e.printStackTrace();
			}
		}
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
