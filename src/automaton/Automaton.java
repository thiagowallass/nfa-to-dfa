/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automaton;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

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
        
        Scanner input = new Scanner(System.in);
        
        //Read file's name
        System.out.println("Insert the file's name: ");
        String nameFile = input.nextLine();
        
        //Printing the file's content
        System.out.println("\nFile's content: \n");
        try{
            
            FileReader file = new FileReader(nameFile);
            BufferedReader readFile = new BufferedReader(file);
            
            //Read a row
            String row = readFile.readLine();

            //With this loop we read a line 'til it's "\n"
            while (row != null){
                
                System.out.printf("%s\n", row);
                
                //To read all file
                row = readFile.readLine();
                
            }
            
            file.close();
            
        } catch (IOException e) {
            //Exception if the file could not be opened
            
            System.err.printf("An error occurred opening the file. %s.\n", e.getMessage());
            
        }
        
        System.out.println("");
        
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
