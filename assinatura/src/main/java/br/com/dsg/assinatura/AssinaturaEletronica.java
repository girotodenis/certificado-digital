package br.com.dsg.assinatura;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.Arrays;

import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaCertStore;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSProcessableByteArray;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.CMSSignedDataGenerator;
import org.bouncycastle.cms.CMSTypedData;
import org.bouncycastle.cms.SignerInfoGenerator;
import org.bouncycastle.cms.jcajce.JcaSignerInfoGeneratorBuilder;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.DigestCalculatorProvider;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.operator.jcajce.JcaDigestCalculatorProviderBuilder;
import org.bouncycastle.util.Store;

import br.gov.dsg.certificado.entidades.Certificado;

public class AssinaturaEletronica {

	// as informações que devem ser tratadas
	// como parâmetros de configuração foram
	// extraídas como constantes
	private static final String ALGORITMOS = "SHA256WithRSA";

	private Certificado certificado;

	public AssinaturaEletronica(Certificado certificado) {
		this.certificado = certificado;
	}

	public byte[] assinar(byte[] conteudo) throws KeyStoreException, IOException, CertificateException,
			NoSuchAlgorithmException, UnrecoverableKeyException, OperatorCreationException, CMSException {

		// neste primeiro trecho criamos uma maneira de recuperar
		// informações do nosso keystore JKS. Até aqui não usamos
		// a biblioteca Bouncy Castle, apenas a API {java.security}.
		// note que a senha do par de chaves não precisa ser igual
		// a senha do keystore, apesar de este ser o caso frequentemente
		PrivateKey privateKey = certificado.getPrivateKey();
		Certificate[] certificates = certificado.getCertificateChain();
		Certificate certificate = certificado.getCertificate();

		// agora que temos a chave privada e o certificado público,
		// podemos prosseguir com a assinatura em si, usando a API CMS
		// do Bouncy Castle
		Store certs = new JcaCertStore(Arrays.asList(certificate));
		CMSSignedDataGenerator gen = new CMSSignedDataGenerator();
		org.bouncycastle.asn1.x509.Certificate cert = org.bouncycastle.asn1.x509.Certificate
				.getInstance(ASN1Primitive.fromByteArray(certificate.getEncoded()));
		
		// repare que nesta linha estamos escolhendo um algoritmo de
		// assinatura (RSA) e um algoritmo de espalhamento/hash (SHA256).
		// Esta escolha é bem importante e a tendência é que com o tempo
		// ela tenha que ser atualizada, conforme as versões atuais se
		// tornem obsoletas ou se mostrem vulneráveis.
		ContentSigner sha1Signer = new JcaContentSignerBuilder(ALGORITMOS).build(privateKey);
		DigestCalculatorProvider dcp = new JcaDigestCalculatorProviderBuilder().build();
		SignerInfoGenerator sig = new JcaSignerInfoGeneratorBuilder(dcp).build(sha1Signer,
				new X509CertificateHolder(cert));
		gen.addSignerInfoGenerator(sig);
		gen.addCertificates(certs);
		
		// repare que existem outras implementações de CMSTypedData,
		// como por exemplo CMSProcessableFile. Escolha uma delas de maneira
		// a facilitar a integração com a origem do dado a ser assinado
		CMSTypedData msg = new CMSProcessableByteArray(conteudo);
		CMSSignedData signedData = gen.generate(msg, false);
		return signedData.getEncoded();
	}

}
