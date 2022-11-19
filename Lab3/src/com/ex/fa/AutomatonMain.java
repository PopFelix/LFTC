package com.ex.fa;

import java.util.List;
import java.util.Scanner;

public class AutomatonMain {

    public static void main(String[] args) throws FAException {
        Automaton automaton = new Automaton("res/fa.in");
        System.out.println("1. Print states");
        System.out.println("2. Print input state");
        System.out.println("3. Print output states");
        System.out.println("4. Print alphabet");
        System.out.println("5. Print transitions");
        System.out.println("6. Check sequence");
        System.out.println("0. Quit");
        String choice;
        Scanner scan = new Scanner(System.in);
        while (!(choice = scan.nextLine()).equals("0")){
            switch (choice){
                case "1":
                    System.out.println(automaton.printStates());
                    break;
                case "2":
                    System.out.println(automaton.printInState());
                    break;
                case "3":
                    System.out.println(automaton.printOutStates());
                    break;
                case "4":
                    System.out.println(automaton.printAlphabet());
                    break;
                case "5":
                    System.out.println(automaton.printTransitions());
                    break;
                case "6":
                    Scanner seqScanner = new Scanner(System.in);
                    System.out.println("Input the sequence:");
                    String sequence = scan.nextLine();
                    List<String> listSequence = List.of(sequence.split(""));
                    if (automaton.checkSequence(listSequence)){
                        System.out.println("Sequence accepted");
                    }
                    else{
                        System.out.println("Sequence invalid with the definition");
                    }
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }

    }
}
