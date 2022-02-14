package com.hedronarc.mathsinterpreter;

import java.util.ArrayList;

import com.hedronarc.mathsinterpreter.Token.TokenType;

public class Lexer {

    // Private
    private String m_Source;
    private ArrayList<Token> m_Tokens = new ArrayList<Token>();

    private int current = 0, start = 0;

    private boolean IsAtEnd() {
        return current >= m_Source.length();
    }

    private char Advance() {
        return m_Source.charAt(current++);
    }

    private void AddToken(TokenType tt) {
        Token t = new Token(tt, m_Source.substring(start, current));
        m_Tokens.add(t);
    }

    private char PeekAt(int forward) {
        if (current + (forward - 1) >= m_Source.length()) return '\0';
        return m_Source.charAt(current + (forward - 1));
    }

    private void AddByMatch(char match, TokenType whenFalse, TokenType whenTrue) {
        if (PeekAt(1) == match) {
            Advance();
            AddToken(whenTrue);
            return;
        }
        AddToken(whenFalse);
    }

    private boolean IsDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private void LexNumber() {
        while (!IsAtEnd() && IsDigit(PeekAt(1))) {
            Advance();
        }
        if (PeekAt(1) == '.' && IsDigit(PeekAt(2))) {
            Advance();
            while (!IsAtEnd() && IsDigit(PeekAt(1))) {
                Advance();
                while (!IsAtEnd() && IsDigit(PeekAt(1))) {
                    Advance();
                }
            }

            AddToken(TokenType.DECIMAL_NUMBER);
            return;
        }
        AddToken(TokenType.INTEGER_NUMBER);

    }

    private void ScanToken() {
        char c = Advance();

        switch (c) {
            case '+': AddToken(TokenType.PLUS); break;
            case '-': AddToken(TokenType.MINUS); break;
            case '*': AddToken(TokenType.ASTERISK); break;
            case '/': AddToken(TokenType.FORWARD_SLASH); break;
            case '^': AddToken(TokenType.CARET); break;

            case '!': AddToken(TokenType.FACTORIAL); break;
            case '%': AddToken(TokenType.PERCENT); break;

            case '(': AddToken(TokenType.LEFT_PAREN); break;
            case ')': AddToken(TokenType.RIGHT_PAREN); break;

            case '>':
                AddByMatch('=', TokenType.GREATER, TokenType.GREATER_EQUAL);
                break;
            case '<':
                AddByMatch('=', TokenType.LESSER, TokenType.LESSER_EQUAL);
                break;
            case '=':
                if (PeekAt(1) == '=') Advance();
                AddToken(TokenType.EQUAL_EQUAL);
                break;
            case ' ' :
            case '\r':
            case '\t':
                break;

            default:
                if (IsDigit(c)) {
                    LexNumber();
                } else {
                    ErrorHandler.Report("Unrecognized Symbol -> " + c);
                }
        }
    }

    // Public
    public Lexer(String source) {
        m_Source = source;
    }

    public ArrayList<Token> Tokenize() {

        while (!IsAtEnd()) {
            start = current;
            ScanToken();
        }

        m_Tokens.add(new Token(Token.TokenType.EOF, ""));
        return m_Tokens;
    }

}
