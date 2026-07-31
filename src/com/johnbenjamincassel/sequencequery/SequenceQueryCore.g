grammar SequenceQueryCore;

query : subquery (booleanQuery)?;

booleanQuery : (('AND'|'and') query) | orquery;

orquery	:	('OR'|'or') query;

subquery : ( 'NOT' )? positiveQuery;

parenQuery
	:	positiveQuery | '(' query ')';


positiveQuery : temporalQuery | containmentQuery;
temporalQuery : occuringitem temporalOperator single (bound)?;


temporalOperator 
	:	 'BEFORE' | 'AFTER' | 'OVERLAPS';
single 	:	 occuringitem | interval;
interval :	 'INTERVAL' INTEGER;
bound 	:	 'WITHIN' INTEGER 'INTERVALS';

occuringitem : ('OCCURANCE' INTEGER)? item;
containmentQuery 
	:	 databasePortion 'CONTAINS' occuringitem;
databasePortion 
	:	 'DATABASE' | interval;

item	:	(ALPHA|INTEGER)+;	
ALPHA	: ('a'..'z'|'A'..'Z')+;		
INTEGER : ('0'..'9')+;
WHITESPACE :		(' '|'\t'|'\r'|'\n') {$channel=HIDDEN; };
