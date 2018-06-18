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
public class NFA extends Automaton{
    
    NFA(){
        super();
    }
    
    DFA convertToDFA(){
        
        DFA dfa = new DFA();
        
        dfa.alphabet = alphabet;
        newStates(dfa, initial);
        
        return dfa;
    }
    
    private void newStates(DFA dfa, State state){
        
        if(state.equals(initial)){  //If the state is the initial state of nfa
            
            State newInitial = new State(initial.name);
            
            dfa.addInitialState(newInitial);
            
            newStates(dfa, newInitial);
        
        } else{
            
            for(String letter: alphabet){

                String name = stateComponents(state, letter);   //Find the next dfa state

                State newState = dfa.findStateInProgramFunction(name);

                if(newState == null){                           //If the state isn't in program function

                    newState = new State(name);

                    for(State finalState: last){                //Look if the new state has in his name the name of some final state of nfa

                        if(newState.name.contains(finalState.name)){

                            dfa.addFinalState(newState);

                        }

                    }

                    dfa.addState(newState);         //Add the new state to dfa

                }

                state.addTransition(new Transition(letter, newState));

                if(newState.transitions.isEmpty()){
                 
                    newStates(dfa, newState);

                }

            }
            
        }
        
    }
    
    
    private String stateComponents(State newState, String letter){  //Find the next state
        
        String name = "";
        State back;
        
        for (State state: programFunction) {
            
            boolean contains = newState.name.contains(state.name);
            
            if(contains){
                
                name += nextDFAState(state, letter);
                
            }
        
        }
        
        return name;
    
    }
    
    private String nextDFAState(State state, String letter){
        
        String name = "";
        
        for(Transition t: state.transitions){
        
            if(t.letter.equals(letter))
            
                name += t.next.name;
        
        }
        
        return name;
    }
    
    private void nextDFAStateToInitial(State newInitial, String letter){
        
        String name = "";
        
        for(Transition t: initial.transitions){
        
            if(t.letter.equals(letter))
            
                name += t.next.name;
        
        }
        
        if(!name.equals("")){
        
            State newState = new State(name);
            
            newInitial.addTransition(new Transition(letter, newState));
        
        }
    
    }
    
}
