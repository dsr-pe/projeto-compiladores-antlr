package projeto;

import org.antlr.v4.runtime.tree.TerminalNode;

import antlr.DdlParser.DefinicaoColunaContext;
import antlr.DdlParser.NomeColunaContext;
import antlr.DdlParser.TipoColunaContext;

public class DadosColuna {
	
	private DefinicaoColunaContext definicaoColunaContext;
	private String nomeColuna;
	private String nomeAtributo;
	private String tipoAtributo;
	private String tamanho;
	private boolean pk;
	private boolean naoNulo;
	
	public DadosColuna(DefinicaoColunaContext definicaoColunaContext) {
		this.definicaoColunaContext = definicaoColunaContext;
		this.processa();
	}

	private void processa() {
		this.capturarNome();
		this.capturaPk();
		this.capturaNaoNulo();
		this.capturaTipo();
		this.capturaTamanho();
	}

	private void capturaTamanho() {
		TerminalNode tam = definicaoColunaContext.TAM();
		if(tam != null) {
			this.tamanho = tam.getText().replace("(", "").replace(")", "");
		}
	}

	private void capturaTipo() {
		TipoColunaContext tipo = definicaoColunaContext.tipoColuna();
		String t = null;
		switch (tipo.getText()) {
		case "INT":
			t = "Long";
			break;
		case "FLOAT":
			t = "Float";
			break;
		case "DECIMAL":
		case "DOUBLE":
		case "NUMERIC":
			t = "Double";
			break;
		case "BOOL":
		case "BOOLEAN":
			t = "Boolean";
			break;
		case "VARCHAR":
		case "TEXT":
			t = "String";
			break;
		case "DATE":
			t = "Date";
			break;
		}
		this.tipoAtributo = t;
		
	}

	private void capturaNaoNulo() {
		TerminalNode naoNulo = definicaoColunaContext.NN();
		this.naoNulo = naoNulo != null;
	}

	private void capturaPk() {
		TerminalNode pkNode = definicaoColunaContext.PK();
		this.pk = pkNode != null;
	}

	private void capturarNome() {
		NomeColunaContext nome = definicaoColunaContext.nomeColuna();
		
		String nomeVar = nome.getText();
		this.nomeColuna = nomeVar;
		this.nomeAtributo = Util.nome(nomeVar, false);
	}

	public String getNomeColuna() {
		return nomeColuna;
	}

	public String getNomeAtributo() {
		return nomeAtributo;
	}

	public String getTipoAtributo() {
		return tipoAtributo;
	}

	public String getTamanho() {
		return tamanho;
	}

	public boolean isPk() {
		return pk;
	}

	public boolean isNaoNulo() {
		return naoNulo;
	}
	
	
}
