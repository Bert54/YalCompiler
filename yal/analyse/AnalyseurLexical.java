/* The following code was generated by JFlex 1.6.1 */

package yal.analyse ;

import java_cup.runtime.*;
import yal.exceptions.AnalyseLexicaleException;
      

/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.6.1
 * from the specification file <tt>/home/profil/licho2u/Fac/L3/S6/Compilation/yalCompiler/yal/analyse/AnalyseurLexical.jflex</tt>
 */
public class AnalyseurLexical implements java_cup.runtime.Scanner {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;
  public static final int Chaine = 2;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0,  0,  1, 1
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\15\1\4\1\6\1\5\1\5\22\0\1\15\1\14\1\0"+
    "\5\0\1\41\1\42\1\10\1\11\1\0\1\7\1\0\1\3\12\2"+
    "\1\0\1\40\1\12\1\13\1\12\2\0\32\1\4\0\1\1\1\0"+
    "\1\22\1\26\1\37\1\25\1\24\1\31\1\21\1\1\1\32\2\1"+
    "\1\35\1\23\1\33\1\20\1\16\1\36\1\17\1\34\1\30\1\27"+
    "\5\1\12\0\1\6\u1fa2\0\1\6\1\6\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\udfe6\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\2\0\1\1\1\2\1\3\1\4\1\5\1\6\1\4"+
    "\1\6\1\7\1\10\1\1\13\2\1\11\1\12\1\13"+
    "\1\5\1\14\2\2\1\15\1\2\1\16\6\2\1\17"+
    "\10\2\1\20\1\21\14\2\1\22\2\2\1\23\2\2"+
    "\1\24\2\2\1\25\1\26\2\2\1\27\1\30\3\2"+
    "\1\31\1\32\3\2\1\33\1\2\1\34";

