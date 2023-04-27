
# Gerador de classes Spring MVC

## Equipe

 - Danilo S Rocha
 - Heitor Lima
 - Jônatas Henrique

## Motivação

A velocidade de entrega de um projeto é um fator crucial nos dias de hoje por diversas razões. Em um mundo cada vez mais competitivo, as empresas precisam ser ágeis e flexíveis para acompanhar as mudanças do mercado e atender às necessidades dos clientes.
Em um projeto utilizando Spring MVC a criação de CRUDs (Create, Read, Update, Delete) manualmente pode ser uma tarefa demorada e propensa a erros, por exemplo:

 - As classes devem corresponder exatamente as definições do banco de dados
 - O desenvolvedor deve ficar atento aos usos das Anotações Java.
 - Dúvidas nas correspondências dos tipos definidos no banco de dados e os tipos definidos no Java
 - Dentre outros

Além disso, esse processo de criação é bastante mecânico e o desenvolvedor poderia dedicar o seu tempo em códigos que diferenciam sua aplicação das outras, não em processos totalmente semelhantes.

## Proposta
A ideia é fazer um gerador de código, utilizando ANTLR, que utilizando como entrada os comando DDL dos banco de dados (os creates tables), seja possível gerar as classes dos pacotes: model, repository, service e controller. Seguindo a hierarquia indicada a seguir:

