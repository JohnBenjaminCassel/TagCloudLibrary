package com.johnbenjamincassel.taglibs;

// $ANTLR 3.2 Sep 23, 2009 12:02:23 C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g 2010-06-19 15:36:11

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class StructuredCloudGrammarLexer extends Lexer {
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

    public StructuredCloudGrammarLexer() {;} 
    public StructuredCloudGrammarLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public StructuredCloudGrammarLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g"; }

    // $ANTLR start "T__9"
    public final void mT__9() throws RecognitionException {
        try {
            int _type = T__9;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:3:6: ( 'cloud' )
            // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:3:8: 'cloud'
            {
            match("cloud"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__9"

    // $ANTLR start "T__10"
    public final void mT__10() throws RecognitionException {
        try {
            int _type = T__10;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:4:7: ( 'expression' )
            // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:4:9: 'expression'
            {
            match("expression"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__10"

    // $ANTLR start "T__11"
    public final void mT__11() throws RecognitionException {
        try {
            int _type = T__11;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:5:7: ( '(' )
            // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:5:9: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__11"

    // $ANTLR start "T__12"
    public final void mT__12() throws RecognitionException {
        try {
            int _type = T__12;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:6:7: ( ')' )
            // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:6:9: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__12"

    // $ANTLR start "T__13"
    public final void mT__13() throws RecognitionException {
        try {
            int _type = T__13;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:7:7: ( ':' )
            // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:7:9: ':'
            {
            match(':'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__13"

    // $ANTLR start "TAG"
    public final void mTAG() throws RecognitionException {
        try {
            int _type = TAG;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:54:5: ( ( 'A' .. 'Z' | 'a' .. 'z' ) ( 'A' .. 'Z' | 'a' .. 'z' | '0' .. '9' | '-' | '_' )* )
            // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:54:7: ( 'A' .. 'Z' | 'a' .. 'z' ) ( 'A' .. 'Z' | 'a' .. 'z' | '0' .. '9' | '-' | '_' )*
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:54:26: ( 'A' .. 'Z' | 'a' .. 'z' | '0' .. '9' | '-' | '_' )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0=='-'||(LA1_0>='0' && LA1_0<='9')||(LA1_0>='A' && LA1_0<='Z')||LA1_0=='_'||(LA1_0>='a' && LA1_0<='z')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:
            	    {
            	    if ( input.LA(1)=='-'||(input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TAG"

    // $ANTLR start "VARIABLE"
    public final void mVARIABLE() throws RecognitionException {
        try {
            int _type = VARIABLE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:61:10: ( '$' ( 'A' .. 'Z' | 'a' .. 'z' | '0' .. '9' )+ )
            // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:61:11: '$' ( 'A' .. 'Z' | 'a' .. 'z' | '0' .. '9' )+
            {
            match('$'); 
            // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:61:15: ( 'A' .. 'Z' | 'a' .. 'z' | '0' .. '9' )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='0' && LA2_0<='9')||(LA2_0>='A' && LA2_0<='Z')||(LA2_0>='a' && LA2_0<='z')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt2 >= 1 ) break loop2;
                        EarlyExitException eee =
                            new EarlyExitException(2, input);
                        throw eee;
                }
                cnt2++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "VARIABLE"

    // $ANTLR start "FRACTION"
    public final void mFRACTION() throws RecognitionException {
        try {
            int _type = FRACTION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:63:10: ( '0' ( '.' ( '0' .. '9' )* )? )
            // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:63:12: '0' ( '.' ( '0' .. '9' )* )?
            {
            match('0'); 
            // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:63:17: ( '.' ( '0' .. '9' )* )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0=='.') ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:63:18: '.' ( '0' .. '9' )*
                    {
                    match('.'); 
                    // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:63:22: ( '0' .. '9' )*
                    loop3:
                    do {
                        int alt3=2;
                        int LA3_0 = input.LA(1);

                        if ( ((LA3_0>='0' && LA3_0<='9')) ) {
                            alt3=1;
                        }


                        switch (alt3) {
                    	case 1 :
                    	    // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:63:23: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    break loop3;
                        }
                    } while (true);


                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FRACTION"

    // $ANTLR start "ONE"
    public final void mONE() throws RecognitionException {
        try {
            int _type = ONE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:65:6: ( '1' ( '.' ( '0' )* )? )
            // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:65:8: '1' ( '.' ( '0' )* )?
            {
            match('1'); 
            // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:65:12: ( '.' ( '0' )* )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0=='.') ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:65:13: '.' ( '0' )*
                    {
                    match('.'); 
                    // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:65:17: ( '0' )*
                    loop5:
                    do {
                        int alt5=2;
                        int LA5_0 = input.LA(1);

                        if ( (LA5_0=='0') ) {
                            alt5=1;
                        }


                        switch (alt5) {
                    	case 1 :
                    	    // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:65:18: '0'
                    	    {
                    	    match('0'); 

                    	    }
                    	    break;

                    	default :
                    	    break loop5;
                        }
                    } while (true);


                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ONE"

    // $ANTLR start "WHITESPACE"
    public final void mWHITESPACE() throws RecognitionException {
        try {
            int _type = WHITESPACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:67:12: ( ( ' ' | '\\t' | '\\r' | '\\n' ) )
            // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:67:14: ( ' ' | '\\t' | '\\r' | '\\n' )
            {
            if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            _channel=HIDDEN; 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WHITESPACE"

    public void mTokens() throws RecognitionException {
        // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:1:8: ( T__9 | T__10 | T__11 | T__12 | T__13 | TAG | VARIABLE | FRACTION | ONE | WHITESPACE )
        int alt7=10;
        alt7 = dfa7.predict(input);
        switch (alt7) {
            case 1 :
                // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:1:10: T__9
                {
                mT__9(); 

                }
                break;
            case 2 :
                // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:1:15: T__10
                {
                mT__10(); 

                }
                break;
            case 3 :
                // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:1:21: T__11
                {
                mT__11(); 

                }
                break;
            case 4 :
                // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:1:27: T__12
                {
                mT__12(); 

                }
                break;
            case 5 :
                // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:1:33: T__13
                {
                mT__13(); 

                }
                break;
            case 6 :
                // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:1:39: TAG
                {
                mTAG(); 

                }
                break;
            case 7 :
                // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:1:43: VARIABLE
                {
                mVARIABLE(); 

                }
                break;
            case 8 :
                // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:1:52: FRACTION
                {
                mFRACTION(); 

                }
                break;
            case 9 :
                // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:1:61: ONE
                {
                mONE(); 

                }
                break;
            case 10 :
                // C:\\Documents and Settings\\John\\workspace\\sequence-db\\src\\com\\johnbenjamincassel\\taglibs\\StructuredCloudGrammar.g:1:65: WHITESPACE
                {
                mWHITESPACE(); 

                }
                break;

        }

    }


    protected DFA7 dfa7 = new DFA7(this);
    static final String DFA7_eotS =
        "\1\uffff\2\6\10\uffff\6\6\1\23\1\6\1\uffff\4\6\1\31\1\uffff";
    static final String DFA7_eofS =
        "\32\uffff";
    static final String DFA7_minS =
        "\1\11\1\154\1\170\10\uffff\1\157\1\160\1\165\1\162\1\144\1\145"+
        "\1\55\1\163\1\uffff\1\163\1\151\1\157\1\156\1\55\1\uffff";
    static final String DFA7_maxS =
        "\1\172\1\154\1\170\10\uffff\1\157\1\160\1\165\1\162\1\144\1\145"+
        "\1\172\1\163\1\uffff\1\163\1\151\1\157\1\156\1\172\1\uffff";
    static final String DFA7_acceptS =
        "\3\uffff\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\10\uffff\1\1\5\uffff"+
        "\1\2";
    static final String DFA7_specialS =
        "\32\uffff}>";
    static final String[] DFA7_transitionS = {
            "\2\12\2\uffff\1\12\22\uffff\1\12\3\uffff\1\7\3\uffff\1\3\1"+
            "\4\6\uffff\1\10\1\11\10\uffff\1\5\6\uffff\32\6\6\uffff\2\6\1"+
            "\1\1\6\1\2\25\6",
            "\1\13",
            "\1\14",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\15",
            "\1\16",
            "\1\17",
            "\1\20",
            "\1\21",
            "\1\22",
            "\1\6\2\uffff\12\6\7\uffff\32\6\4\uffff\1\6\1\uffff\32\6",
            "\1\24",
            "",
            "\1\25",
            "\1\26",
            "\1\27",
            "\1\30",
            "\1\6\2\uffff\12\6\7\uffff\32\6\4\uffff\1\6\1\uffff\32\6",
            ""
    };

    static final short[] DFA7_eot = DFA.unpackEncodedString(DFA7_eotS);
    static final short[] DFA7_eof = DFA.unpackEncodedString(DFA7_eofS);
    static final char[] DFA7_min = DFA.unpackEncodedStringToUnsignedChars(DFA7_minS);
    static final char[] DFA7_max = DFA.unpackEncodedStringToUnsignedChars(DFA7_maxS);
    static final short[] DFA7_accept = DFA.unpackEncodedString(DFA7_acceptS);
    static final short[] DFA7_special = DFA.unpackEncodedString(DFA7_specialS);
    static final short[][] DFA7_transition;

    static {
        int numStates = DFA7_transitionS.length;
        DFA7_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA7_transition[i] = DFA.unpackEncodedString(DFA7_transitionS[i]);
        }
    }

    class DFA7 extends DFA {

        public DFA7(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 7;
            this.eot = DFA7_eot;
            this.eof = DFA7_eof;
            this.min = DFA7_min;
            this.max = DFA7_max;
            this.accept = DFA7_accept;
            this.special = DFA7_special;
            this.transition = DFA7_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__9 | T__10 | T__11 | T__12 | T__13 | TAG | VARIABLE | FRACTION | ONE | WHITESPACE );";
        }
    }
 

}