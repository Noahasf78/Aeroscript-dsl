// Generated from c:/Users/noaha/OneDrive/Dokumenter/aeroscript/aeroscript/IN2031_2024/src/main/antlr/AeroScript.g4 by ANTLR 4.13.1

package no.uio.aeroscript.antlr;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class AeroScriptLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		WS=1, COMMENT=2, LINE_COMMENT=3, LCURL=4, RCURL=5, LSQUARE=6, RSQUARE=7, 
		LPAREN=8, RPAREN=9, SEMI=10, COMMA=11, NEG=12, PLUS=13, MINUS=14, TIMES=15, 
		RANDOM=16, POINT=17, NUMBER=18;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"WS", "COMMENT", "LINE_COMMENT", "LCURL", "RCURL", "LSQUARE", "RSQUARE", 
			"LPAREN", "RPAREN", "SEMI", "COMMA", "NEG", "PLUS", "MINUS", "TIMES", 
			"RANDOM", "POINT", "NUMBER"
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


	public AeroScriptLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "AeroScript.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u0012{\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002"+
		"\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0001"+
		"\u0000\u0004\u0000\'\b\u0000\u000b\u0000\f\u0000(\u0001\u0000\u0001\u0000"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0005\u00011\b\u0001"+
		"\n\u0001\f\u00014\t\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0005\u0002"+
		"?\b\u0002\n\u0002\f\u0002B\t\u0002\u0001\u0002\u0001\u0002\u0001\u0003"+
		"\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0006"+
		"\u0001\u0006\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\t\u0001\t\u0001"+
		"\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\r"+
		"\u0001\r\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0011\u0003\u0011m\b"+
		"\u0011\u0001\u0011\u0004\u0011p\b\u0011\u000b\u0011\f\u0011q\u0001\u0011"+
		"\u0001\u0011\u0004\u0011v\b\u0011\u000b\u0011\f\u0011w\u0003\u0011z\b"+
		"\u0011\u00012\u0000\u0012\u0001\u0001\u0003\u0002\u0005\u0003\u0007\u0004"+
		"\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017"+
		"\f\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011#\u0012\u0001\u0000"+
		"\u0003\u0003\u0000\t\n\f\r  \u0002\u0000\n\n\r\r\u0001\u000009\u0081\u0000"+
		"\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000"+
		"\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000"+
		"\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r"+
		"\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011"+
		"\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015"+
		"\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000\u0000\u0000\u0000\u0019"+
		"\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000\u0000\u0000\u0000\u001d"+
		"\u0001\u0000\u0000\u0000\u0000\u001f\u0001\u0000\u0000\u0000\u0000!\u0001"+
		"\u0000\u0000\u0000\u0000#\u0001\u0000\u0000\u0000\u0001&\u0001\u0000\u0000"+
		"\u0000\u0003,\u0001\u0000\u0000\u0000\u0005:\u0001\u0000\u0000\u0000\u0007"+
		"E\u0001\u0000\u0000\u0000\tG\u0001\u0000\u0000\u0000\u000bI\u0001\u0000"+
		"\u0000\u0000\rK\u0001\u0000\u0000\u0000\u000fM\u0001\u0000\u0000\u0000"+
		"\u0011O\u0001\u0000\u0000\u0000\u0013Q\u0001\u0000\u0000\u0000\u0015S"+
		"\u0001\u0000\u0000\u0000\u0017U\u0001\u0000\u0000\u0000\u0019X\u0001\u0000"+
		"\u0000\u0000\u001bZ\u0001\u0000\u0000\u0000\u001d\\\u0001\u0000\u0000"+
		"\u0000\u001f^\u0001\u0000\u0000\u0000!e\u0001\u0000\u0000\u0000#l\u0001"+
		"\u0000\u0000\u0000%\'\u0007\u0000\u0000\u0000&%\u0001\u0000\u0000\u0000"+
		"\'(\u0001\u0000\u0000\u0000(&\u0001\u0000\u0000\u0000()\u0001\u0000\u0000"+
		"\u0000)*\u0001\u0000\u0000\u0000*+\u0006\u0000\u0000\u0000+\u0002\u0001"+
		"\u0000\u0000\u0000,-\u0005/\u0000\u0000-.\u0005*\u0000\u0000.2\u0001\u0000"+
		"\u0000\u0000/1\t\u0000\u0000\u00000/\u0001\u0000\u0000\u000014\u0001\u0000"+
		"\u0000\u000023\u0001\u0000\u0000\u000020\u0001\u0000\u0000\u000035\u0001"+
		"\u0000\u0000\u000042\u0001\u0000\u0000\u000056\u0005*\u0000\u000067\u0005"+
		"/\u0000\u000078\u0001\u0000\u0000\u000089\u0006\u0001\u0000\u00009\u0004"+
		"\u0001\u0000\u0000\u0000:;\u0005/\u0000\u0000;<\u0005/\u0000\u0000<@\u0001"+
		"\u0000\u0000\u0000=?\b\u0001\u0000\u0000>=\u0001\u0000\u0000\u0000?B\u0001"+
		"\u0000\u0000\u0000@>\u0001\u0000\u0000\u0000@A\u0001\u0000\u0000\u0000"+
		"AC\u0001\u0000\u0000\u0000B@\u0001\u0000\u0000\u0000CD\u0006\u0002\u0000"+
		"\u0000D\u0006\u0001\u0000\u0000\u0000EF\u0005{\u0000\u0000F\b\u0001\u0000"+
		"\u0000\u0000GH\u0005}\u0000\u0000H\n\u0001\u0000\u0000\u0000IJ\u0005["+
		"\u0000\u0000J\f\u0001\u0000\u0000\u0000KL\u0005]\u0000\u0000L\u000e\u0001"+
		"\u0000\u0000\u0000MN\u0005(\u0000\u0000N\u0010\u0001\u0000\u0000\u0000"+
		"OP\u0005)\u0000\u0000P\u0012\u0001\u0000\u0000\u0000QR\u0005;\u0000\u0000"+
		"R\u0014\u0001\u0000\u0000\u0000ST\u0005,\u0000\u0000T\u0016\u0001\u0000"+
		"\u0000\u0000UV\u0005-\u0000\u0000VW\u0005-\u0000\u0000W\u0018\u0001\u0000"+
		"\u0000\u0000XY\u0005+\u0000\u0000Y\u001a\u0001\u0000\u0000\u0000Z[\u0005"+
		"-\u0000\u0000[\u001c\u0001\u0000\u0000\u0000\\]\u0005*\u0000\u0000]\u001e"+
		"\u0001\u0000\u0000\u0000^_\u0005r\u0000\u0000_`\u0005a\u0000\u0000`a\u0005"+
		"n\u0000\u0000ab\u0005d\u0000\u0000bc\u0005o\u0000\u0000cd\u0005m\u0000"+
		"\u0000d \u0001\u0000\u0000\u0000ef\u0005p\u0000\u0000fg\u0005o\u0000\u0000"+
		"gh\u0005i\u0000\u0000hi\u0005n\u0000\u0000ij\u0005t\u0000\u0000j\"\u0001"+
		"\u0000\u0000\u0000km\u0005-\u0000\u0000lk\u0001\u0000\u0000\u0000lm\u0001"+
		"\u0000\u0000\u0000mo\u0001\u0000\u0000\u0000np\u0007\u0002\u0000\u0000"+
		"on\u0001\u0000\u0000\u0000pq\u0001\u0000\u0000\u0000qo\u0001\u0000\u0000"+
		"\u0000qr\u0001\u0000\u0000\u0000ry\u0001\u0000\u0000\u0000su\u0005.\u0000"+
		"\u0000tv\u0007\u0002\u0000\u0000ut\u0001\u0000\u0000\u0000vw\u0001\u0000"+
		"\u0000\u0000wu\u0001\u0000\u0000\u0000wx\u0001\u0000\u0000\u0000xz\u0001"+
		"\u0000\u0000\u0000ys\u0001\u0000\u0000\u0000yz\u0001\u0000\u0000\u0000"+
		"z$\u0001\u0000\u0000\u0000\b\u0000(2@lqwy\u0001\u0000\u0001\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}