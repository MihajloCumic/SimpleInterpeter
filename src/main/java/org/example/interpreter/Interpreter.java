package org.example.interpreter;

import org.example.interpreter.ast.*;

public class Interpreter implements Visitor<Void> {
    private final Environment environment;

    public Interpreter(){
        this.environment = new Environment();
    }

    @Override
    public Void visitBlock(Block block) {
        environment.addNewScope();
        for(Expression expression: block.getExpressions()){
            expression.accept(this);
        }
        environment.removeScope();
        return null;
    }

    @Override
    public Void visitScope(Scope expr) {
        expr.getBlock().accept(this);
        return null;
    }

    @Override
    public Void visitAssignment(Assignment expr) {
        String varName = expr.getIdentifier().lexeme;
        Token literal  = expr.getLiteral().getValue();
        if(literal.tokenType == TokenType.NUMBER){
            environment.putVariable(varName, literal.literal);
            return null;
        }
        Object value = environment.getFirstVariableFromScopes(literal.lexeme);
        environment.putVariable(varName, value);
        return null;
    }

    @Override
    public Void visitLiteral(Literal expr) {
        return null;
    }

    @Override
    public Void visitPrint(Print expr) {
        Token literal = expr.getLiteral().getValue();
        if(literal.tokenType == TokenType.NUMBER){
            System.out.println(literal.literal);
            return null;
        }
        String name = literal.lexeme;
        Object value = environment.getVariable(name);
        System.out.println(value);
        return null;
    }




}
