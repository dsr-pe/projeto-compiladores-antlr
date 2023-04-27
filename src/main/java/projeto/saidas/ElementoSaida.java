/***
 * Classe abstrata com informaçoes básicas dos elementos de saída
 * 
 * @author Danilo Rocha
 * @author Heitor Lima
 * @author Jônatas Henrique
 */

package projeto.saidas;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class ElementoSaida {
	protected Set<String> imports = new HashSet<>();
	protected List<String> linhas = new ArrayList<>();
	protected String pacote;
	protected String nomeArquivo;
	
	protected StringBuffer saida = new StringBuffer();
	
	public StringBuffer getSaida() {
		return saida;
	}
	
	public ElementoSaida(String pacote, String nomeArquivo) {
		this.pacote = pacote;
		this.nomeArquivo = nomeArquivo;
	}
	
	
	
	protected void montarArquivo() {
		if(this.pacote != null) {
			saida.append(String.format("package %s;\n\n", this.pacote));
		}
		for (String imp : imports) {
			saida.append(imp);
		}
		saida.append("\n");
		for (String l : linhas) {
			saida.append(l);
		}
	}
	
	public String getPacote() {
		return this.pacote;
	}
	
	public String getNomeArquivo() {
		return this.nomeArquivo;
	}
}
