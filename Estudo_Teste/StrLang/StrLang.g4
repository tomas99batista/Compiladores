grammar StrLang;

main: stat* EOF;

stat: print
    | assignment
    ;

print: 'print' expr;

assignment: Identifier ':' expr;

expr: 'input' '(' expr ')'              #ExprInput
    | expr '+' expr                     #ExprConcat
    | '(' expr '/' expr '/' expr ')'    #ExprSubstitute
    | String                            #ExprString
    | Identifier                        #ExprIdentifier
    ;

String: '"' .*? '"';
Identifier: [a-zA-z][a-zA-z0-9]*;

Comment: '//' .*? '\n' -> skip;
WhiteSpace: [ \t\r\n]+ -> skip;
Error: .; 