grammar SuffixCalculator;
program:
    stat* EOF ; // 0 ou + repetiçoes de stat
stat:
    expr? NEWLINE ; // optative expr
expr: 
    expr expr op=('*'|'/'|'+'|'-') #ExprSuffix
    | Number                       #ExprNumber
    ;
Number: [0-9]+('.'[0-9]+)?;
NEWLINE: '\r'? '\n';
WS: [ \t]+ -> skip;