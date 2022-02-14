package com.hedronarc.mathsinterpreter;

public class Interpreter implements ExpressionVisitor<Object> {

    private long Factorial(double left) {
        int ans = 1;
        for (int i = 2; i <= left; i++) {
            ans *= i;
        }
        return ans;
    }

    public Object Evaluate (Expression expr) {
        return expr.Accept(this);
    }

    @Override
    public Object VisitUnaryExpression(Unary unary) {
        Object left = Evaluate(unary.Left);

        return switch (unary.Right.m_TokenType) {
            case FACTORIAL -> (double) Factorial((double)left);
            case PERCENT -> (double) left / 100;
            default -> null;
        };

    }

    @Override
    public Object VisitBinaryExpression(Binary binary) {

        Object left = Evaluate(binary.Left);
        Object right = Evaluate(binary.Right);

        return switch (binary.Middle.m_TokenType) {
            case PLUS  ->         (double) left + (double) right;
            case MINUS ->         (double) left - (double) right;
            case ASTERISK ->      (double) left * (double) right;
            case FORWARD_SLASH -> (double) left / (double) right;
            case CARET ->         Math.pow((double) left, (double) right);
            case GREATER  ->      (double) left > (double) right;
            case GREATER_EQUAL -> (double) left >= (double) right;
            case LESSER ->        (double) left < (double) right;
            case LESSER_EQUAL ->  (double) left <= (double) right;
            case EQUAL_EQUAL ->   (double) left == (double) right;
            default -> null;
        };

    }

    @Override
    public Object VisitGroupingExpression(Grouping grouping) {
        return Evaluate(grouping.Middle);
    }

    @Override
    public Object VisitLiteralExpression(Literal literal) {
        return Double.parseDouble((String) literal.value);
    }
}
