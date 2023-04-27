package projeto.saidas;

import java.util.ArrayList;
import java.util.List;

import projeto.DadosTabela;

public class SaidasTabela {
	List<ElementoSaida> elementos = new ArrayList<>();
	
	
	public SaidasTabela(DadosTabela daTabela) {
		ElementoJpa jpa = new ElementoJpa(daTabela);
		ElementoRepositorio rep = new ElementoRepositorio(daTabela);
		ElementoServicoInterface si = new ElementoServicoInterface(daTabela);
		ElementoServico s = new ElementoServico(daTabela);
		ElementoController c = new ElementoController(daTabela);
		elementos.add(jpa);
		elementos.add(rep);
		elementos.add(si);
		elementos.add(s);
		elementos.add(c);
	}


	public List<ElementoSaida> getElementos() {
		return elementos;
	}
	
}
