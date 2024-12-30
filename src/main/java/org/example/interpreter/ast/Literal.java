package org.example.interpreter.ast;

import org.example.interpreter.Token;

public class Literal extends Expression{
    private final Token value;

    public Literal(Token value) {
        this.value = value;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitLiteral(this);
    }

    public Token getValue() {
        return value;
    }
}
