package com.ex;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException {
        LanguageScanner scanner = new LanguageScanner("programs/p1.txt","res/tokens.in");
        try{
            scanner.scanProgram();
            System.out.println("Lexically correct!");
        }
        catch (ScannerException se){
            System.out.println(se.getMessage());
        }

        Files.writeString(Path.of("res/PIF.out"), scanner.extractPifTable());
        Files.writeString(Path.of("res/ST.out"), scanner.getSymbolTable().toString());
    }
}
