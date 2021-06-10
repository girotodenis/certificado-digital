package br.com.dsg.uia3;

import br.com.dsg.legui.controller.StartLeGui;
import br.com.dsg.legui.controller.eventos.EventAdicionarItemMenu;
import br.com.dsg.uia3.certificado.ControllerCertificado;

public class LeguiCertificate {

	public static void main(String[] args) {

		StartLeGui.get(600, 300, "Certificado A3")
		.abrirFecharMenuPadrao()
		.addItemMenu(
				new EventAdicionarItemMenu(
						"Selecionar Certificado", 
						"img/pendrive.png", null, false,
						(controllerPai) -> new ControllerCertificado(controllerPai), 
						true
					)
		)
		.menuFechado()
		.start();
	}

}
