/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automaton;

import java.util.ArrayList;

/**
 *
 * @author thiag
 */
public class Automaton {

    ArrayList<String> alphabet;
    ArrayList programFunction;
    
    Automaton(){
        alphabet = new ArrayList<>();
        programFunction = new ArrayList<>();
    }
    
    void addLetterAlphabet(String letter){
        alphabet.add(letter);
    }
    
    void addState(State state){
        programFunction.add(state);
    }
    
    void addLastState(State state){
        
    }
    
    boolean findLetterInAlphabet(String key){
        for(String letter: alphabet){
            if(key.equals(letter))
                return true;
        }
        return false;
    }
    
    State findStateInProgramFunction(String name){
        for(Object object: programFunction){
            State state = (State) object;
            if(state.name.equals(name))
                return state;
        }
        
            
        return null;
    }
    
    public static void main(String[] args) {
        //Read file and build object AFN
        NFA nfa = new NFA();
        DFA dfa;
        StateNFA state;
        
        //Setting alphabet
        nfa.addLetterAlphabet("a");
        nfa.addLetterAlphabet("b");
        //Setting states of AFN
        nfa.addInitialState(new StateNFA("q0"));
        nfa.addState(new StateNFA("q1"));
        nfa.addLastState(new StateNFA("q2"));
        //Adding transitions to states
        //To state q0
        state = (StateNFA)nfa.findStateInProgramFunction("q0");
        state.addTransition(new Transition("a", nfa.findStateInProgramFunction("q0")));
        state.addTransition(new Transition("a", nfa.findStateInProgramFunction("q1")));
        state.addTransition(new Transition("b", nfa.findStateInProgramFunction("q0")));
        //To state q1
        state = (StateNFA)nfa.findStateInProgramFunction("q1");
        state.addTransition(new Transition("b", nfa.findStateInProgramFunction("q2")));
        //Call function nfa.convertToDFA()
        System.out.println(nfa);
        dfa = nfa.convertToAFD();
        System.out.println("");
        System.out.println(dfa);
        //dfa.outputFile()
    }

}
