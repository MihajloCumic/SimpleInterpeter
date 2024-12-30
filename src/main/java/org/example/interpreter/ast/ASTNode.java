package org.example.interpreter.ast;

public abstract class ASTNode {
    public abstract <T> T accept(Visitor<T> visitor);
}
