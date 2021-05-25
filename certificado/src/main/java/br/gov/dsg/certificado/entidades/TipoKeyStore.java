package br.gov.dsg.certificado.entidades;

import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public interface TipoKeyStore {
	
	public void load(char[] senha) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException;

	public KeyStore getKeyStore();
	public X509Certificate getCertificate(String alias);
	public PrivateKey getPrivateKey(String alias);
	

}
