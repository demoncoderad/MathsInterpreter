package com.hedronarc.mathsinterpreter;

public class ErrorHandler {

    public static class ParseError extends RuntimeException {}

    public static boolean ErrorStatus = false;

    public static void Report(String message) {
        System.out.println("ERROR -> " + message);
        ErrorStatus = true;
    }

    private static void Report(Token token, String message) {
        System.out.println("Error at " + (token.m_TokenType == Token.TokenType.EOF ?
                "end " : "`" + token.m_Lexeme + "` "
                ) + ": " + message);
        ErrorStatus = true;
    }


    public static ParseError Error(Token token, String message) {
        Report(token, message);
        return new ParseError();
    }

}
