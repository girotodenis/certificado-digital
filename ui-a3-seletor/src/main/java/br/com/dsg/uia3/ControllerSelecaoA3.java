package br.com.dsg.uia3;

import org.liquidengine.legui.component.Dialog;
import org.liquidengine.legui.component.Label;
import org.liquidengine.legui.component.event.selectbox.SelectBoxChangeSelectionEvent;
import org.liquidengine.legui.component.event.selectbox.SelectBoxChangeSelectionEventListener;
import org.liquidengine.legui.listener.processor.EventProcessorProvider;

import br.com.dsg.legui.AbstractController;
import br.gov.dsg.certificado.CarregadorKeyStore;

public class ControllerSelecaoA3 extends AbstractController<SelecaoA3Gui>{

	public ControllerSelecaoA3(AbstractController<?> controllerPai) {
		super(controllerPai, new SelecaoA3Gui());
		
		
		registerAction(getPanel().addPathCertificao, (event)->{
            getPanel().dialog.show(event.getFrame());
		});
		
		registerAction(getPanel().addPath, (event)->{
			String novoElemento = getPanel().valueText.getTextState().getText();
			if(!novoElemento.isBlank()) {
				getPanel().selectBox.addElement(novoElemento);
				getPanel().selectBox.setSelected(novoElemento, true);
				
				EventProcessorProvider.getInstance().pushEvent(new SelectBoxChangeSelectionEvent(
						getPanel().selectBox, null, 
						getPanel().getFrame(), getPanel().selectBox.getSelection(), novoElemento));
				
				getPanel().valueText.getTextState().setText("");
				getPanel().dialog.close();
			}
		});
		
		registerAction(getPanel().cancelar, (event)->{
			getPanel().dialog.close();
		});
		
		getPanel().selectBox.addSelectBoxChangeSelectionEventListener((SelectBoxChangeSelectionEventListener<String>) event -> {
    	if(!event.getNewValue().toString().equals("Selecionar")) {
    		Dialog dialog = new Dialog("SelectBox clicked", 300, 100);
    		Label valueLabel = new Label("Value: " + event.getNewValue().toString(), 10, 10, 300, 20);
    		dialog.getContainer().add(valueLabel);
    		Label classLabel = new Label("Class: " + event.getNewValue().getClass().getName(), 10, 30, 300, 20);
    		dialog.getContainer().add(classLabel);
    		dialog.show(event.getFrame());
    	}
    });
	
	}

}
