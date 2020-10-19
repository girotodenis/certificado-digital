package br.com.dsg.driver.resoures;

import java.util.List;
import java.util.stream.Collectors;

import br.com.dsg.driver.entidade.DriverA3;
import br.gov.dsg.certificado.entidades.Local;

public class ResourceProperties implements Resource {

	@Override
	public List<Local> todosDrivers() {
		return DriversA3Util.keys().stream().map(nomeDriver -> new DriverA3(
				nomeDriver, 
				DriversA3Util.get(nomeDriver),
				DriversA3Util.getValores(nomeDriver).length>1)
				)
				.collect(Collectors.toList());
	}

	@Override
	public void salvarPadrao(Local driver) {
		DriversA3Util.setPadrao(driver.getNome());
	}

	@Override
	public void remover(Local item) {
		DriversA3Util.remover(item.getNome());
	}

	@Override
	public void adicionar(String text) {
		DriversA3Util.set("novo_"+todosDrivers().size(), text);
	}

}
