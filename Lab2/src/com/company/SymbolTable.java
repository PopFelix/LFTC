package com.company;

public class SymbolTable {
    private final HashTable<String> identifierTable;
    private final HashTable<String> constantTable;
    private final int size;

    public SymbolTable(int size) {
        this.size = size;
        this.identifierTable = new HashTable<>(size);
        this.constantTable = new HashTable<>(size);
    }

    public void addIdentifier(String identifier) {
        identifierTable.add(identifier);
    }

    public void addConstant(String constant) {
        constantTable.add(constant);
    }

    @Override
    public String toString() {
        return "Identifier table: " + identifierTable + "\nConstant table: " + constantTable + "\n";
    }
}
