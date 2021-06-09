package br.com.dsg.uia3;

import br.com.dsg.driver.DriverService;
import br.com.dsg.driver.IDriverServices;
import br.gov.dsg.certificado.CertificadoService;

public class FactoryCertificadoA3Driver {
	
	private static FactoryCertificadoA3Driver fac = new FactoryCertificadoA3Driver();
	
	private FactoryCertificadoA3Driver() {
	}
	
	public static FactoryCertificadoA3Driver getInstance() {
		return fac;
	}
	
	
	public CertificadoService getCertificadoServiceA3Token() {
		
		IDriverServices driverServices = new DriverService(); 
		
		return new CertificadoService(
				new SelecionadorHandler(driverServices)
		);
//		return new CertificadoService(
//				new SwingSelecaoTipoCertificadoHandler(driverServices)
//				);
	}


}
