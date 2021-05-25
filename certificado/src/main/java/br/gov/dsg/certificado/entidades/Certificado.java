package br.gov.dsg.certificado.entidades;

import java.io.StringWriter;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableEntryException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.bouncycastle.asn1.DERObjectIdentifier;
import org.bouncycastle.asn1.DLSequence;
import org.bouncycastle.openssl.PEMWriter;

import br.gov.dsg.certificado.resources.UtilBouncyCastle;

public class Certificado {

	private TipoKeyStore tipoKeyStore; 
	private X509Certificate certificate;
	private String alias;
	
	private PessoaInfo  pessoa = new PessoaInfo();
	
	
	public Certificado(TipoKeyStore tipoKeyStore, String alias) throws KeyStoreException, NoSuchAlgorithmException, UnrecoverableEntryException {
		this.tipoKeyStore = tipoKeyStore;
		this.alias = alias;
		this.certificate = tipoKeyStore.getCertificate(alias);
		
		try {
			carrgarDadosDePessoa();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	private void carrgarDadosDePessoa() throws CertificateParsingException {
		
		Collection<List<?>> subjectAlternativeNames = this.tipoKeyStore.getCertificate(alias).getSubjectAlternativeNames();
		for (Iterator<List<?>> iterator = subjectAlternativeNames.iterator(); iterator.hasNext();) {
			
			List<?> item = iterator.next();
			Integer tipo = (Integer) item.get(0);
			// se o tipo 0, e uma extensao codificada
			if (tipo == 0) {
				
				DLSequence sequence = UtilBouncyCastle.extractDLSequence(item);
				DERObjectIdentifier objectIdentifier = (DERObjectIdentifier) sequence.getObjectAt(0);
				String infoOID = UtilBouncyCastle.convertDERtoString(sequence);
				pessoa.addOID(objectIdentifier.toString(), infoOID);
			}
		}
	}
	
	
	public PrivateKey getPrivateKey() {
		return this.tipoKeyStore.getPrivateKey(alias);
	}
	public PublicKey getPublicKey() {
		return this.certificate.getPublicKey();
	}
	
	public Certificate[] getCertificateChain() {
		try {
			return this.tipoKeyStore.getKeyStore().getCertificateChain(alias);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean isValido() {
		Date notBefore =this.certificate.getNotBefore();
		Date notAfter = this.certificate.getNotAfter();
		Date now = new Date();
		if(now.before(notBefore) || now.after(notAfter)) {
			//log.infof("Certificado da KeyStore.Alias %s está fora do prazo de validade", alias);
			return false;
		}
		return true;
	}
	
	public Date validoDe() {
		
		return this.certificate.getNotBefore();
	}
	
	public Date validoAte() {
		
		return this.certificate.getNotAfter();
	}
	
	public String emitidoPara() {
		if (this.certificate.getSubjectDN() != null) {
			String dn = this.certificate.getSubjectDN().toString();
			dn = dn.substring(dn.indexOf("CN=") + 3);
			if (dn.indexOf(',') == -1) {
				return dn;
			}
			dn = dn.substring(0, dn.indexOf(','));
			return dn;
		}
		return null;
	}
	
	
	public String emitidoPor() {
		if (this.certificate.getIssuerDN() != null) {
			String dn = this.certificate.getIssuerDN().toString();
			dn = dn.substring(dn.indexOf("CN=") + 3);
			if (dn.indexOf(',') == -1) {
				return dn;
			}
			dn = dn.substring(0, dn.indexOf(','));
			return dn;
		}
		return null;
	}
	
	public PessoaInfo pessoaInfo() {
		return pessoa;
	}
	
	/**
	 * Converts a {@link X509Certificate} instance into a Base-64 encoded string (PEM format).
	 *
	 * @param x509Cert A X509 Certificate instance
	 * @return PEM formatted String
	 * @throws CertificateEncodingException
	 */
	public String convertToPEMString() {
	    try {
			
	    	StringWriter sw = new StringWriter();
	    	try (PEMWriter pw = new PEMWriter(sw)) {
	    		pw.writeObject(this.certificate);
	    	}
	    	return sw.toString();
//	    			.replaceAll("\n", "")
//	    			.replaceAll("-----BEGIN CERTIFICATE-----", "")
//	    			.replaceAll("-----END CERTIFICATE-----", "");
		} catch (Exception e) {
			throw new IllegalStateException("Não foi possivel gerar cadeia de certificado: " + e.getMessage());
		}
	}

}
