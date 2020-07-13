grammar StrManip ;

main: stat* EOF;

stat: print
    | assign
    ;

print: 'print' expr;

assign: Identifier '=>' expr;

expr: '(' expr '/' expr '/' expr ')'    #ExprSubstitute
    | expr '+' expr     #ExprConcat
    | 'input' '(' expr ')'    #ExprInput
    | Identifier    #ExprID
    | String        #ExprString
    ;

Identifier: [a-zA-Z] [a-zA-Z0-9]*;
String: '"' .*? '"';

WS: [ \t\r\n]+ -> skip;
COMMENT: '#' .*? '\n' -> skip;
ERROR: .;