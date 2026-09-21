// Generated from c:/Users/noaha/OneDrive/Dokumenter/aeroscript/aeroscript/IN2031_2024/src/main/antlr/AeroScript.g4 by ANTLR 4.13.1

package no.uio.aeroscript.antlr;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class AeroScriptParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		WS=1, COMMENT=2, LINE_COMMENT=3, LCURL=4, RCURL=5, LSQUARE=6, RSQUARE=7, 
		LPAREN=8, RPAREN=9, SEMI=10, COMMA=11, NEG=12, PLUS=13, MINUS=14, TIMES=15, 
		RANDOM=16, POINT=17, NUMBER=18;
	public static final int
		RULE_program = 0, RULE_expression = 1, RULE_point = 2, RULE_range = 3;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "expression", "point", "range"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, "'{'", "'}'", "'['", "']'", "'('", "')'", "';'", 
			"','", "'--'", "'+'", "'-'", "'*'", "'random'", "'point'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "WS", "COMMENT", "LINE_COMMENT", "LCURL", "RCURL", "LSQUARE", "RSQUARE", 
			"LPAREN", "RPAREN", "SEMI", "COMMA", "NEG", "PLUS", "MINUS", "TIMES", 
			"RANDOM", "POINT", "NUMBER"
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
	public String getGrammarFileName() { return "AeroScript.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public AeroScriptParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(AeroScriptParser.EOF, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(11);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 463104L) != 0)) {
				{
				{
				setState(8);
				expression(0);
				}
				}
				setState(13);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(14);
			match(EOF);
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
	public static class ExpressionContext extends ParserRuleContext {
		public TerminalNode NEG() { return getToken(AeroScriptParser.NEG, 0); }
		public TerminalNode RANDOM() { return getToken(AeroScriptParser.RANDOM, 0); }
		public RangeContext range() {
			return getRuleContext(RangeContext.class,0);
		}
		public TerminalNode POINT() { return getToken(AeroScriptParser.POINT, 0); }
		public PointContext point() {
			return getRuleContext(PointContext.class,0);
		}
		public TerminalNode NUMBER() { return getToken(AeroScriptParser.NUMBER, 0); }
		public TerminalNode LPAREN() { return getToken(AeroScriptParser.LPAREN, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode RPAREN() { return getToken(AeroScriptParser.RPAREN, 0); }
		public TerminalNode PLUS() { return getToken(AeroScriptParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(AeroScriptParser.MINUS, 0); }
		public TerminalNode TIMES() { return getToken(AeroScriptParser.TIMES, 0); }
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 2;
		enterRecursionRule(_localctx, 2, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(29);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NEG:
				{
				setState(17);
				match(NEG);
				}
				break;
			case RANDOM:
				{
				setState(18);
				match(RANDOM);
				setState(20);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
				case 1:
					{
					setState(19);
					range();
					}
					break;
				}
				}
				break;
			case POINT:
				{
				setState(22);
				match(POINT);
				setState(23);
				point();
				}
				break;
			case NUMBER:
				{
				setState(24);
				match(NUMBER);
				}
				break;
			case LPAREN:
				{
				setState(25);
				match(LPAREN);
				setState(26);
				expression(0);
				setState(27);
				match(RPAREN);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(36);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ExpressionContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_expression);
					setState(31);
					if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
					setState(32);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 57344L) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(33);
					expression(7);
					}
					} 
				}
				setState(38);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PointContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(AeroScriptParser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(AeroScriptParser.COMMA, 0); }
		public TerminalNode RPAREN() { return getToken(AeroScriptParser.RPAREN, 0); }
		public PointContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_point; }
	}

	public final PointContext point() throws RecognitionException {
		PointContext _localctx = new PointContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_point);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(39);
			match(LPAREN);
			setState(40);
			expression(0);
			setState(41);
			match(COMMA);
			setState(42);
			match(RPAREN);
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
	public static class RangeContext extends ParserRuleContext {
		public ExpressionContext min;
		public ExpressionContext max;
		public TerminalNode LSQUARE() { return getToken(AeroScriptParser.LSQUARE, 0); }
		public TerminalNode COMMA() { return getToken(AeroScriptParser.COMMA, 0); }
		public TerminalNode RSQUARE() { return getToken(AeroScriptParser.RSQUARE, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public RangeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_range; }
	}

	public final RangeContext range() throws RecognitionException {
		RangeContext _localctx = new RangeContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_range);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(44);
			match(LSQUARE);
			setState(45);
			((RangeContext)_localctx).min = expression(0);
			setState(46);
			match(COMMA);
			setState(47);
			((RangeContext)_localctx).max = expression(0);
			setState(48);
			match(RSQUARE);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 1:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 6);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u00123\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0001\u0000\u0005\u0000\n\b"+
		"\u0000\n\u0000\f\u0000\r\t\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0003\u0001\u0015\b\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003"+
		"\u0001\u001e\b\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0005\u0001#"+
		"\b\u0001\n\u0001\f\u0001&\t\u0001\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0000\u0001\u0002\u0004\u0000\u0002"+
		"\u0004\u0006\u0000\u0001\u0001\u0000\r\u000f5\u0000\u000b\u0001\u0000"+
		"\u0000\u0000\u0002\u001d\u0001\u0000\u0000\u0000\u0004\'\u0001\u0000\u0000"+
		"\u0000\u0006,\u0001\u0000\u0000\u0000\b\n\u0003\u0002\u0001\u0000\t\b"+
		"\u0001\u0000\u0000\u0000\n\r\u0001\u0000\u0000\u0000\u000b\t\u0001\u0000"+
		"\u0000\u0000\u000b\f\u0001\u0000\u0000\u0000\f\u000e\u0001\u0000\u0000"+
		"\u0000\r\u000b\u0001\u0000\u0000\u0000\u000e\u000f\u0005\u0000\u0000\u0001"+
		"\u000f\u0001\u0001\u0000\u0000\u0000\u0010\u0011\u0006\u0001\uffff\uffff"+
		"\u0000\u0011\u001e\u0005\f\u0000\u0000\u0012\u0014\u0005\u0010\u0000\u0000"+
		"\u0013\u0015\u0003\u0006\u0003\u0000\u0014\u0013\u0001\u0000\u0000\u0000"+
		"\u0014\u0015\u0001\u0000\u0000\u0000\u0015\u001e\u0001\u0000\u0000\u0000"+
		"\u0016\u0017\u0005\u0011\u0000\u0000\u0017\u001e\u0003\u0004\u0002\u0000"+
		"\u0018\u001e\u0005\u0012\u0000\u0000\u0019\u001a\u0005\b\u0000\u0000\u001a"+
		"\u001b\u0003\u0002\u0001\u0000\u001b\u001c\u0005\t\u0000\u0000\u001c\u001e"+
		"\u0001\u0000\u0000\u0000\u001d\u0010\u0001\u0000\u0000\u0000\u001d\u0012"+
		"\u0001\u0000\u0000\u0000\u001d\u0016\u0001\u0000\u0000\u0000\u001d\u0018"+
		"\u0001\u0000\u0000\u0000\u001d\u0019\u0001\u0000\u0000\u0000\u001e$\u0001"+
		"\u0000\u0000\u0000\u001f \n\u0006\u0000\u0000 !\u0007\u0000\u0000\u0000"+
		"!#\u0003\u0002\u0001\u0007\"\u001f\u0001\u0000\u0000\u0000#&\u0001\u0000"+
		"\u0000\u0000$\"\u0001\u0000\u0000\u0000$%\u0001\u0000\u0000\u0000%\u0003"+
		"\u0001\u0000\u0000\u0000&$\u0001\u0000\u0000\u0000\'(\u0005\b\u0000\u0000"+
		"()\u0003\u0002\u0001\u0000)*\u0005\u000b\u0000\u0000*+\u0005\t\u0000\u0000"+
		"+\u0005\u0001\u0000\u0000\u0000,-\u0005\u0006\u0000\u0000-.\u0003\u0002"+
		"\u0001\u0000./\u0005\u000b\u0000\u0000/0\u0003\u0002\u0001\u000001\u0005"+
		"\u0007\u0000\u00001\u0007\u0001\u0000\u0000\u0000\u0004\u000b\u0014\u001d"+
		"$";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}