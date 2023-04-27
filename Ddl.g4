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
tipoColuna : 'INT'  | 'FLOAT' | 'DECIMAL' |'DOUBLE' |'NUMERIC'| 'VARCHAR' | 'TEXT' | 'BOOLEAN'| 'BOOL'| 'DATE' ;
TAM: '('[0-9]+')';
NN    : 'NOT NULL';
PK : 'PRIMARY KEY' ;
nomeTabela : ID ;
ID : [a-zA-Z_][a-zA-Z0-9_]* ;
WS : [ \t\n\r]+ -> skip ;
 