/***
 * Classe Main do projeto
 * 
 * @author Danilo Rocha
 * @author Heitor Lima
 * @author Jônatas Henrique
 */

package projeto;

import java.util.List;

import projeto.saidas.ElementoSaida;
import projeto.saidas.SaidasTabela;

public class Main {


	public static void main(String args[]) throws Exception {
		String file = "input.sql", modo = "0";
		if(args.length > 0)
		{
			file = args[0];
			if(args.length > 1) {
				modo = args[1];
			}
		}
		GeradorCodigo gerCodigo = new GeradorCodigo(file);
		gerCodigo.processar();
		
		if("0".equals(modo))
		{
			List<SaidasTabela> saidas = gerCodigo.getSaidasTabelas();
			for (SaidasTabela s : saidas) {
				List<ElementoSaida> elementos = s.getElementos();
				for (ElementoSaida e : elementos) {
					System.out.println( e.getSaida());
					System.out.println("=================================================");
				}
				
			}
		}
		else
		{
			gerCodigo.salvarClasses("saidas");
		}
	}
}
