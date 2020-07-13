grammar Calculator;

main: (stat ';')* EOF;

stat: print
    | assign;

print: 'print' '(' expr ')';

assign: IDENTIFIER '=' expr;

expr: expr op=('*'|'/'|'mod') expr  #ExprMultDivMod
    | expr op=('+'|'-') expr          #ExprPlusMinus
    | '(' expr ')'                    #ExprParentisis 
    | NUM                             #ExprNum
    | IDENTIFIER                      #ExprIdentifier
    ;

IDENTIFIER: [a-zA-Z][a-zA-Z0-9]*;
NUM: [0-9]+ ('.' [0-9]+)?;

WS: [ \t\r\n]+ -> skip;
COMMENT: '//' .*? '\n' -> skip;
ERROR: .;