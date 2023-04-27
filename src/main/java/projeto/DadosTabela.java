package projeto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import antlr.DdlParser.CriarTabelaContext;
import antlr.DdlParser.DefinicaoColunaContext;
import antlr.DdlParser.NomeTabelaContext;

public class DadosTabela {
	
	private String nomeClasse;
	private String nomeTabela;
	private List<DadosColuna> colunas = new ArrayList<>();
	private CriarTabelaContext contexto;
	private Set<String> nomesColunas = new HashSet<>();
	private String tipoPk;
	public DadosTabela(CriarTabelaContext t) throws Exception {
		this.contexto = t;
		this.processar();
	}

	private void processar() throws Exception {
		this.capturarNomes();
		this.capturaColunas();
	}

	private void capturaColunas() throws Exception {
		List<DefinicaoColunaContext> colunas = contexto.definicaoColuna();
		this.tipoPk = null;
		for (DefinicaoColunaContext definicaoColunaContext : colunas) {
			DadosColuna dadosColuna = new DadosColuna(definicaoColunaContext);
			if(nomesColunas.contains(dadosColuna.getNomeColuna()))
			{
				throw new Exception("Coluna "+dadosColuna.getNomeColuna()+" redefinida para tabela: "+contexto.nomeTabela().getText());
			}
			if(dadosColuna.isPk())
				this.tipoPk = dadosColuna.getTipoAtributo();
			nomesColunas.add(dadosColuna.getNomeColuna());
			this.colunas.add(dadosColuna);
		}
		//Regra JPA
		if(this.tipoPk == null)
		{
			throw new Exception("Tabela não possui primary key");
		}
	}

	private void capturarNomes() {
		NomeTabelaContext nomeTabela = contexto.nomeTabela();
		String nome =nomeTabela.getText();
		this.nomeClasse = Util.nome(nome,true);
		this.nomeTabela = nome;
	}

	public String getNomeClasse() {
		return nomeClasse;
	}

	public String getNomeTabela() {
		return nomeTabela;
	}

	public List<DadosColuna> getColunas() {
		return colunas;
	}

	public String getTipoPk() {
		return tipoPk;
	}
	
	

}
