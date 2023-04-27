/***
 * Classe responsável por gerar os arquivos do repositório
 * 
 * @author Danilo Rocha
 * @author Heitor Lima
 * @author Jônatas Henrique
 */

package projeto.saidas;

import projeto.DadosTabela;

public class ElementoRepositorio extends ElementoSaida{
	
	private DadosTabela dadosTabela;
	
	public ElementoRepositorio(DadosTabela dTabela) {
		super("repository", dTabela.getNomeClasse() + "Repository");
		this.dadosTabela = dTabela;
		this.imports.add("import org.springframework.stereotype.Repository;\n");
		this.imports.add("import org.springframework.data.jpa.repository.JpaRepository;\n");
		this.imports.add( String.format("import model.%s;\n", this.dadosTabela.getNomeClasse()));
		this.processar();
	}

	private void processar() {
		this.gerarAbaertura();
		this.gerarFechamento();
		this.montarArquivo();
	}
	
	private void gerarAbaertura() {
		linhas.add(String.format("@Repository\npublic interface %s extends JpaRepository<%s,%s> {\n\n", this.nomeArquivo, this.dadosTabela.getNomeClasse(), this.dadosTabela.getTipoPk()));
	}
	
	private void gerarFechamento() {
		linhas.add("\n}");
	}
	
	

}
