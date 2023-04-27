package projeto.saidas;

import projeto.DadosTabela;
import projeto.Util;

public class ElementoController extends ElementoSaida{
	
	private DadosTabela dadosTabela;
	
	public ElementoController(DadosTabela dTabela) {
		this.dadosTabela = dTabela;
		this.pacote = "controller";
		this.imports.add("import java.util.List;\n");
		this.imports.add("import org.springframework.beans.factory.annotation.Autowired;\n");
		this.imports.add("import org.springframework.web.bind.annotation.ModelAttribute;\n");
		this.imports.add("import org.springframework.web.bind.annotation.GetMapping;\n");
		this.imports.add("import org.springframework.web.bind.annotation.PostMapping;\n");
		this.imports.add("import org.springframework.web.bind.annotation.PathVariable;\n");
		this.imports.add("import org.springframework.stereotype.Controller;\n");
		this.imports.add("import org.springframework.web.bind.annotation.RequestMapping;\n");
		this.imports.add("import org.springframework.ui.Model;\n");
		this.imports.add( String.format("import service.%sService;\n", this.dadosTabela.getNomeClasse()));
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
		linhas.add("\t@GetMapping(\"/\")\n");
		linhas.add("\tpublic String index(Model model) {\n");
		linhas.add(String.format("\t\tList<%s> lista = this.%sService.getAll();\n",this.dadosTabela.getNomeClasse(),Util.nome(this.dadosTabela.getNomeClasse(), false)));
		linhas.add("\t\tmodel.addAttribute(\"lista\",lista);\n");
		linhas.add("\t\treturn \"index\";\n\t}\n\n");
		
		linhas.add("\t@GetMapping(\"/novo\")\n");
		linhas.add("\tpublic String add(Model model) {\n");
		linhas.add(String.format("\t\t%s %s = new %s();\n",this.dadosTabela.getNomeClasse(), Util.nome(this.dadosTabela.getNomeClasse(), false), this.dadosTabela.getNomeClasse()));
		linhas.add(String.format("\t\tmodel.addAttribute(\"%s\",%s);\n",Util.nome(this.dadosTabela.getNomeClasse(), false), Util.nome(this.dadosTabela.getNomeClasse(), false)));
		linhas.add(String.format("\t\treturn \"cad-%s\";\n\t}\n\n",Util.nome(this.dadosTabela.getNomeClasse(), false)));
		
		linhas.add("\t@PostMapping(\"/salvar\")\n");
		linhas.add(String.format("\tpublic String salvar(@ModelAttribute(\"%s\") %s %s) {\n", Util.nome(this.dadosTabela.getNomeClasse(), false), this.dadosTabela.getNomeClasse(),Util.nome(this.dadosTabela.getNomeClasse(), false)));
		linhas.add(String.format("\t\tthis.%sService.save(%s);\n", Util.nome(this.dadosTabela.getNomeClasse(), false),  Util.nome(this.dadosTabela.getNomeClasse(), false)));
		linhas.add("\t\treturn \"redirect:/\";\n\t}\n\n");
		
		linhas.add("\t@GetMapping(\"/alterar/{pk}\")\n");
		linhas.add(String.format("\tpublic String alterar(@PathVariable( value = \"pk\") %s pk, Model model) {\n", this.dadosTabela.getTipoPk()));
		linhas.add(String.format("\t\t%s %s = this.%sService.getById(pk);\n", this.dadosTabela.getNomeClasse(),Util.nome(this.dadosTabela.getNomeClasse(), false), Util.nome(this.dadosTabela.getNomeClasse(), false)));
		linhas.add(String.format("\t\tmodel.addAttribute(\"%s\", %s);\n", Util.nome(this.dadosTabela.getNomeClasse(), false), Util.nome(this.dadosTabela.getNomeClasse(), false)));
		linhas.add(String.format("\t\treturn \"alt-%s\";\n\t}\n\n",Util.nome(this.dadosTabela.getNomeClasse(), false)));
		
		linhas.add("\t@GetMapping(\"/excluir/{pk}\")\n");
		linhas.add(String.format("\tpublic String excluir(@PathVariable( value = \"pk\") %s pk) {\n", this.dadosTabela.getTipoPk()));
		linhas.add(String.format("\t\tthis.%sService.deleteById(pk);\n", Util.nome(this.dadosTabela.getNomeClasse(), false)));
		linhas.add("\t\treturn \"redirect:/\";\n\t}\n\n");
		
	}

	private void gerarAtributos() {
		// TODO Auto-generated method stub
		linhas.add(String.format("\t@Autowired\n\tprivate %sService %sService;\n\n", this.dadosTabela.getNomeClasse(),Util.nome(this.dadosTabela.getNomeClasse(), false)));
	}

	private void gerarAbaertura() {
		linhas.add(String.format("@Controller\n@RequestMapping(\"/%s\")\npublic class %sController{\n\n", Util.nome(this.dadosTabela.getNomeClasse(), false),this.dadosTabela.getNomeClasse(),this.dadosTabela.getNomeClasse()));
	}
	
	private void gerarFechamento() {
		linhas.add("\n}");
	}
	

}