package uia3;

import java.util.List;

import org.testng.annotations.Test;

import br.com.dsg.uia3.FactoryCertificadoA3Driver;
import br.gov.dsg.certificado.CertificadoService;
import br.gov.dsg.certificado.entidades.Certificado;

public class TesteSeletor {

	@Test
	public void deve_logar_usando_pin() {
		
		CertificadoService service = FactoryCertificadoA3Driver.getInstance().getCertificadoServiceA3Token();
		List<Certificado> lista = service.certificados();
		lista.forEach(e->System.out.println(e.emitidoPara()));
	}
}
