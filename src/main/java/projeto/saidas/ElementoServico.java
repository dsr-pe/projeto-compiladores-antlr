/***
 * Classe responsável por gerar os arquivos Servico
 * 
 * @author Danilo Rocha
 * @author Heitor Lima
 * @author Jônatas Henrique
 */
package projeto.saidas;

import projeto.DadosTabela;
import projeto.Util;

public class ElementoServico extends ElementoSaida{
	
	private DadosTabela dadosTabela;
	
	public ElementoServico(DadosTabela dTabela) {
		super("service", dTabela.getNomeClasse() + "ServiceImpl");
		this.dadosTabela = dTabela;
		this.imports.add("import java.util.List;\n");
		this.imports.add("import java.util.Optional;\n");
		this.imports.add("import org.springframework.beans.factory.annotation.Autowired;\n");
		this.imports.add("import org.springframework.stereotype.Service;\n");
		this.imports.add( String.format("import repository.%sRepository;\n", this.dadosTabela.getNomeClasse()));
		this.imports.add( String.format("import model.%s;\n", this.dadosTabela.getNomeClasse()));
		this.processar();
	}

	private void processar() {
		this.gerarAbaertura();
		this.gerarAtributos();
		this.gerarMetodos();
		this.gerarFechamento();
		this.montarArquivo();
	}
	
	private void gerarMetodos() {
		linhas.add("\t@Override\n");
		linhas.add(String.format("\tpublic List<%s> getAll() {\n",this.dadosTabela.getNomeClasse()));
		linhas.add(String.format("\t\treturn this.%sRepository.findAll();\n\t}\n",Util.nome(this.dadosTabela.getNomeClasse(), false)));
		
		linhas.add("\t@Override\n");
		linhas.add(String.format("\tpublic void save(%s %s) {\n",this.dadosTabela.getNomeClasse(),Util.nome(this.dadosTabela.getNomeClasse(), false)));
		linhas.add(String.format("\t\tthis.%sRepository.save(%s);\n\t}\n",Util.nome(this.dadosTabela.getNomeClasse(), false),Util.nome(this.dadosTabela.getNomeClasse(), false)));
		
		
		linhas.add("\t@Override\n");
		linhas.add(String.format("\tpublic %s getByPk(%s pk) {\n",this.dadosTabela.getNomeClasse(),this.dadosTabela.getTipoPk()));
		linhas.add(String.format("\t\tOptional<%s> optional%s = this.%sRepository.findById(pk);\n",this.dadosTabela.getNomeClasse(),this.dadosTabela.getNomeClasse(),Util.nome(this.dadosTabela.getNomeClasse(), false)));
		linhas.add(String.format("\t\t%s %s = null;\n",this.dadosTabela.getNomeClasse(),Util.nome(this.dadosTabela.getNomeClasse(), false)));
		linhas.add(String.format("\t\tif (optional%s.isPresent()) {\n",this.dadosTabela.getNomeClasse()));
		linhas.add(String.format("\t\t\t%s = optional%s.get();\n",Util.nome(this.dadosTabela.getNomeClasse(), false),this.dadosTabela.getNomeClasse()));
		linhas.add(String.format("\t\t} else {\n",Util.nome(this.dadosTabela.getNomeClasse(), false),this.dadosTabela.getNomeClasse()));
		linhas.add(String.format("\t\t\tthrow new RuntimeException(\"%s não encontrado(a) para o identifcador : \" + pk);\n",this.dadosTabela.getNomeClasse()));
		linhas.add(String.format("\t\t}\n\t\treturn %s;\n\t}\n\n",Util.nome(this.dadosTabela.getNomeClasse(), false)));
		
		
		linhas.add("\t@Override\n");
		linhas.add(String.format("\tpublic void deleteById(%s pk) {\n",this.dadosTabela.getTipoPk()));
		linhas.add(String.format("\t\tthis.%sRepository.deleteById(pk);\n\t}\n",Util.nome(this.dadosTabela.getNomeClasse(), false)));
		
	}

	private void gerarAtributos() {
		// TODO Auto-generated method stub
		linhas.add(String.format("\t@Autowired\n\tprivate %sRepository %sRepository;\n\n", this.dadosTabela.getNomeClasse(),Util.nome(this.dadosTabela.getNomeClasse(), false)));
	}

	private void gerarAbaertura() {
		linhas.add(String.format("@Service\npublic class %s implements %sService {\n\n", this.nomeArquivo,this.dadosTabela.getNomeClasse()));
	}
	
	private void gerarFechamento() {
		linhas.add("\n}");
	}
	

}