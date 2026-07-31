grammar StructuredCloudGrammar;

@header { 
package com.johnbenjamincassel.taglibs;

import com.johnbenjamincassel.utilities.datastructures.*;
import com.johnbenjamincassel.conditionable.Variable;

import java.util.HashMap;
}


@members {
	HashMap<String, Variable> m_variable_hash = new HashMap<String, Variable>(); 
	
	public static Cloudlike parseCloud(String cloud_string) throws RecognitionException {
		StructuredCloudGrammarLexer lexer
			= new StructuredCloudGrammarLexer(new ANTLRStringStream(cloud_string));
		CommonTokenStream tokens = new CommonTokenStream();
		tokens.setTokenSource(lexer);
		StructuredCloudGrammarParser parser
			= new StructuredCloudGrammarParser(tokens);
		return parser.cloudlike();
	}
	
	private Variable getOrCreateVariable(String varname) {
		Variable v = m_variable_hash.get(varname);
		if(v != null) { return v; }
		v = new Variable(varname, Object.class);
		m_variable_hash.put(varname, v);
		return v;
	}
}	
	
cloudlike returns[Cloudlike cl]
	: 'cloud' sc=structuredcloud { $cl=$sc.sc; } | 'expression' cx=cloudexpression { $cl=$cx.cex; };

structuredcloud returns [StructuredTagCloud sc]
	: cc=cloudchild[null] { $sc=$cc.sc; };

cloudexpression returns [CloudExpression cex] : '(' {$cex = new CloudExpression(); } 
	(va=var { $cex.addTagVariable($va.v); } | child=cloudexpression { $cex.addChildExpression($child.cex);  } 
	| tag=TAG {  $cex.addTag($tag.text); } )*
	')';

cloudchild[StructuredTagCloud parent] returns [StructuredTagCloud sc]
	: '(' { $sc = new StructuredTagCloud(parent); } (wt { $sc.addWeightedTag($wt.wt); })* 
	(cc=cloudchild[sc] { $sc.addChild($cc.sc); } )*  
	')';

wt returns [WeightedThing wt] :	TAG { $wt = new WeightedThing($TAG.text,1,true); } 
	(':' w=weight { $wt.setWeight($w.d); })? ;

TAG	:	('A'..'Z'|'a'..'z')('A'..'Z'|'a'..'z'|'0'..'9'|'-'|'_')*;

weight returns [double d]: ONE { $d = Double.parseDouble($ONE.text); } 
	| FRACTION {  $d = Double.parseDouble($FRACTION.text); };

var returns [Variable v]: VARIABLE { $v =  getOrCreateVariable($VARIABLE.text); };
 
VARIABLE :'$' ('A'..'Z'|'a'..'z'|'0'..'9')+; 
 
FRACTION : '0' 	('.' ('0'..'9')*)?;

ONE 	:	'1' ('.' ('0')*)?;

WHITESPACE :	(' '|'\t'|'\r'|'\n') {$channel=HIDDEN; };
