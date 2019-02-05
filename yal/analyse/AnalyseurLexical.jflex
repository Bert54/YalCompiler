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
operateur = [\*\-\+]
operateurComp = (<|>|\=\=|!\=|<\=|>\=)

finDeLigne = \r|\n
espace = {finDeLigne}  | [ \t\f]

%%

"programme"            { return symbol(CodesLexicaux.PROGRAMME); }
"debut"                { return symbol(CodesLexicaux.DEBUT); }
"fin"              	   { return symbol(CodesLexicaux.FIN); }

"entier"               { return symbol(CodesLexicaux.ENTIER); }

"tantque"              { return symbol(CodesLexicaux.TANTQUE); }

"repeter"              { return symbol(CodesLexicaux.REPETER); }

"fintantque"           { return symbol(CodesLexicaux.FINTANTQUE); }

{operateurComp}        { return return symbol(CodesLexicaux.OPELOGIQUE, yytext()); }

"="                    { return symbol(CodesLexicaux.EGALE); }

"ecrire"               { return symbol(CodesLexicaux.ECRIRE); }

"lire"                 { return symbol(CodesLexicaux.LIRE); }

";"                    { return symbol(CodesLexicaux.POINTVIRGULE); }

{csteE}      	       { return symbol(CodesLexicaux.CSTENTIERE, yytext()); }

"("                    { return symbol(CodesLexicaux.PAROUVRANTE); }

")"                    { return symbol(CodesLexicaux.PARFERMANTE); }

{operateur}            { return symbol(CodesLexicaux.OPER, yytext()); }

{idf}      	           { return symbol(CodesLexicaux.IDF, yytext()); }

{espace}               { }
{commentDoubleSl}      { }
.                      { throw new AnalyseLexicaleException(yyline, yycolumn, yytext()) ; }

