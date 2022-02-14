package com.hedronarc.mathsinterpreter;

abstract class Expression {

    abstract <R> R Accept(ExpressionVisitor<R> visitor);

}

class Binary extends Expression {

    public Expression Left;
    public Expression Right;
    public Token Middle;

    public Binary(Expression left, Token middle, Expression right) {
        Left = left;
        Right = right;
        Middle = middle;
    }

    @Override
    <R> R Accept(ExpressionVisitor<R> visitor) {
        return visitor.VisitBinaryExpression(this);
    }
}

class Grouping extends Expression {

    public Expression Middle;

    public Grouping(Expression middle) {
        Middle = middle;
    }

    @Override
    <R> R Accept(ExpressionVisitor<R> visitor) {
        return visitor.VisitGroupingExpression(this);
    }
}

class Literal extends Expression {

    public Object value;

    public Literal(Object value) {
        this.value = value;
    }

    @Override
    <R> R Accept(ExpressionVisitor<R> visitor) {
        return visitor.VisitLiteralExpression(this);
    }
}

class Unary extends Expression {

    public Expression Left;
    public Token Right;

    public Unary(Expression Left, Token Right) {
        this.Left = Left;
        this.Right = Right;
    }

    @Override
    <R> R Accept(ExpressionVisitor<R> visitor) {
        return visitor.VisitUnaryExpression(this);
    }
}

interface ExpressionVisitor<R> {
    R VisitUnaryExpression(Unary unary);
    R VisitBinaryExpression(Binary binary);
    R VisitGroupingExpression(Grouping grouping);
    R VisitLiteralExpression(Literal literal);
}
