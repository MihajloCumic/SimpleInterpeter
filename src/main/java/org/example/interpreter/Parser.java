package org.example.interpreter;

import org.example.interpreter.ast.*;

import java.util.List;

public class Parser {
    private final List<Token> tokens;
    private int current = 0;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    public Block parse(){
        return block();
    }

    private Block block(){
        Block block = new Block();
        while (!isAtEnd()){
            System.out.println(peek().tokenType);
            block.addExpression(expression());
        }
        return block;
    }


    private Expression expression(){
        if(match(TokenType.IDENTIFIER)) return assignment();
        if(match(TokenType.PRINT)) return print();
        if(match(TokenType.SCOPE)) return scope();
        throw new RuntimeException("Expression fun error!: " + peek().tokenType);
    }

    private Expression scope(){
        if(match(TokenType.LEFT_BRACKET)){
            Block block = new Block();
            while(!check(TokenType.RIGHT_BRACKET) && !match(TokenType.EOF)){
                block.addExpression(expression());
            }
            if(peek().tokenType == TokenType.RIGHT_BRACKET){
                advance();
                return new Scope(block);
            }
        }
        throw new RuntimeException("Scope fun error!");
    }

    private Expression assignment(){
        Token identifier = previous();
        if(match(TokenType.EQUAL) && literal() instanceof Literal literal){
            return new Assignment(identifier, literal);
        }
        throw new RuntimeException("Assignment fun error!");
    }

    private Expression print(){
       if(literal() instanceof Literal literal){
           return new Print(literal);
       }
       throw new RuntimeException("Print fun error!");
    }

    private Expression literal(){
        if(match(TokenType.IDENTIFIER, TokenType.NUMBER)) return new Literal(previous());
        throw new RuntimeException("Literal fun error!");
    }

    private boolean match(TokenType... types){
        for(TokenType type: types){
            if(check(type)){
                advance();
                return true;
            }
        }
        return false;
    }

    private boolean check(TokenType type){
        if(isAtEnd()) return false;
        return peek().tokenType == type;
    }

    private Token advance(){
        if(!isAtEnd()) current++;
        return previous();
    }

    private boolean isAtEnd(){
        return peek().tokenType == TokenType.EOF;
    }

    private Token peek(){
        return tokens.get(current);
    }

    private Token previous(){
        return tokens.get(current - 1);
    }
}
