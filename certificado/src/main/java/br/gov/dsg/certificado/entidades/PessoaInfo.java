package br.gov.dsg.certificado.entidades;

public class PessoaInfo {

	/**
	 * ICP-Brasil Dados de Pessoa Fisica
	 * <p>
	 * OID value: 2.16.76.1.3.1 <br>
	 * OID description: nas primeiras 8 (oito) posições, a data de nascimento do titular, no formato ddmmaaaa;
	 * nas 11 (onze) posições subseqüentes, o Cadastro de Pessoa Física (CPF) do titular;
	 * nas 11 (onze) posições subseqüentes, o número de inscrição do titular no PIS/PASEP;
	 * nas 11 (onze) posições subseqüentes, o número do Registro Geral - RG do titular;
	 * nas 6 (seis) posições subseqüentes, as siglas do órgão expedidor do RG e respectiva UF.
	 */
	public static final String OID_DADOS_PF = "2.16.76.1.3.1";
	public static final String OID_DADOS_PJ = "2.16.76.1.3.4";
	public static final String OID_CNPJ = "2.16.76.1.3.3";
	
	/**
	 * contendo informações sobre o Título de Eleitor do titular;
	 */
	public static final String PF_TITULO_ELEITOR = "2.16.76.1.3.5";
	public static final String PJ_TITULO_ELEITOR = "2.16.76.1.3.5";
	
	/**
	 *  12 (doze) posições o número do Cadastro 
	 *  Específico do INSS (CEI) da pessoa física titular do
	 *  certificado;
	 */
	public static final String PF_DADOS_INSS1 = "2.16.76.1.3.6";
	public static final String PJ_DADOS_INSS = "2.16.76.1.3.7";
	
	
	public static final String OID_NOME_PJ = "2.16.76.1.3.8";
	
	/**
	 * Registro de Identidade Civil.
	 */
	public static final String OID_DADOS_IDENTIDADE_CIVIL = "2.16.76.1.3.9";
	
	private String dataDeNascimento;
	private String cpf;
	private String pis;
	private String rg;
	private String orgaoExpedidor;
	private String tituloEleitor;
	private String inss;
	private String identidadeCivil;
	
	private boolean tipoPessoaJuridica = false;
	private String cnpj;
	private String nomePessoaJuridica;
	
	
	public void addOID(String OID, String infoOID) {
		if(OID_DADOS_PF.equals(OID)) {
			
			dataDeNascimento = infoOID.substring(0, 8);
			cpf = infoOID.substring(8, 19);
			pis = infoOID.substring(19, 30);
			rg = infoOID.substring(30, 35);
			orgaoExpedidor = infoOID.substring(35, 45);
			
		}else if(PF_TITULO_ELEITOR.equals(OID)) {
			
			tituloEleitor = infoOID;
			
		}else if(PF_DADOS_INSS1.equals(OID) || PJ_DADOS_INSS.equals(OID)) {
			
			inss = infoOID.substring(0, 12);
			
		}else if(OID_DADOS_IDENTIDADE_CIVIL.equals(OID)) {
			
			identidadeCivil = infoOID.substring(0, 11);
			
		}else if(OID_CNPJ.equals(OID)) {
			
			cnpj = infoOID;
			tipoPessoaJuridica = true;
			
		}else if(OID_NOME_PJ.equals(OID)) {
			
			nomePessoaJuridica = infoOID;
			
		}else if(OID_DADOS_PJ.equals(OID)) {
			
			dataDeNascimento = infoOID.substring(0, 8);
			cpf = infoOID.substring(8, 19);
			pis = infoOID.substring(19, 30);
			rg = infoOID.substring(30, 41);
			orgaoExpedidor = infoOID.substring(41, 47);
		}
	}


	public String getDataDeNascimento() {
		return dataDeNascimento;
	}


	public String getCpf() {
		return cpf;
	}


	public String getPis() {
		return pis;
	}


	public String getRg() {
		return rg;
	}


	public String getTituloEleitor() {
		return tituloEleitor;
	}


	public String getInss() {
		return inss;
	}


	public String getIdentidadeCivil() {
		return identidadeCivil;
	}


	public String getOrgaoExpedidor() {
		return orgaoExpedidor;
	}


	public boolean isTipoPessoaJuridica() {
		return tipoPessoaJuridica;
	}


	public String getCnpj() {
		return cnpj;
	}


	public String getNomePessoaJuridica() {
		return nomePessoaJuridica;
	}
	
	

}
