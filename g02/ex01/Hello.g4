grammar Hello ;
top: greetings | bye;
greetings: 'hello' ID {System.out.println("Olá " + $ID.text); } ;
bye: 'bye' ID {System.out.println("Adeus " + $ID.text); } ;
ID : [a-z]+ ;
WS : [ \t\r\n]+ -> skip ;