package uia3;

import org.testng.annotations.Test;

import br.com.dsg.uia3.FactoryCertificadoA3Driver;
import br.gov.dsg.certificado.CertificadoService;

public class TesteSeletor {

	@Test
	public void deve_logar_usando_pin() {
		
		CertificadoService service = FactoryCertificadoA3Driver.getInstance().getCertificadoServiceA3Token();
		service.certificados();
	}
}
