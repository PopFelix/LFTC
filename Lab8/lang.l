%{
#include <math.h>
#include <stdio.h>
int line = 0;
%}

%option noyywrap 

LETTER		    [A-Za-z_ ]
DIGIT		    [0-9]
NON-ZERO-DIGIT  [1-9]
IDENTIFIER      {LETTER}({LETTER}|{DIGIT})*
INT-CONSTANT    [-]?{NON-ZERO-DIGIT}{DIGIT}*|0
STRING-CONSTANT \"({LETTER}|{DIGIT})*\"
INVALID-IDENTIFIER ({DIGIT})+({LETTER})+({LETTER}|{DIGIT})*

%%

"int"           {printf("Reserved word: %s\n", yytext); /*return int;*/}
"string"        {printf("Reserved word: %s\n", yytext); /*return string;*/}
"if"            {printf("Reserved word: %s\n", yytext); /*return if;*/}
"else"          {printf("Reserved word: %s\n", yytext); /*return else;*/}
"while"         {printf("Reserved word: %s\n", yytext); /*return while;*/}
"break"         {printf("Reserved word: %s\n", yytext); /*return break;*/}
"vector"        {printf("Reserved word: %s\n", yytext); /*return vector;*/}
"print"         {printf("Reserved word: %s\n", yytext); /*return print;*/}
"scanInt"       {printf("Reserved word: %s\n", yytext); /*return scanInt;*/}
"scanString"    {printf("Reserved word: %s\n", yytext); /*return scanString;*/}

{IDENTIFIER}            {printf("Identifier: %s\n", yytext); /*return IDENTIFIER;*/}
{INVALID-IDENTIFIER}    {printf("Invalid identifier name \"%s\" at line %d\n", yytext,line); /*return INVALID-IDENTIFIER;*/}
{INT-CONSTANT}          {printf("Int constant: %s\n", yytext); /*return INT-CONSTANT;*/}
{STRING-CONSTANT}       {printf("String constant: %s\n", yytext); /*return {STRING-CONSTANT;*/}


";"			{printf("Separator: %s\n", yytext); /*return SEMI_COLON;*/}
","			{printf("Separator: %s\n", yytext); /*return COMA;*/}
"."			{printf("Separator: %s\n", yytext); /*return DOT;*/}
"+"			{printf("Operator: %s\n", yytext); /*return PLUS;*/}
"-"			{printf("Operator: %s\n", yytext); /*return MINUS;*/}
"*"			{printf("Operator: %s\n", yytext); /*return MULTIPLY;*/}
"/"			{printf("Operator: %s\n", yytext); /*return DIVISION;*/}
"("			{printf("Separator: %s\n", yytext); /*return LEFT_ROUND_PARANTHESIS;*/}
")"			{printf("Separator: %s\n", yytext); /*return RIGHT_ROUND_PARANTHESIS;*/}
"["			{printf("Separator: %s\n", yytext); /*return LEFT_SQUARE_PARANTHESIS;*/}
"]"			{printf("Separator: %s\n", yytext); /*return RIGHT_SQUARE_PARANTHESIS;*/}
"{"			{printf("Separator: %s\n", yytext); /*return LEFT_CURLY_PARANTHESIS;*/}
"}"			{printf("Separator: %s\n", yytext); /*return RIGHT_CURLY_PARANTHESIS;*/}
">"			{printf("Operator: %s\n", yytext); /*return GREATER_THAN;*/}
">="		{printf("Operator: %s\n", yytext); /*return GREATER_OR_EQUAL_THAN;*/}
"<="		{printf("Operator: %s\n", yytext); /*return LESS_OR_EQUAL_THAN;*/}
"<"			{printf("Operator: %s\n", yytext); /*return LESS_THAN;*/}
"="			{printf("Operator: %s\n", yytext); /*return ASSIGNMENT;*/}
"=="		{printf("Operator: %s\n", yytext); /*return EQUAL;*/}
"!="		{printf("Operator: %s\n", yytext); /*return DIFFERENT;*/}
"!"			{printf("Operator: %s\n", yytext); /*return NEGATION;*/}
"&&"		{printf("Operator: %s\n", yytext); /*return AND;*/}
"||"		{printf("Operator: %s\n", yytext); /*return OR;*/}


[ \t]+	/* elimina spatii */  	{}
[\n]+	{++line;}

. {printf("Illegal symbol %s at line %d\n", yytext, line); return -1;}
%%