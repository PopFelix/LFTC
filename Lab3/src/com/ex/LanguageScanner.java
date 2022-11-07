package com.ex;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class LanguageScanner {
    private List<String> tokens;
    private String programContent;
    private SymbolTable symbolTable;
    private List<Pair<String, Pair<Integer, Integer>>> pif;
    private int currentIndex = 0;
    private int lineCounter = 1;

    public LanguageScanner(String filename, String tokenFileName) {
        this.symbolTable = new SymbolTable(100);
        this.pif = new ArrayList<>();
        this.tokens = new ArrayList<>();
        try {
            this.programContent = new Scanner(new File(filename)).useDelimiter("\\Z").next();
            BufferedReader br = new BufferedReader(new FileReader(new File(tokenFileName)));
            String line;
            while ((line = br.readLine()) != null) {
                this.tokens.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void scanProgram() throws ScannerException {
        while (currentIndex < programContent.length()) {
            tokenScan();
        }
    }

    public void tokenScan() throws ScannerException {
        takeoverSpace();
        if (currentIndex == programContent.length())
            return;
        if (verifyStringConstant() || verifyIntConstant() || verifyTokenFromList() || verifyIdentifier())
            return;
        throw new ScannerException("Lexical ERROR: Unexpected token at line  " + lineCounter);
    }

    private void takeoverSpace() {
        while (currentIndex < programContent.length() && (programContent.charAt(currentIndex) == ' ' || programContent.charAt(currentIndex) == '\n'
                || programContent.charAt(currentIndex) == '\r' || programContent.charAt(currentIndex) == 9)) {
            if (programContent.charAt(currentIndex) == '\n') {
                lineCounter++;
            }
            currentIndex++;
        }
    }

    private boolean verifyStringConstant() throws ScannerException {
        var valid = Pattern.compile("^\"[a-zA-Z0-9_ ]*\"");
        var matcher = valid.matcher(programContent.substring(currentIndex));
        if (!matcher.find()) {
            if (Pattern.matches("\"", programContent.substring(currentIndex))) {
                throw new ScannerException("Lexical ERROR: Unclosed quotes at line " + currentIndex);
            }
            return false;
        }
        var result = matcher.group(0);
        currentIndex += result.length() + 2;
        var pos = symbolTable.addConstant(result);
        pif.add(new Pair<>("String constant: "+result, pos));
        return true;
    }

    private boolean verifyIntConstant() {
        if (Character.isDigit(programContent.charAt(currentIndex)) || ((programContent.charAt(currentIndex) == '+'
                || programContent.charAt(currentIndex) == '-') && currentIndex + 1 < programContent.length() && Character.isDigit(programContent.charAt(currentIndex + 1)))) {
            int result = 0, sign = 1;
            if (programContent.charAt(currentIndex) == '-') {
                sign = -1;
                currentIndex++;
            }
            if (programContent.charAt(currentIndex) == '+') {
                sign = 1;
                currentIndex++;
            }
            while (currentIndex < programContent.length() && Character.isDigit(programContent.charAt(currentIndex))) {
                result = result * 10 + Integer.parseInt(String.valueOf(programContent.charAt(currentIndex)));
                currentIndex++;
            }
            result *= sign;
            var pos = symbolTable.addConstant(String.valueOf(result));
            pif.add(new Pair<>("Int constant: "+result, pos));
            return true;
        }
        return false;
    }

    private boolean verifyTokenFromList() {
        for (String token : tokens) {
            if (programContent.startsWith(token, currentIndex)) {
                pif.add(new Pair<>(token, new Pair<>(-1, -1)));
                currentIndex += token.length();
                return true;
            }
        }
        return false;
    }

    private boolean verifyIdentifier() throws ScannerException {
        var valid = Pattern.compile("^[a-zA-Z_][a-zA-Z0-9_]*");
        var matcher = valid.matcher(programContent.substring(currentIndex));
        if (!matcher.find()) {
            if (Pattern.matches("^[0-9]+[a-zA-Z0-9_]*", programContent.substring(currentIndex))) {
                throw new ScannerException("Lexical ERROR: Incorrect identifier declaration at line " + currentIndex);
            }
            return false;
        }
        var result = matcher.group(0);
        currentIndex += result.length();
        var pos = symbolTable.addIdentifier(result);
        pif.add(new Pair<>("Identifier: "+result, pos));
        return true;
    }



    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    public List<Pair<String, Pair<Integer, Integer>>> getPif() {
        return pif;
    }

    public String extractPifTable(){
        StringBuilder finalString = new StringBuilder();
        for (Pair<String, Pair<Integer, Integer>> id: pif){
            finalString.append(String.format("%s = %s\n", id.first, id.second));
        }
        return finalString.toString();
    }
}
