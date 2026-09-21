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

NEG     : '--';
SEMI    : ';';
COMMA   : ',';
GREATER : '>';

PLUS    : '+';
MINUS   : '-';
TIMES   : '*';

// Define all the elements of the language for the various keywords that you need
RANDOM  : 'random';
POINT   : 'point';


// Keywords
ID: [a-zA-Z_]+; // Allow underscore in ID
NUMBER: [0-9]+('.'[0-9]+)?;


program : (execution)+;
execution : '->'? ID LCURL (statement)* RCURL ('->' ID)? ;
statement : action | reaction | execution ;
reaction : 'on' event '->' ID ;
event : 'obstacle' | 'low battery' | 'message' LSQUARE ID RSQUARE ; 
action : (acDock | acMove | acTurn | acAscend | acDescend)
( 'for' expression 's' | 'at speed' expression )? ;

acDock : 'return to base' ;
acMove : 'move' ('to' 'point' point | 'by' expression) ;        // n can be a expression which covers everything
acTurn : 'turn' ('right' | 'left')? 'by' expression ;
acAscend : 'ascend by' expression ;
acDescend: 'descend by' expression | 'descend to ground' ;

expression : NEG expression |
             expression TIMES expression |
             expression (PLUS | MINUS) expression |
             RANDOM range? |
             POINT point |
             NUMBER |
             LPAREN expression RPAREN;

point : LPAREN expression COMMA expression RPAREN;
range : LSQUARE expression COMMA expression RSQUARE;
