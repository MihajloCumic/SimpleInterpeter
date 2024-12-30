package org.example.interpreter;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Environment {
    private final Stack<Map<String, Object>> scopeStack = new Stack<>();
    public void addNewScope(){
        scopeStack.push(new HashMap<>());
    }
    public void putVariable(String name, Object value){
        scopeStack.peek().put(name, value);
    }

    public Object getVariable(String name){
        Map<String, Object> currScope = scopeStack.peek();
        if(!currScope.containsKey(name)) return null;
        return currScope.get(name);
    }

    public void removeScope(){
        scopeStack.pop();
    }
}