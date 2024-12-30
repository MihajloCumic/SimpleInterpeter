package org.example.interpreter.ast;


import java.util.ArrayList;
import java.util.List;

public class Block extends ASTNode{
    List<Expression> expressions = new ArrayList<>();

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitBlock(this);
    }

    public void addExpression(Expression expression){
        expressions.add(expression);
    }

    public List<Expression> getExpressions() {
        return expressions;
    }
}
