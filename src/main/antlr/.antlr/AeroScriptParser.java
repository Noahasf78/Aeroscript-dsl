// Generated from c:/Users/noaha/OneDrive/Dokumenter/IN2031/oblig3/aeroscript/aeroscript/src/main/antlr/AeroScript.g4 by ANTLR 4.13.1

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
		T__0=1, WS=2, COMMENT=3, LINE_COMMENT=4, LCURL=5, RCURL=6, LSQUARE=7, 
		RSQUARE=8, LPAREN=9, RPAREN=10, NEG=11, SEMI=12, COMMA=13, GREATER=14, 
		PLUS=15, MINUS=16, TIMES=17, ARROW=18, ON=19, EVENT=20, ASCEND=21, BY=22, 
		MOVE=23, TO=24, POINT=25, TURN=26, RIGHT=27, LEFT=28, AT=29, SPEED=30, 
		RETURN=31, BASE=32, DESCEND=33, GROUND=34, RANDOM=35, OBSTACLE=36, BATTERY=37, 
		MESSAGE=38, FOR=39, ID=40, NUMBER=41;
	public static final int
		RULE_program = 0, RULE_execution = 1, RULE_statement = 2, RULE_reaction = 3, 
		RULE_event = 4, RULE_action = 5, RULE_acAscend = 6, RULE_acMove = 7, RULE_acTurn = 8, 
		RULE_acDock = 9, RULE_acDescend = 10, RULE_expression = 11, RULE_point = 12, 
		RULE_range = 13;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "execution", "statement", "reaction", "event", "action", "acAscend", 
			"acMove", "acTurn", "acDock", "acDescend", "expression", "point", "range"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'s'", null, null, null, "'{'", "'}'", "'['", "']'", "'('", "')'", 
			"'--'", "';'", "','", "'>'", "'+'", "'-'", "'*'", "'->'", "'on'", "'event'", 
			"'ascend'", "'by'", "'move'", "'to'", "'point'", "'turn'", "'right'", 
			"'left'", "'at'", "'speed'", "'return'", "'base'", "'descend'", "'ground'", 
			"'random'", "'obstacle'", "'low battery'", "'message'", "'for'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, "WS", "COMMENT", "LINE_COMMENT", "LCURL", "RCURL", "LSQUARE", 
			"RSQUARE", "LPAREN", "RPAREN", "NEG", "SEMI", "COMMA", "GREATER", "PLUS", 
			"MINUS", "TIMES", "ARROW", "ON", "EVENT", "ASCEND", "BY", "MOVE", "TO", 
			"POINT", "TURN", "RIGHT", "LEFT", "AT", "SPEED", "RETURN", "BASE", "DESCEND", 
			"GROUND", "RANDOM", "OBSTACLE", "BATTERY", "MESSAGE", "FOR", "ID", "NUMBER"
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
		public List<ExecutionContext> execution() {
			return getRuleContexts(ExecutionContext.class);
		}
		public ExecutionContext execution(int i) {
			return getRuleContext(ExecutionContext.class,i);
		}
		public TerminalNode EOF() { return getToken(AeroScriptParser.EOF, 0); }
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AeroScriptListener ) ((AeroScriptListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AeroScriptListener ) ((AeroScriptListener)listener).exitProgram(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(28);
			execution();
			setState(32);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ARROW || _la==ID) {
				{
				{
				setState(29);
				execution();
				}
				}
				setState(34);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(35);
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
	public static class ExecutionContext extends ParserRuleContext {
		public Token exec;
		public Token define;
		public Token declare;
		public TerminalNode LCURL() { return getToken(AeroScriptParser.LCURL, 0); }
		public TerminalNode RCURL() { return getToken(AeroScriptParser.RCURL, 0); }
		public List<TerminalNode> ID() { return getTokens(AeroScriptParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(AeroScriptParser.ID, i);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public List<TerminalNode> ARROW() { return getTokens(AeroScriptParser.ARROW); }
		public TerminalNode ARROW(int i) {
			return getToken(AeroScriptParser.ARROW, i);
		}
		public ExecutionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_execution; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AeroScriptListener ) ((AeroScriptListener)listener).enterExecution(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AeroScriptListener ) ((AeroScriptListener)listener).exitExecution(this);
		}
	}

	public final ExecutionContext execution() throws RecognitionException {
		ExecutionContext _localctx = new ExecutionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_execution);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(38);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ARROW) {
				{
				setState(37);
				((ExecutionContext)_localctx).exec = match(ARROW);
				}
			}

			setState(40);
			((ExecutionContext)_localctx).define = match(ID);
			setState(41);
			match(LCURL);
			setState(45);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1110327427072L) != 0)) {
				{
				{
				setState(42);
				statement();
				}
				}
				setState(47);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(48);
			match(RCURL);
			setState(51);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				{
				setState(49);
				match(ARROW);
				setState(50);
				((ExecutionContext)_localctx).declare = match(ID);
				}
				break;
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
	public static class StatementContext extends ParserRuleContext {
		public ActionContext action() {
			return getRuleContext(ActionContext.class,0);
		}
		public ReactionContext reaction() {
			return getRuleContext(ReactionContext.class,0);
		}
		public ExecutionContext execution() {
			return getRuleContext(ExecutionContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AeroScriptListener ) ((AeroScriptListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AeroScriptListener ) ((AeroScriptListener)listener).exitStatement(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_statement);
		try {
			setState(56);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ASCEND:
			case MOVE:
			case TURN:
			case RETURN:
			case DESCEND:
				enterOuterAlt(_localctx, 1);
				{
				setState(53);
				action();
				}
				break;
			case ON:
				enterOuterAlt(_localctx, 2);
				{
				setState(54);
				reaction();
				}
				break;
			case ARROW:
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(55);
				execution();
				}
				break;
			default:
				throw new NoViableAltException(this);
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
	public static class ReactionContext extends ParserRuleContext {
		public Token func;
		public TerminalNode ON() { return getToken(AeroScriptParser.ON, 0); }
		public EventContext event() {
			return getRuleContext(EventContext.class,0);
		}
		public TerminalNode ARROW() { return getToken(AeroScriptParser.ARROW, 0); }
		public TerminalNode ID() { return getToken(AeroScriptParser.ID, 0); }
		public ReactionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_reaction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AeroScriptListener ) ((AeroScriptListener)listener).enterReaction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AeroScriptListener ) ((AeroScriptListener)listener).exitReaction(this);
		}
	}

	public final ReactionContext reaction() throws RecognitionException {
		ReactionContext _localctx = new ReactionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_reaction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
			match(ON);
			setState(59);
			event();
			setState(60);
			match(ARROW);
			setState(61);
			((ReactionContext)_localctx).func = match(ID);
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
	public static class EventContext extends ParserRuleContext {
		public Token msg;
		public TerminalNode OBSTACLE() { return getToken(AeroScriptParser.OBSTACLE, 0); }
		public TerminalNode BATTERY() { return getToken(AeroScriptParser.BATTERY, 0); }
		public TerminalNode MESSAGE() { return getToken(AeroScriptParser.MESSAGE, 0); }
		public TerminalNode LSQUARE() { return getToken(AeroScriptParser.LSQUARE, 0); }
		public TerminalNode RSQUARE() { return getToken(AeroScriptParser.RSQUARE, 0); }
		public TerminalNode ID() { return getToken(AeroScriptParser.ID, 0); }
		public EventContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_event; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AeroScriptListener ) ((AeroScriptListener)listener).enterEvent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AeroScriptListener ) ((AeroScriptListener)listener).exitEvent(this);
		}
	}

	public final EventContext event() throws RecognitionException {
		EventContext _localctx = new EventContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_event);
		try {
			setState(69);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OBSTACLE:
				enterOuterAlt(_localctx, 1);
				{
				setState(63);
				match(OBSTACLE);
				}
				break;
			case BATTERY:
				enterOuterAlt(_localctx, 2);
				{
				setState(64);
				match(BATTERY);
				}
				break;
			case MESSAGE:
				enterOuterAlt(_localctx, 3);
				{
				setState(65);
				match(MESSAGE);
				setState(66);
				match(LSQUARE);
				setState(67);
				((EventContext)_localctx).msg = match(ID);
				setState(68);
				match(RSQUARE);
				}
				break;
			default:
				throw new NoViableAltException(this);
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
	public static class ActionContext extends ParserRuleContext {
		public AcAscendContext acAscend() {
			return getRuleContext(AcAscendContext.class,0);
		}
		public AcMoveContext acMove() {
			return getRuleContext(AcMoveContext.class,0);
		}
		public AcTurnContext acTurn() {
			return getRuleContext(AcTurnContext.class,0);
		}
		public AcDockContext acDock() {
			return getRuleContext(AcDockContext.class,0);
		}
		public AcDescendContext acDescend() {
			return getRuleContext(AcDescendContext.class,0);
		}
		public TerminalNode FOR() { return getToken(AeroScriptParser.FOR, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode AT() { return getToken(AeroScriptParser.AT, 0); }
		public TerminalNode SPEED() { return getToken(AeroScriptParser.SPEED, 0); }
		public ActionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_action; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AeroScriptListener ) ((AeroScriptListener)listener).enterAction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AeroScriptListener ) ((AeroScriptListener)listener).exitAction(this);
		}
	}

	public final ActionContext action() throws RecognitionException {
		ActionContext _localctx = new ActionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_action);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ASCEND:
				{
				setState(71);
				acAscend();
				}
				break;
			case MOVE:
				{
				setState(72);
				acMove();
				}
				break;
			case TURN:
				{
				setState(73);
				acTurn();
				}
				break;
			case RETURN:
				{
				setState(74);
				acDock();
				}
				break;
			case DESCEND:
				{
				setState(75);
				acDescend();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(85);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FOR:
				{
				setState(78);
				match(FOR);
				setState(79);
				expression(0);
				setState(80);
				match(T__0);
				}
				break;
			case AT:
				{
				setState(82);
				match(AT);
				setState(83);
				match(SPEED);
				setState(84);
				expression(0);
				}
				break;
			case RCURL:
			case ARROW:
			case ON:
			case ASCEND:
			case MOVE:
			case TURN:
			case RETURN:
			case DESCEND:
			case ID:
				break;
			default:
				break;
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
	public static class AcAscendContext extends ParserRuleContext {
		public TerminalNode ASCEND() { return getToken(AeroScriptParser.ASCEND, 0); }
		public TerminalNode BY() { return getToken(AeroScriptParser.BY, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public AcAscendContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_acAscend; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AeroScriptListener ) ((AeroScriptListener)listener).enterAcAscend(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AeroScriptListener ) ((AeroScriptListener)listener).exitAcAscend(this);
		}
	}

	public final AcAscendContext acAscend() throws RecognitionException {
		AcAscendContext _localctx = new AcAscendContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_acAscend);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(87);
			match(ASCEND);
			setState(88);
			match(BY);
			setState(89);
			expression(0);
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
	public static class AcMoveContext extends ParserRuleContext {
		public TerminalNode MOVE() { return getToken(AeroScriptParser.MOVE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode TO() { return getToken(AeroScriptParser.TO, 0); }
		public TerminalNode BY() { return getToken(AeroScriptParser.BY, 0); }
		public AcMoveContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_acMove; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AeroScriptListener ) ((AeroScriptListener)listener).enterAcMove(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AeroScriptListener ) ((AeroScriptListener)listener).exitAcMove(this);
		}
	}

	public final AcMoveContext acMove() throws RecognitionException {
		AcMoveContext _localctx = new AcMoveContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_acMove);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91);
			match(MOVE);
			setState(92);
			_la = _input.LA(1);
			if ( !(_la==BY || _la==TO) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(93);
			expression(0);
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
	public static class AcTurnContext extends ParserRuleContext {
		public TerminalNode TURN() { return getToken(AeroScriptParser.TURN, 0); }
		public TerminalNode BY() { return getToken(AeroScriptParser.BY, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RIGHT() { return getToken(AeroScriptParser.RIGHT, 0); }
		public TerminalNode LEFT() { return getToken(AeroScriptParser.LEFT, 0); }
		public AcTurnContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_acTurn; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AeroScriptListener ) ((AeroScriptListener)listener).enterAcTurn(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AeroScriptListener ) ((AeroScriptListener)listener).exitAcTurn(this);
		}
	}

	public final AcTurnContext acTurn() throws RecognitionException {
		AcTurnContext _localctx = new AcTurnContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_acTurn);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(95);
			match(TURN);
			setState(97);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==RIGHT || _la==LEFT) {
				{
				setState(96);
				_la = _input.LA(1);
				if ( !(_la==RIGHT || _la==LEFT) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(99);
			match(BY);
			setState(100);
			expression(0);
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
	public static class AcDockContext extends ParserRuleContext {
		public TerminalNode RETURN() { return getToken(AeroScriptParser.RETURN, 0); }
		public TerminalNode TO() { return getToken(AeroScriptParser.TO, 0); }
		public TerminalNode BASE() { return getToken(AeroScriptParser.BASE, 0); }
		public AcDockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_acDock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AeroScriptListener ) ((AeroScriptListener)listener).enterAcDock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AeroScriptListener ) ((AeroScriptListener)listener).exitAcDock(this);
		}
	}

	public final AcDockContext acDock() throws RecognitionException {
		AcDockContext _localctx = new AcDockContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_acDock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
			match(RETURN);
			setState(103);
			match(TO);
			setState(104);
			match(BASE);
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
	public static class AcDescendContext extends ParserRuleContext {
		public TerminalNode DESCEND() { return getToken(AeroScriptParser.DESCEND, 0); }
		public TerminalNode BY() { return getToken(AeroScriptParser.BY, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode TO() { return getToken(AeroScriptParser.TO, 0); }
		public TerminalNode GROUND() { return getToken(AeroScriptParser.GROUND, 0); }
		public AcDescendContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_acDescend; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AeroScriptListener ) ((AeroScriptListener)listener).enterAcDescend(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AeroScriptListener ) ((AeroScriptListener)listener).exitAcDescend(this);
		}
	}

	public final AcDescendContext acDescend() throws RecognitionException {
		AcDescendContext _localctx = new AcDescendContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_acDescend);
		try {
			setState(112);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(106);
				match(DESCEND);
				setState(107);
				match(BY);
				setState(108);
				expression(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(109);
				match(DESCEND);
				setState(110);
				match(TO);
				setState(111);
				match(GROUND);
				}
				break;
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
		public ExpressionContext rrange;
		public ExpressionContext ppoint;
		public TerminalNode NEG() { return getToken(AeroScriptParser.NEG, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode RANDOM() { return getToken(AeroScriptParser.RANDOM, 0); }
		public TerminalNode POINT() { return getToken(AeroScriptParser.POINT, 0); }
		public PointContext point() {
			return getRuleContext(PointContext.class,0);
		}
		public RangeContext range() {
			return getRuleContext(RangeContext.class,0);
		}
		public TerminalNode NUMBER() { return getToken(AeroScriptParser.NUMBER, 0); }
		public TerminalNode LPAREN() { return getToken(AeroScriptParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(AeroScriptParser.RPAREN, 0); }
		public TerminalNode TIMES() { return getToken(AeroScriptParser.TIMES, 0); }
		public TerminalNode PLUS() { return getToken(AeroScriptParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(AeroScriptParser.MINUS, 0); }
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AeroScriptListener ) ((AeroScriptListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AeroScriptListener ) ((AeroScriptListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 22;
		enterRecursionRule(_localctx, 22, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(130);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				{
				setState(115);
				match(NEG);
				setState(116);
				expression(9);
				}
				break;
			case 2:
				{
				setState(117);
				match(RANDOM);
				setState(119);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
				case 1:
					{
					setState(118);
					((ExpressionContext)_localctx).rrange = expression(0);
					}
					break;
				}
				}
				break;
			case 3:
				{
				setState(121);
				match(POINT);
				setState(122);
				((ExpressionContext)_localctx).ppoint = expression(5);
				}
				break;
			case 4:
				{
				setState(123);
				point();
				}
				break;
			case 5:
				{
				setState(124);
				range();
				}
				break;
			case 6:
				{
				setState(125);
				match(NUMBER);
				}
				break;
			case 7:
				{
				setState(126);
				match(LPAREN);
				setState(127);
				expression(0);
				setState(128);
				match(RPAREN);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(140);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(138);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(132);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(133);
						match(TIMES);
						setState(134);
						expression(9);
						}
						break;
					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(135);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(136);
						_la = _input.LA(1);
						if ( !(_la==PLUS || _la==MINUS) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(137);
						expression(8);
						}
						break;
					}
					} 
				}
				setState(142);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
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
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode COMMA() { return getToken(AeroScriptParser.COMMA, 0); }
		public TerminalNode RPAREN() { return getToken(AeroScriptParser.RPAREN, 0); }
		public PointContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_point; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AeroScriptListener ) ((AeroScriptListener)listener).enterPoint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AeroScriptListener ) ((AeroScriptListener)listener).exitPoint(this);
		}
	}

	public final PointContext point() throws RecognitionException {
		PointContext _localctx = new PointContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_point);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(143);
			match(LPAREN);
			setState(144);
			expression(0);
			setState(145);
			match(COMMA);
			setState(146);
			expression(0);
			setState(147);
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
		public TerminalNode LSQUARE() { return getToken(AeroScriptParser.LSQUARE, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode COMMA() { return getToken(AeroScriptParser.COMMA, 0); }
		public TerminalNode RSQUARE() { return getToken(AeroScriptParser.RSQUARE, 0); }
		public RangeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_range; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AeroScriptListener ) ((AeroScriptListener)listener).enterRange(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AeroScriptListener ) ((AeroScriptListener)listener).exitRange(this);
		}
	}

	public final RangeContext range() throws RecognitionException {
		RangeContext _localctx = new RangeContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_range);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(149);
			match(LSQUARE);
			setState(150);
			expression(0);
			setState(151);
			match(COMMA);
			setState(152);
			expression(0);
			setState(153);
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
		case 11:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 8);
		case 1:
			return precpred(_ctx, 7);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001)\u009c\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0001\u0000\u0001\u0000\u0005\u0000\u001f\b"+
		"\u0000\n\u0000\f\u0000\"\t\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0003"+
		"\u0001\'\b\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0005\u0001,\b\u0001"+
		"\n\u0001\f\u0001/\t\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0001"+
		"4\b\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u00029\b\u0002\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004F\b"+
		"\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0003"+
		"\u0005M\b\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0003\u0005V\b\u0005\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\b\u0001\b\u0003\bb\b\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0003"+
		"\nq\b\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0003"+
		"\u000bx\b\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0003\u000b\u0083"+
		"\b\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0005\u000b\u008b\b\u000b\n\u000b\f\u000b\u008e\t\u000b\u0001\f"+
		"\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0000\u0001\u0016\u000e\u0000\u0002\u0004\u0006"+
		"\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u0000\u0003\u0002\u0000"+
		"\u0016\u0016\u0018\u0018\u0001\u0000\u001b\u001c\u0001\u0000\u000f\u0010"+
		"\u00a6\u0000\u001c\u0001\u0000\u0000\u0000\u0002&\u0001\u0000\u0000\u0000"+
		"\u00048\u0001\u0000\u0000\u0000\u0006:\u0001\u0000\u0000\u0000\bE\u0001"+
		"\u0000\u0000\u0000\nL\u0001\u0000\u0000\u0000\fW\u0001\u0000\u0000\u0000"+
		"\u000e[\u0001\u0000\u0000\u0000\u0010_\u0001\u0000\u0000\u0000\u0012f"+
		"\u0001\u0000\u0000\u0000\u0014p\u0001\u0000\u0000\u0000\u0016\u0082\u0001"+
		"\u0000\u0000\u0000\u0018\u008f\u0001\u0000\u0000\u0000\u001a\u0095\u0001"+
		"\u0000\u0000\u0000\u001c \u0003\u0002\u0001\u0000\u001d\u001f\u0003\u0002"+
		"\u0001\u0000\u001e\u001d\u0001\u0000\u0000\u0000\u001f\"\u0001\u0000\u0000"+
		"\u0000 \u001e\u0001\u0000\u0000\u0000 !\u0001\u0000\u0000\u0000!#\u0001"+
		"\u0000\u0000\u0000\" \u0001\u0000\u0000\u0000#$\u0005\u0000\u0000\u0001"+
		"$\u0001\u0001\u0000\u0000\u0000%\'\u0005\u0012\u0000\u0000&%\u0001\u0000"+
		"\u0000\u0000&\'\u0001\u0000\u0000\u0000\'(\u0001\u0000\u0000\u0000()\u0005"+
		"(\u0000\u0000)-\u0005\u0005\u0000\u0000*,\u0003\u0004\u0002\u0000+*\u0001"+
		"\u0000\u0000\u0000,/\u0001\u0000\u0000\u0000-+\u0001\u0000\u0000\u0000"+
		"-.\u0001\u0000\u0000\u0000.0\u0001\u0000\u0000\u0000/-\u0001\u0000\u0000"+
		"\u000003\u0005\u0006\u0000\u000012\u0005\u0012\u0000\u000024\u0005(\u0000"+
		"\u000031\u0001\u0000\u0000\u000034\u0001\u0000\u0000\u00004\u0003\u0001"+
		"\u0000\u0000\u000059\u0003\n\u0005\u000069\u0003\u0006\u0003\u000079\u0003"+
		"\u0002\u0001\u000085\u0001\u0000\u0000\u000086\u0001\u0000\u0000\u0000"+
		"87\u0001\u0000\u0000\u00009\u0005\u0001\u0000\u0000\u0000:;\u0005\u0013"+
		"\u0000\u0000;<\u0003\b\u0004\u0000<=\u0005\u0012\u0000\u0000=>\u0005("+
		"\u0000\u0000>\u0007\u0001\u0000\u0000\u0000?F\u0005$\u0000\u0000@F\u0005"+
		"%\u0000\u0000AB\u0005&\u0000\u0000BC\u0005\u0007\u0000\u0000CD\u0005("+
		"\u0000\u0000DF\u0005\b\u0000\u0000E?\u0001\u0000\u0000\u0000E@\u0001\u0000"+
		"\u0000\u0000EA\u0001\u0000\u0000\u0000F\t\u0001\u0000\u0000\u0000GM\u0003"+
		"\f\u0006\u0000HM\u0003\u000e\u0007\u0000IM\u0003\u0010\b\u0000JM\u0003"+
		"\u0012\t\u0000KM\u0003\u0014\n\u0000LG\u0001\u0000\u0000\u0000LH\u0001"+
		"\u0000\u0000\u0000LI\u0001\u0000\u0000\u0000LJ\u0001\u0000\u0000\u0000"+
		"LK\u0001\u0000\u0000\u0000MU\u0001\u0000\u0000\u0000NO\u0005\'\u0000\u0000"+
		"OP\u0003\u0016\u000b\u0000PQ\u0005\u0001\u0000\u0000QV\u0001\u0000\u0000"+
		"\u0000RS\u0005\u001d\u0000\u0000ST\u0005\u001e\u0000\u0000TV\u0003\u0016"+
		"\u000b\u0000UN\u0001\u0000\u0000\u0000UR\u0001\u0000\u0000\u0000UV\u0001"+
		"\u0000\u0000\u0000V\u000b\u0001\u0000\u0000\u0000WX\u0005\u0015\u0000"+
		"\u0000XY\u0005\u0016\u0000\u0000YZ\u0003\u0016\u000b\u0000Z\r\u0001\u0000"+
		"\u0000\u0000[\\\u0005\u0017\u0000\u0000\\]\u0007\u0000\u0000\u0000]^\u0003"+
		"\u0016\u000b\u0000^\u000f\u0001\u0000\u0000\u0000_a\u0005\u001a\u0000"+
		"\u0000`b\u0007\u0001\u0000\u0000a`\u0001\u0000\u0000\u0000ab\u0001\u0000"+
		"\u0000\u0000bc\u0001\u0000\u0000\u0000cd\u0005\u0016\u0000\u0000de\u0003"+
		"\u0016\u000b\u0000e\u0011\u0001\u0000\u0000\u0000fg\u0005\u001f\u0000"+
		"\u0000gh\u0005\u0018\u0000\u0000hi\u0005 \u0000\u0000i\u0013\u0001\u0000"+
		"\u0000\u0000jk\u0005!\u0000\u0000kl\u0005\u0016\u0000\u0000lq\u0003\u0016"+
		"\u000b\u0000mn\u0005!\u0000\u0000no\u0005\u0018\u0000\u0000oq\u0005\""+
		"\u0000\u0000pj\u0001\u0000\u0000\u0000pm\u0001\u0000\u0000\u0000q\u0015"+
		"\u0001\u0000\u0000\u0000rs\u0006\u000b\uffff\uffff\u0000st\u0005\u000b"+
		"\u0000\u0000t\u0083\u0003\u0016\u000b\tuw\u0005#\u0000\u0000vx\u0003\u0016"+
		"\u000b\u0000wv\u0001\u0000\u0000\u0000wx\u0001\u0000\u0000\u0000x\u0083"+
		"\u0001\u0000\u0000\u0000yz\u0005\u0019\u0000\u0000z\u0083\u0003\u0016"+
		"\u000b\u0005{\u0083\u0003\u0018\f\u0000|\u0083\u0003\u001a\r\u0000}\u0083"+
		"\u0005)\u0000\u0000~\u007f\u0005\t\u0000\u0000\u007f\u0080\u0003\u0016"+
		"\u000b\u0000\u0080\u0081\u0005\n\u0000\u0000\u0081\u0083\u0001\u0000\u0000"+
		"\u0000\u0082r\u0001\u0000\u0000\u0000\u0082u\u0001\u0000\u0000\u0000\u0082"+
		"y\u0001\u0000\u0000\u0000\u0082{\u0001\u0000\u0000\u0000\u0082|\u0001"+
		"\u0000\u0000\u0000\u0082}\u0001\u0000\u0000\u0000\u0082~\u0001\u0000\u0000"+
		"\u0000\u0083\u008c\u0001\u0000\u0000\u0000\u0084\u0085\n\b\u0000\u0000"+
		"\u0085\u0086\u0005\u0011\u0000\u0000\u0086\u008b\u0003\u0016\u000b\t\u0087"+
		"\u0088\n\u0007\u0000\u0000\u0088\u0089\u0007\u0002\u0000\u0000\u0089\u008b"+
		"\u0003\u0016\u000b\b\u008a\u0084\u0001\u0000\u0000\u0000\u008a\u0087\u0001"+
		"\u0000\u0000\u0000\u008b\u008e\u0001\u0000\u0000\u0000\u008c\u008a\u0001"+
		"\u0000\u0000\u0000\u008c\u008d\u0001\u0000\u0000\u0000\u008d\u0017\u0001"+
		"\u0000\u0000\u0000\u008e\u008c\u0001\u0000\u0000\u0000\u008f\u0090\u0005"+
		"\t\u0000\u0000\u0090\u0091\u0003\u0016\u000b\u0000\u0091\u0092\u0005\r"+
		"\u0000\u0000\u0092\u0093\u0003\u0016\u000b\u0000\u0093\u0094\u0005\n\u0000"+
		"\u0000\u0094\u0019\u0001\u0000\u0000\u0000\u0095\u0096\u0005\u0007\u0000"+
		"\u0000\u0096\u0097\u0003\u0016\u000b\u0000\u0097\u0098\u0005\r\u0000\u0000"+
		"\u0098\u0099\u0003\u0016\u000b\u0000\u0099\u009a\u0005\b\u0000\u0000\u009a"+
		"\u001b\u0001\u0000\u0000\u0000\u000e &-38ELUapw\u0082\u008a\u008c";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}