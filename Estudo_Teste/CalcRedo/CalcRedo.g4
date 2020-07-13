grammar CalcRedo;

main: (stat ';')* EOF;

stat: print
    | assign
    ;

print: '>' expr;

assign: Id '->' expr;

expr: expr op=('x' | '/' | '%') expr    #ExprMultDivMod
    | expr op=('+' | '-') expr  #ExprSumMinus
    | '(' expr ')'  #ExprParentisis
    | Id    #ExprID
    | Num   #ExprNum
    ;

Id: [a-zA-Z] [a-zA-Z0-9]*;
Num: ('+'|'-')* [0-9]+ ('.' [0-9]+)?;

WS: [ \t\r\n]+ -> skip;
Comment: '#' .*? '\n' -> skip;
Error: .;