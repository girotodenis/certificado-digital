package br.com.dsg.driver.entidade;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import br.gov.dsg.certificado.entidades.Local;

public class DriverA3 implements Local {
	
	private String nome;
	private String path;
	private Boolean padrao = Boolean.FALSE;
	
	public DriverA3(String nome, String path, Boolean padrao) {
		super();
		this.nome = nome;
		this.path = path;
		this.padrao = padrao;
	}
	
	public void defineComoPadrao() {
		padrao = Boolean.TRUE;
	}

	public boolean isPadrao() {
		return padrao;
	}
	
	public InputStream config() {
		String pkcs11ConfigSettings = null;
		pkcs11ConfigSettings = String.format("name = %s\nlibrary = %s\nslot = 0", nome, path);
		byte[] pkcs11ConfigBytes = pkcs11ConfigSettings.getBytes();
		return new ByteArrayInputStream(pkcs11ConfigBytes);
	}

	public String getNome() {
		return nome;
	}

	public String getPath() {
		return path;
	}
	

}
