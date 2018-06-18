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
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author thiag
 */
public class Automaton {
    
    ArrayList<String> alphabet;
    ArrayList<State> programFunction;
    ArrayList<State> last;
    State initial;
    
    Automaton(){
        
        alphabet = new ArrayList<>();
        programFunction = new ArrayList<>();
        last = new ArrayList<>();
    
    }
    
    void addInitialState(State state){
        
        initial = state;
        addState(state);
    
    }
    
    void addLetterAlphabet(String letter){
    
        alphabet.add(letter);
    
    }
    
    void addState(State state){
        
        State exist = findStateInProgramFunction(state.name);  //Verify if the state is already in program function
        
        if(exist == null){  //If it is not
          
            programFunction.add(state);  //Add state to program function
        
        }
    }
    
    void addFinalState(State state){
        
        last.add(state);
        addState(state);
        
    }
    
    boolean findLetterInAlphabet(String key){
        
        for(String letter: alphabet){
            
            if(key.equals(letter))
                
                return true;
        }
        
        return false;
    }
    
    State findStateInProgramFunction(String name){
        
        for(State state: programFunction){
            
            if(state.name.equals(name))
                
                return state;
        }
        
        return null;
    }
    
    boolean isInProgramFunction(String name){
        
        State exist = findStateInProgramFunction(name);     //Look in program function
        
        if(exist != null)   //If didn't find
        
            return true;
        
        else                //If found it
        
            return false;
    
    }
    
    @Override
    public String toString(){
        
        String back = "";
        
        back += "Alphabet: ";
        
        for(String letter: alphabet)
            back += letter + " ";
        
        back += "\n";
        
        back += "Initial state: " + initial.name;
        
        back += "\n";
        
        back += "Final(s) states: ";
        
        for(State finals: last)
            back += finals.name + " ";
        
        back += "\n";
        
        for(State state: programFunction){
            
            for(Transition t: state.transitions){
                
                back += state.name + " " + t.letter + " " + t.next.name + "\n";
            
            }
        }
        
        return back;
    }
    
    public static void main(String[] args) {
        //Read file and build object AFN

        Scanner input = new Scanner(System.in);
        
        NFA nfa = new NFA();
        
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

            String[] alphabet = row.split(" ");
            
            for(String letter : alphabet){
                nfa.addLetterAlphabet(letter);
            }
            
            row = readFile.readLine();
            nfa.addInitialState(new State(row));
            
            row = readFile.readLine();
            String[] finalStates = row.split(" ");
            
            for(String state : finalStates){
                nfa.addFinalState(new State(state));
            }
            
            row = readFile.readLine();
            State state1;
            State state2;
            //With this loop we read a line 'til it's "\n"
            while (row != null){
                
                String[] part = row.split(" ");
                
                state1 = nfa.findStateInProgramFunction(part[0]);
                state2 = nfa.findStateInProgramFunction(part[2]);
        
                if (state1 == null){ 
                    nfa.addState(new State(part[0]));
                    state1 = nfa.findStateInProgramFunction(part[0]);
                }

                if (state2 == null){
                    if(part[0] == part[2]){
                        state2 = state1;
                    }else{
                        nfa.addState(new State(part[2]));
                        state2 = nfa.findStateInProgramFunction(part[2]);
                    }

                }
        
                state1.addTransition(new Transition(part[1], state2));
        
                
                //To read all file
                row = readFile.readLine();
                
            }
            
            file.close();
            
        } catch (IOException e) {
            //Exception if the file could not be opened
            
            System.err.printf("An error occurred opening the file. %s.\n", e.getMessage());
            
        }
        
        System.out.println("");

        
        DFA dfa;
        State state;
        
        /*
        NFA nfa = new NFA();
        DFA dfa;
        State state;
        
        //Setting alphabet
        nfa.addLetterAlphabet("a");
        nfa.addLetterAlphabet("b");
        
        //Setting states of AFN
        nfa.addInitialState(new State("q0"));
        nfa.addState(new State("q1"));
        nfa.addFinalState(new State("q2"));
        
        //Adding transitions to states
        //To state q0
        state = nfa.findStateInProgramFunction("q0");
        state.addTransition(new Transition("a", nfa.findStateInProgramFunction("q0")));
        state.addTransition(new Transition("a", nfa.findStateInProgramFunction("q1")));
        state.addTransition(new Transition("b", nfa.findStateInProgramFunction("q0")));
        
        //To state q1
        state = nfa.findStateInProgramFunction("q1");
        state.addTransition(new Transition("b", nfa.findStateInProgramFunction("q2")));
        */

        //Call function nfa.convertToDFA()
        System.out.println(nfa);
        dfa = nfa.convertToDFA();
        System.out.println("");
        System.out.println(dfa);
        
        //dfa.outputFile()
        
//        FileWriter arq = new FileWriter("/home/vitorfranca/git/nfa-to-dfa/src/DFA.txt");
//        PrintWriter gravarArq = new PrintWriter(arq);
//        
//        gravarArq.printf("+--Result DFA--+%n");
        
    }

}
