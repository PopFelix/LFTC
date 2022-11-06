package com.ex;

public class SymbolTable {
    private final HashTable<String> identifierTable;
    private final HashTable<String> constantTable;
    private final int size;

    public SymbolTable(int size) {
        this.size = size;
        this.identifierTable = new HashTable<>(size);
        this.constantTable = new HashTable<>(size);
    }

    public Pair<Integer,Integer> addIdentifier(String identifier) {
        return identifierTable.add(identifier);
    }

    public Pair<Integer,Integer> addConstant(String constant) {
        return constantTable.add(constant);
    }

    @Override
    public String toString() {
        return "Identifier table: \n" + identifierTable + "Constant table: \n" + constantTable + "\n";
    }
}
