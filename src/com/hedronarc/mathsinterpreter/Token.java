package com.hedronarc.mathsinterpreter;


public class Token {

    public enum TokenType {

        // Literal
        INTEGER_NUMBER, DECIMAL_NUMBER,

        // Binary Value Operators
        PLUS, MINUS, ASTERISK, FORWARD_SLASH, CARET,

        // Unary Operators -> Right
        FACTORIAL, PERCENT,

        // Binary Boolean Operators
        GREATER, GREATER_EQUAL, LESSER, LESSER_EQUAL, EQUAL_EQUAL,

        LEFT_PAREN, RIGHT_PAREN,

        // End Of File
        EOF

    }

    TokenType m_TokenType;
    String m_Lexeme;

    public Token(TokenType p_TokenType, String p_Lexeme) {
        m_TokenType = p_TokenType;
        m_Lexeme = p_Lexeme;
    }

    public String toString() {
        return m_TokenType + " : " + m_Lexeme;
    }

}
