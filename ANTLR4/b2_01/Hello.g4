grammar Hello;
greetings: (a | b)+ ;
a: 'hello ' name;
b: 'bye ' name;
name: ID+;
ID : [a-zA-Z]+ ;
WS : [ \t\r\n]+ -> skip;
