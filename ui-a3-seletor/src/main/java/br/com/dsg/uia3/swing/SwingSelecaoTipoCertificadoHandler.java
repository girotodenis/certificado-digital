package br.com.dsg.uia3.swing;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import br.com.dsg.driver.IDriverServices;
import br.com.dsg.driver.entidade.DriverA3;
import br.gov.dsg.certificado.CarregadorKeyStore;
import br.gov.dsg.certificado.entidades.A3Pkcs11;
import br.gov.dsg.certificado.entidades.LocalDriver;
import br.gov.dsg.certificado.entidades.TipoKeyStore;

public class SwingSelecaoTipoCertificadoHandler implements CarregadorKeyStore, ItemListener, ActionListener  {
	
	private IDriverServices service;
	
    static JLabel  l1; 
    static JComboBox c1; 
    static JTextField tf; 
  
    
	public SwingSelecaoTipoCertificadoHandler(IDriverServices service) {
		super();
		this.service = service;
	}
	
	 public void actionPerformed(ActionEvent e) 
	    { 
	        String s = e.getActionCommand(); 
	        if (s.equals("Adicionar Driver")) { 
	        	
	            c1.addItem(tf.getText());
	            service.adicionar(tf.getText());
	            
	            c1.setSelectedItem(tf.getText());
	            l1.setText(c1.getSelectedItem().toString()); 
	     
	        }else {
	        	
	            c1.removeItem(c1.getSelectedItem());
	            service.todos().forEach(item -> {
	            	if(item.getPath().equals(c1.getSelectedItem())) {
	            		service.remover(item);
	            	}
	            });
	        } 
	    } 
	  
	    public void itemStateChanged(ItemEvent e) 
	    { 
	        if (e.getSource() == c1) { 
	            l1.setText(c1.getSelectedItem().toString()); 
	            service.todos().forEach(item -> {
	            	if(item.getPath().equals(c1.getSelectedItem())) {
	            		service.driverPadrao(item);
	            	}
	            });
	        } 
	    } 


	@Override
	public TipoKeyStore tipoKeyStore() {
		TipoKeyStore tipo = null;
		
        String s1[] = service.todos().stream()
        		.map(DriverA3::getPath)
    			.toArray(String[]::new); 
        
        JPanel p = new JPanel(new GridLayout(6,2)); 
        
        JLabel l = new JLabel("Selecione o driver:"); 
        l.setForeground(Color.red); 
        p.add(l); 
        
        c1 = new JComboBox(s1); 
        c1.setSelectedItem(service.driverPadrao().getPath());
        c1.addItemListener(this); 
        p.add(c1);
        
        p.add(new JLabel("Driver selecionado:")); 
        
        
        l1 = new JLabel(service.driverPadrao().getPath()); 
        l1.setForeground(Color.blue); 
        p.add(l1); 
        
        p.add(new JLabel("Cadastrar novo Driver:")); 
        
        tf = new JTextField(16); 
        p.add(tf); 
        
        JButton b = new JButton("Adicionar Driver"); 
        b.addActionListener(this); 
        p.add(b);
        
        JButton b1 = new JButton("Remover Selecionado"); 
        b1.addActionListener(this);
        p.add(b1); 
        
        JPasswordField passwordField = new JPasswordField();
        passwordField.setEchoChar('*');
        passwordField.requestFocus();
        p.add(new JLabel("Senha PIM:")); 
        p.add(passwordField); 
        Object[] obj = { "Digite o Pin do Certificado Digital: \n", passwordField };
        Object[] stringArray = { "Confirmar", "Cancelar" };
        if (JOptionPane.showOptionDialog(null, p, "Certificado Digital", 0, 3, null, stringArray, obj) == 0){
			try {
				tipo = new A3Pkcs11(passwordField.getPassword(), service.driverPadrao());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
        
        return tipo;
		
	}

}
