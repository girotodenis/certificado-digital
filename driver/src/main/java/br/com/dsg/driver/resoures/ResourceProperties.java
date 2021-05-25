package br.com.dsg.driver.resoures;

import java.util.List;
import java.util.stream.Collectors;

import br.com.dsg.driver.entidade.DriverA3;

public class ResourceProperties implements Resource {

	@Override
	public List<DriverA3> todosDrivers() {
		return DriversA3Util.keys().stream().map(nomeDriver -> new DriverA3(
				nomeDriver, 
				DriversA3Util.get(nomeDriver),
				DriversA3Util.getValores(nomeDriver).length>1)
				)
				.collect(Collectors.toList());
	}

	@Override
	public void salvarPadrao(DriverA3 driver) {
		DriversA3Util.setPadrao(driver.getNome());
	}

	@Override
	public void remover(DriverA3 item) {
		DriversA3Util.remover(item.getNome());
	}

	@Override
	public void adicionar(String text) {
		DriversA3Util.set("novo_"+todosDrivers().size(), text);
	}

}
