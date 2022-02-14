package com.hedronarc.mathsinterpreter;

import java.util.ArrayList;

public class RunExpression {

    public void Run(String expr_string) {

        Lexer lexer = new Lexer(expr_string);
        ArrayList<Token> tokens = lexer.Tokenize();

        if (ErrorHandler.ErrorStatus) return;

        Expression expr = new Parser(tokens).Parse();

        if (ErrorHandler.ErrorStatus) return;

        String result = Double.toString((Double) new Interpreter().Evaluate(expr));
        if (result.charAt(result.length()-2) == '.' && result.charAt(result.length()-1) == '0')
            result = result.substring(0, result.length() - 2);

        System.out.println(result);

    }

}
