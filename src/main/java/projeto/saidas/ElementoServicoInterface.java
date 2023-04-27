package projeto.saidas;

import projeto.DadosTabela;
import projeto.Util;

public class ElementoServicoInterface extends ElementoSaida{
	
	private DadosTabela dadosTabela;
	
	public ElementoServicoInterface(DadosTabela dTabela) {
		this.dadosTabela = dTabela;
		this.pacote = "service";
		this.imports.add("import java.util.List;\n");
		this.imports.add( String.format("import model.%s;\n", this.dadosTabela.getNomeClasse()));
		this.processar();
	}

	private void processar() {
		linhas.add(String.format("public interface %sService {\n\n", this.dadosTabela.getNomeClasse()));
		linhas.add(String.format("\tList<%s> getAll();\n", this.dadosTabela.getNomeClasse()));
		linhas.add(String.format("\tvoid save(%s %s);\n", this.dadosTabela.getNomeClasse(), Util.nome(this.dadosTabela.getNomeClasse(), false)));
		linhas.add(String.format("\t%s getByPk(%s pk);\n", this.dadosTabela.getNomeClasse(), this.dadosTabela.getTipoPk()));
		linhas.add(String.format("\tvoid deleteByPk(%s pk);\n", this.dadosTabela.getTipoPk()));
		linhas.add("\n}");
		this.montarArquivo();
	}
	
	

}
