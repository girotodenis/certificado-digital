package br.com.dsg.driver.entidade;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import br.gov.dsg.certificado.entidades.LocalDriver;

public class DriverA3 implements LocalDriver {
	
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
	
	public String config() {
		try {
			String pkcs11ConfigSettings = String.format("name = %s\nlibrary = %s\nslot = 0", nome, path);
			Path filePath = criarCfgPkcs11(pkcs11ConfigSettings);
			return filePath.toString();
		} catch (Exception e) {
			throw new RuntimeException(e.getLocalizedMessage(), e );
		}
	}

	private Path criarCfgPkcs11(String pkcs11ConfigSettings) throws IOException {
		File cfg = new File("./config");
		if(!cfg.exists()) {
			cfg.mkdirs();
		}
		
		File pkcs11 = new File(cfg, "pkcs11.cfg");
		if(pkcs11.exists()) {
			pkcs11.delete();
		}
		
		pkcs11 = new File(cfg, "pkcs11.cfg");
		pkcs11.createNewFile();
		
		Path filePath = Paths.get(pkcs11.getAbsolutePath());
		Files.writeString(filePath, pkcs11ConfigSettings, StandardOpenOption.TRUNCATE_EXISTING);
		return filePath;
	}
	
	public String getNome() {
		return nome;
	}

	public String getPath() {
		return path;
	}
	

}
