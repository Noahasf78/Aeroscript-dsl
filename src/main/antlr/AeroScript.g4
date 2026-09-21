grammar AeroScript;

@header {
package no.uio.aeroscript.antlr;
}

// Whitespace and comments added
WS           : [ \t\r\n\u000C]+ -> channel(HIDDEN);
COMMENT      : '/*' .*? '*/' -> channel(HIDDEN) ;
LINE_COMMENT : '//' ~[\r\n]* -> channel(HIDDEN) ;

LCURL   : '{';
RCURL   : '}';
LSQUARE : '[';
RSQUARE : ']';
LPAREN  : '(';
RPAREN  : ')';

SEMI    : ';';
COMMA   : ',';

NEG     : '--';
PLUS    : '+';
MINUS   : '-';
TIMES   : '*';
RANDOM  : 'random';
POINT   : 'point';

// Keywords
NUMBER: '-'?[0-9]+('.'[0-9]+)?;

// Entry point
program : (expression)* EOF;

expression 
    : expression (PLUS | MINUS | TIMES) expression 
    |        NEG 
    |        RANDOM range? 
    |        POINT point 
    |        NUMBER 
    |        LPAREN expression RPAREN
    ;       

point 
    : LPAREN expression COMMA RPAREN
    ;
range 
    : LSQUARE min=expression COMMA max=expression RSQUARE
    ;