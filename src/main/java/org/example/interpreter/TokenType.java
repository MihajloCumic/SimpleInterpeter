package org.example.interpreter;

public enum TokenType {
    //single-letter tokens
    LEFT_BRACKET, RIGHT_BRACKET, EQUAL,

    //literals
    IDENTIFIER, NUMBER,

    //keywords
    SCOPE, PRINT,

    EOF
}
