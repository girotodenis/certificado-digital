package br.gov.dsg.certificado;

import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import br.gov.dsg.certificado.entidades.Certificado;
import br.gov.dsg.certificado.entidades.TipoKeyStore;

public class CertificadoService {
	
	private CarregadorKeyStore carregadorKeyStore;
	
	public CertificadoService(CarregadorKeyStore carregadorKeyStore) {
		
		this.carregadorKeyStore = carregadorKeyStore;
	}
	
	/**
	 * Recuperar a lista de certificados digitais instalados com as
	 * informacões do certificado, ver a classe Certificado.
	 */
	public List<Certificado> certificados() {
	
		return certificados(carregadorKeyStore.tipoKeyStore());
	}
	
	/**
	 * Recuperar a lista de certificados digitais instalados com as
	 * informacões do certificado, ver a classe Certificado.
	 */
	private List<Certificado> certificados(TipoKeyStore tipoKeyStore) {
		List<Certificado> listCertificado = new ArrayList<>();
		try {
			KeyStore keyStore = tipoKeyStore.getKeyStore();
			Enumeration<String> aliases = keyStore.aliases();
			while (aliases.hasMoreElements()) {
				String alias = aliases.nextElement();
				listCertificado.add( new Certificado( tipoKeyStore , alias) );
			}
		} catch (Exception e) {
			//log.warn("Erro ao recuperar lista de certificados", e);
			e.printStackTrace();
		}
		return listCertificado;
		
	}

}
