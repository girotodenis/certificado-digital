package br.com.dsg.driver.resoures;

import java.util.List;

import br.com.dsg.driver.entidade.DriverA3;

public interface Resource {
	
	
	public List<DriverA3> todosDrivers();

	public void salvarPadrao(DriverA3 driver);

	public void remover(DriverA3 item);

	public void adicionar(String text);

}
