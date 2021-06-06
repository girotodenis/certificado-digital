package br.com.dsg.uia3;

import org.liquidengine.legui.component.Button;
import org.liquidengine.legui.component.Dialog;
import org.liquidengine.legui.component.Label;
import org.liquidengine.legui.component.Panel;
import org.liquidengine.legui.component.PasswordInput;
import org.liquidengine.legui.component.SelectBox;
import org.liquidengine.legui.component.TextInput;
import org.liquidengine.legui.component.event.component.ChangeSizeEvent;
import org.liquidengine.legui.component.event.selectbox.SelectBoxChangeSelectionEventListener;
import org.liquidengine.legui.event.MouseClickEvent;
import org.liquidengine.legui.event.WindowSizeEvent;
import org.liquidengine.legui.listener.MouseClickEventListener;


public class SelecaoA3Gui extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5212858828209006392L;

	SelectBox<String> selectBox;
	
	Button addPathCertificao;
	
	Dialog dialog ;
	Button addPath;
	Button cancelar;
	TextInput valueText;
	
	PasswordInput senhaTokem;
	Button validar;
    
	public SelecaoA3Gui() {
        
		selectBox = new SelectBox<String>(20, 20, getSize().x()-100, 40);
		addPathCertificao = new Button("+", getSize().x()-60, 20, 40, 40);
        
		selectBox.addElement("Selecionar");
		selectBox.setElementHeight(40);
		selectBox.setVisibleCount(5);
		
		senhaTokem = new PasswordInput(20, 80, getSize().x()-160, 40);
		validar = new Button("Validar", getSize().x()-10, 120, 100, 40);
		
		
		this.add(selectBox);
		this.add(addPathCertificao);
		this.add(senhaTokem);
		this.add(validar);
        
		
        
        this.dialog = new Dialog("Adicionar caminho drive A3", 300, 150);
        this.dialog.setDraggable(false);
        this.dialog.setResizable(false);
		this.dialog.setCloseable(false);
        
		this.addPath = new Button("Adicionar", ((300-140)/2) , 60, 60, 40);
        this.cancelar = new Button("Cancelar", ((300-140)/2) + 80 , 60, 60, 40);
        this.valueText = new TextInput(10, 10, 280, 40);
        
        this.dialog.getContainer().add(valueText);
        this.dialog.getContainer().add(addPath);
        this.dialog.getContainer().add(cancelar);
        
        getListenerMap().addListener(ChangeSizeEvent.class, (event)->{
        	selectBox.setSize(event.getNewSize().x()-100, 40);
        	addPathCertificao.setPosition( event.getNewSize().x() - 60, 20);
        	
        	senhaTokem.setSize(event.getNewSize().x()-160, 40);
        	validar.setPosition( event.getNewSize().x() - 120, 80);
        });
        
    }
    

   
}
