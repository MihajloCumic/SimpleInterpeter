package org.example.interpreter.ast;

public class Scope extends Expression{
    private final Block block;

    public Scope(Block block) {
        this.block = block;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitScope(this);
    }

    public Block getBlock() {
        return block;
    }
}
