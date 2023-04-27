package projeto.saidas;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class ElementoSaida {
	protected Set<String> imports = new HashSet<>();
	protected List<String> linhas = new ArrayList<>();
	protected String pacote;
	
	protected StringBuffer saida = new StringBuffer();
	
	public StringBuffer getSaida() {
		return saida;
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
}
