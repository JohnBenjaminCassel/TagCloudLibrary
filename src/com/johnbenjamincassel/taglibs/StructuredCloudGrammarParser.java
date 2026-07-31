// $ANTLR 3.2 Sep 23, 2009 12:02:23 C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g 2010-06-19 15:36:11
 
package com.johnbenjamincassel.taglibs;

import com.johnbenjamincassel.utilities.datastructures.*;
import com.johnbenjamincassel.conditionable.Variable;

import java.util.HashMap;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class StructuredCloudGrammarParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "TAG", "ONE", "FRACTION", "VARIABLE", "WHITESPACE", "'cloud'", "'expression'", "'('", "')'", "':'"
    };
    public static final int VARIABLE=7;
    public static final int T__12=12;
    public static final int T__11=11;
    public static final int T__13=13;
    public static final int T__10=10;
    public static final int WHITESPACE=8;
    public static final int ONE=5;
    public static final int FRACTION=6;
    public static final int EOF=-1;
    public static final int T__9=9;
    public static final int TAG=4;

    // delegates
    // delegators


        public StructuredCloudGrammarParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public StructuredCloudGrammarParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return StructuredCloudGrammarParser.tokenNames; }
    public String getGrammarFileName() { return "C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g"; }


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



    // $ANTLR start "cloudlike"
    // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:35:1: cloudlike returns [Cloudlike cl] : ( 'cloud' sc= structuredcloud | 'expression' cx= cloudexpression );
    public final Cloudlike cloudlike() throws RecognitionException {
        Cloudlike cl = null;

        StructuredTagCloud sc = null;

        CloudExpression cx = null;


        try {
            // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:36:2: ( 'cloud' sc= structuredcloud | 'expression' cx= cloudexpression )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==9) ) {
                alt1=1;
            }
            else if ( (LA1_0==10) ) {
                alt1=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:36:4: 'cloud' sc= structuredcloud
                    {
                    match(input,9,FOLLOW_9_in_cloudlike29); 
                    pushFollow(FOLLOW_structuredcloud_in_cloudlike33);
                    sc=structuredcloud();

                    state._fsp--;

                     cl =sc; 

                    }
                    break;
                case 2 :
                    // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:36:49: 'expression' cx= cloudexpression
                    {
                    match(input,10,FOLLOW_10_in_cloudlike39); 
                    pushFollow(FOLLOW_cloudexpression_in_cloudlike43);
                    cx=cloudexpression();

                    state._fsp--;

                     cl =cx; 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return cl;
    }
    // $ANTLR end "cloudlike"


    // $ANTLR start "structuredcloud"
    // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:38:1: structuredcloud returns [StructuredTagCloud sc] : cc= cloudchild[null] ;
    public final StructuredTagCloud structuredcloud() throws RecognitionException {
        StructuredTagCloud sc = null;

        StructuredTagCloud cc = null;


        try {
            // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:39:2: (cc= cloudchild[null] )
            // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:39:4: cc= cloudchild[null]
            {
            pushFollow(FOLLOW_cloudchild_in_structuredcloud60);
            cc=cloudchild(null);

            state._fsp--;

             sc =cc; 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return sc;
    }
    // $ANTLR end "structuredcloud"


    // $ANTLR start "cloudexpression"
    // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:41:1: cloudexpression returns [CloudExpression cex] : '(' (va= var | child= cloudexpression | tag= TAG )* ')' ;
    public final CloudExpression cloudexpression() throws RecognitionException {
        CloudExpression cex = null;

        Token tag=null;
        Variable va = null;

        CloudExpression child = null;


        try {
            // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:41:47: ( '(' (va= var | child= cloudexpression | tag= TAG )* ')' )
            // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:41:49: '(' (va= var | child= cloudexpression | tag= TAG )* ')'
            {
            match(input,11,FOLLOW_11_in_cloudexpression75); 
            cex = new CloudExpression(); 
            // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:42:2: (va= var | child= cloudexpression | tag= TAG )*
            loop2:
            do {
                int alt2=4;
                switch ( input.LA(1) ) {
                case VARIABLE:
                    {
                    alt2=1;
                    }
                    break;
                case 11:
                    {
                    alt2=2;
                    }
                    break;
                case TAG:
                    {
                    alt2=3;
                    }
                    break;

                }

                switch (alt2) {
            	case 1 :
            	    // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:42:3: va= var
            	    {
            	    pushFollow(FOLLOW_var_in_cloudexpression84);
            	    va=var();

            	    state._fsp--;

            	     cex.addTagVariable(va); 

            	    }
            	    break;
            	case 2 :
            	    // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:42:44: child= cloudexpression
            	    {
            	    pushFollow(FOLLOW_cloudexpression_in_cloudexpression92);
            	    child=cloudexpression();

            	    state._fsp--;

            	     cex.addChildExpression(child);  

            	    }
            	    break;
            	case 3 :
            	    // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:43:4: tag= TAG
            	    {
            	    tag=(Token)match(input,TAG,FOLLOW_TAG_in_cloudexpression102); 
            	      cex.addTag((tag!=null?tag.getText():null)); 

            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

            match(input,12,FOLLOW_12_in_cloudexpression110); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return cex;
    }
    // $ANTLR end "cloudexpression"


    // $ANTLR start "cloudchild"
    // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:46:1: cloudchild[StructuredTagCloud parent] returns [StructuredTagCloud sc] : '(' ( wt )* (cc= cloudchild[sc] )* ')' ;
    public final StructuredTagCloud cloudchild(StructuredTagCloud parent) throws RecognitionException {
        StructuredTagCloud sc = null;

        StructuredTagCloud cc = null;

        WeightedThing wt1 = null;


        try {
            // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:47:2: ( '(' ( wt )* (cc= cloudchild[sc] )* ')' )
            // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:47:4: '(' ( wt )* (cc= cloudchild[sc] )* ')'
            {
            match(input,11,FOLLOW_11_in_cloudchild124); 
             sc = new StructuredTagCloud(parent); 
            // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:47:50: ( wt )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==TAG) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:47:51: wt
            	    {
            	    pushFollow(FOLLOW_wt_in_cloudchild129);
            	    wt1=wt();

            	    state._fsp--;

            	     sc.addWeightedTag(wt1); 

            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

            // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:48:2: (cc= cloudchild[sc] )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==11) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:48:3: cc= cloudchild[sc]
            	    {
            	    pushFollow(FOLLOW_cloudchild_in_cloudchild140);
            	    cc=cloudchild(sc);

            	    state._fsp--;

            	     sc.addChild(cc); 

            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);

            match(input,12,FOLLOW_12_in_cloudchild151); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return sc;
    }
    // $ANTLR end "cloudchild"


    // $ANTLR start "wt"
    // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:51:1: wt returns [WeightedThing wt] : TAG ( ':' w= weight )? ;
    public final WeightedThing wt() throws RecognitionException {
        WeightedThing wt = null;

        Token TAG2=null;
        double w = 0.0;


        try {
            // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:51:31: ( TAG ( ':' w= weight )? )
            // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:51:33: TAG ( ':' w= weight )?
            {
            TAG2=(Token)match(input,TAG,FOLLOW_TAG_in_wt163); 
             wt = new WeightedThing((TAG2!=null?TAG2.getText():null),1,true); 
            // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:52:2: ( ':' w= weight )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==13) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:52:3: ':' w= weight
                    {
                    match(input,13,FOLLOW_13_in_wt170); 
                    pushFollow(FOLLOW_weight_in_wt174);
                    w=weight();

                    state._fsp--;

                     wt.setWeight(w); 

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return wt;
    }
    // $ANTLR end "wt"


    // $ANTLR start "weight"
    // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:56:1: weight returns [double d] : ( ONE | FRACTION );
    public final double weight() throws RecognitionException {
        double d = 0.0;

        Token ONE3=null;
        Token FRACTION4=null;

        try {
            // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:56:26: ( ONE | FRACTION )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==ONE) ) {
                alt6=1;
            }
            else if ( (LA6_0==FRACTION) ) {
                alt6=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:56:28: ONE
                    {
                    ONE3=(Token)match(input,ONE,FOLLOW_ONE_in_weight224); 
                     d = Double.parseDouble((ONE3!=null?ONE3.getText():null)); 

                    }
                    break;
                case 2 :
                    // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:57:4: FRACTION
                    {
                    FRACTION4=(Token)match(input,FRACTION,FOLLOW_FRACTION_in_weight232); 
                      d = Double.parseDouble((FRACTION4!=null?FRACTION4.getText():null)); 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return d;
    }
    // $ANTLR end "weight"


    // $ANTLR start "var"
    // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:59:1: var returns [Variable v] : VARIABLE ;
    public final Variable var() throws RecognitionException {
        Variable v = null;

        Token VARIABLE5=null;

        try {
            // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:59:25: ( VARIABLE )
            // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:59:27: VARIABLE
            {
            VARIABLE5=(Token)match(input,VARIABLE,FOLLOW_VARIABLE_in_var245); 
             v =  getOrCreateVariable((VARIABLE5!=null?VARIABLE5.getText():null)); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return v;
    }
    // $ANTLR end "var"

    // Delegated rules


 

    public static final BitSet FOLLOW_9_in_cloudlike29 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_structuredcloud_in_cloudlike33 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_10_in_cloudlike39 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_cloudexpression_in_cloudlike43 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_cloudchild_in_structuredcloud60 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_11_in_cloudexpression75 = new BitSet(new long[]{0x0000000000001890L});
    public static final BitSet FOLLOW_var_in_cloudexpression84 = new BitSet(new long[]{0x0000000000001890L});
    public static final BitSet FOLLOW_cloudexpression_in_cloudexpression92 = new BitSet(new long[]{0x0000000000001890L});
    public static final BitSet FOLLOW_TAG_in_cloudexpression102 = new BitSet(new long[]{0x0000000000001890L});
    public static final BitSet FOLLOW_12_in_cloudexpression110 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_11_in_cloudchild124 = new BitSet(new long[]{0x0000000000001810L});
    public static final BitSet FOLLOW_wt_in_cloudchild129 = new BitSet(new long[]{0x0000000000001810L});
    public static final BitSet FOLLOW_cloudchild_in_cloudchild140 = new BitSet(new long[]{0x0000000000001800L});
    public static final BitSet FOLLOW_12_in_cloudchild151 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TAG_in_wt163 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_13_in_wt170 = new BitSet(new long[]{0x0000000000000060L});
    public static final BitSet FOLLOW_weight_in_wt174 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ONE_in_weight224 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FRACTION_in_weight232 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VARIABLE_in_var245 = new BitSet(new long[]{0x0000000000000002L});

}