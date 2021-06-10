package br.com.dsg.uia3.certificado;

import org.liquidengine.legui.component.Button;
import org.liquidengine.legui.component.Label;
import org.liquidengine.legui.component.Panel;
import org.liquidengine.legui.component.event.component.ChangeSizeEvent;
import org.liquidengine.legui.style.border.SimpleLineBorder;


public class CertificadoA3 extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5212858828209006392L;

	Button trocarCertificao;
	
	Label lbEmitidoPara, lbValido, lbValidoDe, lbValidoAte, lbEmitidoPor;
	Label tiEmitidoPara, tiValido, tiValidoDe, tiValidoAte, tiEmitidoPor;
	
	
	public CertificadoA3() {
        
		lbValido = new Label("Valido:",            40,   60,  40, 40);
		lbEmitidoPara = new Label("Emitido Para:", 40,   100,  40, 40);
		lbEmitidoPor = new Label("Emitido Por:",   40,   140,  40, 40);
		lbValidoDe = new Label("Válido De:",       40,   180, 40, 40);
		lbValidoAte = new Label("Válido Até:",     220,  180, 40, 40);
		
		tiValido = new Label("",      							80,  60, 100, 40);
		tiEmitidoPara = new Label("", 120,  100, 300, 40);
		tiEmitidoPor = new Label("",  120,  140, 100, 40);
		tiValidoDe = new Label("",    95,  180, 80, 40);
		tiValidoAte = new Label("",   280,  180, 80, 40);
		
		tiEmitidoPara.getStyle().setBorder(new SimpleLineBorder());
		tiValido.getStyle().setBorder(new SimpleLineBorder());
		tiEmitidoPor.getStyle().setBorder(new SimpleLineBorder());
		tiValidoDe.getStyle().setBorder(new SimpleLineBorder());
		tiValidoAte.getStyle().setBorder(new SimpleLineBorder());
		
		add(lbEmitidoPara);
		add(lbValido);
		add(lbEmitidoPor);
		add(lbValidoDe);
		add(lbValidoAte);
		
		add(tiEmitidoPara);
		add(tiValido);
		add(tiEmitidoPor);
		add(tiValidoDe);
		add(tiValidoAte);
		
		trocarCertificao = new Button("Trocar", getSize().x()-60, 20, 40, 40);
		
		add(trocarCertificao);
        
        getListenerMap().addListener(ChangeSizeEvent.class, (event)->{
        	trocarCertificao.setPosition( event.getNewSize().x() - 60, 20);
        });
        
    }
    

   
}
