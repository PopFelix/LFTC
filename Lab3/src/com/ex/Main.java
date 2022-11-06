package com.ex;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        LanguageScanner scanner = new LanguageScanner("programs/p1err.txt","res/tokens.in");
        try{
            scanner.scanProgram();
        }
        catch (ScannerException se){
            System.out.println(se.getMessage());
        }

        Files.writeString(Path.of("res/PIF.out"), scanner.extractPifTable());
        Files.writeString(Path.of("res/ST.out"), scanner.getSymbolTable().toString());
    }
}
