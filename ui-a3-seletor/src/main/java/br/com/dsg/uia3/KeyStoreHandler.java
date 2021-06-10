package br.com.dsg.uia3;

import br.gov.dsg.certificado.CarregadorKeyStore;
import br.gov.dsg.certificado.entidades.TipoKeyStore;

public class KeyStoreHandler implements CarregadorKeyStore{

	private TipoKeyStore tipoKeyStore;
	
	public KeyStoreHandler(TipoKeyStore tipoKeyStore) {
		this.tipoKeyStore = tipoKeyStore;
	}

	@Override
	public TipoKeyStore tipoKeyStore() {
		return tipoKeyStore;
	}

}