![enter image description here](https://github.com/dsr-pe/projeto-compiladores-antlr/blob/main/imagens/estrutura-dir.png?raw=true)

Podemos observar que para cada tabela do banco de dados, devemos ter em nosso projeto Spring, normalmente, cinco arquivos Java.

 - Controller - Responsável por receber e processar as requisições HTTP, determinando qual ação deve ser executada e retornando a resposta adequada. 
 - Model - Classe de modelo que representa uma entidade persistida em um banco de dados relacional, sendo mapeada por meio de anotações JPA para a camada de persistência.
 - Repository: É uma interface que define as operações de persistência que podem ser realizadas em um banco de dados, permitindo que a camada de serviço da aplicação possa manipular as entidades sem se preocupar com os detalhes de implementação do acesso ao banco.
 - Service interface: É uma interface que define as operações que a camada de serviço da aplicação deve oferecer para manipular as entidades persistidas, fornecendo uma abstração do domínio da aplicação e possibilitando a separação de responsabilidades.
 - Service classe: A Service classe é a implementação concreta da interface Service, contendo a lógica de negócio da aplicação e utilizando o Repository para acessar o banco de dados e manipular as entidades persistidas.
 
Vale salientar que na maioria dos casos, o código gerado será uma versão definitiva, ou seja, o desenvolvedor terá que realizar as modificações necessárias para atender seu projeto. 

## Características do programa proposto

 - impede a duplicidade de tabelas
 - impede duplicidade de colunas em uma mesma tabela
 - exige que cada tabela possua uma primary key
 - realiza os imports Java apenas das classes necessárias
 - possui dois modos de saídas do resultado (em disco e console)
 - quando em disco, o resultado estará na pasta saída na raiz do projeto
 - quando o resultado for gerado em disco, os arquivos estarão em seu diretório correspondente aos pacotes.

## Limitações
 - A gramática definida não abrange todos os tipos de dados suportados em um banco de dados, mas adicionar um novo tipo é uma tarefa bem simples.
 - Ainda não há suporte para Foreign Key, nem tabelas com múltiplas Primary keys
 - Para entender o que de fato é suportado pelo projeto, leia com atenção o tópico que descreve a gramática.

## Gramática - Frontend

Abaixo pode ser visto como foi definida a gramática do projeto, com detalhamento da sua especificação: 

````ANTLR
grammar Ddl;

  

options {

language = Java;

}

@header {

package antlr;

}

  

sql : criarTabela+;

criarTabela : 'CREATE' 'TABLE' nomeTabela '(' definicaoColuna (',' definicaoColuna)* ')' ';';

definicaoColuna : nomeColuna tipoColuna (TAM)? ( NN )? ( PK )? ;

nomeColuna : ID ;

tipoColuna : 'INT' | 'FLOAT' | 'DECIMAL' |'DOUBLE' |'NUMERIC'| 'VARCHAR' | 'TEXT' | 'BOOLEAN'| 'BOOL'| 'DATE' ;

TAM: '('[0-9]+')';

NN : 'NOT NULL';

PK : 'PRIMARY KEY' ;

nomeTabela : ID ;

ID : [a-zA-Z_][a-zA-Z0-9_]* ;

WS : [ \t\n\r]+ -> skip ;
````


##  Tutorial de uso

### Criando um codespace

Para criar um codespace para o projeto, basta clicar nos botões, correspondentes no menu do repositório do GitHub, identificados em verde abaixo:



![enter image description here](https://github.com/dsr-pe/projeto-compiladores-antlr/blob/main/imagens/codespace-criacao.png?raw=true)



Quando o Codespace for criado com sucesso, você irá visualizar as informações do projeto, conforme imagem:


![enter image description here](https://github.com/dsr-pe/projeto-compiladores-antlr/blob/main/imagens/codespace-projeto.png?raw=true)

Na aba terminal do Codespace, digite o seguinte comando para compilar nossa gramática:

Entre no diretório que a gramática está localizada
````shell
cd src/main/java/gramatica/
````

Após isso, compile a gramática
````shell
java -jar /workspaces/projeto-compiladores-antlr/antlr-4.12.0-complete.jar -o ../antlr Ddl.g4
````

Perceba o valor ../antlr para a opção -o, isso indica que a saída do antlr será na pasta src/main/java/antlr, esse valor pode ser modificado à vontade, mas lembre-se de adaptar os próximos passos.

Volte para o diretório principal do projeto
````shell
cd /workspaces/projeto-compiladores-antlr/
````

Uma vez gerado as classes Java que compreendem nossa gramática, é a hora de compilar nosso projeto, incluindo as classes geradas pelo ANTLR
````shell
javac -cp antlr-4.12.0-complete.jar -d bin  src/main/java/antlr/*.java src/main/java/projeto/saidas/*.java src/main/java/projeto/*.java 
````

Se tudo ocorreu bem, surgirá uma pasta bin no diretório principal do projeto e nenhuma mensagem de erro irá aparecer no console.

![enter image description here](https://github.com/dsr-pe/projeto-compiladores-antlr/blob/main/imagens/codespace-projeto-compilado.png?raw=true)

Por fim, vamos rodar a aplicação. O método main do projeto, pode receber dois parâmetros opcionais, o primeiro é o caminho do arquivo sql, contendo as informações das criações das tabelas,  já segundo corresponde ao modo de saída, podendo valer 0 ou 1. Caso 0 a saída será no console, caso contrário a saída será no disco. Quando nada for informado, o programa irá considerar o arquivo "input.sql" e o valor para o modo de saída será 0. Vejamos os exemplos abaixo:

### Conteúdo de exemplo do arquivo input.sql

````SQL
CREATE TABLE usuario (
	ID INT NOT NULL PRIMARY KEY,
	NOME VARCHAR (200) NOT NULL ,
	DATA_NACIMENTO DATE,
	SALARIO NUMERIC,
	DIVIDA FLOAT,
	PESO DOUBLE
);

CREATE TABLE produto (
	id INT NOT NULL PRIMARY KEY,
	nome VARCHAR (100),
	ATIVO BOOLEAN NOT NULL
);

CREATE TABLE departamento (
	id INT NOT NULL PRIMARY KEY,
	nome VARCHAR (100),
	ATIVO BOOLEAN NOT NULL
);
````

### Rodando "input.sql" com modo saída 0 (Console)
````shell
java -cp bin:antlr-4.12.0-complete.jar projeto.Main input.sql 0
````
#### Resultado
No caso abaixo, para não ficar muito extenso foi considerado apenas a tabela departamento. Mas, executando os comandos no Codespace será possível visualizar todo o resultado.

````shell
package model;

import javax.validation.constraints.Size;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Entity;

@Entity
@Table(name = "departamento" )
public class Departamento {

        @Id
        @Column(name = "id" nullable=false)
        private Long id;

        @Column(name = "nome" nullable=true)
        @Size(min = 0, max = 100)
        private String nome;

        @Column(name = "ATIVO" nullable=false)
        private Boolean ativo;

        public Long getId(){
                return this.id;
        }

        public void setId(Long id){
                 this.id = id;
        }

        public String getNome(){
                return this.nome;
        }

        public void setNome(String nome){
                 this.nome = nome;
        }

        public Boolean getAtivo(){
                return this.ativo;
        }

        public void setAtivo(Boolean ativo){
                 this.ativo = ativo;
        }


}
=================================================
package repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import model.Departamento;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento,Long> {


}
=================================================
package service;

import java.util.List;
import model.Departamento;

public interface DepartamentoService {

        List<Departamento> getAll();
        void save(Departamento departamento);
        Departamento getByPk(Long pk);
        void deleteByPk(Long pk);

}
=================================================
package service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import org.springframework.stereotype.Service;
import model.Departamento;
import repository.DepartamentoRepository;

@Service
public class DepartamentoServiceImpl implements DepartamentoService {

        @Autowired
        private DepartamentoRepository departamentoRepository;

        @Override
        public List<Departamento> getAll() {
                return this.departamentoRepository.findAll();
        }
        @Override
        public void save(Departamento departamento) {
                this.departamentoRepository.save(departamento);
        }
        @Override
        public Departamento getByPk(Long pk) {
                Optional<Departamento> optionalDepartamento = this.departamentoRepository.findById(pk);
                Departamento departamento = null;
                if (optionalDepartamento.isPresent()) {
                        departamento = optionalDepartamento.get();
                } else {
                        throw new RuntimeException("Departamento não encontrado(a) para o identifcador : " + pk);
                }
                return departamento;
        }

        @Override
        public void deleteById(Long pk) {
                this.departamentoRepository.deleteById(pk);
        }

}
=================================================
package controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import model.Departamento;
import service.DepartamentoService;

@Controller
@RequestMapping("/departamento")
public class DepartamentoController {

        @Autowired
        private DepartamentoService departamentoService;

        @GetMapping("/")
        public String index(Model model) {
                List<Departamento> lista = this.departamentoService.getAll();
                model.addAttribute("lista",lista);
                return "index";
        }

        @GetMapping("/novo")
        public String add(Model model) {
                Departamento departamento = new Departamento();
                model.addAttribute("departamento",departamento);
                return "cad-departamento";
        }

        @PostMapping("/salvar")
        public String salvar(@ModelAttribute("departamento") Departamento departamento) {
                this.departamentoService.save(departamento);
                return "redirect:/";
        }

        @GetMapping("/alterar/{pk}")
        public String alterar(@PathVariable( value = "pk") Long pk, Model model) {
                Departamento departamento = this.departamentoService.getById(pk);
                model.addAttribute("departamento", departamento);
                return "alt-departamento";
        }

        @GetMapping("/excluir/{pk}")
        public String excluir(@PathVariable( value = "pk") Long pk) {
                this.departamentoService.deleteById(pk);
                return "redirect:/";
        }


}
=================================================
````

### Rodando "input.sql" com modo saída 1 (Disco)
````shell
java -cp bin:antlr-4.12.0-complete.jar projeto.Main input.sql 1
````
#### Resultado
O programa irá gerar os arquivos, respeitando os "pacotes", no disco. Conforme imagem abaixo:

![enter image description here](https://github.com/dsr-pe/projeto-compiladores-antlr/blob/main/imagens/codespace-resultado-disco.png?raw=true)
