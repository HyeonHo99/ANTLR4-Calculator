grammar Expr;

// parser rules
prog : ((decl | expr) ';' NEWLINE?)*;
expr : '(' expr ')'         # parensExpr
     | expr ('*'|'/') expr  # infixExpr
     | expr ('+'|'-') expr  # infixExpr
     | ID		     			# idExpr
     | NUM                  # numberExpr
     | SQRT '(' NUM ')' 		#sqrtExpr
     | MATH '(' NUM ',' NUM ')' 	#mathExpr
     ;

decl : ID '=' NUM;
     
     
// lexer rules                    
NEWLINE: [\r\n]+ ;
NUM: '-'?[0-9]+('.'[0-9]*)* ; // should handle signs(+/-)
ID: [a-zA-Z] ;
WS: [ \t\r\n]+ -> skip ;
SQRT: 'sqrt' ;
MATH: 'pow' | 'min' | 'max' ;