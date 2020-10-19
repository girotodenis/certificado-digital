package br.com.dsg.uia3;

import br.com.dsg.driver.DriverService;
import br.com.dsg.driver.IDriverServices;
import br.gov.dsg.certificado.CertificadoService;
import br.gov.dsg.certificado.entidades.A3Pkcs11;
import br.gov.dsg.certificado.entidades.TipoKeyStore;

public class FactoryCertificadoA3Driver {
	
	private static FactoryCertificadoA3Driver fac = new FactoryCertificadoA3Driver();
	
	private FactoryCertificadoA3Driver() {
	}
	
	public static FactoryCertificadoA3Driver getInstance() {
		return fac;
	}
	
	
	public CertificadoService getCertificadoServiceA3Token() {
		
		TipoKeyStore tipo = new A3Pkcs11();
		IDriverServices driverServices = new DriverService(); 
		
		return new CertificadoService(
				new SwingSelecaoTipoCertificadoHandler(tipo, driverServices)
		);
	}


}
