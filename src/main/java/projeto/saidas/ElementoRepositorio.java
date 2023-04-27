package projeto.saidas;

import projeto.DadosTabela;

public class ElementoRepositorio extends ElementoSaida{
	
	private DadosTabela dadosTabela;
	
	public ElementoRepositorio(DadosTabela dTabela) {
		this.dadosTabela = dTabela;
		this.pacote = "repository";
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
		linhas.add(String.format("@Repository\npublic interface %sRepository extends JpaRepository<%s,%s> {\n\n", this.dadosTabela.getNomeClasse(), this.dadosTabela.getNomeClasse(), this.dadosTabela.getTipoPk()));
	}
	
	private void gerarFechamento() {
		linhas.add("\n}");
	}
	
	

}
