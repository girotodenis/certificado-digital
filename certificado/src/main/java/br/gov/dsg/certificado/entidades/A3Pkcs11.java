package br.gov.dsg.certificado.entidades;

import java.io.IOException;
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
	private LocalDriver driver;
	
	public A3Pkcs11( LocalDriver driver ) {
		this.driver = driver;
	}
	
	public A3Pkcs11(char[] senha, LocalDriver driver) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		this.driver = driver;
		load(senha);
	}
	
	
	@Override
	public void load(char[] senha) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		
	
		//Provider provider = new sun.security.pkcs11.SunPKCS11(driver.config());
		Provider provider = Security.getProvider("SunPKCS11");
		provider = provider.configure(driver.config());
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
