package br.gov.dsg.certificado.entidades;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStore.PrivateKeyEntry;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.Security;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;


public class A3Pkcs11 implements TipoKeyStore{
	
	private KeyStore keyStore;
	
	public A3Pkcs11() {
	}
	
	public A3Pkcs11(char[] senha, Local driver) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		load(senha, driver, null);
	}
	
	
	@Override
	public void load(char[] senha, Local driver, InputStream fileCertificado) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		
	
		Provider provider = new sun.security.pkcs11.SunPKCS11(driver.config());
		Security.addProvider(provider);
		keyStore = KeyStore.getInstance("pkcs11", provider);
		keyStore.load(null, senha);

		
	}
	@Override
	public KeyStore getKeyStore() {
		
		return keyStore;
	}
	
	@Override
	public X509Certificate getCertificate(String alias) {
		X509Certificate certificate = null;
		try {
			PrivateKeyEntry pkentry = extractedPkentry(alias);
			certificate = (X509Certificate) pkentry.getCertificate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return certificate;
	}

	@Override
	public PrivateKey getPrivateKey(String alias) {
		try {
			PrivateKeyEntry pkentry = extractedPkentry(alias);
			return pkentry.getPrivateKey();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private PrivateKeyEntry extractedPkentry(String alias)
			throws KeyStoreException, NoSuchAlgorithmException, UnrecoverableEntryException {
		PrivateKeyEntry pkentry = null;
		if (keyStore.isKeyEntry(alias)) {
			// recupera chave privada associada à esse alias
			KeyStore.Entry entry = keyStore.getEntry(alias, new KeyStore.PasswordProtection(null));
			if (entry != null && entry instanceof PrivateKeyEntry) {
				pkentry = ((PrivateKeyEntry) entry);
			}
		}
		return pkentry;
	}


}
