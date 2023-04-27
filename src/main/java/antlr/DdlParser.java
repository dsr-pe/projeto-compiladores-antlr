// Generated from Ddl.g4 by ANTLR 4.12.0

  package antlr;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class DdlParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.12.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, TAM=17, 
		NN=18, PK=19, ID=20, WS=21;
	public static final int
		RULE_sql = 0, RULE_criarTabela = 1, RULE_definicaoColuna = 2, RULE_nomeColuna = 3, 
		RULE_tipoColuna = 4, RULE_nomeTabela = 5;
	private static String[] makeRuleNames() {
		return new String[] {
			"sql", "criarTabela", "definicaoColuna", "nomeColuna", "tipoColuna", 
			"nomeTabela"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'CREATE'", "'TABLE'", "'('", "','", "')'", "';'", "'INT'", "'FLOAT'", 
			"'DECIMAL'", "'DOUBLE'", "'NUMERIC'", "'VARCHAR'", "'TEXT'", "'BOOLEAN'", 
			"'BOOL'", "'DATE'", null, "'NOT NULL'", "'PRIMARY KEY'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, "TAM", "NN", "PK", "ID", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Ddl.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public DdlParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SqlContext extends ParserRuleContext {
		public List<CriarTabelaContext> criarTabela() {
			return getRuleContexts(CriarTabelaContext.class);
		}
		public CriarTabelaContext criarTabela(int i) {
			return getRuleContext(CriarTabelaContext.class,i);
		}
		public SqlContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sql; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DdlListener ) ((DdlListener)listener).enterSql(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DdlListener ) ((DdlListener)listener).exitSql(this);
		}
	}

	public final SqlContext sql() throws RecognitionException {
		SqlContext _localctx = new SqlContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_sql);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(13); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(12);
				criarTabela();
				}
				}
				setState(15); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__0 );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CriarTabelaContext extends ParserRuleContext {
		public NomeTabelaContext nomeTabela() {
			return getRuleContext(NomeTabelaContext.class,0);
		}
		public List<DefinicaoColunaContext> definicaoColuna() {
			return getRuleContexts(DefinicaoColunaContext.class);
		}
		public DefinicaoColunaContext definicaoColuna(int i) {
			return getRuleContext(DefinicaoColunaContext.class,i);
		}
		public CriarTabelaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_criarTabela; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DdlListener ) ((DdlListener)listener).enterCriarTabela(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DdlListener ) ((DdlListener)listener).exitCriarTabela(this);
		}
	}

	public final CriarTabelaContext criarTabela() throws RecognitionException {
		CriarTabelaContext _localctx = new CriarTabelaContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_criarTabela);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(17);
			match(T__0);
			setState(18);
			match(T__1);
			setState(19);
			nomeTabela();
			setState(20);
			match(T__2);
			setState(21);
			definicaoColuna();
			setState(26);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(22);
				match(T__3);
				setState(23);
				definicaoColuna();
				}
				}
				setState(28);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(29);
			match(T__4);
			setState(30);
			match(T__5);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DefinicaoColunaContext extends ParserRuleContext {
		public NomeColunaContext nomeColuna() {
			return getRuleContext(NomeColunaContext.class,0);
		}
		public TipoColunaContext tipoColuna() {
			return getRuleContext(TipoColunaContext.class,0);
		}
		public TerminalNode TAM() { return getToken(DdlParser.TAM, 0); }
		public TerminalNode NN() { return getToken(DdlParser.NN, 0); }
		public TerminalNode PK() { return getToken(DdlParser.PK, 0); }
		public DefinicaoColunaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_definicaoColuna; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DdlListener ) ((DdlListener)listener).enterDefinicaoColuna(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DdlListener ) ((DdlListener)listener).exitDefinicaoColuna(this);
		}
	}

	public final DefinicaoColunaContext definicaoColuna() throws RecognitionException {
		DefinicaoColunaContext _localctx = new DefinicaoColunaContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_definicaoColuna);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(32);
			nomeColuna();
			setState(33);
			tipoColuna();
			setState(35);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==TAM) {
				{
				setState(34);
				match(TAM);
				}
			}

			setState(38);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NN) {
				{
				setState(37);
				match(NN);
				}
			}

			setState(41);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PK) {
				{
				setState(40);
				match(PK);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NomeColunaContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(DdlParser.ID, 0); }
		public NomeColunaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nomeColuna; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DdlListener ) ((DdlListener)listener).enterNomeColuna(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DdlListener ) ((DdlListener)listener).exitNomeColuna(this);
		}
	}

	public final NomeColunaContext nomeColuna() throws RecognitionException {
		NomeColunaContext _localctx = new NomeColunaContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_nomeColuna);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(43);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TipoColunaContext extends ParserRuleContext {
		public TipoColunaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tipoColuna; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DdlListener ) ((DdlListener)listener).enterTipoColuna(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DdlListener ) ((DdlListener)listener).exitTipoColuna(this);
		}
	}

	public final TipoColunaContext tipoColuna() throws RecognitionException {
		TipoColunaContext _localctx = new TipoColunaContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_tipoColuna);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(45);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 130944L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NomeTabelaContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(DdlParser.ID, 0); }
		public NomeTabelaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nomeTabela; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DdlListener ) ((DdlListener)listener).enterNomeTabela(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DdlListener ) ((DdlListener)listener).exitNomeTabela(this);
		}
	}

	public final NomeTabelaContext nomeTabela() throws RecognitionException {
		NomeTabelaContext _localctx = new NomeTabelaContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_nomeTabela);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(47);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u00152\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0001\u0000\u0004\u0000\u000e\b\u0000\u000b\u0000\f"+
		"\u0000\u000f\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0005\u0001\u0019\b\u0001\n\u0001\f\u0001\u001c"+
		"\t\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0003\u0002$\b\u0002\u0001\u0002\u0003\u0002\'\b\u0002\u0001\u0002"+
		"\u0003\u0002*\b\u0002\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0000\u0000\u0006\u0000\u0002\u0004"+
		"\u0006\b\n\u0000\u0001\u0001\u0000\u0007\u00100\u0000\r\u0001\u0000\u0000"+
		"\u0000\u0002\u0011\u0001\u0000\u0000\u0000\u0004 \u0001\u0000\u0000\u0000"+
		"\u0006+\u0001\u0000\u0000\u0000\b-\u0001\u0000\u0000\u0000\n/\u0001\u0000"+
		"\u0000\u0000\f\u000e\u0003\u0002\u0001\u0000\r\f\u0001\u0000\u0000\u0000"+
		"\u000e\u000f\u0001\u0000\u0000\u0000\u000f\r\u0001\u0000\u0000\u0000\u000f"+
		"\u0010\u0001\u0000\u0000\u0000\u0010\u0001\u0001\u0000\u0000\u0000\u0011"+
		"\u0012\u0005\u0001\u0000\u0000\u0012\u0013\u0005\u0002\u0000\u0000\u0013"+
		"\u0014\u0003\n\u0005\u0000\u0014\u0015\u0005\u0003\u0000\u0000\u0015\u001a"+
		"\u0003\u0004\u0002\u0000\u0016\u0017\u0005\u0004\u0000\u0000\u0017\u0019"+
		"\u0003\u0004\u0002\u0000\u0018\u0016\u0001\u0000\u0000\u0000\u0019\u001c"+
		"\u0001\u0000\u0000\u0000\u001a\u0018\u0001\u0000\u0000\u0000\u001a\u001b"+
		"\u0001\u0000\u0000\u0000\u001b\u001d\u0001\u0000\u0000\u0000\u001c\u001a"+
		"\u0001\u0000\u0000\u0000\u001d\u001e\u0005\u0005\u0000\u0000\u001e\u001f"+
		"\u0005\u0006\u0000\u0000\u001f\u0003\u0001\u0000\u0000\u0000 !\u0003\u0006"+
		"\u0003\u0000!#\u0003\b\u0004\u0000\"$\u0005\u0011\u0000\u0000#\"\u0001"+
		"\u0000\u0000\u0000#$\u0001\u0000\u0000\u0000$&\u0001\u0000\u0000\u0000"+
		"%\'\u0005\u0012\u0000\u0000&%\u0001\u0000\u0000\u0000&\'\u0001\u0000\u0000"+
		"\u0000\')\u0001\u0000\u0000\u0000(*\u0005\u0013\u0000\u0000)(\u0001\u0000"+
		"\u0000\u0000)*\u0001\u0000\u0000\u0000*\u0005\u0001\u0000\u0000\u0000"+
		"+,\u0005\u0014\u0000\u0000,\u0007\u0001\u0000\u0000\u0000-.\u0007\u0000"+
		"\u0000\u0000.\t\u0001\u0000\u0000\u0000/0\u0005\u0014\u0000\u00000\u000b"+
		"\u0001\u0000\u0000\u0000\u0005\u000f\u001a#&)";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}