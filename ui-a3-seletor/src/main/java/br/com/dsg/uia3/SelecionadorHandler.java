package br.com.dsg.uia3;

import br.com.dsg.driver.IDriverServices;
import br.com.dsg.legui.controller.StartLeGui;
import br.com.dsg.legui.controller.eventos.EventAdicionarItemMenu;
import br.gov.dsg.certificado.CarregadorKeyStore;
import br.gov.dsg.certificado.entidades.TipoKeyStore;

public class SelecionadorHandler implements CarregadorKeyStore{

	private IDriverServices driverServices;
	private TipoKeyStore tipoKeyStore;
	
	public SelecionadorHandler(IDriverServices driverServices) {
		this.driverServices = driverServices;
	}

	@Override
	public TipoKeyStore tipoKeyStore() {
		
		
		StartLeGui.get(800, 600, "Seleção Certificado A3").abrirFecharMenuPadrao()
		.addItemMenu(
				new EventAdicionarItemMenu(
						"Selecionar Certificado", 
						"img/pendrive.png", null, false,
						(controllerPai) -> new ControllerSelecaoA3(
								controllerPai, 
								driverServices,
								(keyStore)-> tipoKeyStore = keyStore
						), 
						true
					)
		)
		.start();
		
		return tipoKeyStore;
	}

}
