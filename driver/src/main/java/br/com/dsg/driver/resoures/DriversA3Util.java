package br.com.dsg.driver.resoures;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

public class DriversA3Util {
	
	private static Properties properties = new Properties();
	private static File arquivo;
	
	private DriversA3Util() {
	    throw new IllegalStateException("Utility class");
	}

	
	static {
		try {
			
			String winRoot = System.getenv("SystemRoot") == null ? "" : System.getenv("SystemRoot").replaceAll("\\\\", "/");
			Map<String, String> map = new HashMap<>();
			map.put("TokenOuSmartCard_00", winRoot.concat("/system32/ngp11v211.dll"));
			map.put("TokenOuSmartCard_01", winRoot.concat("/system32/aetpkss1.dll"));
			map.put("TokenOuSmartCard_02", winRoot.concat("/system32/gclib.dll"));
			map.put("TokenOuSmartCard_03", winRoot.concat("/system32/pk2priv.dll"));
			map.put("TokenOuSmartCard_04", winRoot.concat("/system32/w32pk2ig.dll"));
			map.put("TokenOuSmartCard_05", winRoot.concat("/system32/eTPkcs11.dll"));
			map.put("TokenOuSmartCard_06", winRoot.concat("/system32/acospkcs11.dll"));
			map.put("TokenOuSmartCard_07", winRoot.concat("/system32/dkck201.dll"));
			map.put("TokenOuSmartCard_08", winRoot.concat("/system32/dkck232.dll"));
			map.put("TokenOuSmartCard_09", winRoot.concat("/system32/cryptoki22.dll"));
			map.put("TokenOuSmartCard_10", winRoot.concat("/system32/acpkcs.dll"));
			map.put("TokenOuSmartCard_11", winRoot.concat("/system32/slbck.dll"));
			map.put("TokenOuSmartCard_12", winRoot.concat("/system32/cmP11.dll"));
			map.put("TokenOuSmartCard_13", winRoot.concat("/system32/WDPKCS.dll"));
			map.put("TokenOuSmartCard_14", winRoot.concat("/System32/Watchdata/Watchdata Brazil CSP v1.0/WDPKCS.dll"));
			map.put("TokenOuSmartCard_15", "/Arquivos de programas/Gemplus/GemSafe Libraries/BIN/gclib.dll");
			map.put("TokenOuSmartCard_16", "/Program Files/Gemplus/GemSafe Libraries/BIN/gclib.dll");
			map.put("TokenOuSmartCard_17", "/usr/lib/libaetpkss.so");
			map.put("TokenOuSmartCard_18", "/usr/lib/libgpkcs11.so");
			map.put("TokenOuSmartCard_19", "/usr/lib/libgpkcs11.so.2");
			
			map.put("TokenOuSmartCard_20", "/usr/lib/libepsng_p11.so");
			map.put("TokenOuSmartCard_21", "/usr/lib/libepsng_p11.so.1");
			map.put("TokenOuSmartCard_22", "/usr/local/ngsrv/libepsng_p11.so.1");
			
			map.put("TokenOuSmartCard_23", "/usr/lib/libeTPkcs11.so");
			map.put("TokenOuSmartCard_24", "/usr/lib/libeToken.so");
			map.put("TokenOuSmartCard_25", "/usr/lib/libeToken.so.4");
			map.put("TokenOuSmartCard_26", "/usr/lib/libcmP11.so");
			map.put("TokenOuSmartCard_27", "/usr/lib/libwdpkcs.so");
			map.put("TokenOuSmartCard_28", "/usr/local/lib64/libwdpkcs.so");
			map.put("TokenOuSmartCard_29", "/usr/local/lib/libwdpkcs.so");
			map.put("TokenOuSmartCard_30", "/usr/lib/watchdata/ICP/lib/libwdpkcs_icp.so");
			map.put("TokenOuSmartCard_31", "/usr/lib/watchdata/lib/libwdpkcs.so");
			map.put("TokenOuSmartCard_32", "/opt/watchdata/lib64/libwdpkcs.so");
			map.put("TokenOuSmartCard_33", "/usr/lib/opensc-pkcs11.so");
			map.put("TokenOuSmartCard_34", "/usr/lib/pkcs11/opensc-pkcs11.so");
			map.put("TokenOuSmartCard_35", "/usr/lib/libwdpkcs.dylib");
			map.put("TokenOuSmartCard_36", "/usr/local/lib/libwdpkcs.dylib");
			map.put("TokenOuSmartCard_37", "/usr/local/ngsrv/libepsng_p11.so.1.2.2");
			map.put("TokenOuSmartCard_38", "/usr/local/lib/libetpkcs11.dylib");
			map.put("TokenOuSmartCard_39", "/usr/local/lib/libaetpkss.dylib");
			
			arquivo = new File("./config/drivers_carregados.properties");
			if (arquivo.exists()) {
				
				properties.load(new FileInputStream(arquivo));
				
				Enumeration<Object> keys = properties.keys();
				while (keys.hasMoreElements()) {
					String nomeDriver = (String) keys.nextElement();
					String pathDriver = get(nomeDriver);
					map.remove(nomeDriver);
					File driver = new File(pathDriver);
					if( (!driver.exists() || !driver.isFile()) ) {
						properties.remove(nomeDriver,pathDriver);
					}
				}
				
			} else {
				File dir = new File("./config");
				
				if (!dir.exists()) {
					dir.mkdir();
				}
				
				procurarDrivers(map);
				
			}
			
			salvar();
		} catch (Exception e) {
		}
	}

	private static void procurarDrivers(Map<String, String> map) {
		for (Map.Entry<String,String> entry : map.entrySet()) {
			String nomeDriver = entry.getKey();
			String pathDriver = entry.getValue();
		    
			File driver = new File(pathDriver);
			if(driver.exists() && driver.isFile()) {
				properties.setProperty(nomeDriver, pathDriver);
			}
		}
	}
	
	public static void set(String chave, String valor) {
		properties.setProperty(chave, valor);
		salvar();
	}
	
	public static void setPadrao(String chave) {
		keys().forEach(nome -> {
			if(nome.equals(chave)) {
				properties.setProperty(nome, get(nome)+" "+"*");
			}else {
				
				properties.setProperty(nome, get(nome));
			}
		});
		salvar();
	}

	public static void salvar() {
		try {
			FileOutputStream out = new FileOutputStream(arquivo);
			properties.store( out, "salvo_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String get(String chave) {
		String[] valores = properties.getProperty(chave).split(" ");
		return valores[0];
	}
	
	public static String[] getValores(String chave) {
		String[] valores = properties.getProperty(chave).split(" ");
		return valores;
	}
	
	public static List<String> keys() {
		return properties.keySet().stream()
				.map(Object::toString)
				.collect(Collectors.toList());
	}

	public static void remover(String nomeDriver) {
		properties.remove(nomeDriver);
		salvar();
	}

}
