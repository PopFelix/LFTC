package com.ex.fa;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Automaton {
    private String initialState;
    private List<String> finalStates;
    private List<String> states;
    private List<String> alphabet;
    private String filename;
    private List<Transition> transitions;

    public Automaton(String filename) throws FAException {
        this.filename = filename;
        var pattern = Pattern.compile("^[a-z_]*=");
        this.transitions = new ArrayList<>();
        this.alphabet = new ArrayList<>();
        this.states = new ArrayList<>();
        this.finalStates = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            while (line != null) {
                var matcher = pattern.matcher(line);
                if (!matcher.find()) {
                    throw new FAException("Invalid pattern for file " + filename);
                }
                var exp = matcher.group(0);
                var rightGroup = line.substring(line.lastIndexOf('=') + 1);
                switch (exp) {
                    case "states=":
                        rightGroup = rightGroup.replace("{", "");
                        rightGroup = rightGroup.replace("}", "");
                        rightGroup = rightGroup.trim();
                        this.states = List.of(rightGroup.split(","));
                        break;
                    case "in_state=":
                        rightGroup = rightGroup.trim();
                        this.initialState=rightGroup;
                        break;
                    case "out_states=":
                        rightGroup = rightGroup.replace("{", "");
                        rightGroup = rightGroup.replace("}", "");
                        rightGroup = rightGroup.trim();
                        this.finalStates = List.of(rightGroup.split(","));
                        break;
                    case "alphabet=":
                        rightGroup = rightGroup.replace("{", "");
                        rightGroup = rightGroup.replace("}", "");
                        rightGroup = rightGroup.trim();
                        this.alphabet = List.of(rightGroup.split(","));
                        break;
                    case "transitions=":
                        rightGroup = rightGroup.replace("{", "");
                        rightGroup = rightGroup.replace("}", "");
                        rightGroup = rightGroup.trim();
                        var transitionList = List.of(rightGroup.split(","));

                        for (String transitionString: transitionList){
                            transitionString = transitionString.replace("[","");
                            transitionString = transitionString.replace("]","");
                            var readTransitions = transitionString.split(":");
                            this.transitions.add(new Transition(readTransitions[0],readTransitions[1],readTransitions[2]));
                        }
                        break;
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String printStates(){
        StringBuilder result = new StringBuilder("states={");
        for (String state: states){
            result.append(state).append(",");
        }
        return result+"}";
    }

    public String printInState(){
        return "in_state="+initialState;
    }

    public String printOutStates(){
        StringBuilder result = new StringBuilder("out_states={");
        for (String outState: finalStates){
            result.append(outState).append(",");
        }
        return result+"}";
    }

    public String printAlphabet(){
        StringBuilder result = new StringBuilder("alphabet={");
        for (String alpha: alphabet){
            result.append(alpha).append(",");
        }
        return result+"}";
    }

    public String printTransitions(){
        StringBuilder result = new StringBuilder("transitions={");
        for (Transition transition: transitions){
            result.append(transition).append(",");
        }
        return result+"}";
    }

    public boolean checkSequence(List<String> sequence){
        var state = initialState;
        for (String seq: sequence){
            String nextState = null;
            for (Transition transition: transitions){
                if (transition.getFrom().equals(state) && transition.getValue().equals(seq)){
                    nextState = transition.getTo();
                }
            }
            if (nextState == null){
                return false;
            }
            state = nextState;
        }
        return states.contains(state);
    }
}
