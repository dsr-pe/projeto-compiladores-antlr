# Gerador de classes Spring MVC

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

## Limitações
 - A gramática definida não abrange todos os tipos de dados suportados em um banco de dados, mas adicionar um novo tipo é uma tarefa bem simples.
 - Ainda não há suporte para Foreign Key, nem tabelas com múltiplas Primary keys
 - Para entender o que de fato é suportado pelo projeto, leia com atenção o tópico que descreve a gramática.

## Gramática - Frontend

Abaixo pode ser visto como foi definida a gramatica do projeto o detalhamento da sua especificação: 

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
