grammar Hello;
top: (greetings | bye)* EOF;
greetings : 'hello' ID { System.out.println("Olá " + $names.list); };
bye:        'bye' ID {System.out.println("Adiós " + $names.list); };
names returns [String list = ""] : (ID {$list = $list + ($list.isEmpty() ? "" : ",") + $ID.text; })+;

ID : [A-Za-z]+ ;
WS : [ \t\r\n]+ -> skip ;