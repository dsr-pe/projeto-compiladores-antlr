/***
 * Classe responsável ler o arquivo sql, fazer a parte Léxica, Parser
 * Além de chamar o extrator de características das tabelas, por fim, possibilita o retorno das classes (Lista saidasTabelas)
 * @author Danilo Rocha
 * @author Heitor Lima
 * @author Jônatas Henrique
 */

package projeto;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
import projeto.saidas.ElementoSaida;
import projeto.saidas.SaidasTabela;

public class GeradorCodigo {

	private String caminho;
	private DdlLexer lexer;
	private TokenStream tkStream;
	private DdlParser parser;
	private List<CriarTabelaContext> tabelaContexto;
	private Set<String> nomesTabelas;
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
		nomesTabelas = new HashSet<>();
		this.valido();
		this.gerarClasses();
	}

	private void gerarClasses() throws Exception {
		for (CriarTabelaContext t : tabelaContexto) {
			NomeTabelaContext nomeTabelaContext = t.nomeTabela();
			if (nomesTabelas.contains(nomeTabelaContext.getText())) {
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
	 * public Set<StringBuffer> getTabelasJpa() { return saidas; }
	 */
	public List<SaidasTabela> getSaidasTabelas() {
		return saidasTabelas;
	}

	public void salvarClasses(String string) throws Exception {
		File r = new File(string);
		Util.deleteDir(r);
		Util.criarDiretorio777(r);
		
		List<SaidasTabela> saidas = this.getSaidasTabelas();
		for (SaidasTabela s : saidas) {
			List<ElementoSaida> elementos = s.getElementos();
			for (ElementoSaida e : elementos) {
				File dp = new File(r, e.getPacote());
				Util.criarDiretorio777(dp);
				File classe = new File(dp,e.getNomeArquivo()+".java");
				FileWriter writer;
				try {
					writer = new FileWriter(classe);
					writer.write(e.getSaida().toString());
					writer.close();
				} catch (IOException e1) {
					throw new Exception("Não foi possível gerar os arquivos");
				}
			    
			    
			}
			
		}
	}

	
	
}
