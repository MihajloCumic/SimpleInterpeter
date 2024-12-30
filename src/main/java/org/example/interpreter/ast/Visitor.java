package org.example.interpreter.ast;

public interface Visitor<T>{
    T visitAssignment(Assignment expr);
    T visitLiteral(Literal expr);
    T visitPrint(Print expr);
    T visitScope(Scope expr);
    T visitBlock(Block block);
}
