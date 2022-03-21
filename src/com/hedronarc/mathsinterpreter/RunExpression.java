package com.hedronarc.mathsinterpreter;

import java.util.ArrayList;

public class RunExpression {

    public void Run(String expr_string) {

        Lexer lexer = new Lexer(expr_string);
        ArrayList<Token> tokens = lexer.Tokenize();

        if (ErrorHandler.ErrorStatus) return;

        Expression expr = new Parser(tokens).Parse();

        if (ErrorHandler.ErrorStatus) return;

        Object res = new Interpreter().Evaluate(expr);
        String result;
        if (res instanceof Boolean) {
            if ((Boolean) res == false)
                result = "false";
            else
                result = "true";
        } else {
            result = Double.toString((Double) res);
        }
        if (result.charAt(result.length()-2) == '.' && result.charAt(result.length()-1) == '0')
            result = result.substring(0, result.length() - 2);

        System.out.println(result);

    }

}
