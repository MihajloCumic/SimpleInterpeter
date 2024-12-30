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

    public Object getFirstVariableFromScopes(String name){
        for(int i = scopeStack.size() - 1; i >= 0; i--){
            Map<String, Object> map = scopeStack.get(i);
            if(map.containsKey(name)) return map.get(name);
        }
        return null;
    }
}
