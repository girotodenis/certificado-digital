package br.com.dsg.uia3.selecao;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Arrays;

import org.liquidengine.legui.component.Dialog;
import org.liquidengine.legui.component.Label;
import org.liquidengine.legui.component.event.selectbox.SelectBoxChangeSelectionEvent;
import org.liquidengine.legui.component.event.selectbox.SelectBoxChangeSelectionEventListener;
import org.liquidengine.legui.listener.processor.EventProcessorProvider;

import br.com.dsg.driver.IDriverServices;
import br.com.dsg.driver.entidade.DriverA3;
import br.com.dsg.legui.AbstractController;
import br.com.dsg.legui.controller.LeGuiController;
import br.gov.dsg.certificado.CarregadorKeyStore;
import br.gov.dsg.certificado.entidades.A3Pkcs11;

public class ControllerSelecaoA3 extends AbstractController<SelecaoA3Gui> {

	private IDriverServices service;
	private SelecionarTipoKeyStore selecionarTipoKeyStore;

//	public ControllerSelecaoA3(IDriverServices service, SelecionarTipoKeyStore selecionarTipoKeyStore) {
//		this(LeGuiController.get(), service, selecionarTipoKeyStore);
//	}
	
	public ControllerSelecaoA3(AbstractController<?> controllerPai, IDriverServices service, SelecionarTipoKeyStore selecionarTipoKeyStore) {
		super(controllerPai, new SelecaoA3Gui());
		this.service = service;
		this.selecionarTipoKeyStore = selecionarTipoKeyStore;
		
		String lista[] = this.service.todos().stream()
        		.map(DriverA3::getPath)
    			.toArray(String[]::new); 
		
		Arrays.asList(lista).forEach(caminho->{
			getPanel().selectBox.addElement(caminho);
			getPanel().selectBox.setSelected(this.service.driverPadrao().getPath(), true);
		});
		

		registerAction(getPanel().addPathCertificao, (event) -> {
			getPanel().dialog.show(event.getFrame());
		});

		registerAction(getPanel().addPath, (event) -> {
			String novoElemento = getPanel().valueText.getTextState().getText();
			if (!novoElemento.isBlank()) {
				getPanel().selectBox.addElement(novoElemento);
				getPanel().selectBox.setSelected(novoElemento, true);

				EventProcessorProvider.getInstance().pushEvent(new SelectBoxChangeSelectionEvent(getPanel().selectBox,
						null, getPanel().getFrame(), getPanel().selectBox.getSelection(), novoElemento));

				getPanel().valueText.getTextState().setText("");
				getPanel().dialog.close();
			}
		});

		registerAction(getPanel().cancelar, (event) -> {
			getPanel().dialog.close();
		});

		getPanel().selectBox
				.addSelectBoxChangeSelectionEventListener((SelectBoxChangeSelectionEventListener<String>) event -> {
					if (!event.getNewValue().toString().equals("Selecionar")) {
			            service.todos().forEach(item -> {
			            	if(item.getPath().equals(event.getNewValue())) {
			            		service.driverPadrao(item);
			            	}
			            });
					}
				});
		
		registerAction(getPanel().validar, (event)->{
			try {
				char[] senha = getPanel().senhaTokem.getTextState().getText().toCharArray();
				A3Pkcs11 tipoKeyStore = new A3Pkcs11(senha, service.driverPadrao());
				
				if(this.selecionarTipoKeyStore!=null) {
					selecionarTipoKeyStore.selecionar(tipoKeyStore);
				}
			} catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException e) {
				handlerException(e);
			}
		});

	}

}
