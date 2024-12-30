package org.example.interpreter.ast;

public class Print extends Expression{
    private final Literal literal;

    public Print(Literal literal) {
        this.literal = literal;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitPrint(this);
    }

    public Literal getLiteral() {
        return literal;
    }
}
