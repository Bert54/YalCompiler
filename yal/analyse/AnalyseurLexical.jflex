package yal.analyse ;

import java_cup.runtime.*;
import yal.exceptions.AnalyseLexicaleException;
      
%%
   
%class AnalyseurLexical
%public

%line
%column
    
%type Symbol
%eofval{
        return symbol(CodesLexicaux.EOF) ;
%eofval}

%cup

%{

  private StringBuilder chaine ;

  private Symbol symbol(int type) {
	return new Symbol(type, yyline, yycolumn) ;
  }

  private Symbol symbol(int type, Object value) {
	return new Symbol(type, yyline, yycolumn, value) ;
  }
%}

%xstate Chaine

idf = [A-Za-z_][A-Za-z_0-9]*
commentDoubleSl = \/\/.*\n?
csteE = [0-9]+
guillemet = [\"]
operateur = [\*\/]
operateurT = [\+\-]
operateurComp =  (<|>)
operateurCompT = (\=\=|\!\=)

finDeLigne = \r|\n
espace = {finDeLigne} | [ \t\f]

%%

"programme"            { return symbol(CodesLexicaux.PROGRAMME); }
"debut"                { return symbol(CodesLexicaux.DEBUT); }
"fin"              	   { return symbol(CodesLexicaux.FIN); }
"si"			       { return symbol(CodesLexicaux.SI); }
"alors"			       { return symbol(CodesLexicaux.ALORS); }
"sinon"			       { return symbol(CodesLexicaux.SINON); }
"finsi"		           { return symbol(CodesLexicaux.FINSI); }
"non"                  { return symbol(CodesLexicaux.NON); }
"tantque"              { return symbol(CodesLexicaux.TANTQUE); }
"repeter"              { return symbol(CodesLexicaux.REPETER); }
"fintantque"           { return symbol(CodesLexicaux.FINTANTQUE); }



"entier"               { return symbol(CodesLexicaux.ENTIER); }

{operateur}            { return symbol(CodesLexicaux.OPER, yytext()); }

"+"                    { return symbol(CodesLexicaux.OPERT, yytext()); }

"-"                    { return symbol(CodesLexicaux.OPERTN, yytext()); }

{operateurComp}        { return symbol(CodesLexicaux.OPELOGIQUE, yytext()); }
{operateurCompT}       { return symbol(CodesLexicaux.OPELOGIQUET, yytext()); }


{operateur}            { return symbol(CodesLexicaux.OPER, yytext()); }
{operateurT}           { return symbol(CodesLexicaux.OPERT, yytext()); }


"et"                   { return symbol(CodesLexicaux.OPERMULTET, yytext()); }
"ou"                   { return symbol(CodesLexicaux.OPERMULTOU, yytext()); }

"="                    { return symbol(CodesLexicaux.EGALE); }

"<"                    { return symbol(CodesLexicaux.INF); }

">"                    { return symbol(CodesLexicaux.SUP); }

"ecrire"               { return symbol(CodesLexicaux.ECRIRE); }

"lire"                 { return symbol(CodesLexicaux.LIRE); }

"fonction"             { return symbol(CodesLexicaux.FONCTION); }

"retourne"             { return symbol(CodesLexicaux.RETOURNER); }

";"                    { return symbol(CodesLexicaux.POINTVIRGULE); }

","                    { return symbol(CodesLexicaux.VIRGULE); }

"("                    { return symbol(CodesLexicaux.PAROUVRANTE); }

")"                    { return symbol(CodesLexicaux.PARFERMANTE); }

"["                    { return symbol(CodesLexicaux.CROCHOUVRANT); }

"]"                    { return symbol(CodesLexicaux.CROCHFERMANT); }

{csteE}      	       { return symbol(CodesLexicaux.CSTENTIERE, yytext()); }

{idf}      	           { return symbol(CodesLexicaux.IDF, yytext()); }

{espace}               { }
{commentDoubleSl}      { }
.                      { throw new AnalyseLexicaleException(yyline, yycolumn, yytext()) ; }

