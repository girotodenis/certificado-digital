package certificado;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.gov.dsg.certificado.CarregadorKeyStore;
import br.gov.dsg.certificado.CertificadoService;
import br.gov.dsg.certificado.entidades.A3Pkcs11;
import br.gov.dsg.certificado.entidades.A3WindowsMY;
import br.gov.dsg.certificado.entidades.TipoKeyStore;

public class CertificadoTest {

	@Test
	public void deve_logar_usando_provider_SunPKCS11() {
		CertificadoService service = new CertificadoService(new CarregadorKeyStore() {
			@Override
			public TipoKeyStore tipoKeyStore() {
				try {
					return new A3Pkcs11("3113".toCharArray() , new A3LinuxTeste());
				} catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException e) {
					e.printStackTrace();
					return null;
				}
			}
		});
		
		service.certificados().forEach(cert->{
			System.out.println( String.format( "emitidoPara = %s", cert.emitidoPara() ) );
			System.out.println( String.format( "getCpf = %s", cert.pessoaInfo().getCpf() ) );
			System.out.println( String.format( "emitidoPor = %s", cert.emitidoPor() ) );
			System.out.println( String.format( "getCadeiaCertificado = %s", cert.getCadeiaCertificado() ) );
		});
		
		Assert.assertTrue(service.certificados().get(0).isValido());
		
	}
	
	@Test
	public void deve_logar_usando_provider_WindowsMY() {
		CertificadoService service = new CertificadoService(new CarregadorKeyStore() {
			@Override
			public TipoKeyStore tipoKeyStore() {
				try {
					return new A3WindowsMY();
				} catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException e) {
					e.printStackTrace();
					return null;
				}
			}
		});
		
		service.certificados().forEach(cert->{
			System.out.println( String.format( "emitidoPara = %s", cert.emitidoPara() ) );
			System.out.println( String.format( "getCpf = %s", cert.pessoaInfo().getCpf() ) );
			System.out.println( String.format( "emitidoPor = %s", cert.emitidoPor() ) );
			System.out.println( String.format( "getCadeiaCertificado = %s", cert.getCadeiaCertificado() ) );
		});
		
		Assert.assertTrue(service.certificados().get(0).isValido());
		
	}
}
