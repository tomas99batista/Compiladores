grammar BigIntCalc;

main: (stat ';')* EOF;

stat: print
    | assign
    ;

print: 'show' expr;

assign: expr '->' Identifier;

expr: signal=('+'|'-') expr            #ExprSignal
    | expr op=('*'|'div'|'mod') expr    #ExprMultDivMod
    | expr op=('+'|'-') expr            #ExprPlusMinus
    | '(' expr ')'                      #ExprParentisis
    | Num                               #ExprNum
    | Identifier                        #ExprIdentifier
    ;

Num: ('+'|'-')? [0-9]+;
Identifier: [a-zA-Z][a-zA-Z0-9]*;

WS: [ \t\r\n]+ -> skip;
Comment: '#' .*? '\n';
Error: .;