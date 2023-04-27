// Generated from Ddl.g4 by ANTLR 4.12.0

  package antlr;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link DdlParser}.
 */
public interface DdlListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link DdlParser#sql}.
	 * @param ctx the parse tree
	 */
	void enterSql(DdlParser.SqlContext ctx);
	/**
	 * Exit a parse tree produced by {@link DdlParser#sql}.
	 * @param ctx the parse tree
	 */
	void exitSql(DdlParser.SqlContext ctx);
	/**
	 * Enter a parse tree produced by {@link DdlParser#criarTabela}.
	 * @param ctx the parse tree
	 */
	void enterCriarTabela(DdlParser.CriarTabelaContext ctx);
	/**
	 * Exit a parse tree produced by {@link DdlParser#criarTabela}.
	 * @param ctx the parse tree
	 */
	void exitCriarTabela(DdlParser.CriarTabelaContext ctx);
	/**
	 * Enter a parse tree produced by {@link DdlParser#definicaoColuna}.
	 * @param ctx the parse tree
	 */
	void enterDefinicaoColuna(DdlParser.DefinicaoColunaContext ctx);
	/**
	 * Exit a parse tree produced by {@link DdlParser#definicaoColuna}.
	 * @param ctx the parse tree
	 */
	void exitDefinicaoColuna(DdlParser.DefinicaoColunaContext ctx);
	/**
	 * Enter a parse tree produced by {@link DdlParser#nomeColuna}.
	 * @param ctx the parse tree
	 */
	void enterNomeColuna(DdlParser.NomeColunaContext ctx);
	/**
	 * Exit a parse tree produced by {@link DdlParser#nomeColuna}.
	 * @param ctx the parse tree
	 */
	void exitNomeColuna(DdlParser.NomeColunaContext ctx);
	/**
	 * Enter a parse tree produced by {@link DdlParser#tipoColuna}.
	 * @param ctx the parse tree
	 */
	void enterTipoColuna(DdlParser.TipoColunaContext ctx);
	/**
	 * Exit a parse tree produced by {@link DdlParser#tipoColuna}.
	 * @param ctx the parse tree
	 */
	void exitTipoColuna(DdlParser.TipoColunaContext ctx);
	/**
	 * Enter a parse tree produced by {@link DdlParser#nomeTabela}.
	 * @param ctx the parse tree
	 */
	void enterNomeTabela(DdlParser.NomeTabelaContext ctx);
	/**
	 * Exit a parse tree produced by {@link DdlParser#nomeTabela}.
	 * @param ctx the parse tree
	 */
	void exitNomeTabela(DdlParser.NomeTabelaContext ctx);
}