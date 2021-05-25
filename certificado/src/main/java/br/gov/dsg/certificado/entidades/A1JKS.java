package br.gov.dsg.certificado.entidades;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStore.PrivateKeyEntry;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class A1JKS implements TipoKeyStore{

	private KeyStore keyStore;
	private char[] senha;
	private InputStream fileCertificado;
	
	public A1JKS(InputStream fileCertificado) {
		this.fileCertificado = fileCertificado;
	}
	
	@Override
	public void load(char[] senha) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		this.senha = senha;
		keyStore = KeyStore.getInstance("JKS");
		keyStore.load(fileCertificado, senha);
		
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
			KeyStore.Entry entry = keyStore.getEntry(alias, new KeyStore.PasswordProtection(senha));
			if (entry != null && entry instanceof PrivateKeyEntry) {
				pkentry = ((PrivateKeyEntry) entry);
			}
		}
		return pkentry;
	}


}
