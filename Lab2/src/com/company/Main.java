package com.company;

public class Main {
    public static void main(String[] args) {
        SymbolTable symbolTable = new SymbolTable(101);
        symbolTable.addConstant("3");
        symbolTable.addConstant("3");
        symbolTable.addConstant("21");
        symbolTable.addConstant("dsadas");
        symbolTable.addIdentifier("a");
        symbolTable.addIdentifier("aa");
        System.out.println(symbolTable);
    }
}
