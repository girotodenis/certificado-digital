package br.com.dsg.uia3.certificado;

import java.text.SimpleDateFormat;

import javax.swing.SwingUtilities;

import br.com.dsg.legui.AbstractController;
import br.com.dsg.legui.ExecutarEvento;
import br.com.dsg.legui.controller.LeGuiController;
import br.com.dsg.legui.controller.LeGuiEventos;
import br.com.dsg.legui.controller.eventos.EventAbrirFecharMenu;
import br.com.dsg.uia3.KeyStoreHandler;
import br.com.dsg.uia3.selecao.ControllerSelecaoA3;
import br.gov.dsg.certificado.entidades.Certificado;

public class ControllerCertificado extends AbstractController<CertificadoA3> {


	
	public ControllerCertificado(AbstractController<?> controllerPai) {
		super(controllerPai, new CertificadoA3());
		
		registerAction(getPanel().trocarCertificao, (e)->{
			FactoryCertificado.get().setKeyStoreHandler(null);
			LeGuiEventos.irPara( getControllerSelecaoA3() );
			
		});
		
		loadTipoKeyStore();
		

	}


	private void loadTipoKeyStore() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					Thread.sleep(100);
					if(!FactoryCertificado.get().isKeyStoreLoad()) {
						LeGuiEventos.irPara( getControllerSelecaoA3() );
					}
				} catch (Exception e) {
				}
			}
		});
		
	}

	public ControllerSelecaoA3 getControllerSelecaoA3() {
		return new ControllerSelecaoA3(ControllerCertificado.this, FactoryCertificado.get().getDriverServices(), (tipo)->{
			FactoryCertificado.get().setKeyStoreHandler(new KeyStoreHandler(tipo));
			LeGuiEventos.irPara(this);
			Certificado certificado = FactoryCertificado.get().getCertificadoServiceA3Token().certificados().get(0);
			
			getPanel().tiEmitidoPara.getTextState().setText(certificado.emitidoPara());
			getPanel().tiEmitidoPor.getTextState().setText(certificado.emitidoPor());
			getPanel().tiValido.getTextState().setText(certificado.isValido()?"Sim":"Não");
			
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			getPanel().tiValidoAte.getTextState().setText(simpleDateFormat.format(certificado.validoAte()));
			getPanel().tiValidoDe.getTextState().setText(simpleDateFormat.format(certificado.validoDe()));
		
		});
	}
}
