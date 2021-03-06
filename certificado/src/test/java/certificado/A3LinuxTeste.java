package certificado;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import br.gov.dsg.certificado.entidades.LocalDriver;

public class A3LinuxTeste implements LocalDriver{

	public String config() {
		try {
			String pkcs11ConfigSettings = String.format("name = %s\nlibrary = %s\nslot = 0", "TokenOuSmartCard_24" , "/usr/lib/libeToken.so");
			Path filePath = criarCfgPkcs11(pkcs11ConfigSettings);
			return filePath.toString();
		} catch (Exception e) {
			throw new RuntimeException(e.getLocalizedMessage(), e );
		}
	}

	private Path criarCfgPkcs11(String pkcs11ConfigSettings) throws IOException {
		File cfg = new File("./target");
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
	

}
