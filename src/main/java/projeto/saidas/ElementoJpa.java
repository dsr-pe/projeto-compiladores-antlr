package projeto.saidas;

import java.util.List;

import projeto.DadosColuna;
import projeto.DadosTabela;
import projeto.Util;

public class ElementoJpa extends ElementoSaida{
	
	private DadosTabela dadosTabela;
	
	public ElementoJpa(DadosTabela dTabela) {
		this.dadosTabela = dTabela;
		this.pacote = "model";
		this.imports.add("import javax.persistence.Entity;\n");
		this.imports.add( "import javax.persistence.Column;\n");
		this.imports.add( "import javax.persistence.Table;\n");
		this.processar();
	}

	private void processar() {
		this.gerarAbaerturaTabela();
		this.gerarInfoColunas();
		this.gerarFechamentoTabela();

		this.montarArquivo();
		
	}


	private void gerarAbaerturaTabela() {
		linhas.add(String.format("@Entity\n@Table(name = \"%s\" )\npublic class %s {\n\n", this.dadosTabela.getNomeTabela(),this.dadosTabela.getNomeClasse()));
	}
	
	private void gerarInfoColunas() {
		this.gerarAtributos();
		this.gerarGettersAndSetters();
	}
	
	private void gerarAtributos() {
		List<DadosColuna> colunas = this.dadosTabela.getColunas();
		for (DadosColuna dadosColuna : colunas) {
			if (dadosColuna.isPk()) {
				imports.add("import javax.persistence.Id;\n");
				linhas.add("\t@Id\n");
			}
			
			linhas.add(String.format("\t@Column(name = \"%s\" nullable=%s)\n", dadosColuna.getNomeColuna(), (dadosColuna.isNaoNulo() ? "false" : "true")));
			if (dadosColuna.getTamanho() != null) {
				imports.add("import javax.validation.constraints.Size;\n");
				linhas.add(String.format("\t@Size(min = 0, max = %s)\n", dadosColuna.getTamanho()));
			}
			
			String tipo = dadosColuna.getTipoAtributo();
			if("Date".equals(tipo)) {
				imports.add("import java.util.Date;\n");
				imports.add("import javax.persistence.Temporal;\n");
				imports.add("import javax.persistence.TemporalType;\n");
				linhas.add("\t@Temporal(TemporalType.DATE)\n");
			}
			
			linhas.add(String.format("\tprivate %s %s;\n\n", tipo, dadosColuna.getNomeAtributo()));
		}
	}
	
	private void gerarGettersAndSetters() {
		List<DadosColuna> colunas = this.dadosTabela.getColunas();
		for (DadosColuna dadosColuna : colunas) {
			String metodo = Util.nome(dadosColuna.getNomeAtributo(),true);
			linhas.add(String.format("\tpublic %s get%s(){\n",dadosColuna.getTipoAtributo(),metodo));
			linhas.add(String.format("\t\treturn this.%s;\n",dadosColuna.getNomeAtributo()));
			linhas.add("\t}\n\n");
			linhas.add(String.format("\tpublic void set%s(%s %s){\n",metodo,dadosColuna.getTipoAtributo(), dadosColuna.getNomeAtributo()));
			linhas.add(String.format("\t\t this.%s = %s;\n",dadosColuna.getNomeAtributo(),dadosColuna.getNomeAtributo()));
			linhas.add("\t}\n\n");		
		}
	}
	private void gerarFechamentoTabela() {
		linhas.add("\n}");
	}

}
