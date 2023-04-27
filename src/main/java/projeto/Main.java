package projeto;

import java.util.List;

import projeto.saidas.ElementoSaida;
import projeto.saidas.SaidasTabela;

public class Main {


	public static void main(String args[]) throws Exception {
		GeradorCodigo gerCodigo = new GeradorCodigo("input.sql");
		gerCodigo.processar();
		List<SaidasTabela> saidas = gerCodigo.getSaidasTabelas();
		for (SaidasTabela s : saidas) {
			List<ElementoSaida> elementos = s.getElementos();
			for (ElementoSaida e : elementos) {
				System.out.println( e.getSaida());
				System.out.println("=================================================");
			}
			
		}
	}
}
