package br.gov.dsg.certificado.resources;

import java.util.List;

import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERPrintableString;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.DERUTF8String;
import org.bouncycastle.asn1.DLSequence;

public class UtilBouncyCastle {

	private UtilBouncyCastle() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * @param item
	 * @return
	 */
	public static DLSequence extractDLSequence(List<?> item) {

		byte[] data = (byte[]) item.get(1);
		try (ASN1InputStream is = new ASN1InputStream(data)) {
			return (DLSequence) is.readObject();
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * Converter o objeto do formato DER para String
	 * 
	 * 
	 * @param sequence
	 * 
	 * @return String - Objeto convertido
	 */
	public static String convertDERtoString(DLSequence sequence) {
		
		// Recupera objeto
		DERTaggedObject tag = (DERTaggedObject) sequence.getObjectAt(1);
		
		// sequencia dentro do objeto
		DERTaggedObject seq = (DERTaggedObject) tag.getObject();
		ASN1Primitive derObj = seq.getObject();
		String info = "";
		
		if (derObj instanceof DEROctetString) {
			DEROctetString octet = (DEROctetString) derObj;
			info = new String(octet.getOctets());
		
		} else if (derObj instanceof DERPrintableString) {
			DERPrintableString octet = (DERPrintableString) derObj;
			info = new String(octet.getOctets());
		
		} else if (derObj instanceof DERUTF8String) {
			DERUTF8String str = (DERUTF8String) derObj;
			info = str.getString();
		}
		
		return info;
	}

}
