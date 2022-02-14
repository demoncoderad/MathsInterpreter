package com.hedronarc.mathsinterpreter;

import java.util.ArrayList;
import com.hedronarc.mathsinterpreter.Token.TokenType;

public class Parser {

    // Private
    private boolean Match(TokenType... tokenTypes) {
        for (TokenType T : tokenTypes) {
            if (Check(T)) {
                Advance();
                return true;
            }
        }
        return false;
    }

    private boolean Check(TokenType T) {
        if (IsAtEnd()) return false;
        return PeekAt(1).m_TokenType == T;
    }

    private Token Advance() {
        if (!IsAtEnd()) currentInd++;
        return PeekAt(0);
    }

    private boolean IsAtEnd() {
        return PeekAt(1).m_TokenType == TokenType.EOF;
    }

    private Token PeekAt(int forward) {
        return Tokens.get(currentInd + (forward - 1));
    }

    private Expression ParseExpression() {
        return ParseEquality();
    }

    private Expression ParseEquality() {
        Expression expr = ParseComparison();

        while (Match(TokenType.EQUAL_EQUAL)) {
            Token oper = PeekAt(0);
            Expression right = ParseComparison();
            expr = new Binary(expr, oper, right);
        }

        return expr;
    }

    private Expression ParseComparison() {
        Expression expr = ParseTerm();

        while (Match(TokenType.GREATER, TokenType.GREATER_EQUAL, TokenType.LESSER_EQUAL, TokenType.LESSER_EQUAL)) {
            Token oper = PeekAt(0);
            Expression right = ParseTerm();
            expr = new Binary(expr, oper, right);
        }

        return expr;
    }

    private Expression ParseTerm() {
        Expression expr = ParseFactor();

        while (Match(TokenType.MINUS, TokenType.PLUS)) {
            Token oper = PeekAt(0);
            Expression right = ParseFactor();
            expr = new Binary(expr, oper, right);
        }

        return expr;
    }

    private Expression ParseFactor() {
        Expression expr = ParseExponent();

        while (Match(TokenType.ASTERISK, TokenType.FORWARD_SLASH)) {
            Token oper = PeekAt(0);
            Expression middle = ParseExponent();
            expr = new Binary(expr, oper, middle);
        }

        return expr;
    }

    private Expression ParseExponent() {
        Expression expr = ParseUnary();

        while (Match(TokenType.CARET)) {
            Token oper = PeekAt(0);
            Expression middle = ParseUnary();
            expr = new Binary(expr, oper, middle);
        }

        return expr;
    }

    private Expression ParseUnary() {
        Expression expr = ParsePrimary();

        if (Match(TokenType.FACTORIAL, TokenType.PERCENT)) {
            Token oper = PeekAt(0);
            return new Unary(expr, oper);
        }

        return expr;
    }

    private Expression ParsePrimary() {
        if (Match(TokenType.DECIMAL_NUMBER, TokenType.INTEGER_NUMBER)) {
            return new Literal(PeekAt(0).m_Lexeme);
        }

        if (Match(TokenType.LEFT_PAREN)) {
            Expression expr = ParseExpression();
            if (Check(TokenType.RIGHT_PAREN)) {
                Advance();
                return new Grouping(expr);
            }
            else
                throw ErrorHandler.Error(PeekAt(1), "Expected ')' After '('");
        }

        throw ErrorHandler.Error(PeekAt(1), "Expected Expression");
    }

    // Public

    ArrayList<Token> Tokens;
    int currentInd = 0;

    public Parser(ArrayList<Token> tokens) {
        Tokens = tokens;
    }

    public Expression Parse() {
        try {
            return ParseExpression();
        } catch (ErrorHandler.ParseError e){
            return null;
        }
    }

}
