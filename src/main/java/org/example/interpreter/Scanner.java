package org.example.interpreter;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Scanner {
    private final String source;
    private final List<Token> tokens = new ArrayList<>();

    private int start = 0;
    private int current = 0;
    private int line = 1;

    private static final Map<String, TokenType> keywords;
    static {
        keywords = new HashMap<>();
        keywords.put("scope", TokenType.SCOPE);
        keywords.put("print", TokenType.PRINT);
    }


    public Scanner(String source){
        this.source = source;
    }

    public List<Token> scanTokens(){
        while(!isAtEnd()){
            start = current;
            scanToken();
        }

        tokens.add(new Token(TokenType.EOF, "", null, line));
        return tokens;
    }

    private boolean isAtEnd(){
        return current >= source.length();
    }

    private void scanToken(){
        char c = advance();
        switch (c){
            case '{' : addToken(TokenType.LEFT_BRACKET); break;
            case '}' : addToken(TokenType.RIGHT_BRACKET); break;
            case '=' : addToken(TokenType.EQUAL); break;
            case '/' :
                if(match('/')){
                    while(peek() != '\n' && !isAtEnd()) advance();
                }else {
                    System.err.println("unexpected character: /");
                }
                break;
            case ' ', '\r', '\t': break;
            case '\n' : line++; break;
            default:
                if(isDigit(c)){
                    number();
                }else{
                    System.err.println("Unexpected character");
                }
                break;
        }
    }

    private char advance(){
        return source.charAt(current++);//proveriti da li treba ++current
    }

    private boolean match(char expected){
        if(isAtEnd()) return false;
        if(source.charAt(current) != expected) return false;

        current++;
        return true;
    }

    //lookahead
    private char peek(){
        if(isAtEnd()) return '\0';
        return source.charAt(current);
    }

    private void addToken(TokenType type){
        addToken(type, null);
    }

    private void addToken(TokenType type, Object literal){
        String text = source.substring(start, current);
        tokens.add(new Token(type, text, literal, line));
    }

    private void number(){
        while(isDigit(peek())) advance();
        addToken(TokenType.NUMBER, Long.parseLong(source.substring(start, current)));
    }

    private boolean isDigit(char c){
        return c >= '0' && c <= '9';
    }

    public List<Token> getTokens() {
        return tokens;
    }
}
