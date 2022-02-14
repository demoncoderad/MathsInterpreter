package com.hedronarc.mathsinterpreter;

public class PrettyPrinter implements ExpressionVisitor<String> {

    private Expression expr;

    public PrettyPrinter(Expression expr) {
        this.expr = expr;
    }

    public void Print() {
        System.out.println(expr.Accept(this));
    }

    @Override
    public String VisitUnaryExpression(Unary unary) {
        return "( " + unary.Left.Accept(this) + unary.Right.m_Lexeme + " )";
    }

    @Override
    public String VisitBinaryExpression(Binary binary) {
        return "( " + binary.Left.Accept(this) + " " + binary.Middle.m_Lexeme + " " + binary.Right.Accept(this) + " )";
    }

    @Override
    public String VisitGroupingExpression(Grouping grouping) {
        return "( " + grouping.Middle.Accept(this) + " )";
    }

    @Override
    public String VisitLiteralExpression(Literal literal) {
        return literal.value.toString();
    }
}
