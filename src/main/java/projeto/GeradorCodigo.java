package projeto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;

import antlr.DdlLexer;
import antlr.DdlParser;
import antlr.DdlParser.CriarTabelaContext;
import antlr.DdlParser.NomeTabelaContext;
import antlr.DdlParser.SqlContext;
import projeto.saidas.SaidasTabela;

public class GeradorCodigo {

	
	private String caminho;
	private DdlLexer lexer;
	private TokenStream tkStream;
	private DdlParser parser;
	private List<CriarTabelaContext> tabelaContexto;
	private Set<String> nomesTabelas;
	//private Set<StringBuffer> saidas;
	private List<SaidasTabela> saidasTabelas = new ArrayList<>();
	

	public GeradorCodigo(String c) {
		this.caminho = c;
	}

	public void processar() throws Exception {
		CharStream src = CharStreams.fromFileName(this.caminho);
		this.lexer = new DdlLexer(src);
		this.tkStream = new CommonTokenStream(lexer);
		this.parser = new DdlParser(tkStream);
		SqlContext sql = parser.sql();
		this.tabelaContexto = sql.criarTabela();
		//saidas = new HashSet<>();
		nomesTabelas = new HashSet<>();
		this.valido();
		this.gerarClasses();
	}
	
	public void gerarClasses() throws Exception {
		for (CriarTabelaContext t : tabelaContexto) {
			NomeTabelaContext nomeTabelaContext = t.nomeTabela();
			if(nomesTabelas.contains(nomeTabelaContext.getText()))
			{
				throw new Exception("Tabela redefinada");
			}
			nomesTabelas.add(nomeTabelaContext.getText());
			
			DadosTabela extrairDados = new DadosTabela(t);
			SaidasTabela st = new SaidasTabela(extrairDados);
			saidasTabelas.add(st);
			
		}
		
	}

	private void valido() throws Exception {
		if (this.parser.getNumberOfSyntaxErrors() > 0) {
			throw new Exception("Nao foi possivel processar arquivo SQL com as definicoes da tabela");
		}
	}
	/*
	public Set<StringBuffer> getTabelasJpa()
	{
		return saidas;
	}
	*/
	public List<SaidasTabela> getSaidasTabelas() {
		return saidasTabelas;
	}
	
	
	
	

}
