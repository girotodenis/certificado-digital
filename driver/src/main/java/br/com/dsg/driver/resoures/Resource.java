package br.com.dsg.driver.resoures;

import java.util.List;

import br.gov.dsg.certificado.entidades.Local;

public interface Resource {
	
	
	public List<Local> todosDrivers();

	public void salvarPadrao(Local driver);

	public void remover(Local item);

	public void adicionar(String text);

}
