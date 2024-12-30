package org.example.interpreter.ast;

import org.example.interpreter.Token;

public class Assignment extends Expression{
    private final Token identifier;
    private final Literal literal;

    public Assignment(Token identifier, Literal literal) {
        this.identifier = identifier;
        this.literal = literal;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitAssignment(this);
    }

    public Token getIdentifier() {
        return identifier;
    }

    public Literal getLiteral() {
        return literal;
    }
}
