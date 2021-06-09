package br.com.dsg.uia3;

import br.com.dsg.driver.DriverService;
import br.com.dsg.driver.IDriverServices;
import br.com.dsg.legui.controller.StartLeGui;
import br.com.dsg.legui.controller.eventos.EventAdicionarItemMenu;

/**
 * Created by Aliaksandr_Shcherbin on 1/25/2017.
 */
public class LeguiSelectCertificate {

	public static void main(String[] args) {

		IDriverServices driverServices = new DriverService();

		StartLeGui.get(800, 600, "Seleção Certificado A3").abrirFecharMenuPadrao()
				.addItemMenu(
						new EventAdicionarItemMenu(
								"Selecionar Certificado", 
								"img/pendrive.png", null, false,
								(controllerPai) -> new ControllerSelecaoA3(controllerPai,driverServices,(tipo)->System.out.println(tipo)), 
								true
							)
				)
				.start();
	}

}
