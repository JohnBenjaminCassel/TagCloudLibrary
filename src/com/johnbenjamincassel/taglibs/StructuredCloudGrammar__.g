lexer grammar StructuredCloudGrammar;

T7 : '(' ;
T8 : ')' ;
T9 : ':' ;

// $ANTLR src "C:\eclipse\workspace\sequence-db\src\com\johnbenjamincassel\taglibs\StructuredCloudGrammar.g" 32
TAG	:	('A'..'Z'|'a'..'z')('A'..'Z'|'a'..'z'|'0'..'9'|'-'|'_')*;

// $ANTLR src "C:\eclipse\workspace\sequence-db\src\com\johnbenjamincassel\taglibs\StructuredCloudGrammar.g" 37
FRACTION : '0' 	('.' ('0'..'9')*)?;

// $ANTLR src "C:\eclipse\workspace\sequence-db\src\com\johnbenjamincassel\taglibs\StructuredCloudGrammar.g" 39
ONE 	:	'1' ('.' ('0')*)?;