  private static int [] zzUnpackAction() {
    int [] result = new int[89];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\43\0\43\0\106\0\151\0\214\0\43\0\151"+
    "\0\43\0\43\0\43\0\257\0\257\0\322\0\365\0\u0118"+
    "\0\u013b\0\u015e\0\u0181\0\u01a4\0\u01c7\0\u01ea\0\u020d\0\u0230"+
    "\0\43\0\43\0\43\0\u0253\0\43\0\u0276\0\u0299\0\106"+
    "\0\u02bc\0\106\0\u02df\0\u0302\0\u0325\0\u0348\0\u036b\0\u038e"+
    "\0\u03b1\0\u03d4\0\u03f7\0\u041a\0\u043d\0\u0460\0\u0483\0\u04a6"+
    "\0\u04c9\0\u04ec\0\106\0\u050f\0\u0532\0\u0555\0\u0578\0\u059b"+
    "\0\u05be\0\u05e1\0\u0604\0\u0627\0\u064a\0\u066d\0\u0690\0\106"+
    "\0\u06b3\0\u06d6\0\106\0\u06f9\0\u071c\0\106\0\u073f\0\u0762"+
    "\0\106\0\106\0\u0785\0\u07a8\0\106\0\106\0\u07cb\0\u07ee"+
    "\0\u0811\0\106\0\106\0\u0834\0\u0857\0\u087a\0\106\0\u089d"+
    "\0\106";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[89];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\3\1\4\1\5\1\6\2\7\1\0\1\10\1\11"+
    "\1\12\1\13\1\14\1\15\1\7\1\16\1\17\1\20"+
    "\1\4\1\21\1\4\1\22\1\23\2\4\1\24\1\25"+
    "\1\4\1\26\1\27\1\30\2\4\1\31\1\32\1\33"+
    "\44\0\2\4\13\0\22\4\5\0\1\5\43\0\1\34"+
    "\52\0\1\35\30\0\2\4\13\0\1\4\1\36\20\4"+
    "\4\0\2\4\13\0\6\4\1\37\13\4\4\0\2\4"+
    "\13\0\11\4\1\40\10\4\4\0\2\4\13\0\17\4"+
    "\1\41\2\4\4\0\2\4\13\0\12\4\1\42\2\4"+
    "\1\43\3\4\1\44\4\0\2\4\13\0\6\4\1\45"+
    "\13\4\4\0\2\4\13\0\4\4\1\46\15\4\4\0"+
    "\2\4\13\0\14\4\1\47\5\4\4\0\2\4\13\0"+
    "\2\4\1\50\17\4\4\0\2\4\13\0\14\4\1\51"+
    "\5\4\4\0\2\4\13\0\14\4\1\52\5\4\3\0"+
    "\4\34\1\7\2\0\34\34\1\0\2\4\13\0\2\4"+
    "\1\53\17\4\4\0\2\4\13\0\1\54\21\4\4\0"+
    "\2\4\13\0\2\4\1\55\17\4\4\0\2\4\13\0"+
    "\12\4\1\56\7\4\4\0\2\4\13\0\1\4\1\57"+
    "\20\4\4\0\2\4\13\0\10\4\1\60\11\4\4\0"+
    "\2\4\13\0\15\4\1\61\4\4\4\0\2\4\13\0"+
    "\15\4\1\62\4\4\4\0\2\4\13\0\15\4\1\63"+
    "\4\4\4\0\2\4\13\0\15\4\1\64\4\4\4\0"+
    "\2\4\13\0\1\4\1\65\20\4\4\0\2\4\13\0"+
    "\3\4\1\66\16\4\4\0\2\4\13\0\6\4\1\67"+
    "\13\4\4\0\2\4\13\0\1\4\1\70\20\4\4\0"+
    "\2\4\13\0\14\4\1\71\5\4\4\0\2\4\13\0"+
    "\14\4\1\72\5\4\4\0\2\4\13\0\11\4\1\73"+
    "\10\4\4\0\2\4\13\0\12\4\1\74\7\4\4\0"+
    "\2\4\13\0\12\4\1\75\3\4\1\76\3\4\4\0"+
    "\2\4\13\0\2\4\1\77\17\4\4\0\2\4\13\0"+
    "\6\4\1\100\13\4\4\0\2\4\13\0\1\4\1\101"+
    "\20\4\4\0\2\4\13\0\12\4\1\102\7\4\4\0"+
    "\2\4\13\0\16\4\1\103\3\4\4\0\2\4\13\0"+
    "\6\4\1\104\13\4\4\0\2\4\13\0\1\4\1\105"+
    "\20\4\4\0\2\4\13\0\12\4\1\106\7\4\4\0"+
    "\2\4\13\0\20\4\1\107\1\4\4\0\2\4\13\0"+
    "\4\4\1\110\15\4\4\0\2\4\13\0\14\4\1\111"+
    "\5\4\4\0\2\4\13\0\15\4\1\112\4\4\4\0"+
    "\2\4\13\0\4\4\1\113\15\4\4\0\2\4\13\0"+
    "\6\4\1\114\13\4\4\0\2\4\13\0\1\4\1\115"+
    "\20\4\4\0\2\4\13\0\6\4\1\116\13\4\4\0"+
    "\2\4\13\0\11\4\1\117\10\4\4\0\2\4\13\0"+
    "\15\4\1\120\4\4\4\0\2\4\13\0\5\4\1\121"+
    "\14\4\4\0\2\4\13\0\1\4\1\122\20\4\4\0"+
    "\2\4\13\0\6\4\1\123\13\4\4\0\2\4\13\0"+
    "\12\4\1\124\7\4\4\0\2\4\13\0\5\4\1\125"+
    "\14\4\4\0\2\4\13\0\20\4\1\126\1\4\4\0"+
    "\2\4\13\0\6\4\1\127\13\4\4\0\2\4\13\0"+
    "\11\4\1\130\10\4\4\0\2\4\13\0\6\4\1\131"+
    "\13\4\3\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[2240];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\1\10\1\11\3\1\1\11\1\1\3\11\15\1"+
    "\3\11\1\1\1\11\74\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[89];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;
  
  /** 
   * The number of occupied positions in zzBuffer beyond zzEndRead.
   * When a lead/high surrogate has been read from the input stream
   * into the final zzBuffer position, this will have a value of 1;
   * otherwise, it will have a value of 0.
   */
  private int zzFinalHighSurrogate = 0;

  /* user code: */

  private StringBuilder chaine ;

  private Symbol symbol(int type) {
	return new Symbol(type, yyline, yycolumn) ;
  }

  private Symbol symbol(int type, Object value) {
	return new Symbol(type, yyline, yycolumn, value) ;
  }


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public AnalyseurLexical(java.io.Reader in) {
    this.zzReader = in;
  }


  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x110000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 146) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzBuffer.length*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
    }

    /* fill the buffer with new input */
    int requested = zzBuffer.length - zzEndRead;
    int numRead = zzReader.read(zzBuffer, zzEndRead, requested);

    /* not supposed to occur according to specification of java.io.Reader */
    if (numRead == 0) {
      throw new java.io.IOException("Reader returned 0 characters. See JFlex examples for workaround.");
    }
    if (numRead > 0) {
      zzEndRead += numRead;
      /* If numRead == requested, we might have requested to few chars to
         encode a full Unicode character. We assume that a Reader would
         otherwise never return half characters. */
      if (numRead == requested) {
        if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
          --zzEndRead;
          zzFinalHighSurrogate = 1;
        }
      }
      /* potentially more input available */
      return false;
    }

    /* numRead < 0 ==> end of stream */
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * Internal scan buffer is resized down to its initial length, if it has grown.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    zzFinalHighSurrogate = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
    if (zzBuffer.length > ZZ_BUFFERSIZE)
      zzBuffer = new char[ZZ_BUFFERSIZE];
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public Symbol next_token() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      int zzCh;
      int zzCharCount;
      for (zzCurrentPosL = zzStartRead  ;
           zzCurrentPosL < zzMarkedPosL ;
           zzCurrentPosL += zzCharCount ) {
        zzCh = Character.codePointAt(zzBufferL, zzCurrentPosL, zzMarkedPosL);
        zzCharCount = Character.charCount(zzCh);
        switch (zzCh) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn += zzCharCount;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
            zzDoEOF();
          {         return symbol(CodesLexicaux.EOF) ;
 }
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1: 
            { throw new AnalyseLexicaleException(yyline, yycolumn, yytext()) ;
            }
          case 29: break;
          case 2: 
            { return symbol(CodesLexicaux.IDF, yytext());
            }
          case 30: break;
          case 3: 
            { return symbol(CodesLexicaux.CSTENTIERE, yytext());
            }
          case 31: break;
          case 4: 
            { return symbol(CodesLexicaux.OPER, yytext());
            }
          case 32: break;
          case 5: 
            { 
            }
          case 33: break;
          case 6: 
            { return symbol(CodesLexicaux.OPERT, yytext());
            }
          case 34: break;
          case 7: 
            { return symbol(CodesLexicaux.OPELOGIQUE, yytext());
            }
          case 35: break;
          case 8: 
            { return symbol(CodesLexicaux.EGALE);
            }
          case 36: break;
          case 9: 
            { return symbol(CodesLexicaux.POINTVIRGULE);
            }
          case 37: break;
          case 10: 
            { return symbol(CodesLexicaux.PAROUVRANTE);
            }
          case 38: break;
          case 11: 
            { return symbol(CodesLexicaux.PARFERMANTE);
            }
          case 39: break;
          case 12: 
            { return symbol(CodesLexicaux.OPELOGIQUET, yytext());
            }
          case 40: break;
          case 13: 
            { return symbol(CodesLexicaux.OPERMULTOU, yytext());
            }
          case 41: break;
          case 14: 
            { return symbol(CodesLexicaux.OPERMULTET, yytext());
            }
          case 42: break;
          case 15: 
            { return symbol(CodesLexicaux.SI);
            }
          case 43: break;
          case 16: 
            { return symbol(CodesLexicaux.FIN);
            }
          case 44: break;
          case 17: 
            { return symbol(CodesLexicaux.NON);
            }
          case 45: break;
          case 18: 
            { return symbol(CodesLexicaux.LIRE);
            }
          case 46: break;
          case 19: 
            { return symbol(CodesLexicaux.ALORS);
            }
          case 47: break;
          case 20: 
            { return symbol(CodesLexicaux.DEBUT);
            }
          case 48: break;
          case 21: 
            { return symbol(CodesLexicaux.FINSI);
            }
          case 49: break;
          case 22: 
            { return symbol(CodesLexicaux.SINON);
            }
          case 50: break;
          case 23: 
            { return symbol(CodesLexicaux.ENTIER);
            }
          case 51: break;
          case 24: 
            { return symbol(CodesLexicaux.ECRIRE);
            }
          case 52: break;
          case 25: 
            { return symbol(CodesLexicaux.REPETER);
            }
          case 53: break;
          case 26: 
            { return symbol(CodesLexicaux.TANTQUE);
            }
          case 54: break;
          case 27: 
            { return symbol(CodesLexicaux.PROGRAMME);
            }
          case 55: break;
          case 28: 
            { return symbol(CodesLexicaux.FINTANTQUE);
            }
          case 56: break;
          default:
            zzScanError(ZZ_NO_MATCH);
        }
      }
    }
  }


}
